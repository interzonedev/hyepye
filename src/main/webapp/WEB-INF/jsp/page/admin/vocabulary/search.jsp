<div>Search</div>
<div>
    <a href="#/new">Create</a>
</div>
<form name="vocabularyForm">
    <button ng-click="search()" class="btn btn-primary">Search</button>
</form>
<div ng-switch on="vocabularies.length">
    <div ng-switch-when="0" class="resultOdd">No results</div>
    <div ng-switch-default>
        <div ng-repeat="vocabulary in vocabularies" ng-class-odd="'resultOdd'" ng-class-even="'resultEven'">
            <a href="#/edit/{{vocabulary.id}}">{{vocabulary.english}}</a>
        </div>
    </div>
</div>
