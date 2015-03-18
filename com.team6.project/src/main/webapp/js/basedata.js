function readDates($scope, $http) {

	// 'http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery';

	$scope.sayHello = function(firstDate, secondDate) {

        document.getElementById('errorDiv').innerHTML = " ";

		
	//	var d = Date.parse(firstDate);
	if(!firstDate || !secondDate){

		alert("Fields are mandatory!");

	}

		//if(document.getElementById("validationEnabled").checked){
			if(!validateDate(firstDate, "Invalid start date") || !validateDate(secondDate, "Invalid end date")){
				console.log("initial wave");

				return false;
				
			}
		

			firstDate  = convertDate(firstDate);
			secondDate = convertDate(secondDate);

		
		
		




		
		$scope.loadData($scope,firstDate, secondDate, $http);

	};
	$scope.loadData = function($scope, firstDate, secondDate, $http) {
		console.log("date in load data");
		


		
		
		$http
				.get(
						'http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/basedata/datequery',
						{
							params : {
								firstDate : firstDate,
								secondDate : secondDate
							}
						}).success(function(data, status) {
					$scope.baseDataDate = data;
				}).error(function(data, status) {
			          $scope.baseDataDate = data || "Request failed";
			          $scope.status = status;
			          document.getElementById('dataTables-example').innerHTML = "<div ng-controller='readDates'>Invalid date</div>";
			          $compile( document.getElementById('dataTables-example') )($scope);
			      });
	}

	
}
function validateDate(dateString, errorMessage){
	//yyyy-mm-dd hh-mm-ss
	regexPattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$/;
	if(dateString.match(regexPattern)){
		console.log("pass");
   //     document.getElementById('dataTables-example').innerHTML = "<div ng-controller='readDates'>Invalid date1</div>";

		return true;
	}
	else{
		showError(errorMessage + ": " + dateString);
		console.log("fail");

		return false;
	}
}
function showError(message){
	var errorDiv = document.getElementById("errorDiv");
	errorDiv.innerHTML = message;
}
function convertDate(predate) {

	var newdate =  predate.split(" ");

	var postdate = newdate[0] + 'T' + newdate[1];
	return postdate;
}
