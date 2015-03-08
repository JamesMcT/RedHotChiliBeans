<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<title>Login Error</title>
<head>
<body>
	<p>Invalid user name or password.<p>
	<script>
		alert("Invalid credentials");
	</script>
	<%
	    session.invalidate();
	    response.sendRedirect(request.getContextPath() + "/protected/index.jsp");
	%>

<body>
</html>
