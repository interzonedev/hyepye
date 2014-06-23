package com.interzonedev.hyepye.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

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

    public User(Long id, String username, String passwordHash, String passwordSeed, String email, String firstName,
            String lastName, String role, boolean active, Date timeCreated, Date timeUpdated) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSeed = passwordSeed;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
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

}
