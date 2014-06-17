package com.interzonedev.hyepye.process;

import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp extends AbstractProcess {

    @Override
    public void process(String[] args) throws Exception {
        log.debug("process: Launching Jetty server");

        WebServerParams webServerParams = new WebServerParams(5000);

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams);

        log.debug("process: Jetty server shutdown");
    }

    public static void main(String[] args) throws Exception {
        (new Webapp()).process(args);
    }

}
