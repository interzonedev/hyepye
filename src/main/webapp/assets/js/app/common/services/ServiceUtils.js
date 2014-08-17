(function() {
    "use strict";

    var services;

    /*
     * Define (or get if it was already defined) the module in which to register
     * application wide services. This will make all registered services
     * available to an application that includes this as a dependency.
     */
    services = izng.module("hyepye.common.services");

    /**
     * Creates an injectable singleton with static utility methods.
     */
    services.factory("ServiceUtils", function() {

        return {

            /**
             * Turns the name/value pairs in the specified associative array
             * into a standard HTTP query string for a GET request.
             * 
             * @param {Object}
             *            params An associative array of name/value request
             *            parameters.
             * 
             * @returns A standard HTTP query string for a GET request in the
             *          form name1=value1&name2=value2...if the params input is
             *          a valid object, otherwise returns an empty string.
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
            }

        };

    });

}());
