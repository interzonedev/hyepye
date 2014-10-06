package com.interzonedev.hyepye.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.hyepye.service.security.HyePyeUserDetails;
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

    public static final String DEFINITION_SEARCH_TYPES_KEY = "definitionSearchTypes";

    public static final String STATUSES_MAP_KEY = "statuses";

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
     * Gets the currently authenticated {@link User}.
     * 
     * @return Returns the currently authenticated {@link User} if there is one, otherwise returns null.
     */
    protected User getAuthenticatedUser() {

        User authenticatedUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HyePyeUserDetails userDetails = (HyePyeUserDetails) authentication.getPrincipal();
        if (null != userDetails) {
            authenticatedUser = userDetails.getUser();
        }

        return authenticatedUser;

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

    @RequestMapping(method = RequestMethod.GET, value = "/definitionSearchTypes")
    public ResponseEntity<String> getDefinitionSearchTypes() throws ResponseTransformingException {

        log.debug("getDefinitionSearchTypes: Start");

        Map<String, Object> responseStructure = new HashMap<String, Object>();

        List<Map<String, String>> definitionSearchTypes = new ArrayList<Map<String, String>>();
        for (DefinitionSearchType definitionSearchType : DefinitionSearchType.values()) {
            Map<String, String> definitionSearchTypeMap = new HashMap<String, String>();
            definitionSearchTypeMap.put("value", definitionSearchType.getDefinitionSearchTypeName());
            definitionSearchTypes.add(definitionSearchTypeMap);
        }

        responseStructure.put(DEFINITION_SEARCH_TYPES_KEY, definitionSearchTypes);

        HttpResponse httpResponse = new HttpResponse(responseStructure, null, HttpStatus.OK,
                HttpResponse.JSON_CONTENT_TYPE);

        log.debug("getDefinitionSearchTypes: End");

        return httpResponse.toResponseEntity(serializer);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/statuses")
    public ResponseEntity<String> getStatuses() throws ResponseTransformingException {

        log.debug("getStatuses: Start");

        Map<String, Object> responseStructure = new HashMap<String, Object>();

        List<Map<String, String>> statuses = new ArrayList<Map<String, String>>();
        for (Status status : Status.values()) {
            Map<String, String> statusMap = new HashMap<String, String>();
            statusMap.put("value", status.getStatusName());
            statuses.add(statusMap);
        }

        responseStructure.put(STATUSES_MAP_KEY, statuses);

        HttpResponse httpResponse = new HttpResponse(responseStructure, null, HttpStatus.OK,
                HttpResponse.JSON_CONTENT_TYPE);

        log.debug("getStatuses: End");

        return httpResponse.toResponseEntity(serializer);

    }

}
