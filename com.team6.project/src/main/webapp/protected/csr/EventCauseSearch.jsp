<!DOCTYPE html>
<title>Red Hot Chilli Beans</title>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Customer Service Index">
<meta name="author" content="James">

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${pageContext.request.contextPath}/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">


<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/customerServiceCommon.js"></script>
<script lang="JavaScript" type="text/javascript">
	function findByImsi() {
		cleanTable();
		var imsi = document.getElementById('imsi_attr').value;
		if (validateImsi(imsi) == true) {
			var xmlhttp;

			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				window.crossDomain = true;
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				alert("IE5/ IE6 etc")
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp
					.open(
							"GET",
							"http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/customerservice/"
									+ imsi, true);
			xmlhttp.send();

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var eventCause = JSON.parse(xmlhttp.responseText);
					createTableHead("eventCauseTable", [ "Cause Code",
					         							"Event ID", "Description"]);
					createTableCauseCodeEventIdBody("eventCauseTable", eventCause);
				}
				if (xmlhttp.status == 404) {
					alert("Error: No Page Found");
				}

			}
		} else {
			//alert("Imsi Validation has failed.....")
		}
	}
</script>


</head>

<body onload="loadbar('../sidebar.jsp')">

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"></nav>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Event Cause Search</h1>
					<div class="panel-heading">Search for Event Causes by IMSI</div>

					<form class="form-inline">
						<!--  <div class="form-group">
							<label class="sr-only">IMSI</label>
							<p class="form-control-static"></p>
						</div>-->
						<div class="form-group">
							<label for="imsi_attr" class="sr-only">IMSI</label> <input
								type="text" class="form-control" id="imsi_attr"
								placeholder="IMSI">
						</div>
						<button type="button" class="btn btn-default"
							onclick="findByImsi()">
							<i class="fa fa-search"></i>
						</button>
					</form>
					<br>
					<div class="panel panel-default">
					<div class="panel-heading">Event Id Cause Code for the selected IMSI</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<div id="errorDiv"></div>
							<table class="table table-striped table-bordered table-hover"
									id="eventCauseTable">
								</table>
							</div>
							<!-- /.table-responsive -->

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="../../js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../../js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../../js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="../../js/jquery.dataTables.min.js"></script>
	<script src="../../js/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../../js/sb-admin-2.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true,
				"ordering" : false,
				"info" : false,
				"searching" : false,
				// 			      "scrollY":        "200px",
				"scrollCollapse" : true,
				"paging" : false

			});
		});
	</script>

</body>

</html>