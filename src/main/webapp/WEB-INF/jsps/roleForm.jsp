<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Roles Details</title>
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

				<form:form id="roleFormId" cssClass="form-horizontal"
					modelAttribute="roleForm" method="post" action="saveRole">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>
					<form:hidden path="action" value="${roleForm.action}" />
					<c:if test="${roleForm.action eq 'update'}">
						<div class="form-group">
							<div class="control-label col-xs-3">
								<form:label path="roleId">Id</form:label>
							</div>
							<div class="col-xs-6">
								<form:input cssClass="form-control" path="roleId"
									value="${roleForm.roleId}" disabled="disabled" readonly="true" />
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<form:label path="name" cssClass="control-label col-xs-3">Role Name</form:label>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="name"
								value="${roleForm.name}" maxlength="50" required="required"/>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="description">Role Description</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="description"
								value="${roleForm.description}" />
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-1" style="margin-right: 20px;">
								<input type="button" id="allRolesId"
									class="btn btn-success cmr-button" value="Back"
									onclick="location.href='allRoles'" />
							</div>
							<div class="col-xs-1">
								<input type="submit" id="saveRole"
									class="btn btn-success cmr-button" value="Save"
									onclick="return submitRoleForm();" />
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
	<script type="text/javascript">
		function submitRoleForm() {
			// getting the role form values
			var roleId = $('#roleId').val().trim();
			var roleName = $('#name').val();
			var roleDesc = $('#description').val();

			if (roleName.length == 0) {
				alert('Please enter Role Name');
				$('#name').focus();
				return false;
			}

			if (roleDesc.length == 0) {
				alert('Please enter Role Description');
				$('#description').focus();
				return false;
			}
			return true;
		};
	</script>
</body>
</html>