package com.interzonedev.hyepye.service.dao.user;

import java.util.List;

import com.interzonedev.hyepye.model.User;

/**
 * Data access API for the hp_user table.
 * 
 * @author mmarkarian
 */
public interface UserDAO {

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
     */
    User getUserById(Long id);

    /**
     * Gets the {@link User} with the specified name.
     * 
     * @param name The name of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified name.
     */
    User getUserByName(String name);

    /**
     * Gets the {@link User} with the specified email.
     * 
     * @param email The email of the {@link User} to retrieve.
     * 
     * @return Returns the {@link User} with the specified email.
     */
    User getUserByEmail(String email);

    /**
     * Creates a new {@link User} by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to create.
     * 
     * @return Returns the ID of the newly created {@link User}.
     */
    long createUser(User user);

    /**
     * Updates the database by persisting the specified {@link User}.
     * 
     * @param user The {@link User} to update.
     * 
     * @return Returns the number of updated rows.
     */
    int updateUser(User user);

    /**
     * Makes the {@link User} with the specified ID inactive.
     * 
     * @param id The ID of the {@link User} to deactivate.
     * 
     * @return Returns the number of updated rows.
     */
    int deactivateUser(Long id);

}
