<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>success</title>
</head>
<body>

<%if(request.getAttribute("name").equals(("UserInsertion"))){ %>
<div id="content">User inserted successfully</div>
<%} %>
<%if(request.getAttribute("name").equals(("StudentDeletion"))){ %>
<div id="content">Student deleted successfully</div>
<%} %>
<%if(request.getAttribute("name").equals(("UserPass"))){ %>
<div id="content">Student already exist,password updated</div>
<%} %>
<%if(request.getAttribute("name").equals(("PaperInsertion"))){ %>
<div id="content">Paper inserted successfully</div>
<%} %>
<%if(request.getAttribute("name").equals(("QuestionInsertion"))){ %>
<div id="content">Question inserted successfully</div>
<%} %>
<%if(request.getAttribute("name").equals(("PaperDeletion"))){ %>
<div id="content">Paper deleted successfully</div>
<%} %>

</body>
</html>