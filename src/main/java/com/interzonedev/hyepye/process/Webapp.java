package com.interzonedev.hyepye.process;

import java.util.Properties;

import com.interzonedev.herokusupport.environment.Environment;
import com.interzonedev.herokusupport.webserver.SecureWebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerProperties;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp extends HyePyeProcess {

    private final Properties properties = new Properties();

    @Override
    public void process(String[] args) throws Exception {
        log.debug("process: Launching Jetty server");

        loadProperties();

        WebServerParams webServerParams = getWebServerParams();

        SecureWebServerParams secureWebServerParams = getSecureWebServerParams();

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams, secureWebServerParams, (
                webServerProperties) -> {
            getWebServerProperties(webServerProperties);
        }, null);

        log.debug("process: Jetty server shutdown");
    }

    private void loadProperties() {
        try {
            properties.load(this.getClass().getResourceAsStream(
                    "/com/interzonedev/hyepye/webserver/local-webserver.properties"));
        } catch (Exception e) {
            String errorMessage = "Error loading local webserver properties";
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private WebServerParams getWebServerParams() {
        Integer httpPort = Integer.valueOf(properties.getProperty("http.port"));

        WebServerParams webServerParams = new WebServerParams(httpPort);

        return webServerParams;
    }

    private SecureWebServerParams getSecureWebServerParams() {
        if (!Environment.getCurrentEnvironment().equals(Environment.LOCAL)) {
            return null;
        }

        String keyStorePath = properties.getProperty("key.store.path");
        String keyStorePassword = properties.getProperty("key.store.password");
        String keyManagerPassword = properties.getProperty("key.manager.password");
        Integer httpsPort = Integer.valueOf(properties.getProperty("https.port"));

        return new SecureWebServerParams(keyStorePath, keyStorePassword, keyManagerPassword, httpsPort);

    }

    private void getWebServerProperties(WebServerProperties webServerProperties) {
        Integer httpPort = webServerProperties.getHttpPort();
        Integer httpsPort = webServerProperties.getHttpsPort();
        log.debug("getPorts: httpPort = " + httpPort + " - httpsPort = " + httpsPort);
        if (null == httpsPort) {
            httpsPort = httpPort;
        }
        System.setProperty("webserver.port.http", httpPort.toString());
        System.setProperty("webserver.port.https", httpsPort.toString());
    }

    public static void main(String[] args) throws Exception {
        (new Webapp()).process(args);
    }

}
