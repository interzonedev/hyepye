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
            getStatuses();
            getVocabularyToUpdate();
        };

        getStatuses = function() {
            AdminService.getStatuses().success(function(data, headers) {
                var message;
                if (data.statuses) {
                    $scope.statuses = data.statuses;
                } else {
                    message = "Unable to retrieve statuses";
                    $log.warn("UpdateVocabularyCtrl: getStatuses - " + message);
                    $rootScope.$broadcast("alert", {
                        "msg": message,
                        "type": "warning"
                    });
                }
            }).error(function(error) {
                var message;
                message = "Error retrieving statuses";
                $log.error("UpdateVocabularyCtrl: getStatuses - " + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });
            });
        };
        
        getVocabularyToUpdate = function() {
            VocabularyAdminService.getVocabularyById($routeParams.id).success(function(data, headers) {
                var vocabulary;
                if (data.vocabulary) {
                    vocabulary = data.vocabulary;
                    $scope.id = vocabulary.id;
                    $scope.armenian = vocabulary.armenian;
                    $scope.english = vocabulary.english;
                    $scope.vocabularyType = vocabulary.vocabularyType;
                    angular.forEach($scope.statuses, function(value, key) {
                        if (value.value === vocabulary.status ) {
                            $scope.status = $scope.statuses[key];
                        }
                    });
                } else {
                    message = "Unable to retrieve vocabulary";
                    $log.warn("UpdateVocabularyCtrl: getVocabularyToUpdate - " + message);
                    $rootScope.$broadcast("alert", {
                        "msg": message,
                        "type": "warning"
                    });
                }
            }).error(function(error) {
                var message;
                message = "Error retrieving vocabulary";
                $log.error("UpdateVocabularyCtrl: getVocabularyToUpdate - " + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });
            });
        };

        $scope.update = function() {

            $log.log("UpdateVocabularyCtrl: update - Start");

            $log.log("UpdateVocabularyCtrl: update - End");

        };

        init();

    });

}());
