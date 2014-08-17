(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.admin.services");

    /**
     * Defines a service for sending requests to common admin endpoints.
     */
    services.service("AdminService", function ($rootScope, $http, $log, ServiceUtils) {

        this.getStatuses = function() {
            return $http.get("/admin/statuses");
        };

    });

}());
