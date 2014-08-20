<div class="row">
    <div class="col-xs-12">
        <form name="vocabularyForm" class="form-horizontal" role="form">
            <div class="form-group" ng-class="{'has-error': validationErrors.armenian.length}">
                <label for="armenian" class="col-xs-2 control-label">Armenian</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="armenian" ng-model="armenian" placeholder="Armenian" />
                    <ul ng-hide="!validationErrors.armenian.length" class="list-unstyled">
                        <li ng-repeat="error in validationErrors.armenian"><small class="text-danger">{{error}}</small></li>
                    </ul>
                </div>
            </div>
            <div class="form-group" ng-class="{'has-error': validationErrors.english.length}">
                <label for="english" class="col-xs-2 control-label">English</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="english" ng-model="english" placeholder="English" />
                    <ul ng-hide="!validationErrors.english.length" class="list-unstyled">
                        <li ng-repeat="error in validationErrors.english"><small class="text-danger">{{error}}</small></li>
                    </ul>
                </div>
            </div>
            <div class="form-group" ng-class="{'has-error': validationErrors.vocabularyTypeValue.length}">
                <label for="vocabularyType" class="col-xs-2 control-label">Vocabulary Type</label>
                <div class="col-xs-10">
                    <select class="form-control" id="vocabularyType" ng-model="vocabularyType" ng-options="aVocabularyType.value for aVocabularyType in vocabularyTypes"></select>
                    <ul ng-hide="!validationErrors.vocabularyTypeValue.length" class="list-unstyled">
                        <li ng-repeat="error in validationErrors.vocabularyTypeValue"><small class="text-danger">{{error}}</small></li>
                    </ul>
                </div>
            </div>
            <div class="form-group" ng-class="{'has-error': validationErrors.statusValue.length}">
                <label for="status" class="col-xs-2 control-label">Status</label>
                <div class="col-xs-10">
                    <select class="form-control" id="status" ng-model="status" ng-options="aStatus.value for aStatus in statuses"></select>
                    <ul ng-hide="!validationErrors.statusValue.length" class="list-unstyled">
                        <li ng-repeat="error in validationErrors.statusValue"><small class="text-danger">{{error}}</small></li>
                    </ul>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button ng-click="update()" class="btn btn-default">Update</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <a href="#/">Cancel</a>
    </div>
</div>
