(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for updating a Vocabulary.
     */
    vocabularyAdminApp.controller("UpdateVocabularyCtrl", function($scope, $rootScope, $routeParams, $log, VocabularyAdminService, AdminService) {

        var init, getStatuses, getVocabularyToUpdate;

        init = function() {

            getStatuses()
            .then(getVocabularyToUpdate())
            .catch(function(response) {
                var message;
                message = "Error initiating controller";
                $log.error("UpdateVocabularyCtrl: init - " + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });
            });
            
        };

        getStatuses = function() {
            return AdminService.getStatuses().then(function success(statuses) {
                $scope.statuses = statuses;
            });
        };

        getVocabularyToUpdate = function() {
            return VocabularyAdminService.getVocabularyById($routeParams.id).then(function success(vocabulary) {
                $scope.id = vocabulary.id;
                $scope.armenian = vocabulary.armenian;
                $scope.english = vocabulary.english;
                $scope.vocabularyType = vocabulary.vocabularyType;
                angular.forEach($scope.statuses, function(value, key) {
                    if (value.value === vocabulary.status ) {
                        $scope.status = $scope.statuses[key];
                    }
                });
            });
        };

        $scope.update = function() {

            $log.log("UpdateVocabularyCtrl: update - Start");

            $log.log("UpdateVocabularyCtrl: update - End");

        };

        $scope.$on("$viewContentLoaded", function() {
            $log.log("UpdateVocabularyCtrl: on $viewContentLoaded");
            init();
        });

    });

}());
