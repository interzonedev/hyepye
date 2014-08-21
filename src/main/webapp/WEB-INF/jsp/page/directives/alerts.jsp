<div ng-repeat="alert in alerts" class="alert alert-{{alert.type}}" ng-cloak>
    <button type="button" class="close" data-dismiss="alert" ng-click="closeAlert(alert.key)">&times;</button>
    {{alert.msg}}
</div>
