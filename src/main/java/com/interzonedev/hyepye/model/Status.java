package com.interzonedev.hyepye.model;

/**
 * Enumeration that identifies {@link Verb} and {@link Conjugation} statuses.
 * 
 * @author mmarkarian
 */
public enum Status {

    SUBMITTED("submitted"), APPROVED("approved"), REJECTED("rejected");

    private final String statusName;

    private Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    /**
     * Gets the {@link Status} with the specified name.
     * 
     * @param statusName The name of the {@link Status} to get.
     * 
     * @return Returns the {@link Status} with the specified name or null if the specified status name does not
     *         correspond to any definied {@link Status} values.
     */
    public static Status fromStatusName(String statusName) {

        for (Status status : Status.values()) {
            if (status.getStatusName().equals(statusName)) {
                return status;
            }
        }

        return null;
    }

}
