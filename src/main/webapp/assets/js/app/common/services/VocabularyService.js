(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.common.services");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    services.service("VocabularyService", function ($q, $http, ServiceUtils) {

        var vocabularyTypes, vocabularyProperties, handleGetVocabularyTypesError, handleGetVocabularyPropertiesError,
            handleSearchError, handleGetVocabularyByIdError, handleCreateVocabularyError, handleUpdateVocabularyError;

        this.getVocabularyTypes = function() {
            if (vocabularyTypes) {
                var deferred;
                deferred = $q.defer();
                deferred.resolve(vocabularyTypes);
                return deferred.promise;
            } else {
                return $http.get("/vocabulary/types").then(function success(response) {
                    if (response && response.data && response.data.vocabularyTypes) {
                        vocabularyTypes = response.data.vocabularyTypes;
                        return response.data.vocabularyTypes;
                    } else {
                        return handleGetVocabularyTypesError(response);
                    }
                }, function error(response) {
                    return handleGetVocabularyTypesError(response);
                });
            }
        };

        this.getVocabularyProperties = function() {
            if (vocabularyProperties) {
                var deferred;
                deferred = $q.defer();
                deferred.resolve(vocabularyProperties);
                return deferred.promise;
            } else {
                return $http.get("/vocabulary/properties").then(function success(response) {
                    if (response && response.data && response.data.vocabularyProperties) {
                        vocabularyProperties = response.data.vocabularyProperties;
                        return response.data.vocabularyProperties;
                    } else {
                        return handleGetVocabularyPropertiesError(response);
                    }
                }, function error(response) {
                    return handleGetVocabularyPropertiesError(response);
                });
            }
        };

        this.search = function(params) {
            
            var queryString;

            queryString = ServiceUtils.getQueryStringFromParams(params);

            return $http.get("/vocabulary/search?" + queryString).then(function success(response) {
                if (response && response.data && response.data.vocabularies) {
                    return response.data.vocabularies;
                } else {
                    return handleSearchError(response);
                }
            },function error(response) {
                return handleSearchError(response);
            });
            
        };

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

        this.createVocabulary = function(params) {

            var requestData;

            requestData = ServiceUtils.getQueryStringFromParams(params);

            return $http.post("/admin/vocabulary/", requestData).then(function success(response) {
                if (response && response.data && response.data.vocabulary) {
                    return response.data.vocabulary;
                } else {
                    return handleCreateVocabularyError(response);
                }
            }, function error(response) {
                if (response.data && response.data.validationErrors) {
                    return $q.reject(response.data.validationErrors);
                } else {
                    return handleCreateVocabularyError(response);
                }
            });
            
        };

        this.updateVocabulary = function(params) {

            var requestData;

            requestData = ServiceUtils.getQueryStringFromParams(params);

            return $http.put("/admin/vocabulary/" + params.id, requestData).then(function success(response) {
                if (response && response.data && response.data.vocabulary) {
                    return response.data.vocabulary;
                } else {
                    return handleUpdateVocabularyError(response);
                }
            }, function error(response) {
                if (response.data && response.data.validationErrors) {
                    return $q.reject(response.data.validationErrors);
                } else {
                    return handleUpdateVocabularyError(response);
                }
            });
            
        };

        handleGetVocabularyTypesError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: getVocabularyTypes - ", 
                message: "Error retrieving vocabulary types"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

        handleGetVocabularyPropertiesError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: getVocabularyProperties - ", 
                message: "Error retrieving vocabulary properties"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

        handleSearchError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: search - ", 
                message: "Error executing vocabulary search"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

        handleGetVocabularyByIdError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: getVocabularyById - ", 
                message: "Error retrieving vocabulary"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

        handleCreateVocabularyError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: createVocabulary - ", 
                message: "Error creating vocabulary"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

        handleUpdateVocabularyError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "VocabularyService: updateVocabulary - ", 
                message: "Error updating vocabulary"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

    });

}());
