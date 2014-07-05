package com.interzonedev.hyepye.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * Domain model class for verbs.
 * 
 * @author mmarkarian
 */
public class Verb implements Serializable {

    private static final long serialVersionUID = -6403976549535595497L;

    @Min(1L)
    private final Long id;

    @NotEmpty
    @Size(max = 255)
    private final String armenian;

    @NotEmpty
    @Size(max = 255)
    private final String english;

    @NotNull
    private final ConjugationGroup conjugationGroup;

    private final boolean irregular;

    @NotNull
    private final Status status;

    private final Date timeCreated;

    private final Date timeUpdated;

    @Min(1L)
    private final Long createdBy;

    @Min(1L)
    private final Long modifiedBy;

    /**
     * Creates a new {@link Verb} from the values set on the specified {@link Builder}.
     * 
     * @param builder The {@link Builder} that holds the values for the {@link Verb} to create.
     */
    private Verb(Builder builder) {
        this.id = builder.id;
        this.armenian = builder.armenian;
        this.english = builder.english;
        this.conjugationGroup = builder.conjugationGroup;
        this.irregular = builder.irregular;
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
     * Gets a {@link Builder} with initial values set from the specified template {@link Verb}
     * 
     * @param template A {@link Verb} from which to get the initial values for the new {@link Builder}.
     * 
     * @return Returns a {@link Builder} with initial values set from the specified template {@link Verb}
     */
    public static Builder newBuilder(Verb template) {
        return new Builder(template);
    }

    public Long getId() {
        return id;
    }

    public String getArmenian() {
        return armenian;
    }

    public String getEnglish() {
        return english;
    }

    public ConjugationGroup getConjugationGroup() {
        return conjugationGroup;
    }

    public boolean isIrregular() {
        return irregular;
    }

    public Status getStatus() {
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

        if (!(obj instanceof Verb)) {
            return false;
        }

        Verb that = (Verb) obj;

        return Objects.equal(getId(), that.getId()) && Objects.equal(getArmenian(), that.getArmenian())
                && Objects.equal(getEnglish(), that.getEnglish())
                && Objects.equal(getConjugationGroup(), that.getConjugationGroup())
                && Objects.equal(isIrregular(), that.isIrregular()) && Objects.equal(getStatus(), that.getStatus())
                && Objects.equal(getTimeCreated(), that.getTimeCreated())
                && Objects.equal(getTimeUpdated(), that.getTimeUpdated())
                && Objects.equal(getCreatedBy(), that.getCreatedBy())
                && Objects.equal(getModifiedBy(), that.getModifiedBy());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getArmenian(), getEnglish(), getConjugationGroup(), isIrregular(),
                getStatus(), getTimeCreated(), getTimeUpdated(), getCreatedBy(), getModifiedBy());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass().getName() + "@" + Integer.toHexString(hashCode())).add("id", getId())
                .add("armenian", getArmenian()).add("english", getEnglish())
                .add("conjugationGroup", getConjugationGroup()).add("irregular", isIrregular())
                .add("status", getStatus()).add("timeCreated", getTimeCreated()).add("timeUpdated", getTimeUpdated())
                .add("createdBy", getCreatedBy()).add("modifiedBy", getModifiedBy()).toString();
    }

    /**
     * Mutable builder for creating instances of {@link Verb}. Allows for the individual setting of properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Long id;

        private String armenian;

        private String english;

        private ConjugationGroup conjugationGroup;

        private boolean irregular;

        private Status status;

        private Date timeCreated;

        private Date timeUpdated;

        private Long createdBy;

        private Long modifiedBy;

        /**
         * Default constructor. Allows for building a {@link Verb} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link Verb} starting with initial values set from the specified template {@link Verb}.
         * 
         * @param template A {@link Verb} from which to get the initial values for building a new {@link Verb}.
         */
        private Builder(Verb template) {
            this.id = template.getId();
            this.armenian = template.getArmenian();
            this.english = template.getEnglish();
            this.conjugationGroup = template.getConjugationGroup();
            this.irregular = template.isIrregular();
            this.status = template.getStatus();
            this.timeCreated = template.getTimeCreated();
            this.timeUpdated = template.getTimeUpdated();
            this.createdBy = template.getCreatedBy();
            this.modifiedBy = template.getModifiedBy();
        }

        /**
         * Creates a new {@link Verb} from the values set on this {@link Builder}.
         * 
         * @return Returns a new {@link Verb} from the values set on this {@link Builder}.
         */
        public Verb build() {
            return new Verb(this);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setArmenian(String armenian) {
            this.armenian = armenian;
            return this;
        }

        public Builder setEnglish(String english) {
            this.english = english;
            return this;
        }

        public Builder setConjugationGroup(ConjugationGroup conjugationGroup) {
            this.conjugationGroup = conjugationGroup;
            return this;
        }

        public Builder setIrregular(boolean irregular) {
            this.irregular = irregular;
            return this;
        }

        public Builder setStatus(Status status) {
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
