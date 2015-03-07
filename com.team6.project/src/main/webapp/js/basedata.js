function readDates($scope, $http) {
    $http.get('http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/2013-01-11T17:34:00/2013-01-11T17:36:00').
        success(function(data) {
            $scope.baseDataDate = data;
        });
}