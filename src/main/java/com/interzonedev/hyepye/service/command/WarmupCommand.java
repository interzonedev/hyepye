package com.interzonedev.hyepye.service.command;

import javax.inject.Inject;
import javax.inject.Named;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.commandr.CommandConfiguration;
import com.interzonedev.hyepye.service.dao.JdbiUserDAO;

/**
 * Command that performs a simple database query via JDBI to "warm up" the stack from the command framework down to the
 * data access layer. This can be created and run upon application startup so that the first real command request that
 * is received executes much more quickly.
 * 
 * @author mmarkarian
 */
@Named("service.warmupCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WarmupCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(WarmupCommand.class);

    @Inject
    @Named("dbi")
    private DBI dbi;

    /**
     * Creates an instance of this command with a timeout long enough to make sure all elements of the stack initialize
     * within the limits of executing this command.
     */
    public WarmupCommand() {
        super(CommandConfiguration.newBuilder().setCommandKey("Warmup").setThreadTimeoutMillis(3000).build());
    }

    /**
     * Executes a select query via JDBI to force JDBI component scanning. Ignores the result.
     * 
     * @return Returns the default instance of {@link HyePyeResponse}.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        (dbi.onDemand(JdbiUserDAO.class)).getAllUsers();

        log.debug("doCommand: End");

        return HyePyeResponse.newBuilder().build();

    }

}
