(function() {
    "use strict";

    var directives;

    directives = izng.module("hyepye.common.directives");

    /**
     * Define a directive for receiving and displaying application wide alerts.
     */
    directives.directive("alerts", function($rootScope, $log, $timeout) {

        var alertDefaults, addAlert;

        alertDefaults = {
            type: "danger",
            closeMillis: 3000
        };

        return {
            restrict: "E",
            replace: true,
            templateUrl: "/directives/alerts",
            scope: true,
            controller: function($scope) {
                var addAlert;

                /**
                 * Add an alert with the specified message and level to the alerts associative array. Set a timeout if
                 * the time to close is specified.
                 * 
                 * @param {Object} alert The alert to dislay. The following properties are valid: msg: The text of the
                 *            alert. type: The type of alert (danger, warning, info, success). closeMillis: If greater
                 *            than zero, the amount of time the alert should display before automatically closing.
                 */
                addAlert = function(alert) {
                    var closeMillis, key;

                    $log.log("alert directive: addAlert - alert = "
                            + angular.toJson(alert, true));

                    closeMillis = alert.closeMillis
                            || alertDefaults.closeMillis;

                    key = "alert" + $scope.numAlerts;

                    $scope.alerts[key] = {
                        "key": key,
                        "msg": alert.msg,
                        "type": alert.type || alertDefaults.type
                    };

                    if (closeMillis > 0) {
                        (function(thisKey) {
                            $timeout(function() {
                                $log.log("addAlert: Timeout alert " + thisKey);
                                $scope.closeAlert(thisKey);
                            }, closeMillis);
                        }(key));
                    }

                    $scope.numAlerts = $scope.numAlerts + 1;
                };

                // The array of currently displayed alerts.
                $scope.alerts = {};
                $scope.numAlerts = 0;

                // Listen for alert events and display an alert when one is received.
                $scope.$on("alert", function(evt, data) {
                    $log.log("alert directive: " + evt.name + " - data = "
                            + angular.toJson(data, true));
                    addAlert(data);
                });

                /**
                 * Removes the alert at the specified index from the currently displayed alerts.
                 * 
                 * @param {Number} index The index in the array of currently displayed alerts to remove.
                 */
                $scope.closeAlert = function(key) {
                    $log.log("alert directive: closeAlert - key = " + key);
                    delete $scope.alerts[key];
                };
            }
        };

    });

}());
