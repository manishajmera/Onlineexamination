<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Marks</title>
<%Connection con=(Connection)request.getAttribute("con");
PreparedStatement pst=con.prepareStatement("select * from result");
ResultSet rs=pst.executeQuery();
%>
</head>
<body>
<table border="5" bordercolor="green">
 <tr>
 <th>UserID </th>
 <th>TestID </th>
 <th>Marks </th>
 </tr>
<% while(rs.next()) { %>
<tr>
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getInt(3) %></td>
</tr>
<%  }%>

</table>

</body>
</html>