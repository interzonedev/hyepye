package com.interzonedev.hyepye.service.command;

import org.slf4j.*;

import com.interzonedev.commandr.AbstractIZCommand;
import com.interzonedev.commandr.CommandConfiguration;

public abstract class AbstractHyePyeCommand extends AbstractIZCommand<HyePyeResponse> implements HyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(AbstractHyePyeCommand.class);

    protected AbstractHyePyeCommand(CommandConfiguration commandConfiguration) {
        super(commandConfiguration);
    }

    @Override
    protected HyePyeResponse run() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
