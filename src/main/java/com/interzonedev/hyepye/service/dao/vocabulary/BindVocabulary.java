package com.interzonedev.hyepye.service.dao.vocabulary;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import com.interzonedev.hyepye.model.Vocabulary;

/**
 * An extention of the JDBI {@link BindingAnnotation} annotation that handles binding the properties of a
 * {@link Vocabulary} to SQL query fields.
 * 
 * @author mmarkarian
 */
@BindingAnnotation(BindVocabulary.VocabularyBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface BindVocabulary {

    /**
     * A {@link BinderFactory} implementation specific to the {@link Vocabulary} model.
     * 
     * @author mmarkarian
     */
    class VocabularyBinderFactory implements BinderFactory {

        /**
         * Creates a {@link Binder} that transforms the properties of a {@link Vocabulary} to specific SQL query fields
         * values.
         */
        @Override
        public Binder<BindVocabulary, Vocabulary> build(Annotation annotation) {

            return new Binder<BindVocabulary, Vocabulary>() {

                /**
                 * Binds the values of the specified {@link Vocabulary} onto the SQL query represented by the specified
                 * {@link SQLStatement}.
                 * 
                 * @param statement The {@link SQLStatement} instance that represents the SQL query to be executed with
                 *            values bound from the specified {@link Vocabulary}.
                 * @param bindVocabulary The {@link BindVocabulary} annotation on the {@link Vocabulary} instance.
                 * @param vocabulary The {@link Vocabulary} instance that holds the values to bind onto the SQL query.
                 */
                @Override
                public void bind(SQLStatement<?> statement, BindVocabulary bindVocabulary, Vocabulary vocabulary) {

                    statement.bind("id", vocabulary.getId());
                    statement.bind("armenian", vocabulary.getArmenian());
                    statement.bind("english", vocabulary.getEnglish());
                    statement.bind("vocabularyType", vocabulary.getVocabularyType().getVocabularyTypeName());
                    statement.bind("status", vocabulary.getStatus().getStatusName());

                }

            };

        }

    }

}
