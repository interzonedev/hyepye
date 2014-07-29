package com.interzonedev.hyepye.service.command.vocabulary;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Gets the {@link Vocabulary} with the specified ID.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getVocabularyByIdCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetVocabularyByIdCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetVocabularyByIdCommand.class);

    private final Long id;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param id The ID of the {@link Vocabulary} to retrieve.
     */
    public GetVocabularyByIdCommand(Long id) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getVocabularyByIdCommand")
                .setThreadTimeoutMillis(500).build());
        this.id = id;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the {@link Vocabulary} set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - id = " + id);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        Vocabulary vocabularyOut = vocabularyRepository.getVocabularyById(id);

        log.debug("doCommand: Retrieved - " + vocabularyOut);

        if (null != vocabularyOut) {
            hyePyeResponse.setVocabulary(vocabularyOut);
        }

        return hyePyeResponse.build();

    }

}
