package com.interzonedev.hyepye.service.command;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer.Context;
import com.google.common.base.Throwables;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.hystrix.AbstractIZHystrixCommand;
import com.interzonedev.commandr.hystrix.HystrixCommandConfiguration;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

/**
 * Top level abstract super class for all commands in the Hye Pye application.
 * 
 * Takes care of separating success vs. failure due to a validation error vs. failure due to a processing error.
 * Guaranteed to always return an instance of {@link HyePyeResponse} with different properties set depending on the
 * outcome.
 * 
 * Also takes care of wrapping metrics around the calls to all implementing commands.
 * 
 * Implementing classes must provide the {@link #doCommand()} method to perform the actual work of the command.
 * 
 * @author mmarkarian
 */
public abstract class AbstractHyePyeCommand extends AbstractIZHystrixCommand<HyePyeResponse> implements HyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(AbstractHyePyeCommand.class);

    /**
     * The command group key for all service commands.
     */
    public static final String SERVICE_COMMAND_GROUP_KEY = "HyePyeService";

    @Inject
    @Named("com.interzonedev.metrikos.metricRegistry")
    private MetricRegistry metricRegistry;

    protected AbstractHyePyeCommand(HystrixCommandConfiguration commandConfiguration) {
        super(HystrixCommandConfiguration.newBuilder(commandConfiguration).setCommandGroupKey(SERVICE_COMMAND_GROUP_KEY)
                .setExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE).build());
    }

    /**
     * Template method that executes the core actions of all implementing commands by calling back the
     * {@link #doCommand()} method.
     * 
     * Catches all exceptions, looks for {@link ValidationException} and sets the
     * {@link HyePyeResponse#getValidationError()} property on the response if one is thrown. Otherwise rethrows the
     * exception so the {@link #getFallback()} method can be invoked.
     * 
     * @return Always returns an instance of {@link HyePyeResponse} no matter the outcome of running the implementing
     *         command. The properties that are set on the response vary depending on whether the run resulted in a
     *         success, a validation error or a processing error.
     */
    @Override
    protected HyePyeResponse run() throws Exception {

        log.debug("run: Start");

        // Declare and start a Coda Hale metrics timer for the implementing class.
        Context commandTimerContext = metricRegistry.timer(getClass().getName()).time();

        try {

            // Initialize a default response builder.
            HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

            try {

                // Executes the implementing command and clones its response as a the response builder for this method.
                hyePyeResponse = HyePyeResponse.newBuilder(doCommand());

            } catch (Exception e) {

                // Look for a validation exception and set the validation error message on the response if one was
                // thrown, otherwise let the exception bubble up so the fallback method can be invoked.
                ValidationException validationException = getValidationExceptionFromException(e);

                if (null != validationException) {
                    log.info("run: Validation error - " + validationException);
                    hyePyeResponse.setValidationError(validationException);
                } else {
                    log.error("run: Processing error - " + e);
                    throw e;
                }

            }

            log.debug("run: End");

            return hyePyeResponse.build();

        } finally {

            commandTimerContext.stop();

        }

    }

    /**
     * Called if any exception was thrown from the {@link #run()} method.
     * 
     * @return Returns a {@link HyePyeResponse} with {@link HyePyeResponse#getProcessingError()} set.
     */
    @Override
    protected HyePyeResponse getFallback() {

        // TODO - Figure out how to get the exception that occured. Can check for valiation errors here instead of in
        // the run method and can get rid of the abstract doCommand method.

        String processingErrorMessage = "Error in " + getCommandKey().name();

        log.error("getFallback: " + processingErrorMessage);

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();
        hyePyeResponse.setProcessingError(new Exception(processingErrorMessage));

        return hyePyeResponse.build();

    }

    /**
     * Does the work of the implementing command.
     * 
     * @return Returns an instance of {@link HyePyeResponse} with the successful outcome properties set.
     * 
     * @throws Exception The implementing method should throw an exception when anything goes wrong (validation or
     *             processing problem) so that the {@link #getFallback()} method can get invoked.
     */
    protected abstract HyePyeResponse doCommand() throws Exception;

    /**
     * Attempts to extract an instance of {@link ValidationException} from the specified {@link Exception}.
     * 
     * @param e The {@link Exception} to check to see if it is an instance of or has a root cause that is a
     *            {@link ValidationException}.
     * 
     * @return Returns a {@link ValidationException} if the specified {@link Exception} is either a
     *         {@link ValidationException} or has a root cause that is a {@link ValidationException}. Otherwise returns
     *         null.
     */
    private ValidationException getValidationExceptionFromException(Exception e) {

        ValidationException validationException = null;

        if (e instanceof ValidationException) {
            validationException = (ValidationException) e;
        } else if (Throwables.getRootCause(e) instanceof ValidationException) {
            validationException = (ValidationException) Throwables.getRootCause(e);
        }

        return validationException;

    }

}
