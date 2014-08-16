(function() {
    "use strict";

    var vocabularyAdminApp;

    // Create the top level application for the Hye Pye vocabulary admin.
    vocabularyAdminApp = angular.module("hyepye.admin.vocabulary.App", ["hyepye.common.services", "ngRoute"]);

    vocabularyAdminApp.config(function($routeProvider) {
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
    });

}());
