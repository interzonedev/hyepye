(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a service for sending requests to the Vocabulary endpoints.
     */
    vocabularyAdminApp.service("VocabularyAdminService", function ($http, ServiceUtils) {

        var createVocabularyUrl;

        createVocabularyUrl =   "/admin/vocabulary";

        this.getVocabularyById = function(id) {

            return $http.get("/admin/vocabulary/" + id);
            
        };

        this.create = function(params) {
            
            var queryString;

            queryString = ServiceUtils.getQueryStringFromParams(params);

            return $http.get("/vocabulary/search?" + queryString);
            
        };
        
    });

}());
