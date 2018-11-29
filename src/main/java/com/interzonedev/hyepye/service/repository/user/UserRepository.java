package com.interzonedev.hyepye.service.repository.user;

import java.util.List;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.hyepye.model.User;

/**
 * API for retrieving and persisting {@link User}s.
 * 
 * @author mmarkarian
 */
public interface UserRepository {

    /**
     * Gets a {@link List} of all {@link User}s.
     * 
     * @return Returns a {@link List} of all {@link User}s.
     */
    List<User> getAllUsers();

    /**
     * Gets the {@link User} with the specified ID.
     * 
     * @param id The ID of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified ID.
     * 
     * @throws ValidationException Thrown if the specified ID not a positive integer.
     */
    User getUserById(Long id);

    /**
     * Gets the {@link User} with the specified name.
     * 
     * @param name The name of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified name.
     * 
     * @throws ValidationException Thrown if the specified name is not set.
     */
    User getUserByName(String name);

    /**
     * Creates a new {@link User} by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to create.
     * 
     * @return Returns the newly created {@link User} with identity and timestamps set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid.
     */
    User createUser(User user);

    /**
     * Updates the database by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to update.
     * 
     * @return Returns the updated {@link User} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid.
     */
    User updateUser(User user);

    /**
     * Makes the {@link User} with the specified ID inactive.
     * 
     * @param id The ID of the {@link User} to deactivate.
     * 
     * @return Returns the deactivated {@link User} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if the specified ID not a positive integer.
     */
    User deactivateUser(Long id);

}
