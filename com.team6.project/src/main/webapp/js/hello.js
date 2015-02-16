function Hello($scope, $http) {
    $http.get('http://localhost:8080/PeopleEnterpriseExample-0.0.1-SNAPSHOT/rost/getall').
        success(function(data) {
            $scope.ErroneousBaseData = data;
        });
}