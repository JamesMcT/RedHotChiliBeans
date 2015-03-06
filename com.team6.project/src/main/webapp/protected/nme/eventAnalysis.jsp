<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Red Hot Chilli Beans</title>
   
    <!-- Bootstrap Core CSS -->
	<link href="../../css/bootstrap.min.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="../../css/sb-admin-2.css" rel="stylesheet">
    
    <script>
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
    </script>

</head>

<body onload="getAllTacs()">

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Red Hot Chilli Beans</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
             <li class="active"><a href="index.html">Home</a></li>
	     <li class="active"><a href="../logout.jsp">Logout</a></li> 
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="eventAnalysis.jps"> Event Analysis</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Event Analysis</h1>
					<p>Please select a User Equipment code</p>
					<div>
						<div id="div1">
							<select name="tacs" id="tacs" class="form-control">
							</select>
							<br>
						</div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>

</html>