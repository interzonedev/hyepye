package com.interzonedev.hyepye.service.repository.vocabulary;

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
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.service.ValidationException;
import com.interzonedev.hyepye.service.dao.vocabulary.JdbiVocabularyDAO;
import com.interzonedev.hyepye.service.repository.DuplicateModelException;

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

        log.debug("createVocabulary: Start - vocabulary = " + vocabulary);

        validateVocabulary(vocabulary, userId, true);

        Vocabulary vocabularyOut = dbi.inTransaction(new TransactionCallback<Vocabulary>() {

            @Override
            public Vocabulary inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

                validateDuplicateVocabularies(vocabulary, vocabularyDAO);

                long id = vocabularyDAO.createVocabulary(vocabulary, userId);

                Vocabulary createdVocabulary = vocabularyDAO.getVocabularyById(id);

                return createdVocabulary;

            }

        });

        log.debug("createVocabulary: Returning - vocabularyOut  = " + vocabularyOut);

        return vocabularyOut;

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

        log.debug("updateVocabulary: Start - vocabulary = " + vocabulary);

        validateVocabulary(vocabulary, userId, false);

        Vocabulary vocabularyOut = dbi.inTransaction(new TransactionCallback<Vocabulary>() {

            @Override
            public Vocabulary inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

                validateDuplicateVocabularies(vocabulary, vocabularyDAO);

                int numUpdatedRows = vocabularyDAO.updateVocabulary(vocabulary, userId);

                Vocabulary updatedVocabulary = null;

                if (1 == numUpdatedRows) {
                    updatedVocabulary = vocabularyDAO.getVocabularyById(vocabulary.getId());
                }

                return updatedVocabulary;

            }

        });

        log.debug("updateVocabulary: Returning - vocabularyOut  = " + vocabularyOut);

        return vocabularyOut;

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

        log.debug("deactivateVocabulary: id = " + id);

        if (id <= 0L) {
            throw new ValidationException("The vocabulary id must be a positive integer");
        }

        Vocabulary vocabularyOut = dbi.inTransaction(new TransactionCallback<Vocabulary>() {

            @Override
            public Vocabulary inTransaction(Handle handle, TransactionStatus status) throws Exception {

                JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

                Vocabulary vocabularyToDeactivate = vocabularyDAO.getVocabularyById(id);
                if (null == vocabularyToDeactivate) {
                    throw new ValidationException("The vocabulary to delete doesn't exist");
                }

                int numUpdatedRows = vocabularyDAO.deactivateVocabulary(id, userId);

                Vocabulary deactivatedVocabulary = null;

                if (1 == numUpdatedRows) {
                    deactivatedVocabulary = vocabularyDAO.getVocabularyById(id);
                }

                return deactivatedVocabulary;

            }

        });

        log.debug("deactivateVocabulary: Returning - vocabularyOut  = " + vocabularyOut);

        return vocabularyOut;

    }

    /**
     * Validates the specified {@link Vocabulary}. If creating the ID is allowed to be null.
     * 
     * @param vocabulary The {@link Vocabulary} to validate.
     * @param userId The ID of the {@link User} performing the action.
     * @param creating Whether or not the specified {@link Vocabulary} is being created.
     * 
     * @throws ValidationException Thrown if the specified {@link Vocabulary} or {@link User} ID is invalid.
     */
    private void validateVocabulary(Vocabulary vocabulary, Long userId, boolean creating) throws ValidationException {

        if (null == vocabulary) {
            throw new ValidationException("The vocabulary must be set");
        }

        if (userId <= 0L) {
            throw new ValidationException("The user id must be a positive integer");
        }

        Set<ConstraintViolation<Vocabulary>> errors = jsr303Validator.validate(vocabulary);

        if (!errors.isEmpty()) {
            throw new InvalidVocabularyException(errors);
        }

    }

    /**
     * Checks whether or not the specified {@link Vocabulary} violates any uniqueness constraints of the vocabulary
     * table.
     * 
     * @param vocabulary The {@link Vocabulary} to validate.
     * @param vocabularyDAO The {@link JdbiVocabularyDAO} instance attached to the currently active transaction on the
     *            vocabulary table.
     * 
     * @throws DuplicateModelException Thrown if the specified {@link Vocabulary} violates any uniqueness constraints of
     *             the vocabulary table.
     */
    private void validateDuplicateVocabularies(Vocabulary vocabulary, JdbiVocabularyDAO vocabularyDAO)
            throws DuplicateModelException {

        Vocabulary vocabularyWithSameArmenian = vocabularyDAO.getVocabularyByArmenian(vocabulary.getArmenian());
        if (null != vocabularyWithSameArmenian) {
            throw new DuplicateModelException("A vocabulary with the same Armenian defintion already exists.");
        }

    }

    private JdbiVocabularyDAO getVocabularyDAO() {
        return dbi.onDemand(JdbiVocabularyDAO.class);
    }

}
