package com.interzonedev.hyepye.service.repository.user;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.dao.user.JdbiUserDAO;
import com.interzonedev.hyepye.service.repository.DuplicateModelException;
import com.interzonedev.hyepye.service.repository.InvalidModelException;

@Named("userRepository")
public class JdbiUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbiUserRepository.class);

    private final DBI dbi;

    private final Validator jsr303Validator;

    @Inject
    public JdbiUserRepository(@Named("dbi") DBI dbi, @Named("jsr303Validator") Validator jsr303Validator) {
        this.dbi = dbi;
        this.jsr303Validator = jsr303Validator;
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
    public User getUserById(Long id) throws ValidationException {

        log.debug("getUserById: id = " + id);

        if (id <= 0L) {
            throw new ValidationException("The user id must be a positive integer");
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
    public User getUserByName(String name) throws ValidationException {

        log.debug("getUserByName: name = " + name);

        if (Strings.isNullOrEmpty(name)) {
            throw new ValidationException("The user name must bet set");
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
    public User createUser(User user) throws ValidationException {

        log.debug("createUser: Start - user = " + user);

        validateUser(user, true);

        User userOut = dbi.inTransaction(new TransactionCallback<User>() {

            @Override
            public User inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

                User userWithSameUsername = userDAO.getUserByName(user.getUsername());
                if (null != userWithSameUsername) {
                    throw new DuplicateModelException("A user with the same name already exists.");
                }

                User userWithSameEmail = userDAO.getUserByEmail(user.getEmail());
                if (null != userWithSameEmail) {
                    throw new DuplicateModelException("A user with the same email already exists.");
                }

                long id = userDAO.createUser(user);

                User createdUser = userDAO.getUserById(id);

                return createdUser;
            }

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
    public User updateUser(User user) throws ValidationException {

        log.debug("updateUser: Start - user = " + user);

        validateUser(user, false);

        User userOut = dbi.inTransaction(new TransactionCallback<User>() {

            @Override
            public User inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

                User userWithSameUsername = userDAO.getUserByName(user.getUsername());
                if (null != userWithSameUsername) {
                    throw new DuplicateModelException("A user with the same name already exists.");
                }

                User userWithSameEmail = userDAO.getUserByEmail(user.getEmail());
                if (null != userWithSameEmail) {
                    throw new DuplicateModelException("A user with the same email already exists.");
                }

                int numUpdatedRows = userDAO.updateUser(user);

                User updatedUser = null;

                if (1 == numUpdatedRows) {
                    updatedUser = userDAO.getUserById(user.getId());
                }

                return updatedUser;

            }

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
    public User deactivateUser(Long id) throws ValidationException {

        log.debug("deactivateUser: id = " + id);

        if (id <= 0L) {
            throw new ValidationException("The user id must be a positive integer");
        }

        User userOut = dbi.inTransaction(new TransactionCallback<User>() {

            @Override
            public User inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiUserDAO userDAO = handle.attach(JdbiUserDAO.class);

                User userToDeactivate = userDAO.getUserById(id);
                if (null == userToDeactivate) {
                    throw new ValidationException("The user to delete doesn't exist");
                }

                int numUpdatedRows = userDAO.deactivateUser(id);

                User deactivatedUser = null;

                if (1 == numUpdatedRows) {
                    deactivatedUser = userDAO.getUserById(id);
                }

                return deactivatedUser;

            }

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
     * @throws ValidationException Thrown if the specified {@link User} is invalid.
     */
    private void validateUser(User user, boolean creating) throws ValidationException {

        if (null == user) {
            throw new ValidationException("The user must be set");
        }

        Set<ConstraintViolation<User>> errors = jsr303Validator.validate(user);

        if (!errors.isEmpty()) {
            throw new InvalidModelException(errors);
        }

    }

    private JdbiUserDAO getUserDAO() {
        return dbi.onDemand(JdbiUserDAO.class);
    }

}
