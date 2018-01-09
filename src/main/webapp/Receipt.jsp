<%@ page language="java" 
	import="java.sql.*,del.res.bo.*, del.res.dao.*, del.res.models.*, java.util.ArrayList"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="Error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Receipt</title>
<link rel="stylesheet" href="${context}Bootstrap.css">

</head>
<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderFixed.jsp" %>
<c:if test="${order_id == null}">
	<c:redirect url="Error.jsp"/>
</c:if>

<c:if test="${receipt==null || rs==null}">
	<c:redirect url="Receipt?${order_id}"/>
</c:if>

<div class="container " style="margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:100%;height:85%;min-width:200px;max-width:1000px;">
	<div class="jumbotron text-center" >
      <h1>Order Receipt</h1>
    </div>
    <div class="container theme-showcase">
    <%-- Receipt starts here --%>
    	<div class='jumbotron'>
    		<div class="row align-items-center" style="position:relative; display: table">
    			<div class="col-sm-6" style="display: table-cell; width: 70%; vertical-align: middle;">
    			
    			<%-- Start here for stacking vertically on left side --%>
    			<c:forEach items="${receipt}" var="item">
    				<div class="row align-items-center" style="position: relative; margin:10px 0px">
    					<div class="col-sm-6" style="overflow: auto">
    						<img src="${context}${item.getImageSrc()}" class="img-responsive img-thumbnail"/>
    					</div>
    					<div class="col-sm-6">
    						<h2>${item.getItemName()}</h2>
    						<br>
    						<h3>Price: ${item.getItemPrice()} </h3>
    					</div>    					
    				</div>
    			</c:forEach>
    			
	    		<%-- End vertically left side --%>
	    			
    			</div>
    			<%-- Start right side --%>
    			<div class="col-sm-6" style="display: table-cell; width: 30%; vertical-align:middle;">
    				<div class="jumbotron" style="position: relative; text-align: center; background-color: green">
    					<h5>Total: ${rs.getOrderTotal()}</h5>
    					<h5>Tax: ${rs.getOrderTax()}</h5>
    					<h5>Items: ${rs.getItemCount()}</h5>
    					<br>
    					<h5>${rs.getStoreDescription()}</h5>
    					<h5>${rs.getStoreAddress()}</h5>
    				</div>
    			</div>
    			<%-- End right side --%>
    			
    		</div>    		
    	</div>
    </div>
</div>
<jsp:include page="Footer.html"/>
</body>
</html>