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
<title>Past Orders</title>
<link rel="stylesheet" href="${context}Bootstrap.css">

</head>
<body background="${context}Images/bg3.jpg">
<%-- Make sure that orders request-scope parameter has been set. --%>
<c:if test="${orders == null}">
	<c:redirect url="PastOrders"/>
</c:if>
<%@ include file="HeaderNormal.jsp" %>
<br>
<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:100%;height:85%;min-width:200px;max-width:1000px;">
	<div class="jumbotron text-center" >
      <h1>Past Orders</h1>
    </div>
    <div class="container theme-showcase">
    
	    <%-- Receipt starts here --%>
	    <%-- See del.res.servlets.PastOrders.java for where orders are coming from --%>
	    <%-- See del.res.models.PastOrder.java to see model for each order --%>
	    <c:forEach items="${orders}" var="order">
	    
	    	<div class='jumbotron'>
	    		<div class="row align-items-center" style="position:relative; display: table; width: 100%">
	    			<div class="col-sm-6" style="display: table-cell; width: 70%; vertical-align: middle;">
	    			
	    			<%-- Start here for stacking vertically on left side --%>
	    			<%-- See del.res.models.ReceiptItem.java to see model for each item --%>
	    			<c:set var="items" value="${order.getItems()}"/>
	    			<c:forEach items="${items}" var="item">
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
	    			<%-- See del.res.models.ReceiptSummary to see model for order summary --%>
	    			<div class="col-sm-6" style="display: table-cell; width: 30%; vertical-align:middle;">
	    				<div class="jumbotron" style="position: relative; text-align: center; background-color: green;">
	    					<c:set var="summary" value="${order.getSummary()}"/>
	    					<h5>Total: ${summary.getOrderTotal()}</h5>
	    					<h5>Tax: ${summary.getOrderTax()}</h5>
	    					<h5>Items: ${summary.getItemCount()}</h5>
	    					<br>
	    					<h5>${summary.getStoreDescription()}</h5>
	    					<h5>${summary.getStoreAddress()}</h5>
	    				</div>
	    			</div>
	    			
	    		</div>    		
	    	</div>
    	</c:forEach>
    </div>
</div>

<jsp:include page="Footer.html"/>
</body>
</html>