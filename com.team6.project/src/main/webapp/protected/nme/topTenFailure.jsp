<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Red Hot Chilli Beans</title>

<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-combined.min.cristiana.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script type="text/javascript"
src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js">
</script>
<script>

	function getFailureData() {

		var date = new Date();
		var picker = $('#datetimepicker').data('datetimepicker');
		date = picker.getDate();
		var startDate = date.valueOf();
		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();
		var endDate = date.valueOf();
		

	
		
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root
				+ "/protected/rest/networkmanagement/toptenimsifailures?start="+startDate+"&end="+endDate,
				true);
		xhr.addEventListener('load', function() {
			if (xhr.status == 200) {
				cleanTable();
				var response = JSON.parse(xhr.responseText);
				createTableHead("failureDurationTable", ["IMSI","Failure Count"]);
				createTableBody(response);
			}
			else{
				//bad request, dates could not be parsed. Or no results
				cleanTable();
				var message = 'Error '+xhr.status+': ' + xhr.responseText;
				showError(message);
			}
		}, false);
		xhr.send();
	}

	
	
	function showError(message){
		var errorDiv = document.getElementById("errorDiv");
		errorDiv.innerHTML = message;
	}

	function createTableBody(response) {
		var table = document.getElementById("failureDurationTable");
		var tbody = document.createElement("tbody");
		var max = 10;
		if(response.length < 10){
			max = response.length;
		}
		tbody.id = "tableBody";
		for (var i = 0; i < max; i++) {
			
			var singleResponse = response[i];

			var tr = document.createElement("tr");
			
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			
			var td1 = document.createElement("td");
			td1.appendChild(document.createTextNode(singleResponse[0].imsi));
			var td2 = document.createElement("td");
			td2.appendChild(document.createTextNode(singleResponse[1]));


			tr.appendChild(td1);
			tr.appendChild(td2);

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
					<h1 class="page-header">Top 10 IMSIs which have Failures</h1>
					<p>Please enter a date range:</p>
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
							
							
							<br/> 
							<input type='button' class="btn btn-default" onclick="getFailureData()"
								value="show data" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Table: IMSI, failure count, total duration.</div>
						<div class="panel-body">
							<div class="dataTable_wrapper" id="dataTableDiv">
								<div id="errorDiv"></div>
								<table class="table table-striped table-bordered table-hover"
									id="failureDurationTable">
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