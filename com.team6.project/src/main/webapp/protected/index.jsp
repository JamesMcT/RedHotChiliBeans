<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	    if (request.isUserInRole("administrator")) {
	%>
	<%
	    // New location to be redirected
	        String site = new String("admin/index.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	%>
	<%
	    }
	%>
	<%
	    if (request.isUserInRole("Network Management Engineer")) {
	%>
	<%
	    	// New location to be redirected
	        String site = new String("nme/index.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	%>
	<%
	    }
	%>
	<%
	    if (request.isUserInRole("Support Engineer")) {
	%>
	<%
	    	// New location to be redirected
	        String site = new String("se/index.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	%>
	<%
	    }
	%>
	<%
	    if (request.isUserInRole("Customer Service")) {
	%>
	<%
	    	// New location to be redirected
	        String site = new String("csr/index.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	%>
	<%
	    }
	%>
</body>
</html>