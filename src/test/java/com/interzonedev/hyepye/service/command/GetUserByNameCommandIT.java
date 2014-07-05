package com.interzonedev.hyepye.service.command;

import javax.inject.Inject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.interzonedev.hyepye.HyepyeAbstractIT;

public class GetUserByNameCommandIT extends HyepyeAbstractIT {

    private static final Logger log = LoggerFactory.getLogger(GetUserByNameCommandIT.class);

    @Inject
    private ApplicationContext applicationContext;

    @Test
    public void testFoo() {
        GetUserByNameCommand getUserByNameCommand = (GetUserByNameCommand) applicationContext
                .getBean("service.getUserByNameCommand");
        log.debug("getUserByNameCommand = " + getUserByNameCommand);
    }

}
