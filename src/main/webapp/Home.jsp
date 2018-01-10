<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Delivery Home</title>
</head>
<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderNormal.jsp" %>
<br>
<div class="container " >

	<%-- Page title --%>
	<div class="jumbotron"style="text-align:center;">
		<font class="display-4" >Welcome to Restaurant Delivery</font>
	</div>
	<%-- Logo --%>
	<div class="jumbotron">
		<img src="${context}Images/img-home.jpg" class="img img-responsive img-thumbnail"/>
	</div>
	<%-- Comments --%>
	<div class="jumbotron"style="text-align:center;">
		<font class="lead">
		Welcome to the next level of food delivery.
		We offer far less than the competitors and charge you WAY more.
		<br>
		And the service? Absolutely Abyssmal.
		<br>
		So how do we make our money? That's a very good question. Maybe this is a front, maybe its maybelline.
		</font>
	</div>
	
</div>
<jsp:include page="Footer.html"/>
</body>
</html>