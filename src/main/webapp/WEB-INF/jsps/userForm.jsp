<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Details</title>
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


</head>
<body class=".container-fluid">
	<div class="container myrow-container"></div>
	<div class="container">
		<div class="panel panel-success">
			<%@include file="header.jsp"%>
			<div class="panel-body" style="min-height: 500px;">
				<%@include file="menu.jsp"%>

				<form:form id="userFormId" cssClass="form-horizontal"
					modelAttribute="userForm" method="post" action="saveUser">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>
					<form:hidden path="action" value="${userForm.action}" />
					<c:if test="${userForm.action eq 'update'}">
						<div class="form-group">
							<div class="control-label col-xs-3">
								<form:label path="usrId">Id</form:label>
							</div>
							<div class="col-xs-6">
								<form:input cssClass="form-control" path="usrId"
									value="${userForm.usrId}" disabled="disabled" readonly="true" />
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<form:label path="usrName" cssClass="control-label col-xs-3">User Name</form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="usrName" maxlength="50"
								required="required" value="${userForm.usrName}" />
						</div>
					</div>

					<div class="form-group">
						<form:label path="usrFname" cssClass="control-label col-xs-3">First Name</form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="usrFname"
								maxlength="100" required="required" value="${userForm.usrFname}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="usrLname">Last Name</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="usrLname"
								maxlength="100" required="required" value="${userForm.usrLname}" />
						</div>
					</div>

					<div class="form-group">
						<form:label path="newUsrPsw" cssClass="control-label col-xs-3">New Password</form:label>
						<div class="col-xs-6">
							<form:password cssClass="form-control" path="newUsrPsw"
								value="${userForm.newUsrPsw}" />
						</div>
					</div>

					<div class="form-group">
						<form:label path="confirmNewUsrPsw"
							cssClass="control-label col-xs-3">Confirm New Password</form:label>
						<div class="col-xs-6">
							<form:password cssClass="form-control" path="confirmNewUsrPsw"
								value="${userForm.confirmNewUsrPsw}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="dob">Date Of Birth</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="dob"
								value="${userForm.dob}" id="dobpicker" maxlength="10"
								required="required" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="telephone">Telephone</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="telephone"
								value="${userForm.telephone}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="email">Email</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="email" maxlength="250"
								required="required" value="${userForm.email}" />
						</div>
					</div>

					<c:if test="${userForm.action eq 'update'}">
						<div class="form-group">
							<div class="control-label col-xs-3">
								<form:label path="createdDate">Created at</form:label>
							</div>
							<div class="col-xs-6">
								<form:input cssClass="form-control" path="createdDate"
									value="${userForm.createdDate}" disabled="disabled"
									readonly="true"></form:input>
							</div>
						</div>
						<div class="form-group">
							<div class="control-label col-xs-3">
								<form:label path="modifiedDate">Modified at</form:label>
							</div>
							<div class="col-xs-6">
								<form:input cssClass="form-control" path="modifiedDate"
									value="${userForm.modifiedDate}" disabled="disabled"
									readonly="true"></form:input>
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="roles">Roles</form:label>
						</div>
						<div class="col-xs-6">
							<c:forEach items="${roleList}" var="role" varStatus="status">
								<form:checkbox path="roles" value="${role.roleId }"/>&nbsp;${role.description} (${role.name}) <br>
							</c:forEach>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="faculties">Falcuties</form:label>
						</div>
						<div class="col-xs-6">
							<c:forEach items="${facultyList}" var="faculty" varStatus="status">
								<form:checkbox path="faculties" value="${faculty.facultyId }"/>&nbsp;${faculty.name} <br>
							</c:forEach>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="active">Status</form:label>
						</div>
						<div class="col-xs-6">
							<form:radiobuttons path="active" items="${statusList}"
								cssClass="form-control" itemLabel="desc" itemValue="value" />
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-1" style="margin-right: 20px;">
								<input type="button" id="allUsersId"
									class="btn btn-success cmr-button" value="Back"
									onclick="location.href='allUsers'" />
							</div>
							<div class="col-xs-1">
								<input type="submit" id="saveUser"
									class="btn btn-success cmr-button" value="Save"
									onclick="return submitUserForm();" />
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

	<script type="text/javascript">
		var dateToday = new Date();
		$(function() {
			$("#dobpicker").datepicker({
				dateFormat : 'dd.mm.yy',
				maxDate : dateToday
			}).val();
		});
	</script>

</body>
</html>