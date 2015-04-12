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
				var response = JSON.parse(xhr.responseText);
				createTableHead("eventcauseTable", [ "Event Id", "Cause Code",
						"Description", "Occurence" ]);
				createTableBody(response);
				createPieChart(response);
				showDiv("pieChart");
			}
		}, false);
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
	
	//Flot Pie Chart
	function createPieChart(response){
		var data = []
		var other = 0;
		for (var i = 0; i < response.length; i++) {
			var singleResponse = response[i];
			var eventCause = singleResponse[0];
			var occurence = singleResponse[1];
			if (i<10){
				data[i]={label: eventCause.eventId+" "+eventCause.causeCode,
						data: occurence}
			}
			else{
				other = other+occurence;
			}
		}
		if(other > 0){
			data[10]={label: "other",
					data: other}
		}
		var plotObj = $.plot($("#flot-pie-chart"), data, {
	        series: {
	            pie: {
	                show: true
	            }
	        },
	        grid: {
	            hoverable: true
	        },
	        tooltip: true,
	        tooltipOpts: {
	            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
	            shifts: {
	                x: 20,
	                y: 0
	            },
	            defaultTheme: false
	        }
	    });
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
				</div>
				 <div class="col-lg-6">
                    <div id="pieChart" class="panel panel-default" style="display:none;">
                        <div class="panel-heading">
                            Top ten with max number of occurrences
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="flot-pie-chart"></div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
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
	<!-- /#wrapper -->
	
	<!-- jQuery -->
    <script src="../../js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Flot Charts JavaScript -->
    <script src="../../bower_components/flot/excanvas.min.js"></script>
    <script src="../../bower_components/flot/jquery.flot.js"></script>
    <script src="../../bower_components/flot/jquery.flot.pie.js"></script>
    <script src="../../bower_components/flot/jquery.flot.resize.js"></script>
    <script src="../../bower_components/flot/jquery.flot.time.js"></script>
    <script src="../../bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>

</body>

</html>