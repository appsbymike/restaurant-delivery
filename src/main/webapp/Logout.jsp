<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logged Out</title>
</head>
<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderFixed.jsp" %>
	<div class="container " style="position: absolute; margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:75%; height: 50%;min-width:200px;max-width:1000px; padding: 10px ">
	    <div class="jumbotron text-center" >
	      <h1 style="line-height: 100px">Logged Out</h1>
	      <h3 id="mes1">Thanks for Visiting Us !!! You have been logged out</h3>
	      <h3 id="mes2">Want to login again? Click below</h3>
	      <h4><a href="${context}Login.jsp">Login</a></h4>
		</div>
	</div>
<jsp:include page="Footer.html"/>
</body>
</html>