package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Gets all {@link Vocabulary} instances according to the specified {@link VocabularyType} and {@link Status}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getAllVocabulariesCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetAllVocabulariesCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetAllVocabulariesCommand.class);

    private final VocabularyType vocabularyType;

    private final Status status;

    private final VocabularyProperty orderBy;

    private final boolean ascending;

    private final Long limit;

    private final Long offset;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param vocabularyType The {@link VocabularyType} of {@link Vocabulary} against which to search. If null, searches
     *            against all {@link VocabularyType}s.
     * @param status The {@link Status} of {@link Vocabulary} against which to search. If null, searches against all
     *            {@link Status}es.
     * @param orderBy The {@link VocabularyProperty} by which to order the results.
     * @param ascending Whether or not the results are ordered in ascending order.
     * @param limit The maximum number of results to return.
     * @param offset The number of results to skip before returning results.
     */
    public GetAllVocabulariesCommand(VocabularyType vocabularyType, Status status, VocabularyProperty orderBy,
            boolean ascending, Long limit, Long offset) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getAllVocabulariesCommand")
                .setThreadTimeoutMillis(500).build());
        this.vocabularyType = vocabularyType;
        this.status = status;
        this.orderBy = orderBy;
        this.ascending = ascending;
        this.limit = limit;
        this.offset = offset;
    }

    /**
     * Creates an instance of this command with a specific command key and timeout. Gets all possible results without
     * limit.
     * 
     * @param vocabularyType The {@link VocabularyType} of {@link Vocabulary} against which to search. If null, searches
     *            against all {@link VocabularyType}s.
     * @param status The {@link Status} of {@link Vocabulary} against which to search. If null, searches against all
     *            {@link Status}es.
     * @param orderBy The {@link VocabularyProperty} by which to order the results.
     */
    public GetAllVocabulariesCommand(VocabularyType vocabularyType, Status status, VocabularyProperty orderBy) {
        this(vocabularyType, status, orderBy, true, Long.MAX_VALUE, 0L);
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the collection of {@link Vocabulary}s set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        List<Vocabulary> vocabularies = vocabularyRepository.getAllVocabularies(vocabularyType, status, orderBy,
                ascending, limit, offset);

        log.debug("doCommand: Retrieved - " + vocabularies);

        hyePyeResponse.setVocabularies(vocabularies);

        return hyePyeResponse.build();

    }

}
