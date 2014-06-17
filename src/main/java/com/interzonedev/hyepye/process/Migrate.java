package com.interzonedev.hyepye.process;

import com.interzonedev.herokusupport.data.migration.MigrationOperationException;
import com.interzonedev.herokusupport.data.migration.MigrationService;
import com.interzonedev.herokusupport.data.migration.operation.MigrationTask;
import com.interzonedev.herokusupport.data.migration.result.MigrationResult;
import com.interzonedev.herokusupport.util.SpringContextUtils;

public class Migrate extends AbstractProcess {

    @Override
    public void process(String[] args) throws Exception {
        log.info("process: Start migrating database");

        if (1 != args.length) {
            log.error("process: Usage - <migration task>");
            System.exit(1);
        }

        String migrationTaskValue = args[0].toUpperCase();
        MigrationTask migrationTask = null;
        try {
            migrationTask = MigrationTask.valueOf(migrationTaskValue);
        } catch (IllegalArgumentException e) {
            log.error("process: Usage - " + migrationTaskValue + " is not a valid migration task value");
            System.exit(2);
        }
        log.info("process: Running migration task " + migrationTask);

        MigrationService migrationServiceFlyway = (MigrationService) SpringContextUtils.getBean(CONTEXT_FILE_LOCATIONS,
                "migrationServiceFlyway");

        try {
            MigrationResult migrationResult = herokuSupportClient
                    .migrateDatabase(migrationTask, migrationServiceFlyway);

            log.debug("process: migrationResult = " + migrationResult);
        } catch (MigrationOperationException e) {
            log.error("process: Error running migration operation", e);
        }

        log.info("process: End migrating database");
    }

    public static void main(String[] args) throws Exception {
        (new Migrate()).process(args);
    }

}
