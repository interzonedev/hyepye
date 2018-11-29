package com.interzonedev.hyepye.service.dao.user;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import com.interzonedev.hyepye.model.User;

/**
 * An extention of the JDBI {@link BindingAnnotation} annotation that handles binding the properties of a {@link User}
 * to SQL query fields.
 * 
 * @author mmarkarian
 */
@BindingAnnotation(BindUser.UserBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface BindUser {

    /**
     * A {@link BinderFactory} implementation specific to the {@link User} model.
     * 
     * @author mmarkarian
     */
    class UserBinderFactory implements BinderFactory {

        /**
         * Creates a {@link Binder} that transforms the properties of a {@link User} to specific SQL query fields
         * values.
         */
        @Override
        public Binder<BindUser, User> build(Annotation annotation) {

            return new Binder<BindUser, User>() {

                /**
                 * Binds the values of the specified {@link User} onto the SQL query represented by the specified
                 * {@link SQLStatement}.
                 * 
                 * @param statement The {@link SQLStatement} instance that represents the SQL query to be executed with
                 *            values bound from the specified {@link User}.
                 * @param bindUser The {@link BindUser} annotation on the {@link User} instance.
                 * @param user The {@link User} instance that holds the values to bind onto the SQL query.
                 */
                @Override
                public void bind(SQLStatement<?> statement, BindUser bindUser, User user) {

                    statement.bind("id", user.getId());
                    statement.bind("username", user.getUsername());
                    statement.bind("passwordHash", user.getPasswordHash());
                    statement.bind("email", user.getEmail());
                    statement.bind("firstName", user.getFirstName());
                    statement.bind("lastName", user.getLastName());
                    statement.bind("role", user.getRole().getRoleName());
                    statement.bind("active", user.isActive());

                }

            };

        }

    }

}
