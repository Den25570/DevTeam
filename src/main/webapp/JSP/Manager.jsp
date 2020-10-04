<%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 01.10.2020
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Manager Home Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if((request.getSession(false).getAttribute("Manager")== null) )
    {
%>
<jsp:forward page="/JSP/Login.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Manager Home Page</h2></center>
Welcome <%=request.getAttribute("login") %>
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
</body>
</html>
