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
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-combined.min.cristiana.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/dataTables.responsive.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js">
	
</script>

<script>
	//rework this
	function getImsiByDate() {

		var date = new Date();

		var picker = $('#datetimepicker').data('datetimepicker');
		date = picker.getDate();

		var fromDate = date.valueOf();

		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();
		var toDate = date.valueOf();

		if (fromDate && toDate) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/protected/rest/supportengineer/datequery"
					+ "?firstDate=" + fromDate + "&secondDate=" + toDate, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					cleanTable();
					cleanError();
					response = JSON.parse(xhr.responseText);
					createTableHead("ImsiFailureTable", [ "Imsi", "Date",
							"Failure Type" ]);
					createTableBody("ImsiFailureTable", response);
				} else {
					cleanTable();
					var message = 'Error ' + xhr.status + ': '
							+ xhr.responseText;
					showError(message);
				}
			}, false);
			xhr.send();
		} else {
			alert("Please select a value for both dates");
		}
	}

	function createTableBody(tableId, response) {
		var table = document.getElementById(tableId);
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		for (var i = 0; i < response.length; i++) {
			var baseData = response[i];
			var tr = document.createElement("tr");
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			td1.appendChild(document.createTextNode(baseData.imsi));
			tr.appendChild(td1);
			td2.appendChild(document.createTextNode(Date(baseData.date)));
			tr.appendChild(td2);
			var failure = baseData.failure
			td3.appendChild(document.createTextNode(failure.descrption));
			tr.appendChild(td3);
			tbody.appendChild(tr);
		}
		table.appendChild(tbody);

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
					<h1 class="page-header">Search IMSI failures by date</h1>
					<p>Please select a time period</p>
					<div>
						<div id="div1">


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
										.setLocalDate(
												new Date(2013, 1, 19, 19, 35));
							</script>


							<script type="text/javascript">
								$('#datetimepicker2').datetimepicker({
									format : 'yyyy-MM-dd hh:mm:ss',
									language : 'en'
								});
								$('#datetimepicker2').data('datetimepicker')
										.setLocalDate(
												new Date(2013, 1, 19, 19, 40));
							</script>



							<br> <input id=button1 type='button' class="btn btn-default"
								onclick="getImsiByDate()" value="Search" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Imsi affected by call failures</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="errorDiv"></div>
								<table class="table table-striped table-bordered table-hover"
									id="ImsiFailureTable">
								</table>
							</div>
							<!-- /#dataTable_wrapper -->
						</div>
						<!-- /#panel-body -->
					</div>
				</div>

			</div>
		</div>
		<!-- /#wrapper -->
</body>

</html>