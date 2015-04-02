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
<link href="../../css/bootstrap-combined.min.cristiana.css" rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">

<!-- <link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css"
	rel="stylesheet"> -->
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<!-- Adding functions -->
<script src="../../js/common.js"></script>


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
		
		//var fromDate = 1357924500000;
		//var toDate = 1357924560000;
			
		
		if (fromDate && toDate) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/protected/rest/basedata/top10MOC" + "?fromDate=" + fromDate + "&toDate=" + toDate, true);
			xhr.addEventListener('load', function() {
				if (xhr.status == 200) {
					cleanTable();
					response = JSON.parse(xhr.responseText);
					//document.getElementById("searchResult").innerHTML = response;
					createTableHead();
					createTableBody();
				}
			}, false);
			xhr.send();
		}
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

function createTableBody() {
	var table = document.getElementById("top_10_MOC_Table");
	var tbody = document.createElement("tbody");
	tbody.id = "tableBody";
	for (var i = 0; i < response.length; i++) {
		var singleResponse = response[i];
		var tr = document.createElement("tr");
		if (i % 2) {
			tr.className = "even gradeA";
		} else {
			tr.className = "odd gradeA";
		}
		var td1 = document.createElement("td");
		td1.appendChild(document.createTextNode(singleResponse[0] + " (" + singleResponse[1] + ")"));
		var td2 = document.createElement("td");
		td2.appendChild(document.createTextNode(singleResponse[2] + " (" + singleResponse[3] + ")"));
		var td3 = document.createElement("td");
		td3.appendChild(document.createTextNode(singleResponse[4]));
		var td4 = document.createElement("td");
		td4.appendChild(document.createTextNode(singleResponse[5]));
		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tbody.appendChild(tr);
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

							<script type="text/javascript"
								src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">								
							</script>
							<script type="text/javascript"
								src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">								
							</script>
							<script type="text/javascript"
								src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
							</script>	
							
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
								onclick="getTOP10MarketOperatorCellByDate()" value="Search" /> <br>
						</div>
						<!-- /#div1 -->
					</div>
					<br>
				</div>

				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Table: Top 10 Market/Operator/Cell failure</div>
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
				
			</div>

		</div>
	</div>

	
	<!-- /#wrapper -->
</body>

</html>