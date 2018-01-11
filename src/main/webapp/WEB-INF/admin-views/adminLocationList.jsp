<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
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
			<script>alert("Location Creation Successful!");</script>
		</c:when>
		<c:when test="${create==false}">
			<script>alert("Location Creation Unsuccessful!");</script>
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
				<c:forEach items="${locations}" var="location" >
					<div class="jumbotron" style="margin:10px 0">
				    	<h1 style="line-height: 100px">Items List</h1>
				    	<form method="post" action="${context}/admin/UpdateLocation" id="existingLocation">
						
						<%-- Names: [name(text), desc(text), address(text), zipcode(text), addDesc(text), staff(text), image(select)] --%>
						<label for="name" style="font-size:1.5rem; float: left; font-weight:bold;">Location Name:</label>
						<input name="name" id="name" value="${location.getStoreName()}" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 ]{1,20}" style="font-size:2rem;height:62px" placeholder="Location Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphanumeric Characters"/>
						
						<label for="desc" style="font-size:1.5rem; float: left; font-weight:bold;">Description:</label>
						<input name="desc" id="desc" value='${location.getStoreDesc()}' class="form-control input-lg" type="text" style="font-size:2rem;height:62px" placeholder="Description" maxlength="500" placeholder="Location Description" required="required" title="Please enter a valid description of up to 500 characters"/>
						
						<label for="address" style="font-size:1.5rem; float: left; font-weight:bold;">Address:</label>
						<input name="address" id="address" value="${location.getStoreAddress()}" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 .,]{1,100}" style="font-size:2rem;height:62px" placeholder="Location Address" maxlength="100" required="required" title="Please enter a valid Address of up to 100 characters"/>
						
						<label for="zipcode" style="font-size:1.5rem; float: left; font-weight:bold;">Zipcode:</label>
						<input name="zipcode" id="zipcode" value="${location.getStoreZipcode()}" class="form-control input-lg" type="text" pattern="[0-9]{5}" style="font-size:2rem;height:62px" placeholder="Location Zipcode" maxlength="5" required="required" title="Please enter a zipcode of 5 digits"/>
						
						<label for="addDesc" style="font-size:1.5rem; float: left; font-weight:bold;">Additional Description:</label>
						<input name="addDesc" id="addDesc" value='${location.getStoreAddDesc()}' class="form-control input-lg" type="text" style="font-size:2rem;height:62px" placeholder="Description" maxlength="500" placeholder="Location Additional Description" required="required" title="Please enter a valid description of up to 500 characters"/>
						
						<label for="staff" style="font-size:1.5rem; float: left; font-weight:bold;">Staff:</label>
						<input name="staff" id="staff" value="${location.getStoreStaffCount()}" class="form-control input-lg" type="text" pattern="[0-9]{1,6}" style="font-size:2rem;height:62px" placeholder="Location Staff Count" maxlength="6" required="required" title="Please enter a number of staff up to 6 digits"/>
						
						<label for="image" style="font-size:1.5rem; float: left; font-weight:bold;">Image:</label>
						<select name="image" id="image" class="form-control" style="font-size:2rem; height: 62px">
							<%-- Get all jpg files from Images/ in the format of location*.jpg --%>
							<c:forEach items="${pictures}" var="picture">
								<%-- For items listed, do a choose to select the currently used image by default --%>
								<c:choose> 
									<c:when test="${location.getImageSrc().equals(picture.getFullUrl())}">
										<option value="${picture.getFullUrl()}" selected="selected">${picture.getName()}</option>
									</c:when>
									<c:otherwise>
										<option value="${picture.getFullUrl()}">${picture.getName()}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<br>
						<button name="update" value="${location.getStoreID()}" class="btn btn-primary btn-block" style="font-size: 2rem" type="submit">Update</button>
						<button name="delete" value="${location.getStoreID()}" class="btn btn-danger btn-block" style="font-size: 2rem" type="submit">Delete</button>
					</form>
				   	</div>
				</c:forEach>
		   	</div>
		</div>
		<%-- Right Side / Add Location --%>
		<div class="col-sm-6" style="padding-left:50px">
			<div class="jumbotron" style="margin:10px 0">
			    	<h1 style="line-height: 100px">Create New Location</h1>
			    	<form method="post" action="${context}/admin/CreateLocation" id="newLocation">
					
					<%-- Names: [name(text), desc(text), address(text), zipcode(text), addDesc(text), staff(text), image(select)] --%>
					<label for="name" style="font-size:1.5rem; float: left; font-weight:bold;">Location Name:</label>
					<input name="name" id="name" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 ]{1,20}" style="font-size:2rem;height:62px" placeholder="Location Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphanumeric Characters"/>
					
					<label for="desc" style="font-size:1.5rem; float: left; font-weight:bold;">Description:</label>
					<input name="desc" id="desc" class="form-control input-lg" type="text" style="font-size:2rem;height:62px" placeholder="Description" maxlength="500" placeholder="Location Description" required="required" title="Please enter a valid description of up to 500 characters"/>
					
					<label for="address" style="font-size:1.5rem; float: left; font-weight:bold;">Address:</label>
					<input name="address" id="address" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 .,]{1,100}" style="font-size:2rem;height:62px" placeholder="Location Address" maxlength="100" required="required" title="Please enter a valid Address of up to 100 characters"/>
					
					<label for="zipcode" style="font-size:1.5rem; float: left; font-weight:bold;">Zipcode:</label>
					<input name="zipcode" id="zipcode" class="form-control input-lg" type="text" pattern="[0-9]{5}" style="font-size:2rem;height:62px" placeholder="Location Zipcode" maxlength="5" required="required" title="Please enter a zipcode of 5 digits"/>
					
					<label for="addDesc" style="font-size:1.5rem; float: left; font-weight:bold;">Additional Description:</label>
					<input name="addDesc" id="addDesc" class="form-control input-lg" type="text" style="font-size:2rem;height:62px" placeholder="Description" maxlength="500" placeholder="Location Additional Description" required="required" title="Please enter a valid description of up to 500 characters"/>
					
					<label for="staff" style="font-size:1.5rem; float: left; font-weight:bold;">Staff:</label>
					<input name="staff" id="staff" class="form-control input-lg" type="text" pattern="[0-9]{1,6}" style="font-size:2rem;height:62px" placeholder="Location Staff Count" maxlength="6" required="required" title="Please enter a number of staff up to 6 digits"/>
					
					<label for="image" style="font-size:1.5rem; float: left; font-weight:bold;">Image:</label>
					<select name="image" id="image" class="form-control" style="font-size:2rem; height: 62px">
						<%-- Get all jpg files from Images/ in the format of location*.jpg --%>
						<c:forEach items="${pictures}" var="picture">
							<option value="${picture.getFullUrl()}">${picture.getName()}</option>
						</c:forEach>
					</select>
					<br>
					<button class="btn btn-success btn-block" style="font-size: 2rem" type="submit" id="create">Create</button>
				</form>
			   	</div>
		</div>
	</div>
</div>
<br><br><br>
<jsp:include page="resources/AdminFooter.jsp"/>
</body>
</html>