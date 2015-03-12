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

<link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="../../js/common.js"></script>


<script>
	function getRecordsByDatePOST() {
		var fromDate = document.getElementById("fromDate").value;
		var toDate = document.getElementById("toDate").value;
		var picker = $('#datetimepicker').data('datetimepicker');
		var picker2 = $('#datetimepicker2').data('datetimepicker');
				
		
		if (fromDate && toDate) {
			var reqParams = {};
			reqParams.fromDate = picker.getDate();
			reqParams.toDate = tpicker2.getDate();
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			var root2 = "/com.team6.project-0.0.1-SNAPSHOT";
			xhr.open("POST", root + "/protected/rest/basedata/datequery", false);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.send(JSON.stringify(reqParams));
			if (xhr.status == 200) {
				var response = JSON.parse(xhr.responseText);
				if (response.description) {
					document.getElementById("searchResult").innerHTML = response.description;
				} else {
					alert("Status : " + response.status);
				}
			} else {
				var response = xhr.response;
				document.getElementById("mainPage").innerHTML = response;
			}
		}
	}
	
function getRecordsByDate() {
		var date = new Date();
		
		var picker = $('#datetimepicker').data('datetimepicker');				
		date = picker.getDate();
		
		var fromDate = date.valueOf();
		
		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();
		var toDate = date.valueOf();
		
		//var fromDate = 1357924500000;
		//var toDate = 1357924560000;
			
		
		if (fromDate && toDate) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/protected/rest/basedata/datequery" + "?firstDate=" + fromDate + "&secondDate=" + toDate,  true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					
					var response = JSON.parse(xhr.responseText);
				//	document.getElementById("searchResult").innerHTML = response;
					createTableBody();
				}
			}, false);
			xhr.send();
		}
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
					<h1 class="page-header">Show IMSI of Call Failures</h1>
					<p>Please Enter a 2 dates</p>
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


							
							<br> <input id=button1 type='button'
								onclick="getRecordsByDate()" value="Search" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Number of the search result</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="searchResult"></div>
							
							
								<script>
								function createTableBody() {
		//var table = document.getElementById("eventcauseTable");
		var tbody = document.createElement("tbody");
		tbody.id = "tableBody";
		for (var i = 0; i < response.length; i++) {
			var date = singleResponse[0];
			var imsi = singleResponse[1];
			var tr = document.createElement("tr");
			if (i % 2) {
				tr.className = "even gradeA";
			} else {
				tr.className = "odd gradeA";
			}
			var td1 = document.createElement("td");
			td1.appendChild(document.createTextNode(id));
			var td2 = document.createElement("td");
			td2.appendChild(document.createTextNode(date));

			tr.appendChild(td1);
			tr.appendChild(td2);


			tbody.appendChild(tr);
		}
		table.appendChild(tbody);
	}
								</script>
							
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
</body>

</html>