package com.interzonedev.hyepye.service.command.user;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;

@Named("hyepye.service.getUserByNameCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetUserByNameCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(GetUserByNameCommand.class);

    public GetUserByNameCommand() {
        super(CommandConfiguration.newBuilder().setCommandKey("hyepye.service.getUserByNameCommand")
                .setThreadTimeoutMillis(500).build());
    }

    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        // TODO - Do stuff and return a real response.

        log.debug("doCommand: End");

        return HyePyeResponse.newBuilder().build();

    }

}