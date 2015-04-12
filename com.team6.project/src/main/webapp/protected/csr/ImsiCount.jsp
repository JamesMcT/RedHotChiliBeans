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
<link href="../../css/sb-admin-2.css" rel="stylesheet">
<link href="../../css/bootstrap-combined.min.cristiana.css"
	rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="../../js/common.js"></script>


<script>
	function getRecordsByIMSI() {

		var imsi = document.getElementById("imsi").value;
		var date = new Date();

		var picker = $('#datetimepicker').data('datetimepicker');
		date = picker.getDate();
		var startDate = date.valueOf()

		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();
		var endDate = date.valueOf();

		// 		console.log("Start Date is : " + startDate + " End Date is : "
		// 				+ endDate);
		if (validateImsi(imsi) == true) {
			var xmlhttp;
			window.crossDomain = true;
			xmlhttp = new XMLHttpRequest();

			var root = "${pageContext.servletContext.contextPath}";
			xmlhttp.open("GET", root
					+ "/protected/rest/IMSIEvent/countImsi?imsi=" + imsi
					+ "&startDate=" + startDate + "&endDate=" + endDate, true);
			xmlhttp.send();

			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var response = JSON.parse(xmlhttp.responseText);
					var count = response.length;
					document.getElementById("countResult").innerHTML = count;

					var myTable = "<table id = 'eventCauseTable' class = 'table table-striped table-bordered table-hover'> ";

					myTable += "<thead><tr><th>Cell Id</th><th>Date</th><th>Failure Duration</th></tr></thead><tfoot><tr><th>Cell Id</th><th>Date</th><th>Failure Duration</th></tr></tfoot><tbody>";

					for ( var i in response) {

						var cellId = response[i].cellId;
						var date = response[i].date;
						var duration = response[i].duration;
						
						var d = new Date(date);
						
						myTable += "<tr> <td>" + cellId + "<td>" + d
								+ "<td>" + duration + "<td> </tr>";

					}
					myTable += "</tbody></table>"
					document.getElementById("ImisCountResults").innerHTML = myTable;

				}
				if (xmlhttp.status == 404) {
					alert("Error: No Page Found");
				}

			}
		} else {
			alert("Imsi Validation has failed.")
		}
	}
	/*
		Maybe these functions should be moved into Commons.js - Will leave here for now to 
		prevent Merge issues.
	 */

	function validateImsi(imsi) {

		if (isNotEmpty(imsi, "Please Enter an Imsi") == false) {
			return false;
		} else if (isNaN(imsi) == true) {
			alert("IMSI Field should contain numbers only [0-9]");
			return false;
		} else {
			return true;
		}
	}

	function isNotEmpty(imsi, alertMessage) {
		if (imsi == "") {
			alert(alertMessage);
			return false;
		}
		return true;
	}

	function startup() {
		loadbar('sidebar.html');
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

							<script type="text/javascript"
								src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
								
							</script>
							<script type="text/javascript"
								src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
								
							</script>
							<script type="text/javascript"
								src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
								
							</script>
							<script type="text/javascript"
								src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
								
							</script>

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
								<table class="table table-striped table-bordered table-hover"
									id="ImisCountResults">
									<thead>
										<tr>
											<th>Cell Id</th>
											<th>Date</th>
											<th>Failure Duration</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
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