(function() {
    "use strict";

    var services;

    services = izng.module("hyepye.common.services");

    /**
     * Creates an injectable singleton with static utility methods.
     */
    services.factory("ServiceUtils", function($q, $log, $rootScope) {

        var remoteErrorDefaultParams;

        remoteErrorDefaultParams = {
            logPrefix: "",
            message: "Error executing remote request"
        };

        return {

            /**
             * Turns the name/value pairs in the specified associative array into a standard HTTP query string for a GET
             * request.
             * 
             * @param {Object} params An associative array of name/value request parameters.
             * @param {Boolean} includeNullAndUndefinedValues Whether or not to include name/value pairs in the
             *                                                specified params if the value is null or undefined.
             *                                                Defaults to false if not specified.
             * 
             * @returns A standard HTTP query string for a GET request in the form name1=value1&name2=value2...if the
             *          params input is a valid object, otherwise returns an empty string.
             */
            getQueryStringFromParams: function(params, includeNullAndUndefinedValues) {
                var queryStringArray, name = null, value;

                includeNullAndUndefinedValues = !!includeNullAndUndefinedValues;

                if (!angular.isObject(params)) {
                    return "";
                }

                queryStringArray = [];
                for (name in params) {
                    if (params.hasOwnProperty(name)) {
                        value = params[name];
                        if (((null !== value) && !angular.isUndefined(value)) || includeNullAndUndefinedValues) {
                            queryStringArray.push(encodeURIComponent(name) + "=" + encodeURIComponent(value));
                        }
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
             * @param {Object} params An associative array of parameters used in logging and alerting. The expected
             *            params are:
             *            {String} logPrefix The prefix to set before the specified message in the log message.
             *            {String} message The message to log and to send in the error alert.
             * 
             * @returns An Angular promise that will only reject upon completion with the specified response set as the
             *          rejection argument.
             */
            handleRemoteError: function(response, params) {
                var logPrefix, message;

                logPrefix = params.logPrefix || remoteErrorDefaultParams.params;
                message = params.message || remoteErrorDefaultParams.message;

                $log.error(logPrefix + message);
                $rootScope.$broadcast("alert", {
                    "msg": message
                });

                return $q.reject(response);
            }

        };

    });

}());
