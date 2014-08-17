<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div ng-app="hyepye.admin.vocabulary.App">
    <div ng-controller="AlertCtrl">
        <div class="row">
            <div class="col-xs-12">
                <div ng-repeat="alert in alerts" class="alert alert-{{alert.type}}">
                    <button type="button" class="close" data-dismiss="alert" ng-click="closeAlert(alert.key)">&times;</button>
                    {{alert.msg}}
                </div>
            </div>
       </div>
    </div>
    <div ng-view></div>
</div>
