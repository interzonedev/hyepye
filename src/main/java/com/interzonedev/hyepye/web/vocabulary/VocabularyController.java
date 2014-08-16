package com.interzonedev.hyepye.web.vocabulary;

import java.util.HashMap;
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
import com.interzonedev.respondr.response.ResponseTransformingException;
import com.interzonedev.respondr.serialize.Serializer;

@Controller("hyepye.web.vocabularyController")
public class VocabularyController extends HyePyeController {

    private static final Logger log = LoggerFactory.getLogger(VocabularyController.class);

    @Inject
    public VocabularyController(@Named("hyepye.web.jsonSerializer") Serializer serializer,
            @Named("hyepye.service.validationHelper") ValidationHelper validationHelper,
            @Named("hyepye.web.messageSource") MessageSource messageSource) {
        super(serializer, validationHelper, messageSource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/{id}")
    public ResponseEntity<String> getVocabularyById(@PathVariable("id") Long id) throws ResponseTransformingException {

        log.debug("getVocabularyById: Start");

        // Create the specific command to get the vocabulary.
        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", id);

        // Create a HyePyeCommandExecutor instance to handle the successful response from the GetVocabularyByIdCommand.
        GetVocabularyCommandExecutor getVocabularyCommandExecutor = new GetVocabularyCommandExecutor(
                getVocabularyByIdCommand, serializer, validationHelper, messageSource, getLocale());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor.execute();

        log.debug("getVocabularyById: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/search")
    public ResponseEntity<String> searchVocabulary(@Valid VocabularySearchForm vocabularySearchForm,
            BindingResult bindingResult) throws ResponseTransformingException {

        log.debug("searchVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("searchVocabulary: Form has errors");
            HttpResponse validationErrorResponse = new HttpResponse(bindingResult, messageSource, Locale.getDefault(),
                    null, HttpStatus.BAD_REQUEST, HttpResponse.JSON_CONTENT_TYPE);
            return validationErrorResponse.toResponseEntity(serializer);
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

        // Create a HyePyeCommandExecutor instance to handle the successful response from the SearchVocabularyCommand.
        GetVocabulariesCommandExecutor getVocabulariesCommandExecutor = new GetVocabulariesCommandExecutor(
                searchVocabularyCommand, serializer, validationHelper, messageSource, getLocale());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabulariesCommandExecutor.execute();

        log.debug("searchVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/vocabulary")
    public ResponseEntity<String> createVocabulary(@Valid VocabularyForm vocabularyForm, BindingResult bindingResult)
            throws ResponseTransformingException {

        log.debug("createVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("createVocabulary: Form has errors");
            HttpResponse validationErrorResponse = new HttpResponse(bindingResult, messageSource, Locale.getDefault(),
                    null, HttpStatus.BAD_REQUEST, HttpResponse.JSON_CONTENT_TYPE);
            return validationErrorResponse.toResponseEntity(serializer);
        }

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setArmenian(vocabularyForm.getArmenian());
        vocabularyIn.setEnglish(vocabularyForm.getEnglish());
        vocabularyIn.setVocabularyType(VocabularyType.fromVocabularyTypeName(vocabularyForm.getVocabularyTypeValue()));
        vocabularyIn.setStatus(Status.fromStatusName(vocabularyForm.getStatusValue()));

        // Create the specific command to create the vocabulary.
        CreateVocabularyCommand createVocabularyCommand = (CreateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.createVocabularyCommand", vocabularyIn.build(), getAuthenticatedUser());

        // Create a HyePyeCommandExecutor instance to handle the successful response from the CreateVocabularyCommand.
        GetVocabularyCommandExecutor getVocabularyCommandExecutor = new GetVocabularyCommandExecutor(
                createVocabularyCommand, serializer, validationHelper, messageSource, getLocale());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor.execute();

        log.debug("createVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/vocabulary/{id}")
    public ResponseEntity<String> updateVocabulary(@PathVariable("id") Long id, @Valid VocabularyForm vocabularyForm,
            BindingResult bindingResult) throws ResponseTransformingException {

        log.debug("updateVocabulary: Start");

        if (bindingResult.hasErrors()) {
            log.debug("updateVocabulary: Form has errors");
            HttpResponse validationErrorResponse = new HttpResponse(bindingResult, messageSource, Locale.getDefault(),
                    null, HttpStatus.BAD_REQUEST, HttpResponse.JSON_CONTENT_TYPE);
            return validationErrorResponse.toResponseEntity(serializer);
        }

        Vocabulary.Builder vocabularyIn = Vocabulary.newBuilder();
        vocabularyIn.setId(id);
        vocabularyIn.setArmenian(vocabularyForm.getArmenian());
        vocabularyIn.setEnglish(vocabularyForm.getEnglish());
        vocabularyIn.setVocabularyType(VocabularyType.fromVocabularyTypeName(vocabularyForm.getVocabularyTypeValue()));
        vocabularyIn.setStatus(Status.fromStatusName(vocabularyForm.getStatusValue()));

        // Create the specific command to create the vocabulary.
        UpdateVocabularyCommand updateVocabularyCommand = (UpdateVocabularyCommand) applicationContext.getBean(
                "hyepye.service.updateVocabularyCommand", vocabularyIn.build(), getAuthenticatedUser());

        // Create a HyePyeCommandExecutor instance to handle the successful response from the UpdateVocabularyCommand.
        GetVocabularyCommandExecutor getVocabularyCommandExecutor = new GetVocabularyCommandExecutor(
                updateVocabularyCommand, serializer, validationHelper, messageSource, getLocale());

        // Execute the command and get the response.
        ResponseEntity<String> responseEntity = getVocabularyCommandExecutor.execute();

        log.debug("updateVocabulary: End");

        return responseEntity;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/types")
    public ResponseEntity<String> getVocabularyTypes() throws ResponseTransformingException {

        Map<String, Object> responseStructure = new HashMap<String, Object>();

        for (VocabularyType vocabularyType : VocabularyType.values()) {
            responseStructure.put(vocabularyType.getVocabularyTypeName(), vocabularyType.getVocabularyTypeName());
        }

        HttpResponse httpResponse = new HttpResponse(responseStructure, null, HttpStatus.OK,
                HttpResponse.JSON_CONTENT_TYPE);

        return httpResponse.toResponseEntity(serializer);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/vocabulary/properties")
    public ResponseEntity<String> getVocabularyProperties() throws ResponseTransformingException {

        Map<String, Object> responseStructure = new HashMap<String, Object>();

        for (VocabularyProperty vocabularyProperty : VocabularyProperty.values()) {
            responseStructure.put(vocabularyProperty.getVocabularyColumnName(),
                    vocabularyProperty.getVocabularyColumnName());
        }

        HttpResponse httpResponse = new HttpResponse(responseStructure, null, HttpStatus.OK,
                HttpResponse.JSON_CONTENT_TYPE);

        return httpResponse.toResponseEntity(serializer);

    }

}
