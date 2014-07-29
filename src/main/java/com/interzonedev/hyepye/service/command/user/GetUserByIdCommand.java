package com.interzonedev.hyepye.service.command.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.user.UserRepository;

/**
 * Gets the {@link User} with the specified ID.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getUserByIdCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetUserByIdCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetUserByIdCommand.class);

    private final Long id;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param id The ID of the {@link User} to retrieve.
     */
    public GetUserByIdCommand(Long id) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getUserByIdCommand")
                .setThreadTimeoutMillis(500).build());
        this.id = id;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the {@link User} set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - id = " + id);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        User userOut = userRepository.getUserById(id);

        log.debug("doCommand: Retrieved - " + userOut);

        if (null != userOut) {
            hyePyeResponse.setUser(userOut);
        }

        return hyePyeResponse.build();

    }

}
