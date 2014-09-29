package com.interzonedev.hyepye.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.PortMapper;

import com.interzonedev.herokusupport.environment.Environment;

public class HyePyePortMapper implements PortMapper {

    private static final Logger log = LoggerFactory.getLogger(HyePyePortMapper.class);

    private final Integer localHttpPort = Integer.valueOf(System.getProperty("webserver.port.http"));

    private final Integer localHttpsPort = Integer.valueOf(System.getProperty("webserver.port.https"));

    private final Integer productionHttpPort = Integer.valueOf(80);

    private final Integer productionHttpsPort = Integer.valueOf(80);

    @Override
    public Integer lookupHttpPort(Integer httpsPort) {
        log.debug("lookupHttpPort: httpsPort = " + httpsPort);
        if (Environment.PRODUCTION.equals(Environment.getCurrentEnvironment())) {
            log.debug("lookupHttpPort: Returning " + productionHttpPort);
            return productionHttpPort;
        } else {
            log.debug("lookupHttpPort: Returning " + localHttpPort);
            return localHttpPort;
        }
    }

    @Override
    public Integer lookupHttpsPort(Integer httpPort) {
        log.debug("lookupHttpsPort: httpPort = " + httpPort);
        if (Environment.PRODUCTION.equals(Environment.getCurrentEnvironment())) {
            log.debug("lookupHttpsPort: Returning " + productionHttpsPort);
            return productionHttpsPort;
        } else {
            log.debug("lookupHttpsPort: Returning " + localHttpsPort);
            return localHttpsPort;
        }
    }

}
