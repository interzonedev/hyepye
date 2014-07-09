package com.interzonedev.hyepye.service.repository.vocabulary;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validator;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.dao.vocabulary.JdbiVocabularyDAO;

/**
 * JDBI specific API for retrieving and persisting {@link Vocabulary} instances.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.vocabularyRepository")
public class JdbiVocabularyRepository implements VocabularyRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbiVocabularyRepository.class);

    private final DBI dbi;

    private final Validator jsr303Validator;

    @Inject
    public JdbiVocabularyRepository(@Named("hyepye.service.dbi") DBI dbi,
            @Named("hyepye.service.jsr303Validator") Validator jsr303Validator) {
        this.dbi = dbi;
        this.jsr303Validator = jsr303Validator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#getAllVocabularies()
     */
    @Override
    public List<Vocabulary> getAllVocabularies() {

        log.debug("getAllVocabularies: Start");

        List<Vocabulary> vocabularies = getVocabularyDAO().getAllVocabularies();

        log.debug("getAllVocabularies: Returning - vocabularies  = " + vocabularies);

        return vocabularies;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#getVocabularyById(java.lang.Long)
     */
    @Override
    public Vocabulary getVocabularyById(Long id) throws ValidationException {

        log.debug("getVocabularyById: id = " + id);

        if (id <= 0L) {
            throw new ValidationException("The vocabulary id must be a positive integer");
        }

        Vocabulary vocabulary = getVocabularyDAO().getVocabularyById(id);

        log.debug("getVocabularyById: Returning - vocabulary  = " + vocabulary);

        return vocabulary;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#getVocabularyWithEnglishContaining
     * (java.lang.String)
     */
    @Override
    public List<Vocabulary> getVocabularyWithEnglishContaining(String englishFragment) throws ValidationException {

        log.debug("getVocabularyWithEnglishContaining: englishFragment = " + englishFragment);

        if (Strings.isNullOrEmpty(englishFragment)) {
            throw new ValidationException("The English text fragment id must be set");
        }

        List<Vocabulary> vocabularies = getVocabularyDAO().getVocabularyWithEnglishContaining(englishFragment);

        log.debug("getVocabularyWithEnglishContaining: Returning - vocabularies  = " + vocabularies);

        return vocabularies;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#getVocabularyWithArmenianContaining
     * (java.lang.String)
     */
    @Override
    public List<Vocabulary> getVocabularyWithArmenianContaining(String armenianFragment) throws ValidationException {

        log.debug("getVocabularyWithArmenianContaining: armenianFragment = " + armenianFragment);

        if (Strings.isNullOrEmpty(armenianFragment)) {
            throw new ValidationException("The Armenian text fragment id must be set");
        }

        List<Vocabulary> vocabularies = getVocabularyDAO().getVocabularyWithArmenianContaining(armenianFragment);

        log.debug("getVocabularyWithArmenianContaining: Returning - vocabularies  = " + vocabularies);

        return vocabularies;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#createVocabulary(com.interzonedev.
     * hyepye.model.Vocabulary, java.lang.Long)
     */
    @Override
    public Vocabulary createVocabulary(Vocabulary vocabulary, Long userId) throws ValidationException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#updateVocabulary(com.interzonedev.
     * hyepye.model.Vocabulary, java.lang.Long)
     */
    @Override
    public Vocabulary updateVocabulary(Vocabulary vocabulary, Long userId) throws ValidationException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#deactivateVocabulary(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public Vocabulary deactivateVocabulary(Long id, Long userId) throws ValidationException {
        // TODO Auto-generated method stub
        return null;
    }

    private JdbiVocabularyDAO getVocabularyDAO() {
        return dbi.onDemand(JdbiVocabularyDAO.class);
    }

}
