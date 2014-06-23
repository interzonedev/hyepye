package com.interzonedev.hyepye.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * Domain model class for users.
 * 
 * @author mmarkarian
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8164945372635497639L;

    @NotNull
    @Min(1L)
    private final Long id;

    @NotEmpty
    @Size(max = 255)
    private final String username;

    @NotEmpty
    @Size(max = 64)
    private final String passwordHash;

    @NotEmpty
    @Size(max = 10)
    private final String passwordSeed;

    @NotEmpty
    @Size(max = 255)
    private final String email;

    @Size(max = 255)
    private final String firstName;

    @Size(max = 255)
    private final String lastName;

    @NotEmpty
    @Size(max = 50)
    private final String role;

    private final boolean active;

    private final Date timeCreated;

    private final Date timeUpdated;

    /**
     * Creates a new {@link User} from the values set on the specified {@link Builder}.
     * 
     * @param builder The {@link Builder} that holds the values for the {@link User} to create.
     */
    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.passwordHash = builder.passwordHash;
        this.passwordSeed = builder.passwordSeed;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
        this.active = builder.active;
        this.timeCreated = builder.timeCreated;
        this.timeUpdated = builder.timeUpdated;
    }

    /**
     * Gets a {@link Builder} with default initial values.
     * 
     * @return Returns a {@link Builder} with default initial values.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets a {@link Builder} with initial values set from the specified template {@link User}
     * 
     * @param template A {@link User} from which to get the initial values for the new {@link Builder}.
     * 
     * @return Returns a {@link Builder} with initial values set from the specified template {@link User}
     */
    public static Builder newBuilder(User template) {
        return new Builder(template);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPasswordSeed() {
        return passwordSeed;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User that = (User) obj;

        return Objects.equal(getId(), that.getId()) && Objects.equal(getUsername(), that.getUsername())
                && Objects.equal(getPasswordHash(), that.getPasswordHash())
                && Objects.equal(getPasswordSeed(), that.getPasswordSeed())
                && Objects.equal(getEmail(), that.getEmail()) && Objects.equal(getFirstName(), that.getFirstName())
                && Objects.equal(getLastName(), that.getLastName()) && Objects.equal(getRole(), that.getRole())
                && Objects.equal(isActive(), that.isActive()) && Objects.equal(getTimeCreated(), that.getTimeCreated())
                && Objects.equal(getTimeUpdated(), that.getTimeUpdated());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPasswordHash(), getPasswordSeed(), getEmail(),
                getFirstName(), getLastName(), getRole(), isActive(), getTimeCreated(), getTimeUpdated());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("hashCode", Integer.toHexString(hashCode())).add("id", getId())
                .add("username", getUsername()).add("passwordHash", getPasswordHash())
                .add("passwordSeed", getPasswordSeed()).add("email", getEmail()).add("firstName", getFirstName())
                .add("lastName", getLastName()).add("role", getRole()).add("active", isActive())
                .add("timeCreated", getTimeCreated()).add("timeUpdated", getTimeUpdated()).toString();
    }

    /**
     * Mutable builder for creating instances of {@link User}. Allows for the individual setting of properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Long id;

        private String username;

        private String passwordHash;

        private String passwordSeed;

        private String email;

        private String firstName;

        private String lastName;

        private String role;

        private boolean active;

        private Date timeCreated;

        private Date timeUpdated;

        /**
         * Default constructor. Allows for building a {@link User} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link User} starting with initial values set from the specified template {@link User}.
         * 
         * @param template A {@link User} from which to get the initial values for building a new {@link User}.
         */
        private Builder(User template) {
            this.id = template.getId();
            this.username = template.getUsername();
            this.passwordHash = template.getPasswordHash();
            this.passwordSeed = template.getPasswordSeed();
            this.email = template.getEmail();
            this.firstName = template.getFirstName();
            this.lastName = template.getLastName();
            this.role = template.getRole();
            this.active = template.isActive();
            this.timeCreated = template.getTimeCreated();
            this.timeUpdated = template.getTimeUpdated();
        }

        /**
         * Creates a new {@link User} from the values set on this {@link Builder}.
         */
        public User build() {
            return new User(this);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder setPasswordSeed(String passwordSeed) {
            this.passwordSeed = passwordSeed;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setTimeCreated(Date timeCreated) {
            this.timeCreated = timeCreated;
            return this;
        }

        public Builder setTimeUpdated(Date timeUpdated) {
            this.timeUpdated = timeUpdated;
            return this;
        }

    }

}
