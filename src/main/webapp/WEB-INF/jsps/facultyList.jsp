<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Faculties management</title>
<!-- Bootstrap CSS -->
<%-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> --%>

<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/css/cmr.css" />" rel="stylesheet">

<style type="text/css">
.wrap-text-details {
	word-wrap: break-word;
	max-width: 250px;
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

				<c:if test="${empty facultyList}">
	               There are no Faculties
	           </c:if>
				<c:if test="${not empty facultyList}">

					<table class="table table-hover table-bordered">
						<thead style="background-color: #dff0d8; color: #337ab7;">
							<tr>
								<th>Id</th>
								<th>Faculty Name</th>
								<th>Faculty Description</th>
								<th>Pro-Vice Chancellor</th>
								<th>Director of Learning and Quality</th>
								<th style="text-align: center;">Edit</th>
								<th style="text-align: center;">Delete</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${facultyList}" var="faculty">
								<tr>
									<td style="vertical-align: middle;"><c:out
											value="${faculty.facultyId}" /></td>
									<td style="vertical-align: middle;"><c:out
											value="${faculty.name}" /></td>
									<td style="vertical-align: middle;"><c:out
											value="${faculty.description}" /></td>
									<td style="vertical-align: middle;"><c:out
											value="${faculty.facultyPvc.usrFname} ${faculty.facultyPvc.usrLname}" /></td>
									<td style="vertical-align: middle;"><c:out
											value="${faculty.facultyDlt.usrFname} ${faculty.facultyDlt.usrLname}" /></td>
									<td style="text-align: center; vertical-align: middle;"><a
										href="editFaculty?id=<c:out value='${faculty.facultyId}'/>"><i
											class="fa fa-pencil-square-o" style="font-size: 17px;"></i></a></td>
									<td style="text-align: center; vertical-align: middle;"><a
										id="del"
										href="deleteFaculty?id=<c:out value='${faculty.facultyId}'/>"><i
											class="fa fa-times-circle" style="font-size: 17px;"></i></a></td>
									<%--<i class="fa fa-pencil-square-o" style="font-size: 17px;"></i>--%>
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