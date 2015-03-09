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

<!-- Adding functions -->
<script src="../../js/common.js"></script>

<script>
function getRecordsByTac() {
		
		
		var tac = document.getElementById("tac").value;
		var fromDate = document.getElementById("fromDate").value;
		var toDate = document.getElementById("toDate").value;
		if (tac && fromDate && toDate) {

			var reqParams = {};
			reqParams.tac = tac;
			reqParams.fromDate = fromDate;
			reqParams.toDate = toDate;

			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";

			var root2 = "/com.team6.project-0.0.1-SNAPSHOT";
			xhr.open("POST", root + "/protected/rest/tac", false);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.send(JSON.stringify(reqParams));
			if (xhr.status == 200) {
				var response = JSON.parse(xhr.responseText);
				if (response.description) {
					alert("Status : " + response.status + " \n Description : "
							+ response.description);
					
					document.getElementById("porba").innerHTML= "proba Changed!";
					
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
								<strong>tac: </strong> <input type="text" name="tac" size="25"
									id="tac" value="21060800">
							</div>
							<div>
								<strong>Please enter from date: </strong> <input type="text"
									size="15" name="fromDate" id="fromDate" value="2013-01-11 17:15:00">
							</div>
							<div>
								<strong>Please enter to date: </strong> <input type="text"
									size="15" name="toDate" id="toDate" value="2013-01-11 17:16:00">
							</div>
							</select> 
							<br> <input type='button' onclick="getRecordsByTac()" value="Search" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>
				<div id=proba>proba
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Number of the search result
						</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div id="searchResult"> semmi
								</div>	
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