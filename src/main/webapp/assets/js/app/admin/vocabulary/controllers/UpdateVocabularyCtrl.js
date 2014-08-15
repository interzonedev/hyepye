(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for updating a Vocabulary.
     */
    vocabularyAdminApp.controller("UpdateVocabularyCtrl", function($scope, $rootScope, $log, VocabularyService) {

        $scope.update = function() {

            $log.log("UpdateVocabularyCtrl: update - Start");

            $log.log("UpdateVocabularyCtrl: update - End");

        };

    });

}());
