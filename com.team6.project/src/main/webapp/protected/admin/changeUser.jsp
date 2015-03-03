<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<script>
function findUser() {
		var username = document.getElementById("username").value;
		if (username){
			var xhr = new XMLHttpRequest();
			var root = "${pageContext.servletContext.contextPath}";
			xhr.open("GET", root + "/rest/usermanagement/" + username, false);
			var user = JSON.parse(xhr.responseText);
		}
		}
</script>
</head>
<body>
<h2>Please insert the user name that you want update</h2>
<div>
<input type="text"
				name="username" id="username">
</div>
<div>
			<input type='button' onclick="findUser()" value="find" />
		</div>
</body>