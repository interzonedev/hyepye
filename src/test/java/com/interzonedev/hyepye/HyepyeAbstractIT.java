package com.interzonedev.hyepye;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/hyepye/spring/applicationContext-test.xml" })
public abstract class HyepyeAbstractIT extends AbstractIntegrationTest {

    @Inject
    protected ApplicationContext applicationContext;

    @Inject
    @Named("hyepye.service.dataSource")
    protected DataSource dataSource;

    @Inject
    @Named("hyepye.common.dbUnitDataSetTester")
    protected DbUnitDataSetTester dbUnitDataSetTester;

}
