(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for updating a Vocabulary.
     */
    vocabularyAdminApp.controller("UpdateVocabularyCtrl", function($scope, $rootScope, $routeParams, $log, AdminService, VocabularyService, VocabularyAdminService) {

        var init, getStatuses, getVocabularyTypes, getVocabularyToUpdate, setVocabularyInScope;

        init = function() {

            getStatuses()
            .then(getVocabularyTypes())
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

        getVocabularyTypes = function() {
            return VocabularyService.getVocabularyTypes().then(function success(vocabularyTypes) {
                $scope.vocabularyTypes = vocabularyTypes;
            });
        };

        getVocabularyToUpdate = function() {
            return VocabularyAdminService.getVocabularyById($routeParams.id).then(function success(vocabulary) {
                setVocabularyInScope(vocabulary);
            });
        };

        setVocabularyInScope = function(vocabulary) {
            $scope.id = vocabulary.id;
            $scope.armenian = vocabulary.armenian;
            $scope.english = vocabulary.english;
            angular.forEach($scope.vocabularyTypes, function(vocabularyTypeWrapper, index) {
                if (vocabularyTypeWrapper.value === vocabulary.vocabularyType ) {
                    $scope.vocabularyType = $scope.vocabularyTypes[index];
                }
            });
            angular.forEach($scope.statuses, function(statusWrapper, index) {
                if (statusWrapper.value === vocabulary.status ) {
                    $scope.status = $scope.statuses[index];
                }
            });            
        };

        $scope.update = function() {

            var params;

            $log.log("UpdateVocabularyCtrl: update - Start");

            params = {};
            params.id = $scope.id;
            params.armenian = $scope.armenian;
            params.english = $scope.english;
            params.vocabularyTypeValue = $scope.vocabularyType.value;
            params.statusValue = $scope.status.value;

            VocabularyAdminService.updateVocabulary(params).then(function success(vocabulary) {
                setVocabularyInScope(vocabulary);
                $scope.validationErrors = {};
                $rootScope.$broadcast("alert", {
                    "msg": "Update Successful",
                    "type": "success"
                });
            }, function error(validationErrors) {
                $scope.validationErrors = validationErrors;
                $log.log("UpdateVocabularyCtrl: update - error updating vocabulary - " + angular.toJson(validationErrors, true));
            });

            $log.log("UpdateVocabularyCtrl: update - End");

        };

        $scope.$on("$viewContentLoaded", function() {
            $log.log("UpdateVocabularyCtrl: on $viewContentLoaded");
            init();
        });

    });

}());
