(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.admin.services");

    /**
     * Defines a service for sending requests to common admin endpoints.
     */
    services.service("AdminService", function ($http, ServiceUtils) {

        var statusesUrl, statuses;

        statusesUrl =   "/admin/statuses";
        
        this.getStatuses = function() {
            
            if (!statuses) {
                statuses = $http.get(statusesUrl);
            }

            return statuses;

        };
        
    });

}());
