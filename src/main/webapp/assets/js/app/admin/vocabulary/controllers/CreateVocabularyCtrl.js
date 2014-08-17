(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for creating a new Vocabulary.
     */
    vocabularyAdminApp.controller("CreateVocabularyCtrl", function($scope, $rootScope, $log, VocabularyAdminService, AdminService) {

        $scope.create = function() {

            $log.log("CreateVocabularyCtrl: create - Start");

            $log.log("CreateVocabularyCtrl: create - End");

        };

    });

}());
