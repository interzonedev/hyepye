package com.interzonedev.hyepye.web.security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.herokusupport.environment.Environment;

@Named("hyepye-securityFilter")
public class HyePyeSecurityFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(HyePyeSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        StringBuilder logMessage = new StringBuilder();

        logMessage.append(StringUtils.repeat("*", 10)).append(" ").append(" (").append(httpRequest.hashCode())
                .append("): ").append(httpRequest.getMethod()).append(" ").append(httpRequest.getScheme()).append(" ")
                .append(httpRequest.getServerName()).append(" ").append(httpRequest.getLocalPort()).append(" ")
                .append(httpRequest.getRemotePort()).append(" ").append(httpRequest.getLocalAddr()).append(" ")
                .append(httpRequest.getRemoteAddr()).append(" ").append(httpRequest.getRequestURI()).append(" ")
                .append(StringUtils.repeat("*", 10));

        log.debug("doFilter: Start - " + logMessage);

        if (requiresRedirect(httpRequest)) {
            // The request is HTTP, needs a redirect.
            try {
                String redirectURL = getRedirectUrl(httpRequest);
                log.debug("doFilter: Redirecting to " + redirectURL);
                httpResponse.sendRedirect(redirectURL);
                return;
            } catch (URISyntaxException e) {
                String errorMessage = "Error creating redirect URL";
                log.error("doFilter: " + errorMessage, e);
                throw new ServletException(errorMessage, e);
            }
        } else {
            // The request is HTTPS, continue.
            log.debug("doFilter: Continuing filter chain");
            chain.doFilter(httpRequest, response);
        }

        log.debug("doFilter: End - " + logMessage);

    }

    private boolean requiresRedirect(HttpServletRequest httpRequest) {
        String forwardedScheme = httpRequest.getHeader("X-Forwarded-Proto");
        log.debug("requiresRedirect: forwardedScheme = " + forwardedScheme);
        boolean needsRedirect = Environment.PRODUCTION.equals(Environment.getCurrentEnvironment())
                && ("http".equals(forwardedScheme));
        log.debug("requiresRedirect: needsRedirect = " + needsRedirect);
        return needsRedirect;
    }

    private String getRedirectUrl(HttpServletRequest httpRequest) throws URISyntaxException {
        URI redirectURI = new URI("https", httpRequest.getServerName(), httpRequest.getRequestURI(), null);
        String redirectURL = redirectURI.toString();
        return redirectURL;
    }

}
