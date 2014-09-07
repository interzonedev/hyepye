package com.interzonedev.hyepye.process;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import com.interzonedev.herokusupport.environment.Environment;
import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp extends HyePyeProcess {

    @Override
    public void process(String[] args) throws Exception {
        log.debug("process: Launching Jetty server");

        WebServerParams webServerParams = new WebServerParams(5000);

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams, (Server server,
                WebAppContext webAppContext) -> {
            configure(server, webAppContext);
        });

        log.debug("process: Jetty server shutdown");
    }

    /**
     * Configure for SSL.
     * 
     * @param server
     * @param webAppContext
     * @throws IOException
     */
    private void configure(Server server, WebAppContext webAppContext) {
        if (!Environment.getCurrentEnvironment().equals(Environment.LOCAL)) {
            return;
        }

        log.debug("configure: Configuring Jetty server for SSL");

        Properties properties = new Properties();
        try {
            properties.load(this.getClass()
                    .getResourceAsStream("/com/interzonedev/hyepye/localssl/localssl.properties"));
        } catch (Exception e) {
            String errorMessage = "Error loading local SSL properties";
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }

        String keyStorePath = properties.getProperty("key.store.path");
        String keyStorePassword = properties.getProperty("key.store.password");
        String keyManagerPassword = properties.getProperty("key.manager.password");
        Integer sslPort = Integer.valueOf(properties.getProperty("ssl.port"));

        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(Webapp.class.getResource(keyStorePath).toExternalForm());
        sslContextFactory.setKeyStorePassword(keyStorePassword);
        sslContextFactory.setKeyManagerPassword(keyManagerPassword);

        ServerConnector sslConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory,
                "http/1.1"), new HttpConnectionFactory(https));
        sslConnector.setPort(sslPort);

        server.addConnector(sslConnector);

    }

    public static void main(String[] args) throws Exception {
        (new Webapp()).process(args);
    }

}
