package com.interzonedev.hyepye.service.repository.vocabulary;

import com.google.common.base.Strings;
import com.interzonedev.blundr.ValidationException;
import com.interzonedev.blundr.ValidationHelper;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.User;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.dao.vocabulary.JdbiVocabularyDAO;
import com.interzonedev.hyepye.service.dao.vocabulary.VocabularyMapper;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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

    private final ValidationHelper validationHelper;

    private final VocabularyMapper vocabularyMapper = new VocabularyMapper();

    @Inject
    public JdbiVocabularyRepository(@Named("hyepye.service.dbi") DBI dbi,
            @Named("hyepye.service.jsr303Validator") Validator jsr303Validator,
            @Named("hyepye.service.validationHelper") ValidationHelper validationHelper) {
        this.dbi = dbi;
        this.jsr303Validator = jsr303Validator;
        this.validationHelper = validationHelper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#getVocabularyById(java.lang.Long)
     */
    @Override
    public Vocabulary getVocabularyById(Long id) {

        log.debug("getVocabularyById: id = " + id);

        if (id <= 0L) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary id must be a positive integer");
        }

        Vocabulary vocabulary = getVocabularyDAO().getVocabularyById(id);

        log.debug("getVocabularyById: Returning - vocabulary  = " + vocabulary);

        return vocabulary;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository#searchVocabulary(java.lang.String,
     * com.interzonedev.hyepye.service.repository.DefinitionSearchType, java.lang.String,
     * com.interzonedev.hyepye.service.repository.DefinitionSearchType, com.interzonedev.hyepye.model.VocabularyType,
     * com.interzonedev.hyepye.model.Status, com.interzonedev.hyepye.model.VocabularyProperty, boolean, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public List<Vocabulary> searchVocabulary(String english, DefinitionSearchType englishSearchType, String armenian,
            DefinitionSearchType armenianSearchType, VocabularyType vocabularyType, Status status,
            VocabularyProperty orderBy, boolean ascending, Long limit, Long offset) {

        log.debug("searchVocabulary: Start");

        english = Strings.emptyToNull(english);
        armenian = Strings.emptyToNull(armenian);

        if ((null != english) && (null == englishSearchType)) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The English search type by must be set");
        }

        if ((null != armenian) && (null == armenianSearchType)) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The Armenian search type by must be set");
        }

        if (null == orderBy) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The order by must be set");
        }

        if (null == limit) {
            limit = Long.MAX_VALUE;
        }

        if (null == offset) {
            offset = 0L;
        }

        if (limit < 0L) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The result limit can not be negative");
        }

        if (offset < 0L) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The result offset can not be negative");
        }

        boolean hasWhereClause = (null != english) || (null != armenian) || (null != vocabularyType)
                || (null != status);
        boolean addedWhereClause = false;

        List<Vocabulary> vocabularies;
        try (Handle handle = dbi.open()) {
            StringBuilder queryString = new StringBuilder();
            queryString.append("SELECT vocabulary_id, armenian, english, vocabulary_type, status, time_created,");
            queryString.append(" time_updated, created_by, modified_by");
            queryString.append(" FROM vocabulary");
            if (hasWhereClause) {
                queryString.append(" WHERE");
                if (null != english) {
                    queryString.append(" english ");
                    queryString.append(getWhereClauseForSearchTerm(englishSearchType, english));
                    addedWhereClause = true;
                }
                if (null != armenian) {
                    if (addedWhereClause) {
                        queryString.append(" AND");
                    }
                    queryString.append(" armenian ");
                    queryString.append(getWhereClauseForSearchTerm(armenianSearchType, armenian));
                    addedWhereClause = true;
                }
                if (null != vocabularyType) {
                    if (addedWhereClause) {
                        queryString.append(" AND");
                    }
                    queryString.append(" vocabulary_type = '").append(vocabularyType.getVocabularyTypeName())
                            .append("'");
                    addedWhereClause = true;
                }
                if (null != status) {
                    if (addedWhereClause) {
                        queryString.append(" AND");
                    }
                    queryString.append(" status = '").append(status.getStatusName()).append("'");
                }
            }
            queryString.append(" ORDER BY ").append(orderBy.getVocabularyColumnName());
            if (ascending) {
                queryString.append(" ASC");
            } else {
                queryString.append(" DESC");
            }
            queryString.append(" LIMIT ").append(limit);
            queryString.append(" OFFSET ").append(offset);

            Query<Vocabulary> query = handle.createQuery(queryString.toString()).map(vocabularyMapper);

            log.debug("searchVocabulary: Executing search query - \"" + queryString + "\"");

            vocabularies = query.list();
        }

        log.debug("searchVocabulary: Returning - vocabularies  = " + vocabularies);

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
    public Vocabulary createVocabulary(Vocabulary vocabulary, Long userId) {

        log.debug("createVocabulary: Start - vocabulary = " + vocabulary);

        validateVocabulary(vocabulary, userId, true);

        Vocabulary vocabularyOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

            validateDuplicateVocabularies(vocabulary, vocabularyDAO);

            long id = vocabularyDAO.createVocabulary(vocabulary, userId);

            return vocabularyDAO.getVocabularyById(id);
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
    public Vocabulary updateVocabulary(Vocabulary vocabulary, Long userId) {

        log.debug("updateVocabulary: Start - vocabulary = " + vocabulary);

        validateVocabulary(vocabulary, userId, false);

        Vocabulary vocabularyOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

            validateDuplicateVocabularies(vocabulary, vocabularyDAO);

            int numUpdatedRows = vocabularyDAO.updateVocabulary(vocabulary, userId);

            Vocabulary updatedVocabulary = null;

            if (1 == numUpdatedRows) {
                updatedVocabulary = vocabularyDAO.getVocabularyById(vocabulary.getId());
            }

            return updatedVocabulary;
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
    public Vocabulary deactivateVocabulary(Long id, Long userId) {

        log.debug("deactivateVocabulary: id = " + id);

        if (id <= 0L) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary id must be a positive integer");
        }

        Vocabulary vocabularyOut = dbi.inTransaction((handle, transactionStatus) -> {
            JdbiVocabularyDAO vocabularyDAO = handle.attach(JdbiVocabularyDAO.class);

            Vocabulary vocabularyToDeactivate = vocabularyDAO.getVocabularyById(id);
            if (null == vocabularyToDeactivate) {
                throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary to delete doesn't exist");
            }

            int numUpdatedRows = vocabularyDAO.deactivateVocabulary(id, userId);

            Vocabulary deactivatedVocabulary = null;

            if (1 == numUpdatedRows) {
                deactivatedVocabulary = vocabularyDAO.getVocabularyById(id);
            }

            return deactivatedVocabulary;
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
    private void validateVocabulary(Vocabulary vocabulary, Long userId, boolean creating) {

        if (null == vocabulary) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The vocabulary must be set");
        }

        if (userId <= 0L) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The user id must be a positive integer");
        }

        BindingResult errors = validationHelper.validate(jsr303Validator, vocabulary, Vocabulary.MODEL_NAME);

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
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
     * @throws ValidationException Thrown if the specified {@link Vocabulary} violates any uniqueness constraints of the
     *             vocabulary table.
     */
    private void validateDuplicateVocabularies(Vocabulary vocabulary, JdbiVocabularyDAO vocabularyDAO)
            {

        Vocabulary vocabularyWithSameArmenian = vocabularyDAO.getVocabularyByArmenian(vocabulary.getArmenian());
        if ((null != vocabularyWithSameArmenian) && !vocabularyWithSameArmenian.getId().equals(vocabulary.getId())) {
            throw new ValidationException(Vocabulary.MODEL_NAME,
                    "A vocabulary with the same Armenian defintion already exists.");
        }

    }

    private JdbiVocabularyDAO getVocabularyDAO() {
        return dbi.onDemand(JdbiVocabularyDAO.class);
    }

    /**
     * Gets a query string fragment for a where clause based on the specified {@link DefinitionSearchType} and search
     * term.
     * 
     * @param definitionSearchType The {@link DefinitionSearchType} that determines the form of the where clause.
     * @param searchTerm The literal value on which to search
     * 
     * @return Returns a query string fragment for a where clause based on the specified {@link DefinitionSearchType}
     *         and search term.
     * 
     * @throws ValidationException Thrown if the specifed {@link DefinitionSearchType} is not supported.
     */
    private String getWhereClauseForSearchTerm(DefinitionSearchType definitionSearchType, String searchTerm)
            {

        StringBuilder queryStringFragment = new StringBuilder();

        switch (definitionSearchType) {
            case FULL_WORD:
                queryStringFragment.append("= '").append(searchTerm).append("'");
                break;
            case STARTS_WITH:
                queryStringFragment.append("LIKE '").append(searchTerm).append("%'");
                break;
            case CONTAINS:
                queryStringFragment.append("LIKE '%").append(searchTerm).append("%'");
                break;
            default:
                throw new ValidationException(Vocabulary.MODEL_NAME, "Unsupported definition search type: "
                        + definitionSearchType);
        }

        return queryStringFragment.toString();
    }

}
