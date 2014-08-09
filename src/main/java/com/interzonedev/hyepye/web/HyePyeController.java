package com.interzonedev.hyepye.web;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.respondr.response.HttpResponse;
import com.interzonedev.respondr.response.ResponseTransformingException;
import com.interzonedev.respondr.serialize.Serializer;

/**
 * Abstract top level controller for web controllers exposing Hye Pye endpoints. Provides common properties, helper
 * methods and error handling.
 * 
 * @author mmarkarian
 */
public abstract class HyePyeController {

    private static final Logger log = LoggerFactory.getLogger(HyePyeController.class);

    protected final Serializer serializer;

    protected final ValidationHelper validationHelper;

    protected final MessageSource messageSource;

    @Inject
    protected ApplicationContext applicationContext;

    public HyePyeController(Serializer serializer, ValidationHelper validationHelper, MessageSource messageSource) {
        this.serializer = serializer;
        this.validationHelper = validationHelper;
        this.messageSource = messageSource;
    }

    /**
     * Gets the {@link Locale} to be used on the current request.
     * 
     * @return Returns {@link Locale#US}.
     */
    protected Locale getLocale() {
        return Locale.US;
    }

    /**
     * General exception handler for any {@link Throwable} that makes it out of any endpoint of any implementing
     * controller. Handles the {@link Throwable} as a processing error and set the response status to a 500 internal
     * service error.
     * 
     * @param t The {@link Throwable} caught.
     * 
     * @return Returns a {@link ResponseEntity<String>} that contains a JSON response with information about the
     *         {@link Throwable} set as a processing error.
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(Throwable t) {

        log.error("handleException", t);

        try {
            HttpResponse errorHttpResponse = new HttpResponse(t, null, HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpResponse.JSON_CONTENT_TYPE);
            return errorHttpResponse.toResponseEntity(serializer);
        } catch (ResponseTransformingException e) {
            return HttpResponse.getDefaultJsonErrorResponseEntity();
        }

    }

}
