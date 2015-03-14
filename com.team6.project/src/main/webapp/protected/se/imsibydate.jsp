<!DOCTYPE html>
<html lang="en" ng-app>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>

<!-- Adding CSS -->
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/sb-admin-2.css" rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">

<!-- <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet"> -->
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="../../css/se.css">

<!-- Adding functions -->
<script src="../../js/basedata.js"></script>
<script src="../../js/common.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js"></script>

<script type="text/javascript">
	function startup() {
		loadbar('sidebar.html');

	}
</script>
</head>

<body onload="startup()">

	<div id="wrapper" ng-app="App" ng-controller="readDates">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"></nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Search IMSI failures by date</h1>



					<div id="pickerarea">
						<div id="datetimepickerbox" class="input-append date">
							<input type="text" required="required" ng-model="firstDate"></input> <span
								class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"></i>
							</span>
						</div>

						<div id="datetimepickerbox" class="input-append date">
							<input type="text" required="required"  ng-model="secondDate"></input> <span
								class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"></i>
							</span>
						</div>
												<p id="errormess"></p>
					
						<button onclick="onSelect()"
							ng-click='sayHello(firstDate,secondDate)'>search</button>
					</div>
					<div class="panel-body">
						<div class="dataTable_wrapper">


							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>IMSI</th>
									</tr>
								</thead>
								<tbody>
									<tr class="odd gradeA" ng-repeat="x in baseDataDate">
										<td ng-class="{active: active}">{{x}}</td>

									</tr>
								</tbody>
							</table>
							<p id="errormess"></p>

						</div>
													<p id="errormess"></p>
						
						<!-- /.table-responsive -->

					</div>
					<!-- /.panel-body -->
				</div>

				<div id="errorDiv">
					<table class="table table-striped table-bordered table-hover"
						id="failureDurationTable">
					</table>
				</div>


			</div>

		</div>


	</div>
	<!-- /#wrapper -->


	<script type="text/javascript"
		src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
		
	</script>

</body>

</html>
