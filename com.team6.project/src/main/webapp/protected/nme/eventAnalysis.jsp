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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/dataTables.responsive.css"
	rel="stylesheet">

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script>
	function getAllTacs() {
		var tacs = {};
		var dropdown = document.getElementById("tacs");
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root + "/protected/rest/userequipment/all", false);
		xhr.send();
		if (xhr.status == 200) {
			tacs = JSON.parse(xhr.responseText);
			for (var i = 0; i < tacs.length; i++) {
				var tac = tacs[i].tac;
				var opt = document.createElement("option");
				opt.text = tac + " - " + tacs[i].vendorName + " - "
						+ tacs[i].model;
				opt.value = tac;
				dropdown.options.add(opt);
			}
		} else {
			alert("error! the response status is : " + xhr.status);
		}

	}

	function getEventIdCauseCode() {
		hideDiv("panelChart");
	    cleanTable();
	    cleanError();
		var tac = document.getElementById("tacs").value;
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root
				+ "/protected/rest/networkmanagement/eventidcausecode/" + tac,
				true);
		xhr.addEventListener('load', function() {
			if (xhr.status == 200) {
				cleanTable();
				var response = JSON.parse(xhr.responseText);
				if (response.length == 0) {
					showError("No data found for the selected user equipment");
				} else {
					createTableHead("eventcauseTable", [ "Event Id", "Cause Code",
					             						"Description", "Occurence" ]);
					createTableBody(response);
					createBarChart(response);
					showDiv("panelChart");
			}}}, false);
		xhr.send();
	}

	function createTableBody(response) {
		var table = document.getElementById("eventcauseTable");
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		for (var i = 0; i < response.length; i++) {
			var singleResponse = response[i];
			var eventCause = singleResponse[0];
			var occurence = singleResponse[1];
			var tr = document.createElement("tr");
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			var td1 = document.createElement("td");
			td1.appendChild(document.createTextNode(eventCause.eventId));
			var td2 = document.createElement("td");
			td2.appendChild(document.createTextNode(eventCause.causeCode));
			var td3 = document.createElement("td");
			td3.appendChild(document.createTextNode(eventCause.description));
			var td4 = document.createElement("td");
			td4.appendChild(document.createTextNode(occurence));
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tbody.appendChild(tr);
		}
		table.appendChild(tbody);
	}

	function startup() {
		loadbar('../sidebar.jsp');
		getAllTacs();
	}

	function createBarChart(response) {
		var barDataArray = [];
		for (var i = 0; i < response.length; i++) {
			var singleResponse = response[i];
			var eventCause = singleResponse[0];
			var occurence = singleResponse[1];
			barDataArray[i] = {
				label : "Envent Id: " + eventCause.eventId + " Cause Code: "
						+ eventCause.causeCode,
				data : [ [ (i + 1), occurence ] ]
			}
		}
		var barOptions = {
			series : {
				bars : {
					show : true,
					barWidth : 0.8
				}
			},
			xaxis : {
				show : false
			},
			grid : {
				hoverable : true
			},
			legend : {
				show : false
			},
			tooltip : true,
			tooltipOpts : {
				content : '%s'
			}
		};
		$.plot($('#flot-bar-chart'), barDataArray, barOptions);
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
					<h1 class="page-header">Event Analysis</h1>
					<p>Please select a User Equipment code</p>
					<div>
						<div id="div1">
							<select name="tacs" id="tacs" class="form-control">
							</select> <br> <input type='button' class="btn btn-default"
								onclick="getEventIdCauseCode()" value="show data" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>

					<div id="panelChart" class="panel panel-default"
						style="display: none;">
						<div class="panel-heading">Occurrences of Event Id/Cause Code per User Equipment</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-bar-chart"></div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">Event Id Cause Code and Occurence
							for selected UserEquipment</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="errorDiv"></div>
								<table class="table table-striped table-bordered table-hover"
									id="eventcauseTable">
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