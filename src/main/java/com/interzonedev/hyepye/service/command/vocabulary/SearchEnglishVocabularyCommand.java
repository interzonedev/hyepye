package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Searches all {@link Vocabulary} instances against English definitions.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.searchEnglishVocabularyCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchEnglishVocabularyCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(SearchEnglishVocabularyCommand.class);

    private final String english;

    private final DefinitionSearchType definitionSearchType;

    private final VocabularyType vocabularyType;

    private final Status status;

    private final boolean ascending;

    private final Long limit;

    private final Long offset;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param english The English definition (either whole or a fragment) against which to search.
     * @param definitionSearchType The type of search to perform.
     * @param vocabularyType The {@link VocabularyType} of {@link Vocabulary} against which to search. If null, searches
     *            against all {@link VocabularyType}s.
     * @param status The {@link Status} of {@link Vocabulary} against which to search. If null, searches against all
     *            {@link Status}es.
     * @param ascending Whether or not the results are ordered in ascending order.
     * @param limit The maximum number of results to return.
     * @param offset The number of results to skip before returning results.
     */
    public SearchEnglishVocabularyCommand(String english, DefinitionSearchType definitionSearchType,
            VocabularyType vocabularyType, Status status, boolean ascending, Long limit, Long offset) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.searchEnglishVocabularyCommand")
                .setThreadTimeoutMillis(500).build());
        this.english = english;
        this.definitionSearchType = definitionSearchType;
        this.vocabularyType = vocabularyType;
        this.status = status;
        this.ascending = ascending;
        this.limit = limit;
        this.offset = offset;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the collection of {@link Vocabulary} instances set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        List<Vocabulary> vocabularies = vocabularyRepository.searchEnglishVocabulary(english, definitionSearchType,
                vocabularyType, status, ascending, limit, offset);

        log.debug("doCommand: Retrieved - " + vocabularies);

        hyePyeResponse.setVocabularies(vocabularies);

        return hyePyeResponse.build();

    }

}
