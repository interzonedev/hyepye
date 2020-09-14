(function(context) {
    "use strict";

    var izng;

    /**
     * @namespace
     * 
     * Provides an object to be set on the context in which this is run on which to define common utility functions for
     * use with AngularJS.
     */
    izng = {

        /**
         * Gets or creates the AngularJS module with the specified name.  First attempts to get the module by name.  If
         * the module does not exist, creates the module with the specified name and dependencies.
         * 
         * @param {String} name The name of the module to retrieve or create.
         * @param {Array} requires An array of dependencies of the module when creating.
         * @param {Function} configFn Configuration function for the module when creating.
         * 
         * @returns An angular.Module instance of the specified name, whether retrieved or created.
         */
        module: function(name, requires, configFn) {
            try {
                return angular.module(name);
            } catch (e) {
                return angular.module(name, angular.isArray(requires) ? requires : [], configFn);
            }
        },

    };

    // Set the izng object on the context in which this is run.
    context.izng = izng;

}(this));
