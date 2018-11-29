package com.interzonedev.hyepye.model;

/**
 * Enumeration that identifies {@link Verb} conjugation groups.
 * 
 * @author mmarkarian
 */
public enum ConjugationGroup {

    ONE("one"), TWO("two"), THREE("three");

    private final String conjugationGroupName;

    ConjugationGroup(String conjugationGroupName) {
        this.conjugationGroupName = conjugationGroupName;
    }

    public String getConjugationGroupName() {
        return conjugationGroupName;
    }

    /**
     * Gets the {@link ConjugationGroup} with the specified name.
     * 
     * @param conjugationGroupName The name of the {@link ConjugationGroup} to get.
     * 
     * @return Returns the {@link ConjugationGroup} with the specified name or null if the specified conjugation group
     *         name name does not correspond to any definied {@link ConjugationGroup} values.
     */
    public static ConjugationGroup fromConjugationGroupName(String conjugationGroupName) {

        for (ConjugationGroup conjugationGroup : ConjugationGroup.values()) {
            if (conjugationGroup.getConjugationGroupName().equals(conjugationGroupName)) {
                return conjugationGroup;
            }
        }

        return null;
    }

}
