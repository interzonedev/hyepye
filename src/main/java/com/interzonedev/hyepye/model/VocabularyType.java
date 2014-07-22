package com.interzonedev.hyepye.model;

/**
 * Enumeration that identifies {@link Vocabulary} types.
 * 
 * @author mmarkarian
 */
public enum VocabularyType {

    NOUN("noun"), PHRASE("phrase"), DAY("day"), MONTH("month"), NUMBER("number"), MATHEMATICS("mathematics"), COLOR(
            "color");

    private final String vocabularyTypeName;

    private VocabularyType(String vocabularyTypeName) {
        this.vocabularyTypeName = vocabularyTypeName;
    }

    public String getVocabularyTypeName() {
        return vocabularyTypeName;
    }

    /**
     * Gets the {@link VocabularyType} with the specified name.
     * 
     * @param vocabularyTypeName The name of the {@link VocabularyType} to get.
     * 
     * @return Returns the {@link VocabularyType} with the specified name or null if the specified vocabulary type name
     *         does not correspond to any definied {@link VocabularyType} values.
     */
    public static VocabularyType fromVocabularyTypeName(String vocabularyTypeName) {

        for (VocabularyType vocabularyType : VocabularyType.values()) {
            if (vocabularyType.getVocabularyTypeName().equals(vocabularyTypeName)) {
                return vocabularyType;
            }
        }

        return null;
    }

}
