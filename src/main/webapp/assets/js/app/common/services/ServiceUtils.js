(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.common.services");

    /**
     * Creates an injectable singleton with static utility methods.
     */
    services.factory("ServiceUtils", function($q, $log, $rootScope) {

        return {

            /**
             * Turns the name/value pairs in the specified associative array into a standard HTTP query string for a GET
             * request.
             * 
             * @param {Object} params An associative array of name/value request parameters.
             * 
             * @returns A standard HTTP query string for a GET request in the form name1=value1&name2=value2...if the
             *          params input is a valid object, otherwise returns an empty string.
             */
            getQueryStringFromParams: function(params) {
                var queryStringArray, name = null, value;

                if (!angular.isObject(params)) {
                    return "";
                }

                queryStringArray = [];
                for (name in params) {
                    if (params.hasOwnProperty(name)) {
                        value = params[name];
                        queryStringArray.push(encodeURIComponent(name) + "="
                                + encodeURIComponent(value));
                    }
                }

                return queryStringArray.join("&");
            },

            /**
             * Provides a standard way to handle the error response in remote HTTP requests. An error level message is
             * logged with the specified prefix and message. An error level alert is broadcast with the specified
             * message. The specified response is set as the argument to the rejection of the Angular promise that gets
             * returned.
             * 
             * @param {Object} response The response object returned by the Angular $http module remote request methods.
             * @param {String} logPrefix The prefix to set before the specified message in the log message.
             * @param {String} message The message to log and to send in the error alert.
             * 
             * @returns An Angular promise that will only reject upon completion with the specified response set as the
             *          rejection argument.
             */
            handleRemoteError: function(response, logPrefix, message) {
                $log.error(logPrefix + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });

                return $q.reject(response);
            }

        };

    });

}());
