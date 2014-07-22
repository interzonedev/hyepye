package com.interzonedev.hyepye.service.repository.vocabulary;

import java.util.List;

import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;

/**
 * API for retrieving and persisting {@link Vocabulary} instances.
 * 
 * @author mmarkarian
 */
public interface VocabularyRepository {

    /**
     * Gets a {@link List} of all {@link Vocabulary} instances.
     * 
     * @param orderBy The {@link VocabularyProperty} by which to order the results.
     * @param ascending Whether or not the results are ordered in ascending order.
     * @param limit The maximum number of results to return.
     * @param offset The number of results to skip before returning results.
     * 
     * @return Returns a {@link List} of all {@link Vocabulary} instances.
     * 
     * @throws ValidationException Thrown if the search parameters are invalid.
     */
    public List<Vocabulary> getAllVocabularies(VocabularyProperty orderBy, boolean ascending, Long limit, Long offset)
            throws ValidationException;

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
     * Searches all {@link Vocabulary} instances against Armenian definitions.
     * 
     * @param armenian The Armenian definition (either whole or a fragment) against which to search.
     * @param definitionSearchType The type of search to perform.
     * @param vocabularyType The {@link VocabularyType} of {@link Vocabulary} against which to search.
     * @param status The {@link Status} of {@link Vocabulary} against which to search.
     * @param ascending Whether or not the results are ordered in ascending order.
     * @param limit The maximum number of results to return.
     * @param offset The number of results to skip before returning results.
     * 
     * @return Returns a {@link List} of all {@link Vocabulary} instances that meet the search criteria against their
     *         Armenian definitions.
     * 
     * @throws ValidationException Thrown if the search parameters are invalid.
     */
    public List<Vocabulary> searchArmenianVocabulary(String armenian, DefinitionSearchType definitionSearchType,
            VocabularyType vocabularyType, Status status, boolean ascending, Long limit, Long offset)
            throws ValidationException;

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
