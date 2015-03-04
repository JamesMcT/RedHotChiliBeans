<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
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
			xhr.open("POST", root + "/protected/rest/usermanagement", false);
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
			response = xhr.response;
		}
	}
	
</script>

<title>New User</title>
<head>
<h:body>
	<h2>Please insert user name, password and select a role:</h2>
	<div align="center">
		<div>
			<strong>Please type the user name: </strong> <input type="text"
				name="username" size="25" id="username">
		</div>
		<div>
			<strong>Please type the password: </strong> <input type="password"
				size="15" name="password" id="password">
		</div>
		<div>
			<select name="userRole" id="userRole">
				<option value="Network Management Engineer">Network
					Management Engineer</option>
				<option value="Support Engineer">Support Engineer</option>
				<option value="Customer Service">Customer Sevice</option>
			</select>
		</div>
		<div>
			<input type='button' onclick="addUser()" value="submit" />
		</div>
		<div>
			<input type='button' onclick="window.location.href='../logout.jsp'" value="logout" />
		</div>
	</div>

</h:body>
</html>