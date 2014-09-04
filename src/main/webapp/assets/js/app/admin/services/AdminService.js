(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.admin.services");

    /**
     * Defines a service for sending requests to common admin endpoints.
     */
    services.service("AdminService", function ($rootScope, $q, $http, $log, ServiceUtils) {

        var statuses, handleGetStatusesError;

        this.getStatuses = function() {
            if (statuses) {
                var deferred;
                deferred = $q.defer();
                deferred.resolve(statuses);
                return deferred.promise;
            } else {
                return $http.get("/admin/statuses").then(function success(response) {
                    if (response && response.data && response.data.statuses) {
                        statuses = response.data.statuses;
                        return response.data.statuses;
                    } else {
                        return handleGetStatusesError(response);
                    }
                }, function error(response) {
                    return handleGetStatusesError(response);
                });
            }
        };

        handleGetStatusesError = function(response) {
            var errorParams;

            errorParams = {
                logPrefix: "AdminService: getStatuses - ", 
                message: "Error retrieving statuses"
            };

            return ServiceUtils.handleRemoteError(response, errorParams);
        };

    });

}());
