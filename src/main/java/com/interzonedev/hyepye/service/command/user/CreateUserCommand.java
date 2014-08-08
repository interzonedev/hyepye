package com.interzonedev.hyepye.service.command.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.model.User;
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

    private final User userToCreateTemplate;

    private final String plainTextPassword;

    @Inject
    @Named("hyepye.service.passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param userToCreateTemplate The {@link User} that contains the properties of the new {@link User} to create.
     * @param plainTextPassword The plain text password for the new {@link User} to create.
     */
    public CreateUserCommand(User userToCreateTemplate, String plainTextPassword) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.createUserCommand")
                .setThreadTimeoutMillis(1000).build());
        this.userToCreateTemplate = userToCreateTemplate;
        this.plainTextPassword = plainTextPassword;
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

        log.debug("doCommand: Start - userToCreate = " + userToCreateTemplate);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        if (null == userToCreateTemplate) {
            throw new ValidationException(User.MODEL_NAME, "The user must be set");
        }
        if (Strings.isNullOrEmpty(plainTextPassword)) {
            throw new ValidationException(User.MODEL_NAME, "The plain text password must be set");
        }

        User.Builder userToCreate = User.newBuilder(userToCreateTemplate);

        String passwordHash = passwordEncoder.encode(plainTextPassword);

        userToCreate.setPasswordHash(passwordHash);

        User user = userRepository.createUser(userToCreate.build());

        log.debug("doCommand: Created - user = " + user);

        hyePyeResponse.setUser(user);

        return hyePyeResponse.build();

    }

}
