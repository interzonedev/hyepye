(function() {
    "use strict";

    var vocabularyAdminApp;

    vocabularyAdminApp = izng.module("hyepye.admin.vocabulary.App");

    /**
     * Defines a controller for searching Vocabularies.
     */
    vocabularyAdminApp.controller("SearchVocabularyCtrl", function($scope, $rootScope, $log, VocabularyService, AdminService) {

        $scope.vocabularies = {};

        $scope.currentPage = 1;

        // Initialize the number of results per page of search results.
        $scope.resultsPerPage = 25;

        $scope.search = function() {

            var searchParams;

            $log.log("SearchVocabularyCtrl: search - Start");

            searchParams = {
                vocabularyTypeValue: "day",
                orderBy: "english",
                requestedPageNumber: $scope.currentPage
            };
            /*
            english;
            englishSearchTypeValue;
            armenian;
            armenianSearchTypeValue;
            vocabularyTypeValue;
            statusValue;
            orderBy;
            ascending;
            resultsPerPage;
            requestedPageNumber;
            */

            VocabularyService.search(searchParams).success(function(data, headers) {
                if (data.vocabularies) {
                    $scope.vocabularies = data.vocabularies;
                } else {
                    $rootScope.$broadcast("alert", {
                        "msg": "Unable to perform search"
                    });
                }
            }).error(function(error) {
                $rootScope.$broadcast("alert", {
                    "msg": "Unable to perform search"
                });
            });
            
            $log.log("SearchVocabularyCtrl: search - End");

        };

    });

}());
