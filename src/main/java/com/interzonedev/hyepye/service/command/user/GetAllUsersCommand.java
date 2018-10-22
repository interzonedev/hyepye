package com.interzonedev.hyepye.service.command.user;

import java.util.List;

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
 * Gets all {@link User}s.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getAllUsersCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetAllUsersCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetAllUsersCommand.class);

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     */
    public GetAllUsersCommand() {
        super(HystrixCommandConfiguration.newBuilder().setCommandKey("hyepye.service.getAllUsersCommand")
                .setThreadTimeoutMillis(500).build());
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the collection of {@link User}s set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        List<User> users = userRepository.getAllUsers();

        log.debug("doCommand: Retrieved - " + users);

        hyePyeResponse.setUsers(users);

        return hyePyeResponse.build();

    }

}
