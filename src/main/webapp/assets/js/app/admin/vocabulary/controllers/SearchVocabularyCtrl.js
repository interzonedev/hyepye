(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for searching Vocabularies.
     */
    vocabularyAdminApp.controller("SearchVocabularyCtrl", function($scope, $rootScope, $log, VocabularyService) {

        $scope.search = function() {

            $log.log("SearchVocabularyCtrl: search - Start");

            $log.log("SearchVocabularyCtrl: search - End");

        };

    });

}());
