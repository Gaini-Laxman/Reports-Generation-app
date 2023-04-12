<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">

</head>
<body>

	<div class="container pb-3 pt-3">
		<h3 >Report Application</h3>
	</div>
	

	<form:form action="searchData" modelAttribute="search" method="POST">
		<table>
			<tr>

				<td>Plan Name:</td>
				<td><form:select path="PlanName">
						<form:option value="">-Select-</form:option>
						<form:options items="${names}" />
					</form:select></td>

				<td>Plan Status:
				<td><form:select path="PlanStatus">
						<form:option value="">-Select-</form:option>
						<form:options items="${status}" />
					</form:select></td>


				<td>Gender:
				<td>
				<td><form:select path="gender">
						<form:option value="">-Select-</form:option>
						<form:option value="male">Male</form:option>
						<form:option value="Fe-Male">Fe-Male</form:option>
					</form:select>
				<td />

			</tr>

			<tr>
				<td>Start Date :</td>
				<td><form:input path="startDate" type="date"
						data-date-format="mm/dd/yyyy" /></td>

				<td>End Date :</td>
				<td><form:input path="endDate" type="date"
						data-date-format="mm/dd/yyyy" /></td>
			</tr>
			<tr>
			<td><a href="/" class="btn btn-secondary ">Reset</a></td>
				<td><input type="submit" value="Search" class="btn btn-primary" />
				</td>

			</tr>

		</table>
	</form:form>

	<hr />

	<table class="table table-striped table-hover">

		<thead>
			<tr>

				<th>S.No</th>
				<th>Holder Name</th>
				<th>Gender</th>
				<th>Plan Name</th>
				<th>Plan Status</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Benefit Amount</th>

			</tr>
		</thead>

		<tbody>

			<c:forEach items="${plans}" var="plan" varStatus="index">

				<tr>

					<td>${index.count}</td>
					<td>${plan.citizenName}</td>
					<td>${plan.gender}</td>
					<td>${plan.planName}</td>
					<td>${plan.planStatus}</td>
					<td>${plan.planStartDate}</td>
					<td>${plan.planEndDate}</td>
					<td>${plan.benefitAmount}</td>

				</tr>
			</c:forEach>
			<tr>
			<c:if test="${empty plans}">
			<td colspan="8" style="text-align: center">No Record Found</td>
			</c:if>
			</tr>
		</tbody>

	</table>

	<hr />

	Export : <a href="excel">Excel</a> <a href="pdf">Pdf</a>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>



</body>
</html>