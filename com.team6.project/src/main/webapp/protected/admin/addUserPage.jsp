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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/text-box.css" rel="stylesheet">
<!-- Adding functions -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
	function addUser() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var userRole = document.getElementById("userRole").value;
		hideDiv("divSuccess");
		hideDiv("divError");
		if (username && password && userRole) {
			var u = {};
			u.userId = username;
			u.password = password;
			u.role = userRole;
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("POST", root + "/protected/rest/usermanagement/add", true);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr
					.addEventListener(
							'load',
							function() {
								if (xhr.status == 200) {
									//var response = JSON.parse(xhr.responseText);
									//if (response.description) {
										showDivInLine("divSuccess");
										
									//} else {
										
									//}
								}else{
									document.getElementById("divError").innerHTML = ("Error! - " + xhr.responseText);
									showDivInLine("divError");
								}
							}, false);
			xhr.send(JSON.stringify(u));
			clean();
		} else {
			alert("Fields are mandatory!");
		}
	}
	function clean() {
		document.getElementById("username").value = "";
		document.getElementById("password").value = "";
	}
	function startup() {
		loadbar('../sidebar.jsp');
		hideDiv("divSuccess");
		hideDiv("divError");
	}
</script>
<head>
<body onload="startup()">
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"> </nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Add New User</h1>
					<p>Please insert user name, password and select a role:</p>
					<div>
						<div>
							<input type="text" name="username" id="username"
								class="form-control" placeholder="User Name"> <br>
						</div>
						<div>
							<input type="password" size="15" name="password" id="password"
								class="form-control" placeholder="Password"> <br>
						</div>
						<div>
							<select name="userRole" id="userRole" class="form-control">
								<option value="Network Management Engineer">Network
									Management Engineer</option>
								<option value="Support Engineer">Support Engineer</option>
								<option value="Customer Service">Customer Sevice</option>
								<option value="administrator">Administrator</option>
							</select> <br>
						</div>
						<div>
							<br> <input type='button' class="btn btn-default"
								onclick="addUser()" value="submit" />
						</div>
						<div id="divSuccess" class="success" style="display: none;">New
							user inserted with success!</div>
						<div id="divError" class="error" style="display: none;"></div>
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