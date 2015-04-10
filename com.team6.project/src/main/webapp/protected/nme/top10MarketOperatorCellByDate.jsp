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
<link rel="stylesheet" type="text/css" media="screen"
href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Flot Charts JavaScript -->
<script src="${pageContext.request.contextPath}/bower_components/flot/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/flot/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/flot/jquery.flot.resize.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/flot/jquery.flot.time.js"></script>
<script
	src="${pageContext.request.contextPath}/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>

<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script type="text/javascript"
src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js">
</script>

<script>
	//rework this
	function getTOP10MarketOperatorCellByDate() {

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
			xhr.open("GET", root + "/protected/rest/networkmanagement/top10MOC"
					+ "?fromDate=" + fromDate + "&toDate=" + toDate, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					cleanTable();
					var response = JSON.parse(xhr.responseText);
					//document.getElementById("searchResult").innerHTML = response;
					createTableHead();
					createTableBody(response);
					createBarChart(response);
				}
			}, false);
			xhr.send();
		}
	}

	function createBarChart(response) {
		  var tot = 0;
		  var baseData = [
		  ];
		  var occurrence = [
		  ];
		  var barDataArray = [
		  ];
		  var max = 10;
		  for (var i = 0; i < response.length; i++) {
		    var singleResponse = response[i];
		    baseData[i] = singleResponse[0];
		    occurrence[i] = singleResponse[1];
		    tot = tot + occurrence[i];
		  }
		  if (baseData.length < 10) {
		    max = baseData.length;
		  }
		  for (var i = 0; i < max; i++) {
		    var percentage = ((occurrence[i] / tot) * 100).toFixed(2);
		    barDataArray[i] = {
		      label: 'Country: ' + baseData[i].operatorCountry.mcc
		      + ' Operator: ' + baseData[i].operatorCountry.mnc
		      + ' Node: ' + baseData[i].cellId + ' - ' + percentage
		      + '%',
		      data: [
		        [(i + 1),
		        occurrence[i]]
		      ]
		    }
		  }
		  var barOptions = {
		    series: {
		      bars: {
		        show: true,
		        barWidth: 0.8
		      }
		    },
		    xaxis: {
		      show: false
		    },
		    grid: {
		      hoverable: true
		    },
		    legend: {
		      show: false
		    },
		    tooltip: true,
		    tooltipOpts: {
		      content: '%s'
		    }
		  };
		  $.plot($('#flot-bar-chart'), barDataArray, barOptions);
		}

	function createTableHead() {
		var table = document.getElementById("top_10_MOC_Table");
		var thead = document.createElement("thead");
		thead.id = "tableHead";
		var tr = document.createElement("tr");
		var th1 = document.createElement("th");
		th1.appendChild(document.createTextNode("Market"));
		var th2 = document.createElement("th");
		th2.appendChild(document.createTextNode("Operator"));
		var th3 = document.createElement("th");
		th3.appendChild(document.createTextNode("Cell"));
		var th4 = document.createElement("th");
		th4.appendChild(document.createTextNode("Occurence"));
		tr.appendChild(th1);
		tr.appendChild(th2);
		tr.appendChild(th3);
		tr.appendChild(th4);
		thead.appendChild(tr);
		table.appendChild(thead);
	}

	function createTableBody(response) {
		var table = document.getElementById("top_10_MOC_Table");
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		var i = 0;
		while (i < response.length && i < 10) {
			var singleResponse = response[i];
			var baseDataRow = singleResponse[0];
			var summa = singleResponse[1];
			var tr = document.createElement("tr");
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			var td1 = document.createElement("td");
			td1.appendChild(document
					.createTextNode(baseDataRow.operatorCountry.mcc + " ("
							+ baseDataRow.operatorCountry.country + ")"));
			var td2 = document.createElement("td");
			td2.appendChild(document
					.createTextNode(baseDataRow.operatorCountry.mnc + " ("
							+ baseDataRow.operatorCountry.operator + ")"));
			var td3 = document.createElement("td");
			td3.appendChild(document.createTextNode(baseDataRow.cellId));
			var td4 = document.createElement("td");
			td4.appendChild(document.createTextNode(summa));

			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tbody.appendChild(tr);
			i += 1;
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
					<h1 class="page-header">TOP 10 Market/Operator/Cell By Date</h1>
					<p>Please give a time period</p>
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
								onclick="getTOP10MarketOperatorCellByDate()" value="Search" />
							<br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Table: Top 10
							Market/Operator/Cell failure</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="top_10_MOC_Table">
								</table>
							</div>
							<!-- /#dataTable_wrapper -->
						</div>
						<!-- /#panel-body -->
					</div>
				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">Top Ten Bar Chart</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="flot-bar-chart"></div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->

			</div>

		</div>
	</div>
	<!-- /#wrapper -->
</body>

</html>