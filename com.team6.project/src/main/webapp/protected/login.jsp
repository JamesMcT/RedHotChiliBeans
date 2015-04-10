<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html">
<h:head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login Form</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</h:head>
<h:body>
	<div class="container">

		<form name="loginForm" method="POST" action="j_security_check"
			class="form-signin">
			<h2 class="form-signin-heading">Please log in:</h2>
			
			
				 <label for="inputusername" class="sr-only">User Name</label>
					<input type="text"
						name="j_username" id="inputusername" class="form-control" placeholder="User Name" required autofocus>
				
				 <label for="inputpassword" class="sr-only">Password</label>
					<input type="password"
						name="j_password" id="inputpassword" class="form-control" placeholder="Password" required>
				
					<input type="submit" value="Submit" class="btn btn-lg btn-primary btn-block"/>
				
		
		</form>
	</div>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</h:body>
</html>