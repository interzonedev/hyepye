package com.interzonedev.hyepye.service.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.google.common.base.Strings;
import com.interzonedev.hyepye.model.Role;
import com.interzonedev.hyepye.model.User;

/**
 * Implementation of {@link ResultSetMapper} that creates {@link User} instances.
 * 
 * @author mmarkarian
 */
public class UserMapper implements ResultSetMapper<User> {

    /**
     * Creates a {@link User} instance with properties set from the JDBC {@link ResultSet}.
     * 
     * @param index The zero based count of the current row from the overall query results.
     * @param rs The JDBC {@link ResultSet} query results.
     * @param ctx The JDBI {@link StatementContext} for the query that generated the JDBC {@link ResultSet}.
     * 
     * @return Returns A {@link User} instance with properties set from the JDBC {@link ResultSet}.
     * 
     * @throws SQLException Thrown if there was an error reading from the JDBC {@link ResultSet}.
     */
    @Override
    public User map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

        User.Builder user = User.newBuilder();

        user.setId(rs.getLong("hp_user_id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));

        if (!Strings.isNullOrEmpty(rs.getString("first_name"))) {
            user.setFirstName(rs.getString("first_name"));
        }

        if (!Strings.isNullOrEmpty(rs.getString("last_name"))) {
            user.setLastName(rs.getString("last_name"));
        }

        user.setRole(Role.fromRoleName(rs.getString("role")));
        user.setActive(rs.getBoolean("active"));
        user.setTimeCreated(rs.getTimestamp("time_created"));
        user.setTimeUpdated(rs.getTimestamp("time_updated"));

        return user.build();

    }

}
