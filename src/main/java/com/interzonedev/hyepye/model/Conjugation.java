package com.interzonedev.hyepye.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * Domain model class for conjugations.
 * 
 * @author mmarkarian
 */
public class Conjugation implements Serializable {

    private static final long serialVersionUID = 8894778400592539558L;

    @Min(1L)
    private final Long id;

    @NotNull
    @Min(1L)
    private final Long verbId;

    @NotEmpty
    @Size(max = 50)
    private final String tense;

    @NotEmpty
    @Size(max = 255)
    private final String firstPersonSingular;

    @NotEmpty
    @Size(max = 255)
    private final String secondPersonSingular;

    @NotEmpty
    @Size(max = 255)
    private final String thirdPersonSingular;

    @NotEmpty
    @Size(max = 255)
    private final String firstPersonPlural;

    @NotEmpty
    @Size(max = 255)
    private final String secondPersonPlural;

    @NotEmpty
    @Size(max = 255)
    private final String thirdPersonPlural;

    @NotEmpty
    @Size(max = 50)
    private final String status;

    private final Date timeCreated;

    private final Date timeUpdated;

    @Min(1L)
    private final Long createdBy;

    @Min(1L)
    private final Long modifiedBy;

    /**
     * Creates a new {@link Conjugation} from the values set on the specified {@link Builder}.
     * 
     * @param builder The {@link Builder} that holds the values for the {@link Conjugation} to create.
     */
    private Conjugation(Builder builder) {
        this.id = builder.id;
        this.verbId = builder.verbId;
        this.tense = builder.tense;
        this.firstPersonSingular = builder.firstPersonSingular;
        this.secondPersonSingular = builder.secondPersonSingular;
        this.thirdPersonSingular = builder.thirdPersonSingular;
        this.firstPersonPlural = builder.firstPersonPlural;
        this.secondPersonPlural = builder.secondPersonPlural;
        this.thirdPersonPlural = builder.thirdPersonPlural;
        this.status = builder.status;
        this.timeCreated = builder.timeCreated;
        this.timeUpdated = builder.timeUpdated;
        this.createdBy = builder.createdBy;
        this.modifiedBy = builder.modifiedBy;
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
     * Gets a {@link Builder} with initial values set from the specified template {@link Conjugation}
     * 
     * @param template A {@link Conjugation} from which to get the initial values for the new {@link Builder}.
     * 
     * @return Returns a {@link Builder} with initial values set from the specified template {@link Conjugation}
     */
    public static Builder newBuilder(Conjugation template) {
        return new Builder(template);
    }

    public Long getId() {
        return id;
    }

    public Long getVerbId() {
        return verbId;
    }

    public String getTense() {
        return tense;
    }

    public String getFirstPersonSingular() {
        return firstPersonSingular;
    }

    public String getSecondPersonSingular() {
        return secondPersonSingular;
    }

    public String getThirdPersonSingular() {
        return thirdPersonSingular;
    }

    public String getFirstPersonPlural() {
        return firstPersonPlural;
    }

    public String getSecondPersonPlural() {
        return secondPersonPlural;
    }

    public String getThirdPersonPlural() {
        return thirdPersonPlural;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Conjugation)) {
            return false;
        }

        Conjugation that = (Conjugation) obj;

        return Objects.equal(getId(), that.getId()) && Objects.equal(getVerbId(), that.getVerbId())
                && Objects.equal(getTense(), that.getTense())
                && Objects.equal(getFirstPersonSingular(), that.getFirstPersonSingular())
                && Objects.equal(getSecondPersonSingular(), that.getSecondPersonSingular())
                && Objects.equal(getThirdPersonSingular(), that.getThirdPersonSingular())
                && Objects.equal(getFirstPersonPlural(), that.getFirstPersonPlural())
                && Objects.equal(getSecondPersonPlural(), that.getSecondPersonPlural())
                && Objects.equal(getThirdPersonPlural(), that.getThirdPersonPlural())
                && Objects.equal(getStatus(), that.getStatus())
                && Objects.equal(getTimeCreated(), that.getTimeCreated())
                && Objects.equal(getTimeUpdated(), that.getTimeUpdated())
                && Objects.equal(getCreatedBy(), that.getCreatedBy())
                && Objects.equal(getModifiedBy(), that.getModifiedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getVerbId(), getTense(), getFirstPersonSingular(), getSecondPersonSingular(),
                getThirdPersonSingular(), getFirstPersonPlural(), getSecondPersonPlural(), getThirdPersonPlural(),
                getStatus(), getTimeCreated(), getTimeUpdated(), getCreatedBy(), getModifiedBy());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("hashCode", Integer.toHexString(hashCode())).add("id", getId())
                .add("verbId", getVerbId()).add("tense", getTense())
                .add("firstPersonSingular", getFirstPersonSingular())
                .add("secondPersonSingular", getSecondPersonSingular())
                .add("thirdPersonSingular", getThirdPersonSingular()).add("firstPersonPlural", getFirstPersonPlural())
                .add("secondPersonPlural", getSecondPersonPlural()).add("thirdPersonPlural", getThirdPersonPlural())
                .add("status", getStatus()).add("timeCreated", getTimeCreated()).add("timeUpdated", getTimeUpdated())
                .add("createdBy", getCreatedBy()).add("modifiedBy", getModifiedBy()).toString();
    }

    /**
     * Mutable builder for creating instances of {@link Conjugation}. Allows for the individual setting of properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Long id;

        private Long verbId;

        private String tense;

        private String firstPersonSingular;

        private String secondPersonSingular;

        private String thirdPersonSingular;

        private String firstPersonPlural;

        private String secondPersonPlural;

        private String thirdPersonPlural;

        private String status;

        private Date timeCreated;

        private Date timeUpdated;

        private Long createdBy;

        private Long modifiedBy;

        /**
         * Default constructor. Allows for building a {@link Conjugation} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link Conjugation} starting with initial values set from the specified template
         * {@link Conjugation}.
         * 
         * @param template A {@link Conjugation} from which to get the initial values for building a new
         *            {@link Conjugation}.
         */
        private Builder(Conjugation template) {
            this.id = template.getId();
            this.verbId = template.getVerbId();
            this.tense = template.getTense();
            this.firstPersonSingular = template.getFirstPersonSingular();
            this.secondPersonSingular = template.getSecondPersonSingular();
            this.thirdPersonSingular = template.getThirdPersonSingular();
            this.firstPersonPlural = template.getFirstPersonPlural();
            this.secondPersonPlural = template.getSecondPersonPlural();
            this.thirdPersonPlural = template.getThirdPersonPlural();
            this.status = template.getStatus();
            this.timeCreated = template.getTimeCreated();
            this.timeUpdated = template.getTimeUpdated();
            this.createdBy = template.getCreatedBy();
            this.modifiedBy = template.getModifiedBy();
        }

        /**
         * Creates a new {@link Conjugation} from the values set on this {@link Builder}.
         * 
         * @return Returns a new {@link Conjugation} from the values set on this {@link Builder}.
         */
        public Conjugation build() {
            return new Conjugation(this);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setVerbId(Long verbId) {
            this.verbId = verbId;
            return this;
        }

        public Builder setTense(String tense) {
            this.tense = tense;
            return this;
        }

        public Builder setFirstPersonSingular(String firstPersonSingular) {
            this.firstPersonSingular = firstPersonSingular;
            return this;
        }

        public Builder setSecondPersonSingular(String secondPersonSingular) {
            this.secondPersonSingular = secondPersonSingular;
            return this;
        }

        public Builder setThirdPersonSingular(String thirdPersonSingular) {
            this.thirdPersonSingular = thirdPersonSingular;
            return this;
        }

        public Builder setFirstPersonPlural(String firstPersonPlural) {
            this.firstPersonPlural = firstPersonPlural;
            return this;
        }

        public Builder setSecondPersonPlural(String secondPersonPlural) {
            this.secondPersonPlural = secondPersonPlural;
            return this;
        }

        public Builder setThirdPersonPlural(String thirdPersonPlural) {
            this.thirdPersonPlural = thirdPersonPlural;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
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

        public Builder setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder setModifiedBy(Long modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

    }

}
