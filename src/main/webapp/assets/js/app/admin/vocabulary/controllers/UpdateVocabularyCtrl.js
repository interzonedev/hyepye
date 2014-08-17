(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for updating a Vocabulary.
     */
    vocabularyAdminApp.controller("UpdateVocabularyCtrl", function($scope, $rootScope, $routeParams, $log, VocabularyAdminService, AdminService) {

        var getVocabularyToUpdate;

        getVocabularyToUpdate = function() {
            VocabularyAdminService.getVocabularyById($routeParams.id).success(function(data, headers) {
                var vocabulary;
                if (data.vocabulary) {
                    vocabulary = data.vocabulary;
                    $scope.armenian = vocabulary.armenian;
                    $scope.english = vocabulary.english;
                    $scope.vocabularyType = vocabulary.vocabularyType;
                    $scope.status = vocabulary.status;
                } else {
                    $rootScope.$broadcast("alert", {
                        "msg": "Unable to retrieve vocabulary"
                    });
                }
            }).error(function(error) {
                $rootScope.$broadcast("alert", {
                    "msg": "Unable to retrieve vocabulary"
                });
            });
        };

        $scope.update = function() {

            $log.log("UpdateVocabularyCtrl: update - Start");

            $log.log("UpdateVocabularyCtrl: update - End");

        };

        getVocabularyToUpdate();

    });

}());
