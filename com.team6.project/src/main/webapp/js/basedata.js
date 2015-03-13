function readDates($scope, $http) {

	// var dd1 = '2013-01-11T17:34:00';
	// var dd2 = '2013-01-11T17:36:00';
	// var url =
	// 'http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery';

	$scope.sayHello = function(firstDate, secondDate) {
		console.log(firstDate);
//		var dd1 = Date.parseDate(firstDate, "yyyy-MM-ddThh:mm:ss");
//		var dd2 = Date.parseDate(secondDate, "yyyy-MM-ddThh:mm:ss");

	//	var d = Date.parse(firstDate);

		
		//if(document.getElementById("validationEnabled").checked){
	//		if(!validateDate(firstDate, "Invalid start date") || !validateDate(secondDate, "Invalid end date")){
				
			//	return false;
		//	}
		//}


		
		console.log("firstdate!!!: " + firstDate);
		console.log("seconddate!!!: " + secondDate);

		console.log("scopefirstdate!!!: " + $scope.firstDate);
		console.log("scopeseconddate!!!: " + $scope.secondDate);

		
		$scope.firstDate = firstDate;
		$scope.secondDate = secondDate;
		$scope.loadData($scope, $http);

	};
	$scope.loadData = function($scope, $http) {
		console.log("date in load data");
		


		
		
		$http
				.get(
						'http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery',
						{
							params : {
								firstDate : $scope.firstDate,
								secondDate : $scope.secondDate
							}
						}).success(function(data, status) {
					$scope.baseDataDate = data;
				}).error(function(data, status) {
			          $scope.data = data || "Request failed";
			          $scope.status = status;
			          document.getElementById('dataTables-example').innerHTML = "<div ng-controller='sheet'>Invalid date</div>";
			          $compile( document.getElementById('dataTables-example') )($scope);
			      });
	}
	// $scope.loadData($scope, $http);
}
function validateDate(dateString, errorMessage){
	//yyyy-mm-dd hh-mm-ss
	regexPattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}[T][0-9]{2}:[0-9]{2}:[0-9]{2}$/;
	if(dateString.match(regexPattern)){
		return true;
	}
	else{
		showError(errorMessage + ": " + dateString);
		return false;
	}
	sayHello(firstDate, secondDate);
}


