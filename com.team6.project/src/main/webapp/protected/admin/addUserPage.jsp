<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Add New User Page">
<meta name="author" content="Cristiana">

<title>Red Hot Chilli Beans</title>

<!-- Adding CSS -->
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/sb-admin-2.css" rel="stylesheet">

<script>
	function addUser() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var userRole = document.getElementById("userRole").value;
		if (username && password && userRole) {
			var u = {};
			u.userId = username;
			u.password = password;
			u.role = userRole;
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr
					.open("POST", root + "/protected/rest/usermanagement/add",
							false);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.send(JSON.stringify(u));
			if (xhr.status == 200) {
				var response = JSON.parse(xhr.responseText);
				if (response.description) {
					alert("Status : " + response.status + " \n Description : "
							+ response.description);
				} else {
					alert("Status : " + response.status);
				}
			}
			clean();
		}
	}
	
function clean(){
	document.getElementById("username").value = "";
	document.getElementById("password").value = "";
}
</script>
<head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
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
					<li><a href="addUserPage.jsp"> Add New User</a></li>
					<li><a href="changeUserPage.jsp"> Change User Role</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Add New User</h1>
					<p>Please insert user name, password and select a role:</p>
					<div>
						<div>
							<input type="text" name="username" size="25" id="username"
								class="form-control" placeholder="User Name" required> <br>
						</div>
						<div>
							<input type="password" size="15" name="password" id="password"
								class="form-control" placeholder="Password" required> <br>
						</div>
						<div>
							<select name="userRole" id="userRole" class="form-control">
								<option value="Network Management Engineer">Network
									Management Engineer</option>
								<option value="Support Engineer">Support Engineer</option>
								<option value="Customer Service">Customer Sevice</option>
							</select> <br>
						</div>
						<div>
							<br> <input type='button' onclick="addUser()" value="submit" />
						</div>
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