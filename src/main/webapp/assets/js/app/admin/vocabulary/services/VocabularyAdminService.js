(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    vocabularyAdminApp.service("VocabularyAdminService", function ($rootScope, $q, $http, $log, ServiceUtils) {

        var handleGetVocabularyByIdError;

        this.getVocabularyById = function(id) {

            return $http.get("/admin/vocabulary/" + id).then(function success(response) {
                if (response && response.data && response.data.vocabulary) {
                    return response.data.vocabulary;
                } else {
                    return handleGetVocabularyByIdError(response);
                }
            }, function error(response) {
                return handleGetVocabularyByIdError(response);
            });

        };

        this.create = function(params) {

            var queryString;

            queryString = ServiceUtils.getQueryStringFromParams(params);

            return $http.get("/vocabulary/search?" + queryString);
            
        };

        handleGetVocabularyByIdError = function(response) {
            var logPrefix, message;

            logPrefix = "VocabularyAdminService: getVocabularyById - "; 
            message = "Error retrieving vocabulary";

            return ServiceUtils.handleRemoteError(response, logPrefix, message);
        };

    });

}());
