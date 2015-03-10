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
	function getRecordsByTacPOST() {

		

		var pickedTac = document.getElementById("tacs").value;
		var fromDate = document.getElementById("fromDate").value;
		var toDate = document.getElementById("toDate").value;
		var picker = $('#datetimepicker').data('datetimepicker');
		var picker2 = $('#datetimepicker2').data('datetimepicker');
		
		
		document.getElementById("button1").value = picker.getDate();
		
		if (pickedTac && fromDate && toDate) {

			var reqParams = {};
			reqParams.tac = pickedTac;
			reqParams.fromDate = picker.getDate();
			reqParams.toDate = tpicker2.getDate();

			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";

			var root2 = "/com.team6.project-0.0.1-SNAPSHOT";
			xhr.open("POST", root + "/protected/rest/tac", false);
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

	
function getRecordsByTac() {

		

		var pickedTac = document.getElementById("tacs").value;
		
		//var fromDate = 1357924500000;
		//var toDate = 1357924560000;
		
		var date = new Date();
		
		var picker = $('#datetimepicker').data('datetimepicker');				
		date = picker.getDate();
		
		var fromDate = date.valueOf();
		
		var picker2 = $('#datetimepicker2').data('datetimepicker');
		date = picker2.getDate();
		var toDate = date.valueOf();
		
		
		
		//document.getElementById("button1").value = picker.getDate();
		
		if (pickedTac && fromDate && toDate) {

			

			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";

			
			xhr.open("GET", root + "/protected/rest/tac" + "?tac=" + pickedTac + "&fromDate=" + fromDate + "&toDate=" + toDate, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					
					var response = JSON.parse(xhr.responseText);
					document.getElementById("searchResult").innerHTML = response;
				}
			}, false);
			xhr.send();

		}
	}
	
	
	
	
	
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

	function startup() {
		loadbar('sidebar.html');
		getAllTacs();
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
					<h1 class="page-header">Tac Time Count</h1>
					<p>Please give a tac and time period</p>
					<div>
						<div id="div1">
							<div>
								<select name="tacs" id="tacs" class="form-control">
								</select>
							</div>

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




							<div>
								<strong>Please enter from date: </strong> <input type="text"
									size="15" name="fromDate" id="fromDate"
									value="2013-01-11 17:15:00">
							</div>
							<div>
								<strong>Please enter to date: </strong> <input type="text"
									size="15" name="toDate" id="toDate" value="2013-01-11 17:16:00">
							</div>
							
							<br> <input id=button1 type='button'
								onclick="getRecordsByTac()" value="Search" /> <br>
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