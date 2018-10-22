package com.interzonedev.hyepye.service.command.user;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.hystrix.HystrixCommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.user.UserRepository;

/**
 * Updates a {@link User} except for the active properties.
 * 
 * @see DeactivateUserCommand to alter the active property.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.updateUserCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UpdateUserCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserCommand.class);

    private final User userToUpdateTemplate;

    @Nullable
    private final String currentPlainTextPassword;

    @Nullable
    private final String newPlainTextPassword;

    @Inject
    @Named("hyepye.service.passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout. Use this when the {@link User}'s
     * password is being updated.
     * 
     * @param userToUpdateTemplate The {@link User} that contains the properties to set on the {@link User} to update.
     * @param currentPlainTextPassword The current plain text password of the {@link User} to update if the password is
     *            being updated.
     * @param newPlainTextPassword The new plain text password to set on the {@link User} to update if the password is
     *            being updated.
     */
    public UpdateUserCommand(User userToUpdateTemplate, String currentPlainTextPassword, String newPlainTextPassword) {
        super(HystrixCommandConfiguration.newBuilder().setCommandKey("hyepye.service.updateUserCommand")
                .setThreadTimeoutMillis(1000).build());
        this.userToUpdateTemplate = userToUpdateTemplate;
        this.currentPlainTextPassword = currentPlainTextPassword;
        this.newPlainTextPassword = newPlainTextPassword;
    }

    /**
     * Creates an instance of this command with a specific command key and timeout. Use this when the {@link User}'s
     * password is not being updated.
     * 
     * @param userToUpdateTemplate The {@link User} that contains the properties to set on the {@link User} to update.
     */
    public UpdateUserCommand(User userToUpdateTemplate) {
        super(HystrixCommandConfiguration.newBuilder().setCommandKey("hyepye.service.updateUserCommand")
                .setThreadTimeoutMillis(500).build());
        this.userToUpdateTemplate = userToUpdateTemplate;
        this.currentPlainTextPassword = null;
        this.newPlainTextPassword = null;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the updated {@link User} set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start - userToCreate = " + userToUpdateTemplate);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        if (null == userToUpdateTemplate) {
            throw new ValidationException(User.MODEL_NAME, "The user must be set");
        }

        // Get the current User from the database.
        User currentUser = userRepository.getUserById(userToUpdateTemplate.getId());
        if (null == currentUser) {
            throw new ValidationException(User.MODEL_NAME, "The user to update doesn't exist");
        }

        User.Builder userToUpdate = User.newBuilder(currentUser);

        if (!Strings.isNullOrEmpty(currentPlainTextPassword) && !Strings.isNullOrEmpty(newPlainTextPassword)) {
            // The password is being updated.

            // Validate that the specified current password is the same as that in the database for the User.
            if (!passwordEncoder.matches(currentPlainTextPassword, currentUser.getPasswordHash())) {
                throw new ValidationException(User.MODEL_NAME, "The current password doesn't match");
            }

            // Update the password.
            String newPasswordHash = passwordEncoder.encode(newPlainTextPassword);
            userToUpdate.setPasswordHash(newPasswordHash);
        }

        // Copy the properties to be updated onto the User instance that will be persisted to the database.
        userToUpdate.setUsername(userToUpdateTemplate.getUsername());
        userToUpdate.setEmail(userToUpdateTemplate.getEmail());
        userToUpdate.setFirstName(userToUpdateTemplate.getFirstName());
        userToUpdate.setLastName(userToUpdateTemplate.getLastName());
        userToUpdate.setRole(userToUpdateTemplate.getRole());

        User updatedUser = userRepository.updateUser(userToUpdate.build());

        log.debug("doCommand: Updated - updatedUser = " + updatedUser);

        hyePyeResponse.setUser(updatedUser);

        return hyePyeResponse.build();

    }

}
