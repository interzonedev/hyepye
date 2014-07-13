package com.interzonedev.hyepye.service.command.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.user.UserRepository;

/**
 * Creates a new {@link User}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.createUserCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateUserCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(CreateUserCommand.class);

    private final User userToCreate;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param userToCreate The {@link User} that contains the properties of the new {@link User} to create.
     */
    public CreateUserCommand(User userToCreate) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.createUserCommand")
                .setThreadTimeoutMillis(500).build());
        this.userToCreate = userToCreate;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the newly created {@link User} set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - userToCreate = " + userToCreate);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        User user = userRepository.createUser(userToCreate);

        log.debug("doCommand: Created - user = " + user);

        hyePyeResponse.setUser(user);

        return hyePyeResponse.build();

    }

}
