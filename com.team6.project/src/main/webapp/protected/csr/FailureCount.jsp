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
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-combined.min.cristiana.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">
<!--  href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css" -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script
	src="${pageContext.request.contextPath}/js/customerServiceCommon.js"></script>

<script>
	function getRecordsByIMSI() {
		cleanTable();
		document.getElementById("countResult").innerHTML = "";
		var imsi = document.getElementById("imsi").value;
		var dates = getDatesFromDatePicker();
		var startDate = dates[0];
		var endDate = dates[1];
		if (startDate && endDate) {

			if (validateImsi(imsi) == true) {
				var xmlhttp;
				window.crossDomain = true;
				xmlhttp = new XMLHttpRequest();
				console.log("Start Date is : " + startDate + " EndDate is : "
						+ endDate + " Imsi is : " + imsi);
				var root = "${pageContext.servletContext.contextPath}";
				xmlhttp
						.open(
								"GET",
								"http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/customerservice/countImsi?imsi="
										+ imsi
										+ "&startDate="
										+ startDate
										+ "&endDate=" + endDate, true);
				xmlhttp.send();

				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						cleanTable();
						cleanError();
						var response = JSON.parse(xmlhttp.responseText);

						var count = response.length;

						document.getElementById("countResult").innerHTML = count;
						createTableHead("ImisCountResults", [ "Cell Id",
								"Date", "Failure Duration" ]);
						createTableFailureCountBody("ImisCountResults",
								response);
					} else {
						cleanTable();
						cleanError();
						var message = 'Error ' + ': '
								+ xmlhttp.responseText;
						showError(message);
					}

				}
			} else {
				console.log("Imsi Validation has failed.")
			}
		} else {
			cleanTable();
			cleanError();
			var message = 'Error : Please select a value for both dates';
			showError(message);
		}
	}

	/*
		Maybe these functions should be moved into Commons.js - Will leave here for now to 
		prevent Merge issues.
	 */

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
					<h1 class="page-header">IMSI Failure Count</h1>
					<p>Please enter an IMSI and time period</p>
					<div>
						<div id="div1">


							<input type="text" class="form-control-inline" id="imsi"
								placeholder="IMSI">

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
												new Date(2013, 0, 11, 17, 15));
							</script>


							<script type="text/javascript">
								$('#datetimepicker2').datetimepicker({
									format : 'yyyy-MM-dd hh:mm:ss',
									language : 'en'
								});
								$('#datetimepicker2').data('datetimepicker')
										.setLocalDate(
												new Date(2013, 0, 11, 17, 20));
							</script>



							<br> <input id=button1 type='button' class="btn btn-default"
								onclick="getRecordsByIMSI()" value="Search" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Total Number of Failures</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="countResult"></div>
							</div>
							<!-- /#dataTable_wrapper -->
						</div>
						<!-- /#panel-body -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="errorDiv"></div>
								<table class="table table-striped table-bordered table-hover"
									id="ImisCountResults">
								</table>
							</div>
							<!-- /.table-responsive -->

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- /#wrapper -->
</body>

</html>