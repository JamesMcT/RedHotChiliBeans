<!DOCTYPE html>
<html lang="en" ng-app>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Adding CSS -->
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/sb-admin-2.css" rel="stylesheet">
<link href="../../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../../css/dataTables.responsive.css" rel="stylesheet">
<link href="../../css/se.css" rel="stylesheet">

<!-- <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet"> -->

<!-- Adding functions -->
<script src="../../js/basedata.js"></script>
<script src="../../js/common.js"></script>
<script src="../../js/angular.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>



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
							<input type="text" ng-model="firstDate"></input>
						</div>

						<div id="datetimepickerbox" class="input-append date">
							<input type="text" ng-model="secondDate"></input>
						</div>

						<button ng-click='sayHello(firstDate,secondDate)'>search</button>
					</div>
					<!--  <h1>IMSIs</h1>-->
					<h3>IMSIs</h3>
					<div class="panel-body">
						<div class="dataTable_wrapper scrollableContainer">

							<div class="scrollingArea">

								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr class="odd gradeA" ng-repeat="x in baseDataDate">
											<td><div class="imsi">{{x}}</div></td>

										</tr>
									</tbody>
								</table>

							</div>
							<div id="errorDiv">
								<table class="table table-striped table-bordered table-hover"
									id="failureDurationTable">
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.panel-body -->
				</div>




			</div>

		</div>


	</div>
	<!-- /#wrapper -->




	</script>

</body>

</html>
