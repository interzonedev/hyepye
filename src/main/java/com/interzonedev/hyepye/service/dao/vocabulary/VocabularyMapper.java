package com.interzonedev.hyepye.service.dao.vocabulary;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyType;

/**
 * Implementation of {@link ResultSetMapper} that creates {@link Vocabulary} instances.
 * 
 * @author mmarkarian
 */
public class VocabularyMapper implements ResultSetMapper<Vocabulary> {

    /**
     * Creates a {@link Vocabulary} instance with properties set from the JDBC {@link ResultSet}.
     * 
     * @param index The zero based count of the current row from the overall query results.
     * @param rs The JDBC {@link ResultSet} query results.
     * @param ctx The JDBI {@link StatementContext} for the query that generated the JDBC {@link ResultSet}.
     * 
     * @return Returns A {@link Vocabulary} instance with properties set from the JDBC {@link ResultSet}.
     * 
     * @throws SQLException Thrown if there was an error reading from the JDBC {@link ResultSet}.
     */
    @Override
    public Vocabulary map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

        Vocabulary.Builder vocabulary = Vocabulary.newBuilder();

        vocabulary.setId(rs.getLong("vocabulary_id"));
        vocabulary.setArmenian(rs.getString("armenian"));
        vocabulary.setEnglish(rs.getString("english"));
        vocabulary.setVocabularyType(VocabularyType.fromVocabularyTypeName(rs.getString("vocabulary_type")));
        vocabulary.setStatus(Status.fromStatusName(rs.getString("status")));
        vocabulary.setTimeCreated(rs.getDate("time_created"));
        vocabulary.setTimeUpdated(rs.getDate("time_updated"));
        vocabulary.setCreatedBy(rs.getLong("created_by"));
        vocabulary.setModifiedBy(rs.getLong("modified_by"));

        return vocabulary.build();

    }

}
