package com.interzonedev.hyepye.service.repository;

import java.util.List;

import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.ValidationException;

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
    public List<User> getAllUsers();

    /**
     * Gets the {@link User} with the specified ID.
     * 
     * @param id The ID of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified ID.
     * 
     * @throws ValidationException Thrown if the specified ID not a positive integer.
     */
    public User getUserById(Long id) throws ValidationException;

    /**
     * Gets the {@link User} with the specified name.
     * 
     * @param name The name of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified name.
     * 
     * @throws ValidationException Thrown if the specified name is not set.
     */
    public User getUserByName(String name) throws ValidationException;

    /**
     * Creates a new {@link User} by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to create.
     * 
     * @return Returns the newly created {@link User} with identity and timestamps set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid.
     */
    public User createUser(User user) throws ValidationException;

    /**
     * Updates the database by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to update.
     * 
     * @return Returns the updated {@link User} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if the specified {@link User} is invalid.
     */
    public User updateUser(User user) throws ValidationException;

    /**
     * Makes the {@link User} with the specified ID inactive.
     * 
     * @param id The ID of the {@link User} to deactivate.
     * 
     * @return Returns the deactivated {@link User} with the updated timestamp set.
     * 
     * @throws ValidationException Thrown if the specified ID not a positive integer.
     */
    public User deactivateUser(Long id) throws ValidationException;

}
