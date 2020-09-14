package com.interzonedev.hyepye.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@ImportResource({"classpath:com/interzonedev/hyepye/spring/applicationContext.xml"})
/*
@ComponentScan(basePackages = {"com.interzonedev.hyepye.web"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {EnableWebSecurity.class})})

*/
@ComponentScan(basePackages = {"com.interzonedev.hyepye.web"})
public class AppConfig {
}
