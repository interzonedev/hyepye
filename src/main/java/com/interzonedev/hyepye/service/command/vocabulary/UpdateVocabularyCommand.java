package com.interzonedev.hyepye.service.command.vocabulary;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Updates a {@link Vocabulary}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.updateVocabularyCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UpdateVocabularyCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(UpdateVocabularyCommand.class);

    private final Vocabulary vocabularyTemplateToUpdate;

    private final Long userId;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Updates an instance of this command with a specific command key and timeout.
     * 
     * @param vocabularyTemplateToUpdate The {@link Vocabulary} that contains the properties to set on the
     *            {@link Vocabulary} to update.
     * @param userId The ID of the {@link User} updating the {@link Vocabulary}.
     */
    public UpdateVocabularyCommand(Vocabulary vocabularyTemplateToUpdate, Long userId) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.updateVocabularyCommand")
                .setThreadTimeoutMillis(500).build());
        this.vocabularyTemplateToUpdate = vocabularyTemplateToUpdate;
        this.userId = userId;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the newly updated {@link Vocabulary} set.
     * 
     * @throws ValidationException Thrown if this instance was updated with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - vocabularyToUpdate = " + vocabularyTemplateToUpdate + " - userId = " + userId);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        if (null == vocabularyTemplateToUpdate) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary must be set");
        }

        // Get the current Vocabulary from the database.
        Vocabulary currentVocabulary = vocabularyRepository.getVocabularyById(vocabularyTemplateToUpdate.getId());
        if (null == currentVocabulary) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary to update doesn't exist");
        }

        Vocabulary.Builder vocabularyToUpdate = Vocabulary.newBuilder(currentVocabulary);

        // Copy the properties to be updated onto the Vocabulary instance that will be persisted to the database.
        vocabularyToUpdate.setArmenian(vocabularyTemplateToUpdate.getArmenian());
        vocabularyToUpdate.setEnglish(vocabularyTemplateToUpdate.getEnglish());
        vocabularyToUpdate.setVocabularyType(vocabularyTemplateToUpdate.getVocabularyType());
        vocabularyToUpdate.setStatus(vocabularyTemplateToUpdate.getStatus());

        Vocabulary updatedVocabulary = vocabularyRepository.updateVocabulary(vocabularyToUpdate.build(), userId);

        log.debug("doCommand: Updated - updatedVocabulary = " + updatedVocabulary);

        hyePyeResponse.setVocabulary(updatedVocabulary);

        return hyePyeResponse.build();

    }

}
