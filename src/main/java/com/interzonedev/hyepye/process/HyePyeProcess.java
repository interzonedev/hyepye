package com.interzonedev.hyepye.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.herokusupport.client.DefaultHerokuSupportClient;
import com.interzonedev.herokusupport.client.HerokuSupportClient;

public abstract class HyePyeProcess {

    protected static final String[] CONTEXT_FILE_LOCATIONS = { "com/interzonedev/hyepye/spring/processContext.xml" };

    protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    protected final HerokuSupportClient herokuSupportClient = new DefaultHerokuSupportClient();

    protected abstract void process(String[] args) throws Exception;

}
