package com.interzonedev.hyepye.service.command.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.hystrix.HystrixCommandConfiguration;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.user.UserRepository;

/**
 * Deactivates a {@link User}.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.deactivateUserCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeactivateUserCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(DeactivateUserCommand.class);

    private final Long id;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param id The ID of the {@link User} to deactivate.
     */
    public DeactivateUserCommand(Long id) {
        super(HystrixCommandConfiguration.newBuilder().setCommandKey("hyepye.service.deactivateUserCommand")
                .setThreadTimeoutMillis(500).build());
        this.id = id;
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

        log.debug("doCommand: Start - id = " + id);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        User user = userRepository.deactivateUser(id);

        log.debug("doCommand: Deactivated - user = " + user);

        hyePyeResponse.setUser(user);

        return hyePyeResponse.build();

    }

}
