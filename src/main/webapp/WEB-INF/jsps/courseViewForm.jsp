<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses Details</title>
<!-- Bootstrap CSS -->
<%-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> --%>

<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css">
<link href="<c:url value="/resources/css/cmr.css" />" rel="stylesheet">

<style type="text/css">
#crsDetailsId {
	resize: none;
}
</style>

</head>
<body class=".container-fluid">
	<div class="container myrow-container"></div>
	<div class="container">
		<div class="panel panel-success">
			<%@include file="header.jsp"%>
			<div class="panel-body" style="min-height: 500px;">
				<%@include file="menu.jsp"%>

				<form:form id="courseFormId" cssClass="form-horizontal"
					modelAttribute="courseForm" method="post">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="crsCode">Course Code</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden id="actionId" path="action"
								value="${courseForm.action}" />
							<form:input cssClass="form-control" path="crsCode"
								value="${courseForm.crsCode}" disabled="disabled"
								readonly="true" required="required" />
						</div>
					</div>

					<div class="form-group">
						<form:label path="crsName" cssClass="control-label col-xs-3">Course Name</form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="crsName"
								value="${courseForm.crsName}" maxlength="150"
								required="required" disabled="disabled" readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="crsDetails">Course Description</form:label>
						</div>
						<div class="col-xs-6">
							<form:textarea cssClass="form-control" path="crsDetails"
								value="${courseForm.crsDetails}" maxlength="400"
								required="required" rows="6" cols="40" id="crsDetailsId"
								disabled="disabled" readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="credits">Course Credits</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="credits"
								value="${courseForm.credits}" disabled="disabled"
								readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="preRequisite">Course Prerequisite</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="preRequisite"
								value="${courseForm.preRequisite}" disabled="disabled"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="clId">Course Leader</form:label>
						</div>
						<div class="col-xs-6">
							<form:select cssClass="form-control" path="clId" disabled="true">
								<form:option value="">&nbsp;</form:option>
								<form:options items="${courseForm.cls}" itemLabel="usrFname"
									itemValue="usrId" />
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="cmId">Course Moderator</form:label>
						</div>
						<div class="col-xs-6">
							<form:select cssClass="form-control" path="cmId" disabled="true">
								<form:option value="">&nbsp;</form:option>
								<form:options items="${courseForm.cms}" itemLabel="usrFname"
									itemValue="usrId" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="facultyIds">Falcuties</form:label>
						</div>
						<div class="col-xs-6">
							<c:forEach items="${facultyList}" var="faculty"
								varStatus="status">
								<form:checkbox path="facultyIds" value="${faculty.facultyId }"
									disabled="true" />&nbsp;${faculty.name} <br>
							</c:forEach>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="startDate">Start Date</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="startDate"
								value="${courseForm.startDate}" id="startDatePicker"
								maxlength="10" required="required" disabled="disabled"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="endDate">End Date</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="endDate"
								value="${courseForm.endDate}" id="endDatePicker" maxlength="10"
								required="required" disabled="disabled" readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-1" style="margin-right: 20px;">
								<input type="button" id="allCoursesId"
									class="btn btn-success cmr-button" value="Back"
									onclick="location.href='allMyCourses?usrId=${sessionUser.usrId}'" />
							</div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

	<%-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>

</body>
</html>