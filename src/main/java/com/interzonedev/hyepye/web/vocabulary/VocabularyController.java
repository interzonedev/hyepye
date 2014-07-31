package com.interzonedev.hyepye.web.vocabulary;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.commandr.IZCommandResponse;
import com.interzonedev.commandr.http.CommandExecutor;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.command.vocabulary.GetVocabularyByIdCommand;
import com.interzonedev.hyepye.web.HyePyeCommandExecutor;
import com.interzonedev.hyepye.web.HyePyeController;
import com.interzonedev.respondr.response.ResponseTransformingException;
import com.interzonedev.respondr.serialize.Serializer;

@Controller("hyepye.web.vocabularyController")
@RequestMapping(value = "/vocabulary")
public class VocabularyController extends HyePyeController {

    private static final Logger log = LoggerFactory.getLogger(VocabularyController.class);

    @Inject
    public VocabularyController(@Named("hyepye.web.jsonSerializer") Serializer serializer,
            @Named("hyepye.service.validationHelper") ValidationHelper validationHelper,
            @Named("hyepye.web.messageSource") MessageSource messageSource) {
        super(serializer, validationHelper, messageSource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<String> getVocabularyById(@PathVariable("id") Long id) throws ResponseTransformingException {

        log.debug("getVocabularyById: Start");

        // Create the specific command to get the widget.
        GetVocabularyByIdCommand getVocabularyByIdCommand = (GetVocabularyByIdCommand) applicationContext.getBean(
                "hyepye.service.getVocabularyByIdCommand", id);

        // Create a CommandExecutor instance to handle the successful response from the GetWidgetByUuidCommand.
        CommandExecutor getVocabularyByIdCommandExecutor = new HyePyeCommandExecutor(getVocabularyByIdCommand,
                serializer, validationHelper, messageSource, getLocale()) {

            @Override
            protected Map<String, Object> onSuccessMap(IZCommandResponse izCommandResponse) {

                log.debug("onSuccessMap: Start");

                HyePyeResponse hyePyeResponse = (HyePyeResponse) izCommandResponse;

                Map<String, Object> responseStructure = new HashMap<String, Object>();

                Vocabulary vocabulary = hyePyeResponse.getVocabulary();
                if (null != vocabulary) {
                    // Create a map containing a minimal set of the vocabulary properties.
                    Map<String, Object> vocabularyProperties = new HashMap<String, Object>();
                    vocabularyProperties.put("id", vocabulary.getId());
                    vocabularyProperties.put("armenian", vocabulary.getArmenian());
                    vocabularyProperties.put("english", vocabulary.getEnglish());
                    vocabularyProperties.put("status", vocabulary.getStatus().getStatusName());
                    vocabularyProperties.put("vocabularyType", vocabulary.getVocabularyType().getVocabularyTypeName());

                    responseStructure.put("vocabulary", vocabularyProperties);
                }

                log.debug("onSuccessMap: End");

                return responseStructure;

            }
        };

        ResponseEntity<String> responseEntity = getVocabularyByIdCommandExecutor.execute();

        log.debug("getVocabularyById: End");

        return responseEntity;

    }

}
