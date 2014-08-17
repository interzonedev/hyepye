(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.common.services");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    services.service("VocabularyService", function ($http, ServiceUtils) {

        var vocabularyTypesUrl, vocabularyPropertiesUrl, vocabularyTypes, vocabularyProperties;

        vocabularyTypesUrl =   "/vocabulary/types";
        
        vocabularyPropertiesUrl =   "/vocabulary/properties";

        this.getVocabularyTypes = function() {
            
            if (!vocabularyTypes) {
                vocabularyTypes = $http.get(vocabularyTypesUrl);
            }

            return vocabularyTypes;

        };

        this.getVocabularyProperties = function() {
            
            if (!vocabularyTypes) {
                vocabularyTypes = $http.get(vocabularyTypesUrl);
            }

            return vocabularyTypes;

        };

        this.search = function(params) {
            
            var queryString;

            queryString = ServiceUtils.getQueryStringFromParams(params);

            return $http.get("/vocabulary/search?" + queryString);
            
        };
        
    });

}());
