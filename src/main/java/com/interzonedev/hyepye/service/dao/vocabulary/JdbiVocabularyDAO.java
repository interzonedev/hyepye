package com.interzonedev.hyepye.service.dao.vocabulary;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import com.interzonedev.hyepye.model.Vocabulary;

/**
 * JDBI specific data access API for the vocabulary table.
 * 
 * @author mmarkarian
 */
@RegisterMapper(VocabularyMapper.class)
public interface JdbiVocabularyDAO extends VocabularyDAO, Transactional<JdbiVocabularyDAO> {

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.vocabulary.VocabularyDAO#getVocabularyById(java.lang.Long)
     */
    @Override
    @SqlQuery("SELECT vocabulary_id, armenian, english, vocabulary_type, status, time_created, time_updated, "
            + "created_by, modified_by FROM vocabulary WHERE vocabulary_id = :id")
    Vocabulary getVocabularyById(@Bind("id") Long id);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.vocabulary.VocabularyDAO#getVocabularyByArmenian(java.lang.String)
     */
    @Override
    @SqlQuery("SELECT vocabulary_id, armenian, english, vocabulary_type, status, time_created, time_updated, "
            + "created_by, modified_by FROM vocabulary WHERE armenian = :armenian")
    Vocabulary getVocabularyByArmenian(@Bind("armenian") String armenian);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.vocabulary.VocabularyDAO#createVocabulary(com.interzonedev.hyepye.model.
     * Vocabulary, java.lang.Long)
     */
    @Override
    @SqlUpdate("INSERT INTO vocabulary (armenian, english, vocabulary_type, status, created_by, modified_by) "
            + "VALUES (:armenian, :english, :vocabularyType, :status, :userId, :userId)")
    @GetGeneratedKeys
    long createVocabulary(@BindVocabulary Vocabulary vocabulary, @Bind("userId") Long userId);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.vocabulary.VocabularyDAO#updateVocabulary(com.interzonedev.hyepye.model.
     * Vocabulary, java.lang.Long)
     */
    @Override
    @SqlUpdate("UPDATE vocabulary SET armenian = :armenian, english = :english, vocabulary_type = :vocabularyType, "
            + "status = :status, modified_by = :userId WHERE vocabulary_id = :id")
    int updateVocabulary(@BindVocabulary Vocabulary vocabulary, @Bind("userId") Long userId);

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.dao.vocabulary.VocabularyDAO#deactivateVocabulary(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    @SqlUpdate("UPDATE vocabulary SET active = FALSE, modified_by = :userId WHERE vocabulary_id = :id")
    int deactivateVocabulary(@Bind("id") Long id, @Bind("userId") Long userId);

}
