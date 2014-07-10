package com.interzonedev.hyepye.service.command.vocabulary;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Creates a new {@link Vocabulary}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.createVocabularyCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateVocabularyCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(CreateVocabularyCommand.class);

    private final Vocabulary vocabularyToCreate;

    private final Long userId;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param vocabularyToCreate The {@link Vocabulary} that contains the properties of the new {@link Vocabulary} to
     *            create.
     * @param userId The ID of the {@link User} creating the {@link Vocabulary}.
     */
    public CreateVocabularyCommand(Vocabulary vocabularyToCreate, Long userId) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.createVocabularyCommand")
                .setThreadTimeoutMillis(500).build());
        this.vocabularyToCreate = vocabularyToCreate;
        this.userId = userId;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the newly created {@link Vocabulary} set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - vocabularyToCreate = " + vocabularyToCreate + " - userId = " + userId);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        Vocabulary vocabulary = vocabularyRepository.createVocabulary(vocabularyToCreate, userId);

        log.debug("doCommand: Created - vocabulary = " + vocabulary);

        hyePyeResponse.setVocabulary(vocabulary);

        return hyePyeResponse.build();

    }

}
