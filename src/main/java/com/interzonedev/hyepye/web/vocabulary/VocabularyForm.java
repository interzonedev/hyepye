package com.interzonedev.hyepye.web.vocabulary;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VocabularyForm {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String armenian;

    @NotBlank
    @Size(max = 255)
    private String english;

    @NotBlank
    private String vocabularyTypeValue;

    @NotBlank
    private String statusValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArmenian() {
        return armenian;
    }

    public void setArmenian(String armenian) {
        this.armenian = armenian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVocabularyTypeValue() {
        return vocabularyTypeValue;
    }

    public void setVocabularyTypeValue(String vocabularyTypeValue) {
        this.vocabularyTypeValue = vocabularyTypeValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

}
