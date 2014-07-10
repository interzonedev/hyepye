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
 * Gets the {@link User} with the specified name.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.getUserByNameCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetUserByNameCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetUserByNameCommand.class);

    private final String name;

    @Inject
    @Named("hyepye.service.userRepository")
    private UserRepository userRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param name The name of the {@link User} to retrieve.
     */
    public GetUserByNameCommand(String name) {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getUserByNameCommand")
                .setThreadTimeoutMillis(500).build());
        this.name = name;
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

        log.debug("doCommand: Start - name = " + name);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        User userOut = userRepository.getUserByName(name);

        log.debug("doCommand: Retrieved - " + userOut);

        if (null != userOut) {
            hyePyeResponse.setUser(userOut);
        }

        return hyePyeResponse.build();

    }

}
