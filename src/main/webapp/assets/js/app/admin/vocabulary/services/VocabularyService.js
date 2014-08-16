(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    vocabularyAdminApp.service("VocabularyService", function ($http, ServiceUtils) {

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
