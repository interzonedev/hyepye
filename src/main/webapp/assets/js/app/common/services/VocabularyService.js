(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.common.services");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    services.service("VocabularyService", function ($q, $http, ServiceUtils) {

        var vocabularyTypes, vocabularyProperties, handleGetVocabularyTypesError, handleGetVocabularyPropertiesError,
            handleSearchError;

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
                        return handleGetStatusesError(response);
                    }
                }, function error(response) {
                    return handleGetStatusesError(response);
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
                        return handleGetStatusesError(response);
                    }
                }, function error(response) {
                    return handleGetStatusesError(response);
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
                return handleSearchError;
            });
            
        };

        handleGetVocabularyTypesError = function(response) {
            var message;

            message = "Error retrieving vocabulary types";
            $log.error("VocabularyService: getVocabularyProperties - " + message);
            $rootScope.$broadcast("alert", {
                "msg": message
            });

            return $q.reject(response);
        };

        handleGetVocabularyPropertiesError = function(response) {
            var message;

            message = "Error retrieving vocabulary properties";
            $log.error("VocabularyService: getVocabularyProperties - " + message);
            $rootScope.$broadcast("alert", {
                "msg": message
            });

            return $q.reject(response);
        };

        handleSearchError = function(response) {
            var message;

            message = "Error executing vocabulary search";
            $log.error("VocabularyService: search - " + message);
            $rootScope.$broadcast("alert", {
                "msg": message
            });

            return $q.reject(response);
        };

    });

}());
