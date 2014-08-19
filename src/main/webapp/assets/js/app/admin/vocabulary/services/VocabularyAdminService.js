(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    vocabularyAdminApp.service("VocabularyAdminService", function ($rootScope, $q, $http, $log, ServiceUtils) {

        var handleGetVocabularyByIdError, handleUpdateVocabularyError;

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

        this.updateVocabulary = function(params) {

            var postData;

            postData = ServiceUtils.getQueryStringFromParams(params);

            return $http.put("/admin/vocabulary/" + params.id, postData).then(function success(response) {
                if (response && response.data && response.data.vocabulary) {
                    return response.data.vocabulary;
                } else {
                    return handleUpdateVocabularyError(response);
                }
            }, function error(response) {
                if (400 === response.status) {
                    if (response.data && response.data.validationErrors) {
                        return $q.reject(response.data.validationErrors);
                    }
                } else {
                    return handleUpdateVocabularyError(response);
                }
            });
            
        };

        handleGetVocabularyByIdError = function(response) {
            var logPrefix, message;

            logPrefix = "VocabularyAdminService: getVocabularyById - "; 
            message = "Error retrieving vocabulary";

            return ServiceUtils.handleRemoteError(response, logPrefix, message);
        };

        handleUpdateVocabularyError = function(response) {
            var logPrefix, message;

            logPrefix = "VocabularyAdminService: updateVocabulary - "; 
            message = "Error updating vocabulary";

            return ServiceUtils.handleRemoteError(response, logPrefix, message);
        };
        
    });

}());
