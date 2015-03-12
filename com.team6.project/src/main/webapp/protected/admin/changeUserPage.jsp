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
<link href="../../css/text-box.css" rel="stylesheet">
<!-- Adding functions -->
<script src="../../js/common.js"></script>
<script>
	var user;
	function findUser() {
		var username = document.getElementById("users").value;
		var currentDiv = document.getElementById("div2");
		hideDiv("div6");
		hideDiv("div7");
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
										appendDescription(currentDiv,
												currentRole);
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
						clean();
						if (response.description) {
							showDiv("div7");
						} else {
							showDiv("div6");
						}
					} else {
						alert("error! the response status is : " + xhr.status);
					}
				}, false);
				xhr.send(JSON.stringify(u));
			}
		}
	}
	function fieldValidation(oldPassword, newPassword, reNewPassword) {
		if (oldPassword && newPassword && reNewPassword) {
			if (oldPassword == user.password) {
				if (newPassword == reNewPassword) {
					return true;
				} else {
					alert("New Password is not the same in both fields!");
				}
			} else {
				alert("The old password is incorrect!");
				return false;
			}
		} else if (!oldPassword && !newPassword && !reNewPassword) {
			return true;
		} else {
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
	function appendDescription(currentDiv, currentRole) {
		while (currentDiv.childNodes.length > 3) {
			currentDiv.removeChild(currentDiv.lastChild);
		}
		currentDiv.appendChild(document.createElement("br"));
		currentDiv.appendChild(currentRole);
	}
	function showDivs() {
		showDiv("div3");
		showDiv("div4");
		showDiv("div5");
	}
	function hideDivs() {
		hideDiv("div3");
		hideDiv("div4");
		hideDiv("div5");
	}
	function showDiv(divId) {
		document.getElementById(divId).style.display = 'block';
	}
	function hideDiv(divId) {
		document.getElementById(divId).style.display = 'none';
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
		loadbar('../sidebar.jsp');
		getAllUsers();
		hideDiv("div3");
		hideDiv("div4");
		hideDiv("div5");
		hideDiv("div6");
		hideDiv("div7");
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
						<div id="div3" class="form-group">
							<br> <select name="userRole" id="userRole"
								class="form-control">
								<option value="Network Management Engineer">Network
									Management Engineer</option>
								<option value="Support Engineer">Support Engineer</option>
								<option value="Customer Service">Customer Sevice</option>
								<option value="administrator">Administrator</option>
							</select> <br>
						</div>
						<div id="div4" class="form-group">
							<label> Fill this forms ONLY if you want change the
								password </label> <input id="oldPassword" type="password"
								class="form-control" placeholder="Old Password"></input> <br>
							<input id="newPassword" type="password" class="form-control"
								placeholder="New Password"></input> <br> <input
								id="reinsertNewPassword" type="password" class="form-control"
								placeholder="Re-insert New Password"></input>
						</div>
						<div id="div5">
							<input type='button' onclick="updateUser()" value="update"
								class="btn btn-default" />
						</div>
						<div id="div6" class="success">User updated with success!</div>
						<div id="div7" class="error">User update failed.</div>
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