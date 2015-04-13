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
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/css/table-pagination.css">

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
	var tablepagination = {
		"tablepage" : 0,
		"maxpage" : 0,
		"recordPerPage" : 50,
		"data" : []
	}
	function getImsiByDate() {
		cleanTablePagination();
		hideDiv("previous");
		hideDiv("next");
		var dates = getDatesFromDatePicker();
		var fromDate = dates[0];
		var toDate = dates[1];

		if (fromDate && toDate) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/protected/rest/supportengineer/datequery"
					+ "?firstDate=" + fromDate + "&secondDate=" + toDate, true);
			xhr
					.addEventListener(
							'load',
							function() {
								if (xhr.status == 200) {
									cleanTable();
									cleanError();
									tablepagination.data = JSON
											.parse(xhr.responseText);
									tablepagination.maxpage = Math
									.ceil(tablepagination.data.length
											/ parseFloat(tablepagination.recordPerPage));
									createTableHead("ImsiFailureTable", [
											"Imsi", "Failure Type",
											"Occurrence" ]);
									
									createTableBody("ImsiFailureTable");
								} else {
									cleanTable();
									cleanError();
									var message = 'Error ' + ': '
											+ xhr.responseText;
									showError(message);
								}
							}, false);
			xhr.send();
		} else {
			cleanTable();
			cleanError();
			var message = 'Error : Please select a value for both dates';
			showError(message);
		}
	}

	function createTableBody(tableId) {
		showLinkPrevNext();
		var table = document.getElementById(tableId);
		if (document.getElementById("tableBody")) {
			document.getElementById("tableBody").parentNode
					.removeChild(document.getElementById("tableBody"));
		}
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		var max = (tablepagination.recordPerPage * tablepagination.tablepage)
				+ tablepagination.recordPerPage;
		if (tablepagination.data.length < max) {
			max = tablepagination.data.length;
		}
		for (var i = (tablepagination.recordPerPage * tablepagination.tablepage); i < max; i++) {
			var imsi = tablepagination.data[i][0];
			var failure = tablepagination.data[i][1];
			var occurrence = tablepagination.data[i][2];
			var tr = document.createElement("tr");
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			td1.appendChild(document.createTextNode(imsi));
			tr.appendChild(td1);
			td2.appendChild(document.createTextNode(failure.descrption));
			tr.appendChild(td2);
			td3.appendChild(document.createTextNode(occurrence));
			tr.appendChild(td3);
			tbody.appendChild(tr);
		}
		table.appendChild(tbody);

	}

	function previous() {
		tablepagination.tablepage = tablepagination.tablepage - 1;
		createTableBody("ImsiFailureTable");

	}

	function next() {
		tablepagination.tablepage = tablepagination.tablepage + 1;
		createTableBody("ImsiFailureTable");

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
								<div class="linkblock">
									<span id="previous" style="display: none"><a
										onclick="previous()" href="javascript:void(0);"> prev </a></span> <span
										id="next" style="display: none" class="nextlink"><a
										onclick="next()" href="javascript:void(0);"> next </a></span>
								</div>
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