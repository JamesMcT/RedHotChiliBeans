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
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
<!-- Adding functions -->
<script src="../../js/common.js"></script>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>

<script>
	function getAllFailureTypes() {
		var tacs = {};
		var dropdown = document.getElementById("failureCode");
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root + "/protected/rest/supportengineer/failuretype",
				false);
		xhr.send();
		if (xhr.status == 200) {
			types = JSON.parse(xhr.responseText);
			for (var i = 0; i < types.length; i++) {
				var type = types[i].failureCode;
				var descrption = types[i].descrption;

				var opt = document.createElement("option");
				opt.text = type + " - " + descrption;
				opt.value = type;
				dropdown.options.add(opt);
			}
		} else {
			alert("error! the response status is : " + xhr.status);
		}

	}

	function getFailureData() {

		var failureCode = document.getElementById("failureCode").value;

		if (validateFailureCode(failureCode) == true) {

			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr
					.open(
							"GET",
							root
									+ "/protected/rest/supportengineer/failurecode?failureCode="
									+ failureCode, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					cleanTable();
					response = JSON.parse(xhr.responseText);
					createTableHead();
					createTableBody();
				} else {
					//bad request, dates could not be parsed. Or no results
					cleanTable();
					var message =  xhr.responseText;
					showError(message);
				}
			}, false);
			xhr.send();

		} else {
			//	alert("Error: Validation failed Found");

		}
	}

	function showError(message) {
		var errorDiv = document.getElementById("errorDiv");
		errorDiv.innerHTML = message;
	}

	function createTableHead() {
		var table = document.getElementById("failureDurationTable");
		var thead = document.createElement("thead");
		thead.id = "tableHead";
		var tr = document.createElement("tr");
		var th1 = document.createElement("th");
		var th2 = document.createElement("th");
		var th3 = document.createElement("th");

		th1.appendChild(document.createTextNode("IMSI"));
		th2.appendChild(document.createTextNode("Date"));
		th3.appendChild(document.createTextNode("Call Failure Type"));

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

		var baseData = [];
		for (var i = 0; i < response.length; i++) {

			var singleResponse = response[i];
			var baseData = singleResponse[0];
			var ff = singleResponse[2];

			var failure = baseData.failure

			var tr = document.createElement("tr");

			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}

			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");

			td1.appendChild(document.createTextNode(singleResponse[0]));
			td2.appendChild(document.createTextNode(Date(singleResponse[1])));
			td3.appendChild(document.createTextNode(ff.descrption));

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

	function validateFailureCode(failureCode) {

		if (isNotEmpty(failureCode, "Please Enter a Failure Code") == false) {
			return false;
		} else if (isNaN(failureCode) == true) {
			alert("Failure Code Field should contain numbers only");
			return false;
		} else {
			// alert("True Entered on IMSI...")
			return true;
		}
	}

	function isNotEmpty(failureCode, alertMessage) {
		if (failureCode == "") {
			alert(alertMessage);
			return false;
		}
		return true;
	}

	function startup() {
		loadbar('../sidebar.jsp');
		getAllFailureTypes();
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
					<h1 class="page-header">IMSIs By Call Failure Type</h1>
					<div>
							<p>Please select a Call Failure Type</p>
								<div id="div1">
									<select name="failureCode" id="failureCode"
										class="form-control">
									</select> <br> <input type='button' class="btn btn-default"
										onclick="getFailureData()" value="show data" /> <br>
								</div>

								
							<!-- /#div1 -->
						</div>
						<br>
					</div>
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">Table: IMSI, Failure Type.</div>
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


		<!-- /#wrapper -->
</body>

</html>