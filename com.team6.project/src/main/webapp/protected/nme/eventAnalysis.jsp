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

<!-- Adding functions -->
<script src="../../js/common.js"></script>

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
				opt.text = tac;
				opt.value = tac;
				dropdown.options.add(opt);
			}
		} else {
			alert("error! the response status is : " + xhr.status);
		}

	}

	function getEventIdCauseCode() {
		var tac = document.getElementById("tacs").value;
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root
				+ "/protected/rest/networkmanagement/eventidcausecode/" + tac,
				true);
		xhr.addEventListener('load', function() {
			if (xhr.status == 200) {
				cleanTable();
				response = JSON.parse(xhr.responseText);
				createTableHead();
				createTableBody();
			}
		}, false);
		xhr.send();
	}

	function createTableHead() {
		var table = document.getElementById("eventcauseTable");
		var thead = document.createElement("thead");
		thead.id = "tableHead";
		var tr = document.createElement("tr");
		var th1 = document.createElement("th");
		th1.appendChild(document.createTextNode("Event Id"));
		var th2 = document.createElement("th");
		th2.appendChild(document.createTextNode("Cause Code"));
		var th3 = document.createElement("th");
		th3.appendChild(document.createTextNode("Description"));
		var th4 = document.createElement("th");
		th4.appendChild(document.createTextNode("Occurence"));
		tr.appendChild(th1);
		tr.appendChild(th2);
		tr.appendChild(th3);
		tr.appendChild(th4);
		thead.appendChild(tr);
		table.appendChild(thead);
	}

	function createTableBody() {
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

	function cleanTable() {
		var tableBody = document.getElementById("tableBody");
		var tableHead = document.getElementById("tableHead");
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
		getAllTacs();
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
							</select> <br> <input type='button' class="btn btn-default" onclick="getEventIdCauseCode()"
								value="show data" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Table: Event Id Cause Code and
							Occurence for selected UserEquipment</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
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

	</div>
	</div>
	<!-- /#wrapper -->
</body>

</html>