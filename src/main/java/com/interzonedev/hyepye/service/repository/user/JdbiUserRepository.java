package com.interzonedev.hyepye.service.repository.user;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.dao.user.JdbiUserDAO;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * JDBI specific API for retrieving and persisting {@link User}s.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.userRepository")
public class JdbiUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbiUserRepository.class);

    private final DBI dbi;

    private final Validator jsr303Validator;

    private final ValidationHelper validationHelper;

    @Inject
    public JdbiUserRepository(@Named("hyepye.service.dbi") DBI dbi,
            @Named("hyepye.service.jsr303Validator") Validator jsr303Validator,
            @Named("hyepye.service.validationHelper") ValidationHelper validationHelper) {
        this.dbi = dbi;
        this.jsr303Validator = jsr303Validator;
        this.validationHelper = validationHelper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#getAllUsers()
     */
    @Override
    public List<User> getAllUsers() {

        log.debug("getAllUsers: Start");

        List<User> users = getUserDAO().getAllUsers();

        log.debug("getAllUsers: Returning - users  = " + users);

        return users;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#getUserById(java.lang.Long)
     */
    @Override
    public User getUserById(Long id) {

        log.debug("getUserById: id = " + id);

        if (id <= 0L) {
            throw new ValidationException(User.MODEL_NAME, "The user id must be a positive integer");
        }

        User user = getUserDAO().getUserById(id);

        log.debug("getUserById: Returning - user  = " + user);

        return user;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#getUserByName(java.lang.String)
     */
    @Override
    public User getUserByName(String name) {

        log.debug("getUserByName: name = " + name);

        if (Strings.isNullOrEmpty(name)) {
            throw new ValidationException(User.MODEL_NAME, "The user name must bet set");
        }

        User user = getUserDAO().getUserByName(name);

        log.debug("getUserByName: Returning - user  = " + user);

        return user;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#createUser(com.interzonedev.hyepye.model.User)
     */
    @Override
    public User createUser(User user) {

        log.debug("createUser: Start - user = " + user);

        validateUser(user, true);

        User userOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

            validateDuplicateUsers(user, userDAO);

            long id = userDAO.createUser(user);

            return userDAO.getUserById(id);
        });

        log.debug("createUser: Returning - userOut  = " + userOut);

        return userOut;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#updateUser(com.interzonedev.hyepye.model.User)
     */
    @Override
    public User updateUser(User user) {

        log.debug("updateUser: Start - user = " + user);

        validateUser(user, false);

        User userOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

            validateDuplicateUsers(user, userDAO);

            int numUpdatedRows = userDAO.updateUser(user);

            User updatedUser = null;

            if (1 == numUpdatedRows) {
                updatedUser = userDAO.getUserById(user.getId());
            }

            return updatedUser;
        });

        log.debug("updateUser: Returning - userOut  = " + userOut);

        return userOut;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.UserRepository#deactivateUser(java.lang.Long)
     */
    @Override
    public User deactivateUser(Long id) {

        log.debug("deactivateUser: id = " + id);

        if (id <= 0L) {
            throw new ValidationException(User.MODEL_NAME, "The user id must be a positive integer");
        }

        User userOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

            User userToDeactivate = userDAO.getUserById(id);
            if (null == userToDeactivate) {
                throw new ValidationException(User.MODEL_NAME, "The user to delete doesn't exist");
            }

            int numUpdatedRows = userDAO.deactivateUser(id);

            User deactivatedUser = null;

            if (1 == numUpdatedRows) {
                deactivatedUser = userDAO.getUserById(id);
            }

            return deactivatedUser;
        });

        log.debug("deactivateUser: Returning - userOut  = " + userOut);

        return userOut;
    }

    /**
     * Validates the specified {@link User}. If creating the ID is allowed to be null.
     * 
     * @param user The {@link User} to validate.
     * @param creating Whether or not the specified {@link User} is being created.
     *
     * @throws if the specified {@link User} is invalid.
     */
    private void validateUser(User user, boolean creating) {

        if (null == user) {
            throw new ValidationException(User.MODEL_NAME, "The user must be set");
        }

        BindingResult errors = validationHelper.validate(jsr303Validator, user, User.MODEL_NAME);

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

    }

    /**
     * Checks whether or not the specified {@link User} violates any uniqueness constraints of the hp_user table.
     * 
     * @param user The {@link User} to validate.
     * @param userDAO The {@link JdbiUserDAO} instance attached to the currently active transaction on the hp_user
     *            table.
     *
     * @throws if the specified {@link User} violates any uniqueness constraints of the hp_user table.
     */
    private void validateDuplicateUsers(User user, JdbiUserDAO userDAO) {

        User userWithSameUsername = userDAO.getUserByName(user.getUsername());
        if (null != userWithSameUsername) {
            throw new ValidationException(User.MODEL_NAME, "A user with the same name already exists.");
        }

        User userWithSameEmail = userDAO.getUserByEmail(user.getEmail());
        if (null != userWithSameEmail) {
            throw new ValidationException(User.MODEL_NAME, "A user with the same email already exists.");
        }

    }

    private JdbiUserDAO getUserDAO() {
        return dbi.onDemand(JdbiUserDAO.class);
    }

}
