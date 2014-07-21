package com.interzonedev.hyepye.service.repository;

/**
 * Enumeration that identifies the type of definition search to perform.
 * 
 * @author mmarkarian
 */
public enum DefinitionSearchType {

    FULL_WORD("Full Word"), STARTS_WITH("Starts With"), CONTAINS("Contains");

    private final String definitionSearchTypeName;

    private DefinitionSearchType(String definitionSearchTypeName) {
        this.definitionSearchTypeName = definitionSearchTypeName;
    }

    public String getDefinitionSearchTypeName() {
        return definitionSearchTypeName;
    }

    /**
     * Gets the {@link DefinitionSearchType} with the specified name.
     * 
     * @param definitionSearchTypeName The name of the {@link DefinitionSearchType} to get.
     * 
     * @return Returns the {@link DefinitionSearchType} with the specified name or null if the specified definition
     *         search type name does not correspond to any definied {@link DefinitionSearchType} values.
     */
    public static DefinitionSearchType fromDefinitionSearchTypeName(String definitionSearchTypeName) {

        for (DefinitionSearchType definitionSearchType : DefinitionSearchType.values()) {
            if (definitionSearchType.getDefinitionSearchTypeName().equals(definitionSearchTypeName)) {
                return definitionSearchType;
            }
        }

        return null;
    }

}
