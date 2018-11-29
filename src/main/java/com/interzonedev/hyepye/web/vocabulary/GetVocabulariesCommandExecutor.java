package com.interzonedev.hyepye.web.vocabulary;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.commandr.IZCommandResponse;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.respondr.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extension of {@link GetVocabularyCommandExecutor} for getting a {@link List} of {@link Vocabulary} instances from a
 * {@link HyePyeResponse}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.web.getVocabulariesCommandExecutor")
public class GetVocabulariesCommandExecutor extends GetVocabularyCommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(GetVocabulariesCommandExecutor.class);

    public static final String VOCABULARIES_MAP_KEY = "vocabularies";

    @Inject
    public GetVocabulariesCommandExecutor(@Named("hyepye.web.jsonSerializer") Serializer serializer,
                                          @Named("hyepye.service.validationHelper") ValidationHelper validationHelper,
                                          @Named("hyepye.web.messageSource") MessageSource messageSource) {
        super(serializer, validationHelper, messageSource);
    }

    /**
     * Gets the {@link List} of {@link Vocabulary} instances set on the specified {@link IZCommandResponse} (cast to a
     * {@link HyePyeResponse}) and sets the properties of each {@link Vocabulary} in a {@link Map} to be sent as the
     * response body.
     * 
     * @return Returns a {@link Map} with the single {@link #VOCABULARIES_MAP_KEY} key set to a {@link List} of
     *         {@link Map}s that contains the properties of each {@link Vocabulary} instance set on the specified
     *         {@link IZCommandResponse}.
     */
    @Override
    protected Map<String, Object> onSuccessMap(IZCommandResponse izCommandResponse) {

        log.debug("onSuccessMap: Start");

        HyePyeResponse hyePyeResponse = (HyePyeResponse) izCommandResponse;

        Map<String, Object> responseStructure = new HashMap<>();

        List<Map<String, Object>> vocabularies = new ArrayList<>();

        hyePyeResponse.getVocabularies().forEach(
            (Vocabulary vocabulary) -> vocabularies.add(Collections.unmodifiableMap(getVocabularyProperties(vocabulary)))
        );

        responseStructure.put(VOCABULARIES_MAP_KEY, Collections.unmodifiableList(vocabularies));

        log.debug("onSuccessMap: End");

        return Collections.unmodifiableMap(responseStructure);

    }

}
