package com.interzonedev.hyepye.web.vocabulary;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.commandr.IZCommandResponse;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.HyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.web.HyePyeCommandExecutor;
import com.interzonedev.respondr.serialize.Serializer;

/**
 * {@link HyePyeCommandExecutor} implementation for getting a single {@link Vocabulary} instance from a
 * {@link HyePyeResponse}.
 * 
 * @author mmarkarian
 *
 */
public class GetVocabularyCommandExecutor extends HyePyeCommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(GetVocabularyCommandExecutor.class);

    public static final String VOCABULARY_MAP_KEY = "vocabulary";

    public GetVocabularyCommandExecutor(HyePyeCommand command, Serializer serializer,
            ValidationHelper validationHelper, MessageSource messageSource, Locale locale) {
        super(command, serializer, validationHelper, messageSource, locale);
    }

    /**
     * Gets the single {@link Vocabulary} instance set on the specified {@link IZCommandResponse} (cast to a
     * {@link HyePyeResponse}) and if it exists, sets it properties in a {@link Map} to be sent as the response body.
     * 
     * @return Returns a {@link Map} with the single {@link #VOCABULARY_MAP_KEY} key set to another {@link Map} that
     *         contains the properties of the {@link Vocabulary} instance set on the specified {@link IZCommandResponse}
     *         . If the {@link Vocabulary} does not exists, the internal {@link Map} is empty.
     */
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

            responseStructure.put(VOCABULARY_MAP_KEY, vocabularyProperties);
        }

        log.debug("onSuccessMap: End");

        return Collections.unmodifiableMap(responseStructure);

    }

}
