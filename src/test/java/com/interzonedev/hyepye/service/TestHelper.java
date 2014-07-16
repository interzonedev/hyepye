package com.interzonedev.hyepye.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Named("hyepye.service.testHelper")
public class TestHelper {

    private static final Logger log = LoggerFactory.getLogger(TestHelper.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Column names to ignore in comparing DbUnit datasets for all tests.
     */
    public static final List<String> COMMON_IGNORE_COLUMN_NAMES = Collections.unmodifiableList(Arrays
            .asList(new String[] { "time_created", "modified_by", "time_updated" }));

    /**
     * Column names to ignore in comparing DbUnit datasets for many but not all tests.
     */
    public static final List<String> EXPANDED_COMMON_IGNORE_COLUMN_NAMES = Collections.unmodifiableList(Lists
            .newArrayList(Iterables.concat(Arrays.asList(new String[] { "created_by" }), COMMON_IGNORE_COLUMN_NAMES)));

    /**
     * Column names to ignore in comparing DbUnit datasets for tests on the hp_user table.
     */
    public static final List<String> USER_IGNORE_COLUMN_NAMES = Collections.unmodifiableList(Lists
            .newArrayList(Iterables.concat(
                    Arrays.asList(new String[] { "hp_user_id", "password_hash", "password_seed" }),
                    EXPANDED_COMMON_IGNORE_COLUMN_NAMES)));
    
    /**
     * Column names to ignore in comparing DbUnit datasets for tests on the hp_user table.
     */
    public static final List<String> EXPANDED_USER_IGNORE_COLUMN_NAMES = Collections.unmodifiableList(Lists
            .newArrayList(Iterables.concat(
                    Arrays.asList(new String[] { "first_name", "last_name"}),
                    USER_IGNORE_COLUMN_NAMES)));

    /**
     * Column names to ignore in comparing DbUnit datasets for tests on the vocabulary table.
     */
    public static final List<String> VOCABULARY_IGNORE_COLUMN_NAMES = Collections.unmodifiableList(Lists
            .newArrayList(Iterables.concat(Arrays.asList(new String[] { "vocabulary_id" }),
                    EXPANDED_COMMON_IGNORE_COLUMN_NAMES)));

    /**
     * Parses the specified date string using the specified format into a {@code Date} in the default {@code TimeZone}.
     * 
     * @param dateString The date string to be parsed.
     * @param dateFormat The format of the date to be parsed.
     * 
     * @return Returns a {@code Date} object parsed from the specified date string using the specified format in the
     *         default {@code TimeZone}.
     */
    public Date parseDateInDefaultTimeZone(String dateString, String dateFormat) {
        try {
            DateFormat formatter = new SimpleDateFormat(dateFormat);
            Date date = (Date) formatter.parse(dateString);
            return date;
        } catch (ParseException pe) {
            String errorMessage = "parseDateInDefaultTimeZone: Error parsing date " + dateString + " into format "
                    + dateFormat;
            log.error(errorMessage, pe);
            throw new RuntimeException(errorMessage, pe);
        }
    }

    /**
     * Parses the specified date string using {@link #DEFAULT_DATE_FORMAT} into a {@code Date} in the default
     * {@code TimeZone}.
     * 
     * @param dateString The date string to be parsed.
     * 
     * @return Returns a {@code Date} object parsed from the specified date string using {@link #DEFAULT_DATE_FORMAT} in
     *         the default {@code TimeZone}.
     */
    public Date parseDateInDefaultTimeZone(String dateString) {
        return parseDateInDefaultTimeZone(dateString, DEFAULT_DATE_FORMAT);
    }

    /**
     * Gets a {@code Date} object set to midnight on January 1, 1970 in the default {@code TimeZone}.
     * 
     * @return Returns a {@code Date} object set to midnight on January 1, 1970 in the default {@code TimeZone}.
     */
    public Date getEpoch() {
        return parseDateInDefaultTimeZone("1970-01-01 00:00:00.000", DEFAULT_DATE_FORMAT);
    }

}
