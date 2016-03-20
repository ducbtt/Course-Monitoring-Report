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
<link
	href="<c:url value="/resources/css/jquery-ui-timepicker-addon.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/cmr.css" />" rel="stylesheet">

<style type="text/css">
#cmrNoteId, #crsDetailsId {
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
					modelAttribute="cmrForm" method="post" action="saveCmr">

					<c:if test="${not empty formError}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>${formError}</div>
					</c:if>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="course">Course</form:label>
						</div>
						<div class="col-xs-6">
							<c:if test="${cmrForm.action eq 'update'}">
								<form:hidden path="crsCode" value="${cmrForm.crsCode}" />
								<form:select cssClass="form-control" path="crsCode"
									disabled="true">
									<form:options items="${courseList}" itemLabel="fullCourseLabel"
										itemValue="crsCode" />
								</form:select>
							</c:if>
							<c:if test="${cmrForm.action eq 'create'}">
								<form:select cssClass="form-control" path="crsCode"
									onchange="updateCourseDesc()">
									<form:options items="${courseList}" itemLabel="fullCourseLabel"
										itemValue="crsCode" />
								</form:select>
							</c:if>
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
								disabled="true" />
						</div>
					</div>

					<form:hidden path="cmrId" value="${cmrForm.cmrId}" />
					<form:hidden id="actionId" path="action" value="${cmrForm.action}" />
					<input type="hidden" id="cmrStatusBeanId" name="isStatusNew"
						value="${empty cmrForm.cmrStatusBean or cmrForm.cmrStatusBean.isStatusNew() }">
					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="allCredited">All credited</form:label>
						</div>
						<div class="col-xs-6">
							<form:radiobuttons path="allCredited" items="${allCreditedList}"
								cssClass="form-control" itemLabel="desc" itemValue="value" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="noStdGreen">No of Allowed Students</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="noStdGreen"
								value="${cmrForm.noStdGreen}" />
						</div>
					</div>

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="crsSubmitDate">Final Course Submit Date</form:label>
						</div>
						<div class="col-xs-6">
							<form:input cssClass="form-control" path="crsSubmitDate"
								value="${cmrForm.crsSubmitDate}" id="crsSubmitDatePicker"
								maxlength="16" disabled="false" />
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
										<td style="vertical-align: middle;"><form:input
												path="fbNoExcellent" cssClass="form-control"
												value="${cmrForm.fbNoExcellent}" /></td>
										<td style="vertical-align: middle;"><form:input
												path="fbNoVeryGood" cssClass="form-control"
												value="${cmrForm.fbNoVeryGood}" /></td>
										<td style="vertical-align: middle;"><form:input
												path="fbNoGood" cssClass="form-control"
												value="${cmrForm.fbNoGood}" /></td>
										<td style="vertical-align: middle;"><form:input
												path="fbNoEnough" cssClass="form-control"
												value="${cmrForm.fbNoEnough}" /></td>
										<td style="vertical-align: middle;"><form:input
												path="fbNoPoor" cssClass="form-control"
												value="${cmrForm.fbNoPoor}" /></td>
										<td style="vertical-align: middle;"><form:input
												path="fbNoVeryPoor" cssClass="form-control"
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
							<form:textarea cssClass="form-control" path="cmrNote"
								value="${cmrForm.cmrNote}" maxlength="400" rows="6" cols="40"
								id="cmrNoteId" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-xs-3">&nbsp;</div>
						<div class="col-xs-6">
							<table>
								<tr>
									<td style="vertical-align: middle;"><input type="button"
										id="allCmrsId" class="btn btn-success cmr-button" value="Back"
										onclick="location.href='allCmrs?usrId=${sessionUser.usrId}'"></td>
									<c:if test="${cmrForm.action eq 'update'}">
										<c:if test="${empty cmrForm.cmrStatusBean or cmrForm.cmrStatusBean.isStatusNew() }" >
											<td style="vertical-align: middle;"><a id="deleteCmrId"
												href="deleteCmr?cmrId=${cmrForm.cmrId}"><input
													type="button" class="btn btn-success cmr-button"
													value="Delete" /></a>
										</c:if>
									</c:if>
									<td style="vertical-align: middle;"><input type="submit"
										id="saveCmrId" class="btn btn-success cmr-button" value="Save"
										name="saveonly" /></td>
									<c:if test="${empty cmrForm.cmrStatusBean or cmrForm.cmrStatusBean.isStatusNew() }">
										<td style="vertical-align: middle;"><input type="submit"
											id="sendCmrToCmId" class="btn btn-success cmr-button"
											style="width: 150px;" value="Send to Moderator"
											name="saveandsend" /></td>
									</c:if>
								</tr>
							</table>
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
	<script
		src="<c:url value='/resources/js/jquery-ui-timepicker-addon.js'/>"></script>

	<%-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>

	<script type="text/javascript">
		$(function() {
			var dateToday = new Date();
			$("#crsSubmitDatePicker").datetimepicker({
				dateFormat : 'dd.mm.yy',
				minDate : dateToday
			}).val();

			$("#deleteCmrId").click(function() {
				if (!confirm("Are you sure to delete?")) {
					return false;
				}
			});

			$("#sendCmrToCmId").click(function() {
				if (!confirm("Are you sure to save and send to CM?")) {
					return false;
				}
			});
			$("#saveCmrId")
					.click(
							function() {
								var statusNew = $('#cmrStatusBeanId').val();
								if (statusNew == 'false') {
									if (!confirm("This Report is being processed. Are you sure to update it?")) {
										return false;
									}
								}
							});

		});
		function updateCourseDesc() {
			var courseCode = $('#crsCode').val();
			$.ajax({
				type : 'POST',
				url : 'getCourseDesc?crsCode=' + courseCode,
				success : function(data) {
					$('#crsDetailsId').html(data);
				}
			});
		}
	</script>
</body>
</html>