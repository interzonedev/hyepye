(function() {
    "use strict";

    var vocabularyAdminApp;

    // Create the top level application for the Hye Pye vocabulary admin.
    vocabularyAdminApp = angular.module("hyepye.admin.vocabulary.App", [
        "hyepye.common.directives",
        "hyepye.common.services",
        "ngRoute"
    ]);

    vocabularyAdminApp.config(function($routeProvider, $httpProvider) {
        $routeProvider
        .when("/", {
            controller: "SearchVocabularyCtrl",
            templateUrl: "vocabularyapp/search"
        }).when("/edit/:id", {
            controller: "UpdateVocabularyCtrl",
            templateUrl: "vocabularyapp/form"
        }).when("/new", {
            controller: "CreateVocabularyCtrl",
            templateUrl: "vocabularyapp/form"
        }).otherwise({
            redirectTo: "/"
        });

        $httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $httpProvider.defaults.headers.put["Content-Type"] = "application/x-www-form-urlencoded";

    });

}());
