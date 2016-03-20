<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Faculties Details</title>
<!-- Bootstrap CSS -->
<%-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> --%>

<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/css/cmr.css" />" rel="stylesheet">

</head>
<body class=".container-fluid">
	<div class="container myrow-container"></div>
	<div class="container">
		<div class="panel panel-success">
			<%@include file="header.jsp"%>
			<div class="panel-body" style="min-height: 500px;">
				<%@include file="menu.jsp"%>

				<form:form id="facultyFormId" cssClass="form-horizontal"
					modelAttribute="facultyForm" method="post" action="saveFaculty">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>
					<form:hidden path="action" value="${facultyForm.action}" />
					<c:if test="${facultyForm.action eq 'update'}">
						<div class="form-group">
							<div class="control-label col-xs-3">
								<form:label path="facultyId">Faculty Id</form:label>
							</div>
							<div class="col-xs-6">
								<form:input cssClass="form-control" path="facultyId"
									value="${facultyForm.facultyId}" disabled="disabled"
									readonly="true" required="required" />
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<form:label path="name" cssClass="control-label col-xs-3">Faculty Name</form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="name"
								value="${facultyForm.name}" maxlength="45" required="required" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="description">Faculty Description</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="description"
								value="${facultyForm.description}" maxlength="45"
								required="required" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="pvc">Pro-Vice Chancellor</form:label>
						</div>
						<div class="col-xs-6">
							<form:select cssClass="form-control" path="pvc">
								<form:options items="${facultyForm.pvcs}"
									itemLabel="usrFname"
									itemValue="usrId" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="dlt">Director of Learning and Quality</form:label>
						</div>
						<div class="col-xs-6">
							<form:select cssClass="form-control" path="dlt">
								<form:options items="${facultyForm.dlts}"
									itemLabel="usrFname"
									itemValue="usrId" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-1" style="margin-right: 20px;">
								<input type="button" id="allFacultiesId"
									class="btn btn-success cmr-button" value="Back"
									onclick="location.href='allFaculties'" />
							</div>
							<div class="col-xs-1">
								<input type="submit" id="saveFaculty"
									class="btn btn-success cmr-button" value="Save"
									onclick="return submitFacultyForm();" />
							</div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

	<%-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>

</body>
</html>