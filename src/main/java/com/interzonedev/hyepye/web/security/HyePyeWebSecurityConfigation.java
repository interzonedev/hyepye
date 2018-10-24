package com.interzonedev.hyepye.web.security;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.interzonedev.herokusupport.environment.Environment;

@Configuration("hyepye.web.webSecurityConfigation")
@EnableWebSecurity
public class HyePyeWebSecurityConfigation extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HyePyeWebSecurityConfigation.class);

    @Inject
    @Named("hyepye.service.passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Inject
    @Named("hyepye.service.userDetailsService")
    private UserDetailsService userDetailsService;

    public HyePyeWebSecurityConfigation() {
        log.debug("Default constructor");
    }

    public HyePyeWebSecurityConfigation(boolean disableDefaults) {
        super(disableDefaults);
        log.debug("Constructor: disableDefaults = " + disableDefaults);
    }

    @Inject
    public void registerAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**").antMatchers("/admin/hystrix.stream/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        setPortMappings(http);

        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("admin");

        http.authorizeRequests().antMatchers("/loginForm").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().anyRequest().permitAll();

        setRequiredChannelUrls(http);

        http.formLogin().loginPage("/loginForm").loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").failureUrl("/loginForm?loginError=true").permitAll();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").permitAll();

        http.rememberMe().rememberMeServices(getRememberMeServices());

        http.sessionManagement().sessionFixation().none();

    }

    private void setPortMappings(HttpSecurity http) throws Exception {
        if (Environment.PRODUCTION.equals(Environment.getCurrentEnvironment())) {
            return;
        }

        Map<String, String> portMappings = new HashMap<String, String>();
        portMappings.put(System.getProperty("webserver.port.http"), System.getProperty("webserver.port.https"));

        PortMapperImpl portMapper = new PortMapperImpl();
        portMapper.setPortMappings(portMappings);

        http.portMapper().portMapper(portMapper);

    }

    private void setRequiredChannelUrls(HttpSecurity http) throws Exception {
        if (Environment.PRODUCTION.equals(Environment.getCurrentEnvironment())) {
            return;
        }

        http.requiresChannel().antMatchers("/loginForm", "/login").requiresSecure();
        http.requiresChannel().anyRequest().requiresInsecure();

    }

    private TokenBasedRememberMeServices getRememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("hpk", userDetailsService);
        rememberMeServices.setParameter("remember");
        rememberMeServices.setCookieName("hprmc");
        rememberMeServices.setTokenValiditySeconds(3600);
        return rememberMeServices;
    }

}
