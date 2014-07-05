package com.interzonedev.hyepye.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.interzonedev.hyepye.service.command.WarmupCommand;

/**
 * Spring configuration class that, when component scanned, creates and executes {@link WarmupCommand} that invokes each
 * layer of the stack from the command framework down to the database.
 * 
 * @author mmarkarian
 */
@Configuration("service.applicationWarmup")
public class ApplicationWarmup {

    protected static final Logger log = LoggerFactory.getLogger(ApplicationWarmup.class);

    @Inject
    private ApplicationContext applicationContext;

    /**
     * Executes {@link WarmupCommand} and ignores the result.
     */
    @PostConstruct
    public void warmup() {

        log.info("warmup: Start");

        ((WarmupCommand) applicationContext.getBean("service.warmupCommand")).execute();

        log.info("warmup: End");

    }

}
