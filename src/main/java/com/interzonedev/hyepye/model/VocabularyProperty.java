package com.interzonedev.hyepye.model;

/**
 * Enumeration that identifies {@link Vocabulary} properties.
 * 
 * @author mmarkarian
 */
public enum VocabularyProperty {

    ID("vocabulary_id"), ARMENIAN("armenian"), ENGLISH("english"), VOCABULARY_TYPE("vocabulary_type"), STATUS("status"), TIME_CREATED(
            "time_created"), TIME_UPDATED("time_updated"), CREATED_BY("created_by"), MODIFIED_BY("modified_by");

    private final String vocabularyColumnName;

    private VocabularyProperty(String vocabularyColumnName) {
        this.vocabularyColumnName = vocabularyColumnName;
    }

    public String getVocabularyColumnName() {
        return vocabularyColumnName;
    }

    /**
     * Gets the {@link VocabularyProperty} with the specified column name.
     * 
     * @param vocabularyColumnName The column name of the {@link VocabularyProperty} to get.
     * 
     * @return Returns the {@link VocabularyProperty} with the specified column name or null if the specified vocabulary
     *         column name does not correspond to any definied {@link VocabularyProperty} values.
     */
    public static VocabularyProperty fromVocabularyColumnName(String vocabularyColumnName) {

        for (VocabularyProperty vocabularyProperty : VocabularyProperty.values()) {
            if (vocabularyProperty.getVocabularyColumnName().equals(vocabularyColumnName)) {
                return vocabularyProperty;
            }
        }

        return null;
    }

}
