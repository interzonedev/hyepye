package com.interzonedev.hyepye.web.vocabulary;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class VocabularySearchForm {

    private String english;

    private String englishSearchTypeValue;

    private String armenian;

    private String armenianSearchTypeValue;

    private String vocabularyTypeValue;

    private String statusValue;

    @NotBlank
    private String orderBy;

    private boolean ascending;

    private Integer resultsPerPage;

    @Min(1)
    private Integer requestedPageNumber;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getEnglishSearchTypeValue() {
        return englishSearchTypeValue;
    }

    public void setEnglishSearchTypeValue(String englishSearchTypeValue) {
        this.englishSearchTypeValue = englishSearchTypeValue;
    }

    public String getArmenian() {
        return armenian;
    }

    public void setArmenian(String armenian) {
        this.armenian = armenian;
    }

    public String getArmenianSearchTypeValue() {
        return armenianSearchTypeValue;
    }

    public void setArmenianSearchTypeValue(String armenianSearchTypeValue) {
        this.armenianSearchTypeValue = armenianSearchTypeValue;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public Integer getRequestedPageNumber() {
        return requestedPageNumber;
    }

    public void setRequestedPageNumber(Integer requestedPageNumber) {
        this.requestedPageNumber = requestedPageNumber;
    }

}
