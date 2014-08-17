<div class="row">
    <div class="col-xs-12">
        <form name="vocabularyForm" class="form-horizontal" role="form">
            <div class="form-group">
                <label for="armenian" class="col-xs-2 control-label">Armenian</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="armenian" ng-model="armenian" placeholder="Armenian" />
                </div>
            </div>
            <div class="form-group">
                <label for="english" class="col-xs-2 control-label">English</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="english" ng-model="english" placeholder="English" />
                </div>
            </div>
            <div class="form-group">
                <label for="vocabularyType" class="col-xs-2 control-label">Vocabulary Type</label>
                <div class="col-xs-10">
                    <input type="text" class="form-control" id="vocabularyType" ng-model="vocabularyType" placeholder="Vocabulary Type" />
                </div>
            </div>
            <div class="form-group">
                <label for="status" class="col-xs-2 control-label">Status</label>
                <div class="col-xs-10">
                    <%--<input type="text" class="form-control" id="status" ng-model="status" placeholder="Status" />--%>
                    <select class="form-control" id="status" ng-model="status" ng-options="thisStatus.value for thisStatus in statuses"></select>
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
