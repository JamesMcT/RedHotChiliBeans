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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


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
					cleanError();
					var response = JSON.parse(xhr.responseText);
					createTableHead("failureDurationTable",["IMSI","Date"]);
					createTableBody(response);
				} else {
					//bad request, dates could not be parsed. Or no results
					cleanTable();
					cleanError();
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
			var td2 = document.createElement("td");

			td1.appendChild(document.createTextNode(singleResponse[0]));
			td2.appendChild(document.createTextNode(new Date(singleResponse[1])));

			tr.appendChild(td1);
			tr.appendChild(td2);

			tbody.appendChild(tr);
		}
		table.appendChild(tbody);
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
							<div class="panel-heading">Effected IMSI and Date.</div>
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