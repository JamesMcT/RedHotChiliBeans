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
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/sb-admin-2.css" rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">
<!-- <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet"> -->
<link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="../../js/common.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js"></script> 

<script>

	function getFailureData(beginIndex, resultCount) {

		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;

		if(document.getElementById("validationEnabled").checked){
			if(!validateDate(startDate, "Invalid start date") || !validateDate(endDate, "Invalid end date")){
				return false;
			}
		}
		
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root
				+ "/protected/rest/networkmanagement/failurecountandduration?startDate="+startDate+"&endDate="+endDate+"&beginIndex="+beginIndex+"&resultCount="+resultCount,
				true);
		xhr.addEventListener('load', function() {
			if (xhr.status == 200) {
				cleanTable();
				response = JSON.parse(xhr.responseText);
				createTableHead();
				createTableBody();
			}
			else{
				//bad request, dates could not be parsed. Or no results
				cleanTable();
				var message = 'Error '+xhr.status+': ' + xhr.responseText;
				showError(message);
			}
		}, false);
		xhr.send();
	}

	function validateDate(dateString, errorMessage){
		//yyyy-mm-dd hh-mm-ss
		regexPattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$/;
		if(dateString.match(regexPattern)){
			return true;
		}
		else{
			showError(errorMessage + ": " + dateString);
			return false;
		}
	}
	
	function showError(message){
		var errorDiv = document.getElementById("errorDiv");
		errorDiv.innerHTML = message;
	}

	function createTableHead() {
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

	function createTableBody() {
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
		
		//$('#datetimepicker').datetimepicker({
		    //format: 'yyyy-MM-dd hh:mm:ss',
		    //language: 'en'
		  //});
		//$('#datetimepicker').data('datetimepicker').setLocalDate(new Date(2013, 0, 11, 17, 15));

		//$('#datetimepicker2').datetimepicker({
		        //format: 'yyyy-MM-dd hh:mm:ss',
		        //language: 'en'
		      //});
		//$('#datetimepicker2').data('datetimepicker').setLocalDate(new Date(2013, 0, 11, 17, 20));
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
					<div>
						<div id="div1">
							<div id="datetimepicker" class="input-append date">
						      <label>Start date: </label><input type="text" id='startDate'></input>
						      <span class="add-on">
						        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						      </span>
						    </div>

							<br/>
							<div id="datetimepicker2" class="input-append date">
						      <label>End date: </label><input type="text" id='endDate'></input>
						      <span class="add-on">
						        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						      </span>
						    </div>
							
							<br/> 
							<input type='button' class="btn btn-default" onclick="getFailureData(0,50)"
								value="show data" /> <br>
							<input type='checkbox' name='validationEnabled' id='validationEnabled' value='JS Validation Enabled'/> JS Validation Enabled<br/>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Table: IMSI, failure count, total duration.</div>
						<div class="panel-body">
							<div class="dataTable_wrapper" id="dataTableDiv">
								<div id="errorDiv"></div>
								<table class="table table-striped table-bordered table-hover"
									id="failureDurationTable">
								</table>
							</div>
							<!-- /#dataTable_wrapper -->
						</div>
						<!-- /#panel-body -->
					</div>
				</div>
			</div>

		</div>
	</div>

	</div>
	</div>
	<!-- /#wrapper -->
</body>

</html>