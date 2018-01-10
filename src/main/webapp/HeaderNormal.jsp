<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${context}Bootstrap.css">
<link rel="stylesheet" href="${context}HeaderNormal.css">
</head>
<body>
<%@ include file="/CheckAdmin.jsp"%>
	<c:choose>
		<c:when test="${sessionScope.user_id != null}"> 
			<ul>
				<li><h1><font color="white">Restaurant Delivery</font></h1></li>
				<li style="float:right" id="hdLogout"><a href="${context}Logout">Log Out</a></li>
				<li style="float:right" id="hdOrders"><a href="${context}PastOrders">View Orders</a></li>
				<li style="float:right" id="hdAccount"><a href="${context}UpdateAccount">Account Information</a></li>
				<li style="float:right" id="hdMenu"><a href="${context}Menu.jsp">Menu</a></li>
				<li style="float:right" id="hdHome"><a href="${context}Home.jsp">Home</a></li>
			</ul>
		</c:when>
		
		<c:otherwise>
			<ul>
				<li><h1><font color="white">Restaurant Delivery</font></h1></li>
				<li style="float:right" id="hdRegister"><a href="${context}Register.jsp">Register</a></li>
				<li style="float:right" id="hdLogin"><a href="${context}Login.jsp">Log In</a></li>
				<li style="float:right" id="hdMenu"><a href="${context}Menu.jsp">Menu</a></li>
				<li style="float:right" id="hdHome"><a href="${context}Home.jsp">Home</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</body>
</html>