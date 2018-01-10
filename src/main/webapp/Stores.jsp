<%@ page language="java" 
	import="java.sql.*,del.res.dao.*, java.util.*, del.res.models.*, del.res.bo.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="${context}Bootstrap.css">
  <title>Select Location</title>

</head>

<body background="${context}Images/bg3.jpg">

<%-- Check if user is logged in --%>
<jsp:directive.include file="CheckLoginError.jsp"/>
<%@ include file="HeaderNormal.jsp" %>
	<div class="container ">
	<form method="post" action="${context}ReviewOrder">
		<button class="btn btn-danger btn-lg" style="float: right;" type="submit" name="cancel" value="cancel">Cancel Order</button>
	</form>
	<br><br>	
	
	
	<div class="jumbotron text-center" >
	      <h1>Select a Store</h1>
	</div>
	
	<%-- Set variable for ArrayList<Store> --%>	  
	<c:set var="storeList" value="${applicationScope.stores}"/>
	  
	  <%-- Check if storeList generated anything. If not, send to Stores servlet to generate --%>
	<c:if test="${storeList == null}">
		<c:redirect url="Stores"/>
	</c:if>
	  
	  <%-- Loop through each Store in the ArrayList --%>
	  <c:forEach items="${storeList}" var="store">
	  	<div class ="container theme-showcase" id="location">
		  	<div class="jumbotron">
		  		<div class="row align-items-center" style="position: relative;">
		  			<div class="col-sm-6" style="overflow: auto">
		  				<img src="${context}${store.getImageSrc()}" class="img-responsive img-thumbnail">
		  			</div>
		  			<div class="col-sm-6">
		  				<div class="jumbotron" style="position:absolute;top:50%; transform: translate(0%,-50%); background-color:transparent; text-align: center; width: 100%">
		  					${store.getStoreName()}
		  					<br>
		  					${store.getStoreDesc()}
		  					<br>
		  					${store.getStoreAddress()}, ${store.getStoreZipcode()}
		  					<br>
		  					${store.getStoreAddDesc()}
		  					<br><br>
		  					<%-- Only display button to select store if user is logged in --%>
		  					<c:if test="${sessionScope.user_id != null}">
		  						<form method="post" action="${context}Stores">
		  							<button class="btn btn-primary btn-block" type="submit" name="bt" value="${store.getStoreID()}">Select Location</button>
		  						</form>		  						
		  					</c:if>
		  				</div>
		  			</div>
		  		</div>
		  	</div>
		  </div>
	  </c:forEach>
    
	</div>
<jsp:include page="Footer.html"/>   
</body>
</html>