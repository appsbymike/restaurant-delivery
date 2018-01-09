<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="${context}/Images/bg3.jpg">
<%@ include file="resources/AdminHeader.jsp" %>
<%@ include file="resources/NavigationPanel.jsp" %>

<%-- Check if update has been attempted --%>
<c:if test="${update != null}">
	<c:choose>
		<%-- Display whether update has or hasn't succeeded --%>
		<c:when test="${update == true}">
			<script>alert("Update Successful!");</script>
		</c:when>
		<c:when test="${update==false}">
			<script>alert("Update Unsuccessful!");</script>
		</c:when>
		<%-- If value is somehow something other than true or false, send to error page --%>
		<c:otherwise>
			<c:redirect url="WEB-INF/views/adminError.jsp"/>
		</c:otherwise>
	</c:choose>
</c:if>

<%-- Check if delete has been attempted --%>
<c:if test="${delete != null}">
	<c:choose>
		<%-- Display whether update has or hasn't succeeded --%>
		<c:when test="${delete == true}">
			<script>alert("Delete Successful!");</script>
		</c:when>
		<c:when test="${delete==false}">
			<script>alert("Delete Unsuccessful!");</script>
		</c:when>
		<%-- If value is somehow something other than true or false, send to error page --%>
		<c:otherwise>
			<c:redirect url="WEB-INF/views/adminError.jsp"/>
		</c:otherwise>
	</c:choose>
</c:if>

<%-- Check if item creation has been attempted --%>
<c:if test="${create != null}">
	<c:choose>
		<%-- Display whether update has or hasn't succeeded --%>
		<c:when test="${create == true}">
			<script>alert("Item Creation Successful!");</script>
		</c:when>
		<c:when test="${create==false}">
			<script>alert("Item Creation Unsuccessful!");</script>
		</c:when>
		<%-- If value is somehow something other than true or false, send to error page --%>
		<c:otherwise>
			<c:redirect url="WEB-INF/views/adminError.jsp"/>
		</c:otherwise>
	</c:choose>
</c:if>

<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:100%; height: 70%;padding: 10px ">
	<div class="row">
		<%-- Left Side / List of current items --%>
		<div class="col-sm-6" style="padding-right:50px">
			<div class="row">
				<c:forEach items="${items}" var="item" >
					<div class="jumbotron" style="margin:10px 0">
				    	<h1 style="line-height: 100px">Items List</h1>
				    	<form method="post" action="${context}/admin/UpdateItem">
						
						<%-- Names: [name(text), price(text), desc(text), image(select), active(select), category(select)] --%>
						<label for="name" style="font-size:1.5rem; float: left; font-weight:bold;">Item Name:</label>
						<input name="name" id="name" value="${item.getItemName()}" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 ]{1,20}" style="font-size:2rem;height:62px" placeholder="Item Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphanumeric Characters"/>
						
						<label for="price" style="font-size:1.5rem; float: left; font-weight:bold;">Price:</label>
						<input name="price" id="price" value="${item.getItemPrice()}" class="form-control input-lg" type="text" pattern="[0-9]{1,17}.[0-9]{2}" style="font-size:2rem;height:62px" placeholder="Item Price" maxlength="20" required="required" title="Please enter a valid Price of up to 20 digits in the format of 999.99"/>
						
						<label for="desc" style="font-size:1.5rem; float: left; font-weight:bold;">Description:</label>
						<input name="desc" id="desc" value="${item.getItemDesc()}" class="form-control input-lg" type="text" style="font-size:2rem;height:62px" placeholder="Description" maxlength="500" placeholder="Item Price" required="required" title="Please enter a valid description of up to 500 characters"/>
						
						<label for="image" style="font-size:1.5rem; float: left; font-weight:bold;">Image:</label>
						<select name="image" id="image" class="form-control" style="font-size:2rem; height: 62px">
							<%-- Get all jpg files from Images/ in the format of item*.jpg --%>
							<c:forEach items="${pictures}" var="picture">
								<%-- For items listed, do a choose to select the currently used image by default --%>
								<c:choose> 
									<c:when test="${item.getImageSrc().equals(picture.getFullUrl())}">
										<option value="${picture.getFullUrl()}" selected="selected">${picture.getName()}</option>
									</c:when>
									<c:otherwise>
										<option value="${picture.getFullUrl()}">${picture.getName()}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						
						<label for="active" style="font-size:1.5rem; float: left; font-weight:bold;">Active:</label>
						<select name="active" id="active" class="form-control" style="font-size:2rem; height: 62px">
							<%-- Check if item is active or not, select corresponding option --%>
							<c:choose>
								<c:when test="${item.getItemIsActive().equals('1')}">
									<option value="0">Not Active</option>
									<option value="1" selected="selected">Active</option>
								</c:when>
								<c:otherwise>
									<option value="0" selected="selected">Not Active</option>
									<option value="1">Active</option>
								</c:otherwise>
							</c:choose>
						</select>
						
						<label for="category" style="font-size:1.5rem; float: left; font-weight:bold;">Category:</label> 
						<select name="category" id="category" class="form-control" style="font-size:2rem; height: 62px">
							<%-- Categories not explicitly defined in requirements. Can potentially loop through options later. --%>
							<c:choose>
								<c:when test="${item.getItemCategory().equals('Pizza')}">
									<option value="Pizza" selected="selected">Pizza</option>
									<option value="Other">Other</option>
								</c:when>
								<c:otherwise>
									<option value="Pizza">Pizza</option>
									<option value="Other" selected="selected">Other</option>
								</c:otherwise>
							</c:choose>
							
						</select>
						<br>
						<button name="update" value="${item.getItemID()}" class="btn btn-primary btn-block" style="font-size: 2rem" type="submit">Update</button>
						<button name="delete" value="${item.getItemID()}" class="btn btn-danger btn-block" style="font-size: 2rem" type="submit">Delete</button>
					</form>
				   	</div>
				</c:forEach>
		   	</div>
		</div>
		<%-- Right Side / Add Item --%>
		<div class="col-sm-6" style="padding-left:50px">
			<div class="jumbotron" style="margin:10px 0">
			    	<h1 style="line-height: 100px">Create New Item</h1>
			    	<form method="post" action="${context}/admin/CreateItem">
					
					<%-- Names: [name(text), price(text), desc(text), image(select), active(select), category(select)] --%>
					<label for="name" style="font-size:1.5rem; float: left; font-weight:bold;">Item Name:</label>
					<input name="name" id="name" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 ]{1,20}" style="font-size:2rem; height:62px" placeholder="Item Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphabetic Characters"/>
					
					<label for="price" style="font-size:1.5rem; float: left; font-weight:bold;">Price:</label>
					<input name="price" id="price" class="form-control input-lg" type="text" pattern="[0-9]{1,17}.[0-9]{2}" style="font-size:2rem; height:62px" placeholder="Item Price" maxlength="20" required="required" title="Please enter a valid Price of up to 20 digits in the format of 999.99"/>
					
					<label for="desc" style="font-size:1.5rem; float: left; font-weight:bold;">Description:</label>
					<input name="desc" id="desc" class="form-control input-lg" type="text" style="font-size:2rem; height:62px" placeholder="Description" maxlength="500" placeholder="Item Price" required="required"/>
					
					<label for="image" style="font-size:1.5rem; float: left; font-weight:bold;">Image:</label>
					<select name="image" id="image" class="form-control" style="font-size:2rem; height: 62px">
						<%-- Get all jpg files from Images/ in the format of item*.jpg --%>
						<c:forEach items="${pictures}" var="picture">
							<option value="${picture.getFullUrl()}">${picture.getName()}</option>
						</c:forEach>
					</select>
					
					<label for="active" style="font-size:1.5rem; float: left; font-weight:bold;">Active:</label>
					<select name="active" id="active" class="form-control" style="font-size:2rem; height: 62px">
						<%-- Can only be active or not active --%>
						<option value="0">Not Active</option>
						<option value="1">Active</option>
					</select>
					
					<label for="category" style="font-size:1.5rem; float: left; font-weight:bold;">Category:</label> 
					<select name="category" id="category" class="form-control" style="font-size:2rem; height: 62px">
						<%-- Categories not explicitly defined in requirements. Can potentially loop through options later. --%>
						<option value="Pizza">Pizza</option>
						<option value="Other">Other</option>
					</select>
					<br>
					<button class="btn btn-success btn-block" style="font-size: 2rem" type="submit">Create</button>
				</form>
			   	</div>
		</div>
	</div>
</div>
<br><br><br>
<jsp:include page="resources/AdminFooter.jsp"/>
</body>
</html>