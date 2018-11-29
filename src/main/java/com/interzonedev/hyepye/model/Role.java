package com.interzonedev.hyepye.model;

/**
 * Enumeration that identifies {@link User} roles.
 * 
 * @author mmarkarian
 */
public enum Role {

    ADMIN("admin"), APPROVER("approver"), USER("user");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    /**
     * Gets the {@link Role} with the specified name.
     * 
     * @param roleName The name of the {@link Role} to get.
     * 
     * @return Returns the {@link Role} with the specified name or null if the specified role name does not correspond
     *         to any definied {@link Role} values.
     */
    public static Role fromRoleName(String roleName) {

        for (Role role : Role.values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }

        return null;
    }

}
