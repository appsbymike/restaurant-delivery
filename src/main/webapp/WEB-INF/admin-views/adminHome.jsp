<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="${context}/Bootstrap.css">
</head>
<body background="${context}/Images/bg3.jpg">
<%@ include file="resources/AdminHeader.jsp" %>
	<div class="container ">
		<div class="jumbotron text-center" >
		    <h1>Welcome to the Administrative Homepage!</h1>
		</div>
	</div>
<%@ include file="resources/AdminFooter.jsp" %>
</body>
</html>