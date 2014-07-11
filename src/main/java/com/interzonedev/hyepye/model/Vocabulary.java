package com.interzonedev.hyepye.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;

/**
 * Domain model class for vocabulary words.
 * 
 * @author mmarkarian
 */
public class Vocabulary implements Serializable {

    private static final long serialVersionUID = 9102738656862208975L;

    @Min(1L)
    private final Long id;

    @NotBlank
    @Size(max = 255)
    private final String armenian;

    @NotBlank
    @Size(max = 255)
    private final String english;

    @NotNull
    private final VocabularyType vocabularyType;

    @NotNull
    private final Status status;

    private final Date timeCreated;

    private final Date timeUpdated;

    @Min(1L)
    private final Long createdBy;

    @Min(1L)
    private final Long modifiedBy;

    /**
     * Creates a new {@link Vocabulary} from the values set on the specified {@link Builder}.
     * 
     * @param builder The {@link Builder} that holds the values for the {@link Vocabulary} to create.
     */
    private Vocabulary(Builder builder) {
        this.id = builder.id;
        this.armenian = builder.armenian;
        this.english = builder.english;
        this.vocabularyType = builder.vocabularyType;
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
     * Gets a {@link Builder} with initial values set from the specified template {@link Vocabulary}
     * 
     * @param template A {@link Vocabulary} from which to get the initial values for the new {@link Builder}.
     * 
     * @return Returns a {@link Builder} with initial values set from the specified template {@link Vocabulary}
     */
    public static Builder newBuilder(Vocabulary template) {
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

    public VocabularyType getVocabularyType() {
        return vocabularyType;
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

        if (!(obj instanceof Vocabulary)) {
            return false;
        }

        Vocabulary that = (Vocabulary) obj;

        return Objects.equal(getId(), that.getId()) && Objects.equal(getArmenian(), that.getArmenian())
                && Objects.equal(getEnglish(), that.getEnglish())
                && Objects.equal(getVocabularyType(), that.getVocabularyType())
                && Objects.equal(getStatus(), that.getStatus())
                && Objects.equal(getTimeCreated(), that.getTimeCreated())
                && Objects.equal(getTimeUpdated(), that.getTimeUpdated())
                && Objects.equal(getCreatedBy(), that.getCreatedBy())
                && Objects.equal(getModifiedBy(), that.getModifiedBy());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getArmenian(), getEnglish(), getVocabularyType(), getStatus(),
                getTimeCreated(), getTimeUpdated(), getCreatedBy(), getModifiedBy());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass().getName() + "@" + Integer.toHexString(hashCode())).add("id", getId())
                .add("armenian", getArmenian()).add("english", getEnglish()).add("vocabularyType", getVocabularyType())
                .add("status", getStatus()).add("timeCreated", getTimeCreated()).add("timeUpdated", getTimeUpdated())
                .add("createdBy", getCreatedBy()).add("modifiedBy", getModifiedBy()).toString();
    }

    /**
     * Mutable builder for creating instances of {@link Vocabulary}. Allows for the individual setting of properties.
     * 
     * @author mmarkarian
     */
    public static class Builder {

        private Long id;
        private String armenian;
        private String english;
        private VocabularyType vocabularyType;
        private Status status;
        private Date timeCreated;
        private Date timeUpdated;
        private Long createdBy;
        private Long modifiedBy;

        /**
         * Default constructor. Allows for building a {@link Vocabulary} starting with default values.
         */
        private Builder() {
        }

        /**
         * Allows for building a {@link Vocabulary} starting with initial values set from the specified template
         * {@link Vocabulary}.
         * 
         * @param template A {@link Vocabulary} from which to get the initial values for building a new
         *            {@link Vocabulary}.
         */
        private Builder(Vocabulary template) {
            this.id = template.getId();
            this.armenian = template.getArmenian();
            this.english = template.getEnglish();
            this.vocabularyType = template.getVocabularyType();
            this.status = template.getStatus();
            this.timeCreated = template.getTimeCreated();
            this.timeUpdated = template.getTimeUpdated();
            this.createdBy = template.getCreatedBy();
            this.modifiedBy = template.getModifiedBy();
        }

        /**
         * Creates a new {@link Vocabulary} from the values set on this {@link Builder}.
         * 
         * @return Returns a new {@link Vocabulary} from the values set on this {@link Builder}.
         */
        public Vocabulary build() {
            return new Vocabulary(this);
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

        public Builder setVocabularyType(VocabularyType vocabularyType) {
            this.vocabularyType = vocabularyType;
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
