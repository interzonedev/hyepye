package com.interzonedev.hyepye.process;

import java.util.Collections;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.webapp.WebAppContext;

import com.interzonedev.herokusupport.webserver.WebServerParams;
import com.interzonedev.herokusupport.webserver.WebServerType;

public class Webapp extends AbstractProcess {

    @Override
    public void process(String[] args) throws Exception {

        log.debug("process: Launching Jetty server");

        WebServerParams webServerParams = new WebServerParams(5000);

        herokuSupportClient.startWebServer(WebServerType.JETTY, webServerParams, (Server server,
                WebAppContext webAppContext) -> {
            configure(server, webAppContext);
        });

    }

    private void configure(Server server, WebAppContext webAppContext) {

        LoginService loginService = new HashLoginService("HyePyeRealm",
                "src/main/resources/com/interzonedev/hyepye/properties/realm.properties");
        server.addBean(loginService);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        server.setHandler(security);

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__BASIC_AUTH);
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[] { "user", "approver", "admin" });

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/admin/*");
        mapping.setConstraint(constraint);

        security.setConstraintMappings(Collections.singletonList(mapping));
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(loginService);

        security.setHandler(webAppContext);

    }

    public static void main(String[] args) throws Exception {
        (new Webapp()).process(args);
    }

}
