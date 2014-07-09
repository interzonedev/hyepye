package com.interzonedev.hyepye.service.repository.vocabulary;

import java.util.List;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;

/**
 * API for retrieving and persisting {@link Vocabulary} instances.
 * 
 * @author mmarkarian
 */
public interface VocabularyRepository {

    /**
     * Gets a {@link List} of all {@link Vocabulary} instances.
     * 
     * @return Returns a {@link List} of all {@link Vocabulary} instances.
     */
    public List<Vocabulary> getAllVocabularies();

    /**
     * Gets the {@link Vocabulary} with the specified ID.
     * 
     * @param id The ID of the {@link Vocabulary} to retrieve.
     * 
     * @return Returns the {@link Vocabulary} with the specified ID.
     * 
     * @throws ValidationException Thrown if the specified ID not a positive integer.
     */
    public Vocabulary getVocabularyById(Long id) throws ValidationException;

    /**
     * Gets a {@link List} of all {@link Vocabulary} instances with an English definition containing the specified text
     * fragment.
     * 
     * @param englishFragment The fragment of text to be search against the English definitions of all
     *            {@link Vocabulary} instances,
     * 
     * @return Returns a {@link List} of all {@link Vocabulary} instances with an English definition containing the
     *         specified text fragment.
     * 
     * @throws ValidationException Thrown if the English text fragment is not set.
     */
    public List<Vocabulary> getVocabularyWithEnglishContaining(String englishFragment) throws ValidationException;

    /**
     * Gets a {@link List} of all {@link Vocabulary} instances with an Armenian definition containing the specified text
     * fragment.
     * 
     * @param armenianFragment The fragment of text to be search against the Armenian definitions of all
     *            {@link Vocabulary} instances,
     * 
     * @return Returns a {@link List} of all {@link Vocabulary} instances with an Armenian definition containing the
     *         specified text fragment.
     * 
     * @throws ValidationException Thrown if the Armenian text fragment is not set.
     */
    public List<Vocabulary> getVocabularyWithArmenianContaining(String armenianFragment) throws ValidationException;

    /**
     * Creates a new {@link Vocabulary} by persisting the specified {@link Vocabulary}.
     * 
     * @param vocabulary The {@link Vocabulary} to create.
     * @param userId The ID of the {@link User} creating the {@link Vocabulary}.
     * 
     * @return Returns the newly created {@link Vocabulary} with identity and timestamps set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid or if the specified {@link User} ID
     *             is not a positive integer.
     */
    public Vocabulary createVocabulary(Vocabulary vocabulary, Long userId) throws ValidationException;

    /**
     * Updates the database by persisting the specified {@link Vocabulary}.
     * 
     * @param vocabulary The {@link Vocabulary} to update.
     * @param userId The ID of the {@link User} updating the {@link Vocabulary}.
     * 
     * @return Returns the updated {@link Vocabulary} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid or if the specified {@link User} ID
     *             is not a positive integer.
     */
    public Vocabulary updateVocabulary(Vocabulary vocabulary, Long userId) throws ValidationException;

    /**
     * Makes the {@link Vocabulary} with the specified ID inactive.
     * 
     * @param id The ID of the {@link Vocabulary} to deactivate.
     * @param userId The ID of the {@link User} deactivating the {@link Vocabulary}.
     * 
     * @return Returns the deactivated {@link Vocabulary} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if either the specified ID or {@link User} ID is not a positive integer.
     */
    public Vocabulary deactivateVocabulary(Long id, Long userId) throws ValidationException;

}
