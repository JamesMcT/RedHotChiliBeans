<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<script>
	function findUser() {
		var username = document.getElementById("username").value;
		if (username) {
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", "http://localhost:8080/com.team6.project-0.0.1-SNAPSHOT/protected/rest/usermanagement/" + username);
			xhr.send();
			if (xhr.status == 200) {
				var user = JSON.parse(xhr.responseText);
				if (user.userId) {
					var btn = document.createElement("BUTTON");
					var password = document.createElement("INPUT");
					password.id = "password"
					password.type = "password";
					password.value = user.password;
					var role = document.createElement("INPUT");
					password.id = "role"
					role.type = "text";
					role.value = user.role;
					document.body.append(password);
					document.body.append(role);

					document.body.append(btn);
				}
			} else {
				alert("error");
			}

		}
	}
</script>
</head>
<body>
	<h2>Please insert the user name that you want update</h2>
	<div>
		<input type="text" name="username" id="username">
	</div>
	<div>
		<input type='button' onclick="findUser()" value="find" />
	</div>
</body>