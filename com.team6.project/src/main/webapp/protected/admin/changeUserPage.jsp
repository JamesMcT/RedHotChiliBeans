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
	var user;

	function findUser() {
		var username = document.getElementById("username").value;
		var currentDiv = document.getElementById("div2");
		if (username) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET",
					root + "/protected/rest/usermanagement/" + username, false);
			xhr.send();
			if (xhr.status == 200) {
				user = JSON.parse(xhr.responseText);
				if (user.userId) {
					var currentRole = document
							.createTextNode("The current role of the user is : "
									+ user.role);
					currentDiv.appendChild(currentRole);
					showDivs();
				} else {
					alert("User not found!");
				}
			} else {
				alert("error! the response status is : " + xhr.status);
			}

		}
	}

	function updateUser() {
		var role = document.getElementById("userRole").value;
		if (user.userId && role) {
			var u = {};
			u.userId = user.userId;
			u.password = user.password;
			u.role = role;
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("POST", root + "/protected/rest/usermanagement/update",
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
		}
	}
	
	function getAllUser() {
			var users = {};
			var dropdown = document.getElementById("users");
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET",
					root + "/protected/rest/usermanagement/all", false);
			xhr.send();
			if (xhr.status == 200) {
				users = JSON.parse(xhr.responseText);
				for(var i =0 ; i< users.length ; i++){
					var userId = users[i].userId;
					var opt = document.createElement("option"); 
					opt.text = userId.;
					opt.value = userId;
					dropdown.options.add(opt);
				} 
			} else {
				alert("error! the response status is : " + xhr.status);
			}

	}

	function showDivs() {
		document.getElementById("div3").style.display = 'block';
		document.getElementById("div4").style.display = 'block';
	}
</script>
</head>
<body onload="getAllUser()">
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
					<h1 class="page-header">Change User Role</h1>
					<p>Please select the user name that you want update</p>
					<div>
						<div id="div1">
							<select name="users" id="users" class="form-control">
							</select>
							<!-- <input type="text" name="username" id="username"
								class="form-control" required> -->
							<br>
						</div>
						<div id="div2">
							<input type='button' onclick="findUser()" value="find" /> <br>
						</div>
						<div id="div3" class="hidden-div-cri">
							<br> <select name="userRole" id="userRole"
								class="form-control">
								<option value="Network Management Engineer">Network
									Management Engineer</option>
								<option value="Support Engineer">Support Engineer</option>
								<option value="Customer Service">Customer Sevice</option>
							</select> <br>
						</div>
						<div id="div4" class="hidden-div-cri">
							<input type='button' onclick="updateUser()" value="update" />
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