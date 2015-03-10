function readDates($scope, $http) {
	
//	var dd1 = '2013-01-11T17:34:00';
//	var dd2 = '2013-01-11T17:36:00';
//	var url = 'http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery';

	$scope.sayHello = function(firstDate, secondDate) {
		console.log(firstDate);

		$scope.firstDate = firstDate;
		$scope.secondDate = secondDate;	
		
	 $scope.loadData($scope, $http);

	};
	 $scope.loadData = function ($scope, $http) {
	$http.get('http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery',
					{
						params : {
							firstDate : $scope.firstDate,
							secondDate : $scope.secondDate
						}
					}).success(function(data, status) {
				$scope.baseDataDate = data;
			});
	 }
//	 $scope.loadData($scope, $http);
}
