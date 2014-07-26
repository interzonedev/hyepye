package com.interzonedev.hyepye.web;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

import com.interzonedev.respondr.response.HttpResponse;

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

    /**
     * Handles the "Not Found" (404) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#NOT_FOUND}.
     */
    @RequestMapping(value = "404")
    public ResponseEntity<String> handle404() {

        log.error("handle404");

        return HttpResponse.getHttpStatusJsonResponseEntity(HttpStatus.NOT_FOUND);

    }

    /**
     * Handles the "Method Not Allowed" (405) case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#METHOD_NOT_ALLOWED}.
     */
    @RequestMapping(value = "405")
    public ResponseEntity<String> handle405() {

        log.error("handle405");

        return HttpResponse.getHttpStatusJsonResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);

    }

    /**
     * Handles the default error case.
     * 
     * @return Returns a simple JSON response body with the status set to {@link HttpStatus#INTERNAL_SERVER_ERROR}.
     */
    @RequestMapping(value = "default")
    public ResponseEntity<String> handleDefault() {

        log.error("handleDefault");

        return HttpResponse.getHttpStatusJsonResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
