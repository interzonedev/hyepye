package com.interzonedev.hyepye.web;

import com.interzonedev.respondr.serialize.Serializer;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

import com.interzonedev.respondr.response.HttpResponse;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Web controller that allows the return of a JSON response body with the proper HTTP response status for errors that
 * would ordinarily generate an HTML response straight from the servlet container.
 * 
 * For instance, if an incoming request generates a 404 (not found) or 405 (bad request) the request will never reach
 * any of the other controllers in this app and will instead be processed by the servlet container. This app should have
 * web.xml configured to send 404 and 405 requests from the container to this controller. Any error codes not configured
 * in web.xml will be handled by {@link #handleDefault}.
 * 
 * @author mmarkarian
 */
@Controller("errorController")
@RequestMapping(value = "/error")
public class ErrorController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ErrorController.class);

    protected final Serializer serializer;

    @Inject
    public ErrorController(@Named("hyepye.web.jsonSerializer") Serializer serializer) {
        this.serializer = serializer;
    }

    /**
     * Handles the "Unauthorized" (401) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#UNAUTHORIZED}.
     */
    @RequestMapping(value = "401")
    public ResponseEntity<String> handle401() {
        log.error("handle401");
        return getHttpStatusJsonResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the "Forbidden" (403) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#FORBIDDEN}.
     */
    @RequestMapping(value = "403")
    public ResponseEntity<String> handle403() {
        log.error("handle403");
        return getHttpStatusJsonResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Handles the "Not Found" (404) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#NOT_FOUND}.
     */
    @RequestMapping(value = "404")
    public ResponseEntity<String> handle404() {
        log.error("handle404");
        return getHttpStatusJsonResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the "Method Not Allowed" (405) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#METHOD_NOT_ALLOWED}.
     */
    @RequestMapping(value = "405")
    public ResponseEntity<String> handle405() {
        log.error("handle405");
        return getHttpStatusJsonResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handles the default error case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#INTERNAL_SERVER_ERROR}.
     */
    @RequestMapping(value = "default")
    public ResponseEntity<String> handleDefault() {
        log.error("handleDefault");
        return getHttpStatusJsonResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> getHttpStatusJsonResponseEntity(HttpStatus httpStatus) {
        if (null == httpStatus) {
            throw new IllegalArgumentException("The HTTP response status must be set");
        }

        String processingErrorMessage = httpStatus.getReasonPhrase();

        HttpResponse httpResponse = HttpResponse
                .newBuilder()
                .setProcessingError(processingErrorMessage)
                .setHttpStatus(httpStatus)
                .setContentType(HttpResponse.JSON_CONTENT_TYPE)
                .build();

        return httpResponse.toResponseEntity(serializer);
    }
}
