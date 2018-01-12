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
		<img src="${context}Images/img-home.jpg" class="img img-thumbnail"/>
	</div>
	<%-- Comments --%>
	<div class="jumbotron"style="text-align:center;">
		<div class="row">
			<div class="col-sm-6 align-self-end">
				<font class="lead">
				"One cannot think well, love well, sleep well if one has not DINED well"
				</font>
			</div>
			<div class="col-sm-6">
				<font class="lead">
				Pizza might just be the ultimate delivery meal, so why not up the ante a bit? This Clinton Hill pizzeria by husband-and-wife team Matt and Emily Hyland has only been open since early 2014, but its dough, mozzarella--both made in-house, by hand--and wood-burning oven have quickly established it as a force.
				</font>
			</div>
		</div>
	</div>
	
</div>
<jsp:include page="Footer.html"/>
</body>
</html>