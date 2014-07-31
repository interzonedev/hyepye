package com.interzonedev.hyepye.web.vocabulary;

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
import com.interzonedev.hyepye.service.command.vocabulary.GetVocabularyByIdCommand;
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

}
