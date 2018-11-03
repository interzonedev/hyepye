package com.interzonedev.hyepye.web;

import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.commandr.http.CommandExecutor;
import com.interzonedev.hyepye.service.command.HyePyeCommand;
import com.interzonedev.respondr.response.HttpResponse;
import com.interzonedev.respondr.serialize.Serializer;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class HyePyeCommandExecutor extends CommandExecutor {

    public HyePyeCommandExecutor(HyePyeCommand command, Serializer serializer, ValidationHelper validationHelper,
            MessageSource messageSource, Locale locale) {
        super(command, serializer, HttpResponse.JSON_CONTENT_TYPE, true, validationHelper, messageSource, locale);
    }

}
