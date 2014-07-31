package com.interzonedev.hyepye.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.commandr.IZCommandResponse;
import com.interzonedev.commandr.http.CommandExecutor;
import com.interzonedev.hyepye.service.command.HyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.respondr.response.HttpResponse;
import com.interzonedev.respondr.serialize.Serializer;

public abstract class HyePyeCommandExecutor extends CommandExecutor {

    private final ValidationHelper validationHelper;

    private final MessageSource messageSource;

    private final Locale locale;

    public HyePyeCommandExecutor(HyePyeCommand command, Serializer serializer, ValidationHelper validationHelper,
            MessageSource messageSource, Locale locale) {
        super(command, serializer, HttpResponse.JSON_CONTENT_TYPE, true);
        this.validationHelper = validationHelper;
        this.messageSource = messageSource;
        this.locale = locale;
    }

    /**
     * Transforms the {@link ValidationException} set on the specified {@link HyePyeResponse} into a {@link Map} that
     * can be serialized in the response.
     * 
     * @param izCommandResponse The {@link HyePyeResponse} returned from the execution of the {@link HyePyeCommand} with
     *            which this executor was initialized.
     * 
     * @return Returns a @link Map} of validation errors that can be serialized in the response.
     */
    @Override
    protected final Map<String, List<String>> getValidationError(IZCommandResponse izCommandResponse) {
        ValidationException validationException = ((HyePyeResponse) izCommandResponse).getValidationError();
        return validationHelper.toMap(validationException, messageSource, locale);
    }

    /**
     * Transforms the processing {@link Exception} set on the specified {@link HyePyeResponse} into a {@link String}
     * that can be set as the body of the response.
     * 
     * @param izCommandResponse The {@link HyePyeResponse} returned from the execution of the {@link HyePyeCommand} with
     *            which this executor was initialized.
     * 
     * @return Returns a processing error message that can be set as the body of the response.
     */
    @Override
    protected final String getProcessingError(IZCommandResponse izCommandResponse) {
        Exception processingError = ((HyePyeResponse) izCommandResponse).getProcessingError();
        return validationHelper.getThrowableMessage(processingError, HttpResponse.DEFAULT_ERROR_MESSAGE);
    }

    /**
     * Implementation that returns an empty {@link String}. No use cases in the Hye Pye application return the response
     * body directly.
     * 
     * @param izCommandResponse The {@link HyePyeResponse} returned from the execution of the {@link HyePyeCommand} with
     *            which this executor was initialized.
     * 
     * @return Returns an empty {@link String}.
     */
    @Override
    protected final String onSuccessBody(IZCommandResponse izCommandResponse) {
        return "";
    }

    /**
     * Default implementation that returns null. This should be overridden by implementations that need to add custom
     * response headers.
     * 
     * @return Returns null.
     */
    @Override
    protected HttpHeaders getResponseHeaders() {
        return null;
    }

}
