package com.interzonedev.hyepye.web;


import com.interzonedev.respondr.serialize.JsonSerializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("hyepye.web.config")
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Gets the {@link MessageSource} instance to be used across the web layer of the application.
     *
     * @return Returns a {@link ReloadableResourceBundleMessageSource} instance set to cache messages for the lifetime
     * of the application.
     */
    @Bean(name = {"messageSource", "hyepye.web.messageSource"})
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("classpath:messages/errors/errors");
        messageSource.setCacheSeconds(-1);
        return messageSource;
    }

    @Bean(name = "hyepye.web.jsonSerializer")
    public JsonSerializer getJsonSerializer() {
        return new JsonSerializer();
    }

    /**
     * Register mappings of URLs to views that do not require a controller.
     *
     * @param registry The {@link ViewControllerRegistry} for the application.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home/home");
        registry.addViewController("/loginForm").setViewName("login/login");
        registry.addViewController("/admin").setViewName("admin/menu/menu");
        registry.addViewController("/admin/").setViewName("admin/menu/menu");
        registry.addViewController("/admin/vocabularyapp").setViewName("admin/vocabulary/app");
        registry.addViewController("/admin/vocabularyapp/search").setViewName("admin/vocabulary/search");
        registry.addViewController("/admin/vocabularyapp/form").setViewName("admin/vocabulary/form");
        registry.addViewController("/directives/alerts").setViewName("directives/alerts");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
    }
}
