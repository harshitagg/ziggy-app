function ziggyViewController($scope) {
    $scope.formBridge = new FormBridge();
    $scope.navigationBridge = new NavigationBridge();

    $scope.openForm = function (formName, entityId, metaData) {
        if (!metaData) {
            metaData = {};
        }
        $scope.formBridge.delegateToFormLaunchView(formName, entityId, JSON.stringify(metaData));
    };

    $scope.openFormWithFieldOverrides = function (formName, entityId, fields) {
        var fieldOverrides = {
            fieldOverrides: fields
        };
        $scope.formBridge.delegateToFormLaunchView(formName, entityId, JSON.stringify(fieldOverrides));
    };

    $scope.openVillageList = function () {
        $scope.navigationBridge.delegateToVillageListView();
    };
}
