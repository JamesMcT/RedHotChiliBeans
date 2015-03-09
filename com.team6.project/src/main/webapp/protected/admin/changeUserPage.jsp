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

<!-- Adding functions -->
<script src="../../js/common.js"></script>

<script>
	var user;

	function findUser() {
		var username = document.getElementById("users").value;
		var currentDiv = document.getElementById("div2");
		if (username) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET",
					root + "/protected/rest/usermanagement/" + username, false);
			xhr
					.addEventListener(
							'load',
							function() {
								if (xhr.status == 200) {
									user = JSON.parse(xhr.responseText);
									if (user.userId) {
										var currentRole = document
												.createTextNode("The current role of the user is : "
														+ user.role);
										currentDiv.appendChild(document
												.createElement("br"));
										currentDiv.appendChild(currentRole);
										showDivs();
									} else {
										alert("User not found!");
									}
								} else {
									alert("error! the response status is : "
											+ xhr.status);
								}
							}, false);
			xhr.send();
		}
	}

	function updateUser() {
		var role = document.getElementById("userRole").value;
		var oldPassword = document.getElementById("oldPassword").value;
		var newPassword = document.getElementById("newPassword").value;
		var reNewPassword = document.getElementById("reinsertNewPassword").value;
		if (fieldValidation(oldPassword, newPassword, reNewPassword)) {
			if (user.userId && role) {
				var u = {};
				u.userId = user.userId;
				if (newPassword) {
					u.password = newPassword;
				} else {
					u.password = user.password;
				}
				u.role = role;
				var xhr = new XMLHttpRequest();
				var root = "${pageContext.servletContext.contextPath}";
				xhr.open("POST",
						root + "/protected/rest/usermanagement/update", true);
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.addEventListener('load', function() {
					if (xhr.status == 200) {
						var response = JSON.parse(xhr.responseText);
						if (response.description) {
							alert("Status : " + response.status
									+ " \n Description : "
									+ response.description);
						} else {
							alert("User updated with success! \n Status : "
									+ response.status);
						}
						clean();
					} else {
						alert("error! the response status is : " + xhr.status);
					}
				}, false);
				xhr.send(JSON.stringify(u));
			}
		}
	}
	function fieldValidation(oldPassword, newPassword, reNewPassword) {
		if(oldPassword && newPassword && reNewPassword){
			if (oldPassword == user.password) {
				if(newPassword == reNewPassword){
					return true;
				}
				else{
					alert("New Password is not the same in both fields!");
				}
			} else {
				alert("The old password is incorrect!");
				return false;
			}
		}
		else if(!oldPassword && !newPassword && !reNewPassword){
			return true;
		}
		else{
			alert("Please fill all passwords fields!");
			return false;
		}
	}
	function getAllUsers() {
		var users = {};
		var dropdown = document.getElementById("users");
		var xhr = new XMLHttpRequest();
		var root = "${pageContext.servletContext.contextPath}";
		xhr.open("GET", root + "/protected/rest/usermanagement/all", false);
		xhr.send();
		if (xhr.status == 200) {
			users = JSON.parse(xhr.responseText);
			for (var i = 0; i < users.length; i++) {
				var userId = users[i].userId;
				var opt = document.createElement("option");
				opt.text = userId;
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
		document.getElementById("div5").style.display = 'block';
	}

	function hideDivs() {
		document.getElementById("div3").style.display = 'none';
		document.getElementById("div4").style.display = 'none';
		document.getElementById("div5").style.display = 'none';
	}

	function clean() {
		var currentDiv = document.getElementById("div2");
		currentDiv.removeChild(currentDiv.lastChild);
		document.getElementById("oldPassword").value = "";
		document.getElementById("newPassword").value = "";
		document.getElementById("reinsertNewPassword").value = "";
		hideDivs();
	}

	function startup() {
		loadbar('sidebar.html');
		getAllUsers();
	}
</script>
</head>
<body onload="startup()">
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0" id="navigation"> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Change User Role</h1>
					<p>Please select the user name that you want update</p>
					<div>
						<div id="div1">
							<select name="users" id="users" class="form-control">
							</select> <br>
						</div>
						<div id="div2">
							<input type='button' onclick="findUser()" value="find"
								class="btn btn-default" /> <br>
						</div>
						<div id="div3" class="form-group" style="display: none">
							<br> <select name="userRole" id="userRole"
								class="form-control">
								<option value="Network Management Engineer">Network
									Management Engineer</option>
								<option value="Support Engineer">Support Engineer</option>
								<option value="Customer Service">Customer Sevice</option>
							</select> <br>
						</div>
						<div id="div4" class="form-group" style="display: none">
							<label> Fill this forms ONLY if you want change the
								password </label> <input id="oldPassword" type="password" class="form-control"
								placeholder="Old Password"></input> <br> <input
								id="newPassword" type="password" class="form-control" placeholder="New Password"></input>
							<br> <input id="reinsertNewPassword" type="password" class="form-control"
								placeholder="Re-insert New Password"></input>
						</div>
						<div id="div5" class="hidden-div-cri">
							<input type='button' onclick="updateUser()" value="update"
								class="btn btn-default" />
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