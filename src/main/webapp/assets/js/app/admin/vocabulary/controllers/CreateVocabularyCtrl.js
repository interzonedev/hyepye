(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for creating a new Vocabulary.
     */
    vocabularyAdminApp.controller("CreateVocabularyCtrl", function($scope, $rootScope, $log, VocabularyService, CommonService) {

        var init, getStatuses, getVocabularyTypes, setInitialPulldownValues, setVocabularyInScope;

        init = function() {
            getStatuses()
            .then(getVocabularyTypes)
            .then(setInitialPulldownValues)
            .catch(function(response) {
                var message;
                message = "Error initiating controller";
                $log.error("CreateVocabularyCtrl: init - " + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });
            });

        };

        getStatuses = function() {
            return CommonService.getStatuses().then(function success(statuses) {
                $scope.statuses = statuses;
            });
        };

        getVocabularyTypes = function() {
            return VocabularyService.getVocabularyTypes().then(function success(vocabularyTypes) {
                $scope.vocabularyTypes = vocabularyTypes;
            });
        };

        setInitialPulldownValues = function() {
            $scope.vocabularyType = $scope.vocabularyTypes[0];
            $scope.status = $scope.statuses[0];
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

        $scope.create = function() {

            var params;

            $log.log("CreateVocabularyCtrl: create - Start");

            params = {};
            params.armenian = $scope.armenian;
            params.english = $scope.english;
            params.vocabularyTypeValue = $scope.vocabularyType.value;
            params.statusValue = $scope.status.value;

            VocabularyService.createVocabulary(params).then(function success(vocabulary) {
                setVocabularyInScope(vocabulary);
                $scope.validationErrors = {};
                $rootScope.$broadcast("alert", {
                    "msg": "Creation Successful",
                    "type": "success"
                });
            }, function error(validationErrors) {
                $scope.validationErrors = validationErrors;
                $log.log("CreateVocabularyCtrl: create - error creating vocabulary - " + angular.toJson(validationErrors, true));
            });

            $log.log("CreateVocabularyCtrl: create - End");

        };

        $scope.$on("$viewContentLoaded", function() {
            $log.log("CreateVocabularyCtrl: on $viewContentLoaded");
            init();
        });

    });

}());
