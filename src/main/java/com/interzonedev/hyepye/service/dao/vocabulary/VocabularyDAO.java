package com.interzonedev.hyepye.service.dao.vocabulary;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;

/**
 * Data access API for the vocabulary table.
 * 
 * @author mmarkarian
 */
public interface VocabularyDAO {

    /**
     * Gets the {@link Vocabulary} with the specified ID.
     * 
     * @param id The ID of the {@link Vocabulary} to retrieve.
     * 
     * @return Returns the {@link Vocabulary} with the specified ID.
     */
    public Vocabulary getVocabularyById(Long id);

    /**
     * Gets the {@link Vocabulary} with the specified Armenian definition.
     * 
     * @param armenian The Armenian definition of the {@link Vocabulary} to retrieve.
     * 
     * @return Returns the {@link Vocabulary} with the specified Armenian definition.
     */
    public Vocabulary getVocabularyByArmenian(String armenian);

    /**
     * Creates a new {@link Vocabulary} by persisting the specified {@link Vocabulary}.
     * 
     * @param vocabulary The {@link Vocabulary} to create.
     * @param userId The ID of the {@link User} creating the {@link Vocabulary}.
     * 
     * @return Returns the ID of the newly created {@link Vocabulary}.
     */
    public long createVocabulary(Vocabulary vocabulary, Long userId);

    /**
     * Updates the database by persisting the specified {@link Vocabulary}.
     * 
     * @param vocabulary The {@link Vocabulary} to update.
     * @param userId The ID of the {@link User} updating the {@link Vocabulary}.
     * 
     * @return Returns the number of updated rows.
     */
    public int updateVocabulary(Vocabulary vocabulary, Long userId);

    /**
     * Makes the {@link Vocabulary} with the specified ID inactive.
     * 
     * @param id The ID of the {@link Vocabulary} to deactivate.
     * @param userId The ID of the {@link User} deactivating the {@link Vocabulary}.
     * 
     * @return Returns the number of updated rows.
     */
    public int deactivateVocabulary(Long id, Long userId);

}
