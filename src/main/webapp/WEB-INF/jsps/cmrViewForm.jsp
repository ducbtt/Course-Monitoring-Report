<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMRs Details</title>
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
#cmrNoteId, #commentsId, #crsDetailsId {
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

				<form:form id="cmrFormId" cssClass="form-horizontal"
					modelAttribute="cmrForm" method="post" action="approveCmr">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="cmrId">CMR Id</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden id="actionId" path="action"
								value="${cmrForm.action}" />
							<form:hidden path="cmrId" value="${cmrForm.cmrId}" />
							<form:input cssClass="form-control" path="cmrId"
								value="${cmrForm.cmrId}" disabled="disabled" readonly="true"
								required="required" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="course">Course</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="course" value="${cmrForm.course}" />
							<form:input cssClass="form-control" path="course"
								value="${cmrForm.course.fullCourseLabel}" required="required"
								disabled="disabled" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="crsDetails">Course Description</form:label>
						</div>
						<div class="col-xs-6">
							<form:textarea cssClass="form-control" path="crsDetails"
								value="${cmrForm.crsDetails}" maxlength="400"
								required="required" rows="6" cols="40" id="crsDetailsId"
								disabled="true" readonly="true"/>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="allCredited">All credited</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="allCredited" value="${cmrForm.allCredited}" />
							<form:radiobuttons path="allCredited" items="${allCreditedList}"
								cssClass="form-control" itemLabel="desc" itemValue="value"
								disabled="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="noStdGreen">No of Allowed Students</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="noStdGreen" value="${cmrForm.noStdGreen}" />
							<form:input cssClass="form-control" path="noStdGreen"
								value="${cmrForm.noStdGreen}" disabled="disabled"
								readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="crsSubmitDate">Final Course Submit Date</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="crsSubmitDate"
								value="${cmrForm.crsSubmitDate}" />
							<form:input cssClass="form-control" path="crsSubmitDate"
								value="${cmrForm.crsSubmitDate}" disabled="disabled"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="fbNoExcellent">Feedback</form:label>
						</div>
						<div class="col-xs-6">
							<table class="table table-hover table-bordered">
								<thead style="background-color: #dff0d8; color: #337ab7;">
									<tr>
										<th>Excellent</th>
										<th>Very Good</th>
										<th>Good</th>
										<th>Enough</th>
										<th>Poor</th>
										<th>Very Poor</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoExcellent}" /></td>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoVeryGood}" /></td>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoGood}" /></td>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoEnough}" /></td>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoPoor}" /></td>
										<td style="vertical-align: middle;"><c:out
												value="${cmrForm.fbNoVeryPoor}" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="cmrNote">Notes</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="cmrNote" value="${cmrForm.cmrNote}" />
							<form:textarea cssClass="form-control" path="cmrNote"
								value="${cmrForm.cmrNote}" maxlength="400" required="required"
								rows="6" cols="40" id="cmrNoteId" disabled="disabled"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="cmrStatusBean">Status</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="cmrStatusBean"
								value="${cmrForm.cmrStatusBean}" />
							<form:input cssClass="form-control" path="cmrStatusBean"
								value="${cmrForm.cmrStatusBean.statusName}" disabled="disabled"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="createdDate">Created Date</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="createdDate" value="${cmrForm.createdDate}" />
							<form:input cssClass="form-control" path="createdDate"
								value="${cmrForm.createdDate}" maxlength="10"
								required="required" disabled="disabled" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="cmrUser">Created By</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="cmrUser" value="${cmrForm.cmrUser}" />
							<form:input cssClass="form-control" path="cmrUser"
								value="${cmrForm.cmrUser.usrFname} ${cmrForm.cmrUser.usrLname}"
								disabled="disabled" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="modifiedDate">Modified at</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="modifiedDate" value="${cmrForm.modifiedDate}" />
							<form:input cssClass="form-control" path="modifiedDate"
								value="${cmrForm.modifiedDate}" maxlength="10"
								disabled="disabled" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="comments">Comments</form:label>
						</div>
						<div class="col-xs-6">
							<c:if test="${cmrForm.enableForApprove}">
								<form:textarea cssClass="form-control" path="comments"
									value="${cmrForm.comments}" maxlength="400" rows="6" cols="40"
									id="commentsId" />
							</c:if>
							<c:if test="${not cmrForm.enableForApprove}">
								<form:textarea cssClass="form-control" path="comments"
									value="${cmrForm.comments}" maxlength="400" rows="6" cols="40"
									id="commentsId" disabled="disabled" readonly="true" />
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-1" style="margin-right: 20px;">
								<input type="button" id="allCmrsId"
									class="btn btn-success cmr-button" value="Back"
									onclick="location.href='allCmrs?usrId=${sessionUser.usrId}'" />
							</div>
							<c:if test="${cmrForm.enableForApprove}">
								<div class="col-xs-1">
									<input type="submit" id="approveCmrId"
										class="btn btn-success cmr-button" value="Approve" />
								</div>
							</c:if>
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
		$(function() {
			$("#approveCmrId").click(function() {
				if (!confirm("Are you sure to approve this Report?")) {
					return false;
				}
			});
		});
	</script>

</body>
</html>