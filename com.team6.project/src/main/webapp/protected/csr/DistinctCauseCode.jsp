<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Add New User Page">
<meta name="author" content="Cristiana">
<title>Red Hot Chilli Beans</title>
<!-- Adding CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/customerServiceCommon.js"></script>
<script>
	function getEventIdCauseCode() {
		cleanTable();
		var imsi = document.getElementById("imsi").value;
		if (validateImsi(imsi) == true) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/protected/rest/customerservice/uniqueec/"+ imsi, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					cleanTable();
					response = JSON.parse(xhr.responseText);
					createTableHead("eventcauseTable", [ "Cause Code",
							"Event ID", "Description", "Occurrences" ]);
					createTableCauseCodeEventIdOccurrenceBody(
							"eventcauseTable", response);
				}
			}, false);
			xhr.send();
		}
	}
</script>
<head>
<body onload="loadbar('../sidebar.jsp')">
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"> </nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Unique Cause Code per IMSI</h1>
					<div class="panel-heading">Please type the IMSI you want to
						analyze</div>
					<form class="form-inline">
					<div class="form-group">
						<input id="imsi" class="form-control" type="text"
							placeholder="IMSI"> 
							</div><!-- <span class="input-group-btn"> --><button
								onclick="getEventIdCauseCode()" class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
							</button>
					</form>
					<br>
					<div class="panel panel-default">
						<div class="panel-heading">Event Id, Cause Code and
							Occurrence for the selected IMSI</div>
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
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</body>
</html>