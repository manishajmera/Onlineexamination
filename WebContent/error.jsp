<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>\error</title>
</head>
<body>
<%if(request.getAttribute("name").equals(("UserServlet"))){ %>
<div id="content">user not found</div>
<%} %>
<%if(request.getAttribute("name").equals(("StudentDeletion"))){ %>
<div id="content">student not found as a user</div>
<%} %>
<%if(request.getAttribute("name").equals(("UserInsertion"))){ %>
<div id="content">you have enter wrong Student details</div>
<%} %>
<%if(request.getAttribute("name").equals(("TestIDNotFound"))){ %>
<div id="content">test id not found</div>
<%} %>
<%if(request.getAttribute("name").equals(("IDFound"))){ %>
<div id="content">Test is already found</div>
<%} %>

<%if(request.getAttribute("name").equals(("RowNotFound"))){ %>
<div id="content">QNO not found for current testid</div>
<%} %>

</body>
</html>