package com.interzonedev.hyepye.service.command.vocabulary;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.interzonedev.blundr.ValidationException;
import com.interzonedev.commandr.hystrix.HystrixCommandConfiguration;
import com.interzonedev.hyepye.model.Status;
import com.interzonedev.hyepye.model.Vocabulary;
import com.interzonedev.hyepye.model.VocabularyProperty;
import com.interzonedev.hyepye.model.VocabularyType;
import com.interzonedev.hyepye.service.command.AbstractHyePyeCommand;
import com.interzonedev.hyepye.service.command.HyePyeResponse;
import com.interzonedev.hyepye.service.repository.DefinitionSearchType;
import com.interzonedev.hyepye.service.repository.vocabulary.VocabularyRepository;

/**
 * Searches {@link Vocabulary} instances against the specified search criteria.
 * 
 * @author mmarkarian
 */
@Named("hyepye.service.searchVocabularyCommand")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchVocabularyCommand extends AbstractHyePyeCommand {

    private static final Logger log = LoggerFactory.getLogger(SearchVocabularyCommand.class);

    private final String english;

    private final DefinitionSearchType englishSearchType;

    private final String armenian;

    private final DefinitionSearchType armenianSearchType;

    private final VocabularyType vocabularyType;

    private final Status status;

    private final VocabularyProperty orderBy;

    private final boolean ascending;

    private final Integer resultsPerPage;

    private final Integer requestedPageNumber;

    @Inject
    @Named("hyepye.service.vocabularyRepository")
    private VocabularyRepository vocabularyRepository;

    /**
     * Creates an instance of this command with a specific command key and timeout.
     * 
     * @param english The English definition (either whole or a fragment) against which to search.
     * @param englishSearchType The type of search to perform on the English definition.
     * @param armenian The Armenian definition (either whole or a fragment) against which to search.
     * @param armenianSearchType The type of search to perform on the Armenian definition.
     * @param vocabularyType The {@link VocabularyType} of {@link Vocabulary} against which to search. If null, searches
     *            against all {@link VocabularyType}s.
     * @param status The {@link Status} of {@link Vocabulary} against which to search. If null, searches against all
     *            {@link Status}es.
     * @param ascending Whether or not the results are ordered in ascending order.
     * @param resultsPerPage The number of search results per page.
     * @param requestedPageNumber The page number of the search results to retrieve.
     */
    public SearchVocabularyCommand(String english, DefinitionSearchType englishSearchType, String armenian,
            DefinitionSearchType armenianSearchType, VocabularyType vocabularyType, Status status,
            VocabularyProperty orderBy, boolean ascending, Integer resultsPerPage, Integer requestedPageNumber) {
        super(HystrixCommandConfiguration.newBuilder().setCommandKey("hyepye.service.searchVocabularyCommand")
                .setThreadTimeoutMillis(500).build());
        this.english = english;
        this.englishSearchType = englishSearchType;
        this.armenian = armenian;
        this.armenianSearchType = armenianSearchType;
        this.vocabularyType = vocabularyType;
        this.status = status;
        this.orderBy = orderBy;
        this.ascending = ascending;
        this.resultsPerPage = resultsPerPage;
        this.requestedPageNumber = requestedPageNumber;
    }

    /**
     * Performs the work of this command.
     * 
     * @return Returns a {@link HyePyeResponse} with the collection of {@link Vocabulary} instances set.
     * 
     * @throws ValidationException Thrown if this instance was created with invalid parameters.
     * @throws Exception Thrown if there was an error executing this command.
     */
    @Override
    protected HyePyeResponse doCommand() throws Exception {

        log.debug("doCommand: Start");

        HyePyeResponse.Builder hyePyeResponse = HyePyeResponse.newBuilder();

        if ((null != resultsPerPage) && (resultsPerPage <= 0)) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The results per page must be positive if it is set");
        }

        if ((null == requestedPageNumber) || (requestedPageNumber <= 0)) {
            throw new ValidationException(Vocabulary.MODEL_NAME, "The requested page number must be positive");
        }

        // Get all possible results for the search criteria.
        List<Vocabulary> vocabularies = vocabularyRepository.searchVocabulary(english, englishSearchType, armenian,
                armenianSearchType, vocabularyType, status, orderBy, ascending, Long.MAX_VALUE, 0L);

        log.debug("doCommand: Retrieved (non paginated) - " + vocabularies);

        // Figure out pagination.
        int totalNumberOfResults = vocabularies.size();

        int effectiveResultsPerPage = totalNumberOfResults;
        if (null != resultsPerPage) {
            effectiveResultsPerPage = Math.min(resultsPerPage, totalNumberOfResults);
        }

        // Take a slice of all the possible results for the requested page.
        int toIndex = Math.min(requestedPageNumber * effectiveResultsPerPage, totalNumberOfResults);
        int fromIndex = toIndex - effectiveResultsPerPage;
        List<Vocabulary> vocabularyPage = vocabularies.subList(fromIndex, toIndex);

        int numberOfPages = (int) Math.ceil(((float) totalNumberOfResults) / ((float) effectiveResultsPerPage));
        int returnedPageNumber = (requestedPageNumber <= numberOfPages) ? requestedPageNumber : numberOfPages;

        log.debug("doCommand: Retrieved (paginated) - " + vocabularyPage);

        hyePyeResponse.setVocabularies(vocabularyPage);
        hyePyeResponse.setNumberOfPages(numberOfPages);
        hyePyeResponse.setReturnedPageNumber(returnedPageNumber);

        return hyePyeResponse.build();

    }

}
