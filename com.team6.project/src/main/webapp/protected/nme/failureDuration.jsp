<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Red Hot Chilli Beans</title>

<!-- Adding CSS -->
<link href="../../css/sb-admin-2.css" rel="stylesheet">
<link href="../../css/bootstrap-combined.min.cristiana.css"
	rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="../../css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="../../js/common.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>
<script src="../../js/dataTables.bootstrap.min.js"></script>



<script>

var response;
var t;

	function getFailureData() {
		console.log("Search Clicked!!")
		var date = new Date();

		var picker = $('#datetimepicker').data('datetimepicker');
		date = picker.getDate();

		var startDate = date.valueOf()

		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();

		var endDate = date.valueOf();

// 				if (document.getElementById("validationEnabled").checked) {
// 					if (!validateDate(startDate, "Invalid start date")
// 							|| !validateDate(endDate, "Invalid end date")) {
// 						return false;
// 					}
// 				}

		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr
				.open(
						"GET",
						root
								+ "/protected/rest/networkmanagement/failurecountandduration?startDate="
								+ startDate + "&endDate=" + endDate, true);
				
		xhr.addEventListener('load', function() {
			if (xhr.status == 200) {
				cleanTable();
				response = JSON.parse(xhr.responseText);
				formatTable();
// 				populateTable();
				console.log("Got here");
				
				//createTableHead();
//				createTableBody(response);
//				reloadTable();
				//t.draw();
			} else {
				//bad request, dates could not be parsed. Or no results
				console.log("Bad Request dates could not be parsed.. ")
				cleanTable();
				var message = 'Error ' + xhr.status + ': ' + xhr.responseText;
				showError(message);
			}
		}, false);
		xhr.send();
	}

	function validateDate(dateString, errorMessage) {
		//yyyy-mm-dd hh-mm-ss
		regexPattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$/;
		if (dateString.match(regexPattern)) {
			return true;
		} else {
			showError(errorMessage + ": " + dateString);
			return false;
		}
	}

	function showError(message) {
		var errorDiv = document.getElementById("errorDiv");
		errorDiv.innerHTML = message;
	}

	function createTableHead() {
		console.log("Create Table Head Entered..")
		var table = document.getElementById("failureDurationTable");
		var thead = document.createElement("thead");
		thead.id = "tableHead";
		var tr = document.createElement("tr");
		var th1 = document.createElement("th");
		th1.appendChild(document.createTextNode("IMSI"));
		var th2 = document.createElement("th");
		th2.appendChild(document.createTextNode("Failure Count"));
		var th3 = document.createElement("th");
		th3.appendChild(document.createTextNode("Total Duration"));
		tr.appendChild(th1);
		tr.appendChild(th2);
		tr.appendChild(th3);
		thead.appendChild(tr);
		table.appendChild(thead);
	}

	function createTableBody(response) {
		var table = document.getElementById("failureDurationTable");
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		for (var i = 0; i < response.length; i++) {

			var singleResponse = response[i];

			var tr = document.createElement("tr");

			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}

			var td1 = document.createElement("td");
			td1.appendChild(document.createTextNode(singleResponse[0]));
			var td2 = document.createElement("td");
			td2.appendChild(document.createTextNode(singleResponse[1]));
			var td3 = document.createElement("td");
			td3.appendChild(document.createTextNode(singleResponse[2]));

			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);

			tbody.appendChild(tr);
		}
		table.appendChild(tbody);
	}

	function cleanTable() {
		var tableBody = document.getElementById("tableBody");
		var tableHead = document.getElementById("tableHead");

		var errorDiv = document.getElementById("errorDiv");
		errorDiv.innerHTML = '';

		if (tableHead) {
			console.log("removing head");
			tableHead.parentNode.removeChild(tableHead);
		}
		if (tableBody) {
			console.log("removing body");
			tableBody.parentNode.removeChild(tableBody);
		}
	}

	function startup() {
		loadbar('../sidebar.jsp');
	}
</script>

</head>

<body onload="startup()">
	
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"></nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Call Failure Durations</h1>

					<p>Please enter a date range:</p>
					<div id="datetimepicker" class="input-append date">
						<input type="text"></input> <span class="add-on"> <i
							data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						</span>
					</div>

					<div id="datetimepicker2" class="input-append date">
						<input type="text"></input> <span class="add-on"> <i
							data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						</span>
					</div>

					<script type="text/javascript">
						$('#datetimepicker').datetimepicker({
							format : 'yyyy-MM-dd hh:mm:ss',
							language : 'en'
						});
						$('#datetimepicker').data('datetimepicker')
								.setLocalDate(new Date(2013, 0, 11, 17, 15));
					</script>


					<script type="text/javascript">
						$('#datetimepicker2').datetimepicker({
							format : 'yyyy-MM-dd hh:mm:ss',
							language : 'en'
						});
						$('#datetimepicker2').data('datetimepicker')
								.setLocalDate(new Date(2013, 0, 11, 17, 20));
					</script>

					<br> <input id=button1 type='button' class="btn btn-default"
						onclick="getFailureData()" value="Search" /> <br>
					<br>
					<div class="panel panel-default">
						<div class="panel-heading">Table: IMSI, failure count, total
							duration.</div>
							
						<div class="panel-body">
							<div class="dataTable_wrapper" id="dataTableDiv">
								<div id="errorDiv"></div>
							</div>
							<table class="table table-striped table-bordered table-hover"
								id="failureDurationTable">
							</table>
						</div>
						<!-- /#div1 -->
					</div>
				</div>
			</div>


		</div>

	</div>

	<!-- /#wrapper -->
</body>



<!-- 		var table = document.getElementById("failureDurationTable"); -->
<!-- 		var thead = document.createElement("thead"); -->
<!-- 		thead.id = "tableHead"; -->
<!-- 		var tr = document.createElement("tr"); -->
<!-- 		var th1 = document.createElement("th"); -->
<!-- 		th1.appendChild(document.createTextNode("IMSI")); -->
<!-- 		var th2 = document.createElement("th"); -->
<!-- 		th2.appendChild(document.createTextNode("Failure Count")); -->
<!-- 		var th3 = document.createElement("th"); -->
<!-- 		th3.appendChild(document.createTextNode("Total Duration")); -->


	<script>
// 	$(document).ready(function() {	
// 		$('#failureDurationTable').DataTable({
//  	        "aaData": response,
// 	        "columns": [
// 	            { "title": "IMSI" },
// 	            { "title": "FAILURE COUNT" },
// 	            { "title": "DURATION" }],
// 	        "bScrollInfinite": true,
// 	        "bScrollCollapse": true,
// 	        "sScrollY": "200px"
// 	});
// 	});

	 	$(document).ready(function() {	
		//t = $dataTable = $('#failureDurationTable').DataTable({
			t = $dataTable = $('#failureDurationTable').DataTable({
				"bScrollInfinite": true,
        		"bScrollCollapse": true,
       			"scrollY": "200px",
				"aaData": response,
		        "columns": [
		            { "title": "IMSI" },
		            { "title": "FAILURE COUNT" },
		            { "title": "DURATION" }],
// 		            "bDeferRender": true,
 		         "bProcessing" : true,

// 				responsive : true,
 				"ordering" : false,
// 				"info" : false,
 				"searching" : false,

 
		});
		

			
 		
		});
	
	function formatTable(){
	     if (t) {
     t.destroy();
 			}
 //		$(document).ready(function() {	
		//t = $dataTable = $('#failureDurationTable').DataTable({
			t = $dataTable = $('#failureDurationTable').DataTable({
				//"paging" : false,
// 				"bScrollInfinite": true,
//         		"bScrollCollapse": true,
       			"scrollY": "200px",
				"aaData": response,
		        "columns": [
		            { "title": "IMSI" },
		            { "title": "FAILURE COUNT" },
		            { "title": "DURATION" }],
// 		            "bDeferRender": true,
 		         "bProcessing" : true,

// 				responsive : true,
 				"ordering" : false,
// 				"info" : false,
 				"searching" : false,
// 				// 			      "scrollY":        "200px",
// 				"scrollCollapse" : true,
 //				"paging" : false
 
//		});
		
// 		        $("#button1").click(function(){
// 		        	t.draw();
// 		            t.fnReloadAjax("process.php?txtId=" + $("txtId").val());
// 		        });
			
 		
		});
	}


	</script>

</html>