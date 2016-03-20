<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users management</title>
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

				<c:if test="${empty userList}">
                There are no Users
            </c:if>
				<c:if test="${not empty userList}">

					<form:form id="searchUserFormId" cssClass="form-horizontal"
						action="searchUser" >

						<div class="form-group">
							<span class="control-label col-xs-3">Search Username: </span>
							<div class="col-xs-6">
								<input class="form-control" name="searchName" value="" />
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<input type="submit" id="searchUserId"
										class="btn btn-success cmr-button" value="Search" />
								</div>
								<div class="col-xs-4">
								</div>
							</div>
						</div>

					</form:form>


					<table class="table table-hover table-bordered">
						<thead style="background-color: #dff0d8; color: #337ab7;">
							<tr>
								<th>Id</th>
								<th>User Name</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>DoB</th>
								<th>Telephone</th>
								<th>Email</th>
								<th>Created at</th>
								<th>Modified at</th>
								<th style="text-align: center;">Edit</th>
								<th style="text-align: center;">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="usr">
								<tr>
									<td style="vertical-align: middle;"><c:out value="${usr.usrId}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.usrName}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.usrFname}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.usrLname}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.dob}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.telephone}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.email}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.createdDate}" /></td>
									<td style="vertical-align: middle;"><c:out value="${usr.modifiedDate}" /></td>
									<td style="vertical-align: middle;text-align: center;"><a href="editUser?id=<c:out value='${usr.usrId}'/>"><i
											class="fa fa-pencil-square-o" style="font-size: 17px;"></i></a></td>
									<td style="vertical-align: middle;text-align: center;"><a id="del"
										href="deleteUser?id=<c:out value='${usr.usrId}'/>"><i
											id="del" class="fa fa-times-circle" style="font-size: 17px;"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<%-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#del").click(function() {
				if (!confirm("Are you sure to delete?")) {
					return false;
				}
			});
		});
	</script>
</body>
</html>