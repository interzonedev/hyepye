package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Gets all {@link Vocabulary} instances.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getAllVocabulariesCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetAllVocabulariesCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetAllVocabulariesCommand.class);

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     */
    public GetAllVocabulariesCommand() {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getAllVocabulariesCommand")
                .setThreadTimeoutMillis(500).build());
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

        List<Vocabulary> vocabularies = vocabularyRepository.getAllVocabularies();

        log.debug("doCommand: Retrieved - " + vocabularies);

        hyePyeResponse.setVocabularies(vocabularies);

        return hyePyeResponse.build();

    }

}
