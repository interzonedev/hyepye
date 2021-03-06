package com.interzonedev.hyepye.web.vocabulary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.vocabulary.CreateVocabularyCommand;
import com.interzonedev.hyepye.service.command.vocabulary.GetVocabularyByIdCommand;
import com.interzonedev.hyepye.service.command.vocabulary.SearchVocabularyCommand;
import com.interzonedev.hyepye.service.command.vocabulary.UpdateVocabularyCommand;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.hyepye.web.HyePyeController;
import com.interzonedev.respondr.response.HttpResponse;
import com.interzonedev.respondr.serialize.Serializer;

@Controller("hyepye.web.vocabularyController")
public class VocabularyController extends HyePyeController {

    private static final Logger log = LoggerFactory.getLogger(VocabularyController.class);

    public static final String VOCABULARY_TYPES_MAP_KEY = "vocabularyTypes";

    public static final String VOCABULARY_PROPERTIES_MAP_KEY = "vocabularyProperties";

    private final GetVocabularyCommandExecutor getVocabularyCommandExecutor;

    private final GetVocabulariesCommandExecutor getVocabulariesCommandExecutor;

    @Inject
    public VocabularyController(@Named("hyepye.web.jsonSerializer") Serializer serializer,
            @Named("hyepye.service.validationHelper") ValidationHelper validationHelper,
            @Named("hyepye.web.messageSource") MessageSource messageSource,
            @Named("hyepye.web.getVocabularyCommandExecutor") GetVocabularyCommandExecutor getVocabularyCommandExecutor,
            @Named("hyepye.web.getVocabulariesCommandExecutor") GetVocabulariesCommandExecutor getVocabulariesCommandExecutor) {
        super(serializer, validationHelper, messageSource);
        this.getVocabularyCommandExecutor = getVocabularyCommandExecutor;
        this.getVocabulariesCommandExecutor = getVocabulariesCommandExecutor;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/vocabulary/{id}")
    public ResponseEntity<String> getVocabularyById(@PathVariable("id") Long id) {

        log.debug("getVocabularyById: Start");

        // Create the specific command to get the vocabulary.
        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", id);

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor
                .execute(getVocabularyByIdCommand, getLocale())
                .getResponseEntity();

        log.debug("getVocabularyById: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/search")
    public ResponseEntity<String> searchVocabulary(@Valid VocabularySearchForm vocabularySearchForm,
            BindingResult bindingResult) {

        log.debug("searchVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("searchVocabulary: Form has errors");
            return HttpResponse.getValidationErrorJsonResponse(bindingResult, messageSource, Locale.getDefault(),
                    serializer);
        }

        String english = vocabularySearchForm.getEnglish();

        DefinitionSearchType englishSearchType = DefinitionSearchType.fromDefinitionSearchTypeName(vocabularySearchForm
                .getEnglishSearchTypeValue());

        String armenian = vocabularySearchForm.getArmenian();

        DefinitionSearchType armenianSearchType = DefinitionSearchType
                .fromDefinitionSearchTypeName(vocabularySearchForm.getArmenianSearchTypeValue());

        VocabularyType vocabularyType = VocabularyType.fromVocabularyTypeName(vocabularySearchForm
                .getVocabularyTypeValue());

        Status status = Status.fromStatusName(vocabularySearchForm.getStatusValue());

        VocabularyProperty orderBy = VocabularyProperty.fromVocabularyColumnName(vocabularySearchForm.getOrderBy());

        boolean ascending = vocabularySearchForm.isAscending();

        Integer resultsPerPage = vocabularySearchForm.getResultsPerPage();

        Integer requestedPageNumber = vocabularySearchForm.getRequestedPageNumber();

        // Create the specific command to create the vocabulary.
        SearchVocabularyCommand searchVocabularyCommand = (SearchVocabularyCommand) applicationContext.getBean(
                "hyepye.service.searchVocabularyCommand", english, englishSearchType, armenian, armenianSearchType,
                vocabularyType, status, orderBy, ascending, resultsPerPage, requestedPageNumber);

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabulariesCommandExecutor
                .execute(searchVocabularyCommand, getLocale())
                .getResponseEntity();

        log.debug("searchVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/vocabulary")
    public ResponseEntity<String> createVocabulary(@Valid VocabularyForm vocabularyForm, BindingResult bindingResult) {

        log.debug("createVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("createVocabulary: Form has errors");
            return HttpResponse.getValidationErrorJsonResponse(bindingResult, messageSource, Locale.getDefault(),
                    serializer);
        }

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(vocabularyForm.getArmenian());
        vocabularyIn.setEnglish(vocabularyForm.getEnglish());
        vocabularyIn.setVocabularyType(VocabularyType.fromVocabularyTypeName(vocabularyForm.getVocabularyTypeValue()));
        vocabularyIn.setStatus(Status.fromStatusName(vocabularyForm.getStatusValue()));

        // Create the specific command to create the vocabulary.
        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), getAuthenticatedUser().getId());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor
                .execute(createVocabularyCommand, getLocale())
                .getResponseEntity();

        log.debug("createVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/vocabulary/{id}")
    public ResponseEntity<String> updateVocabulary(@PathVariable("id") Long id, @Valid VocabularyForm vocabularyForm,
            BindingResult bindingResult) {

        log.debug("updateVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("updateVocabulary: Form has errors");
            return HttpResponse.getValidationErrorJsonResponse(bindingResult, messageSource, Locale.getDefault(),
                    serializer);
        }

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setId(id);
        vocabularyIn.setArmenian(vocabularyForm.getArmenian());
        vocabularyIn.setEnglish(vocabularyForm.getEnglish());
        vocabularyIn.setVocabularyType(VocabularyType.fromVocabularyTypeName(vocabularyForm.getVocabularyTypeValue()));
        vocabularyIn.setStatus(Status.fromStatusName(vocabularyForm.getStatusValue()));

        // Create the specific command to create the vocabulary.
        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), getAuthenticatedUser().getId());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor
                .execute(updateVocabularyCommand, getLocale())
                .getResponseEntity();

        log.debug("updateVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/types")
    public ResponseEntity<String> getVocabularyTypes() {

        log.debug("getVocabularyTypes: Start");

        Map<String, Object> responseStructure = new HashMap<>();

        List<Map<String, String>> vocabularyTypes = new ArrayList<>();
        for (VocabularyType vocabularyType : VocabularyType.values()) {
            Map<String, String> vocabularyTypeMap = new HashMap<>();
            vocabularyTypeMap.put("value", vocabularyType.getVocabularyTypeName());
            vocabularyTypes.add(vocabularyTypeMap);
        }

        responseStructure.put(VOCABULARY_TYPES_MAP_KEY, vocabularyTypes);

        HttpResponse httpResponse = HttpResponse
                .newBuilder()
                .setBodyAsMap(responseStructure)
                .setHttpStatus(HttpStatus.OK)
                .setContentType(HttpResponse.JSON_CONTENT_TYPE)
                .build();

        log.debug("getVocabularyTypes: End");

        return httpResponse.toResponseEntity(serializer);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/properties")
    public ResponseEntity<String> getVocabularyProperties() {

        log.debug("getVocabularyProperties: Start");

        Map<String, Object> responseStructure = new HashMap<>();

        List<Map<String, String>> vocabularyProperties = new ArrayList<>();
        for (VocabularyProperty vocabularyProperty : VocabularyProperty.values()) {
            Map<String, String> vocabularyPropertyMap = new HashMap<>();
            vocabularyPropertyMap.put("value", vocabularyProperty.getVocabularyColumnName());
            vocabularyProperties.add(vocabularyPropertyMap);
        }

        responseStructure.put(VOCABULARY_PROPERTIES_MAP_KEY, vocabularyProperties);

        HttpResponse httpResponse = HttpResponse
                .newBuilder()
                .setBodyAsMap(responseStructure)
                .setHttpStatus(HttpStatus.OK)
                .setContentType(HttpResponse.JSON_CONTENT_TYPE)
                .build();

        log.debug("getVocabularyProperties: End");

        return httpResponse.toResponseEntity(serializer);

    }

}
