package com.interzonedev.hyepye.service.dao.user;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import com.interzonedev.hyepye.model.User;

/**
 * JDBI specific data access API for the hp_user table.
 * 
 * @author mmarkarian
 */
@RegisterMapper(UserMapper.class)
public interface JdbiUserDAO extends UserDAO, Transactional<JdbiUserDAO> {

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#getAllUsers()
     */
    @Override
    @SqlQuery("SELECT hp_user_id, username, password_hash, email, first_name, last_name, role, active, "
            + "time_created, time_updated FROM hp_user")
    public List<User> getAllUsers();

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#getUserById(java.lang.Long)
     */
    @Override
    @SqlQuery("SELECT hp_user_id, username, password_hash, email, first_name, last_name, role, active, "
            + "time_created, time_updated FROM hp_user WHERE hp_user_id = :id")
    public User getUserById(@Bind("id") Long id);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#getUserByName(java.lang.String)
     */
    @Override
    @SqlQuery("SELECT hp_user_id, username, password_hash, email, first_name, last_name, role, active, "
            + "time_created, time_updated FROM hp_user WHERE username = :name")
    public User getUserByName(@Bind("name") String name);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#getUserByEmail(java.lang.String)
     */
    @Override
    @SqlQuery("SELECT hp_user_id, username, password_hash, email, first_name, last_name, role, active, "
            + "time_created, time_updated FROM hp_user WHERE email = :email")
    public User getUserByEmail(@Bind("email") String email);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#createUser(com.interzonedev.hyepye.model.User)
     */
    @Override
    @SqlUpdate("INSERT INTO hp_user (username, password_hash, email, first_name, last_name, role, active) "
            + "VALUES (:username, :passwordHash, :email, :firstName, :lastName, :role, :active)")
    @GetGeneratedKeys
    public long createUser(@BindUser User user);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#updateUser(com.interzonedev.hyepye.model.User)
     */
    @Override
    @SqlUpdate("UPDATE hp_user SET username = :username, password_hash = :passwordHash, email = :email, "
            + "first_name = :firstName, last_name = :lastName, role = :role, active = :active "
            + "WHERE hp_user_id = :id")
    public int updateUser(@BindUser User user);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.UserDAO#deactivateUser(java.lang.Long)
     */
    @Override
    @SqlUpdate("UPDATE hp_user SET active = FALSE WHERE hp_user_id = :id")
    public int deactivateUser(@Bind("id") Long id);

}
