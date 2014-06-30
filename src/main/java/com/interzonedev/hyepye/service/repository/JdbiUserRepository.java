package com.interzonedev.hyepye.service.repository;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interzonedev.hyepye.model.User;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User createUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public User deactivateUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    private void validateUser(User user, boolean editing) throws InvalidUserException {
        Set<ConstraintViolation<User>> errors = jsr303Validator.validate(user);

        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }

    }
}
