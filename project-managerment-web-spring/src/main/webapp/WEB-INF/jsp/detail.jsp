<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JSTL Form -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html lang="en">

<head>

<meta charset="utf-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css">

<!-- Date Picker CSS -->
<link rel="stylesheet" type="text/css"
	href="http://eternicode.github.io/bootstrap-datepicker/bootstrap-datepicker/css/datepicker.css">

<!-- Private CSS -->
<link rel="stylesheet" href='/static/css/detailStyle.css'>

<!-- Optional theme CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.css">

<!-- JQuery JS -->
<script type='text/javascript'
	src='https://code.jquery.com/jquery-2.1.4.js'></script>
<script type='text/javascript'
	src='http://ajax.aspnetcdn.com/ajax/knockout/knockout-3.1.0.js'></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- Private JS -->
<script type='text/javascript' src='/static/js/DetailViewModel.js'></script>

<!-- Date Picker JS   -->
<script type='text/javascript'
	src='http://eternicode.github.io/bootstrap-datepicker/bootstrap-datepicker/js/bootstrap-datepicker.js'></script>

<title><spring:message code="application.title" /></title>
</head>

<body>

	<div class="container">
		<sf:form id="updateProjectForm" class="form-horizontal" role="form"
			method="POST" modelAttribute="project" action="/detail">
			<h2><spring:message code="detail.of.project" />${message}</h2>
			<div class="form-group col-sm-5">
				<div class="row">
					<label class=" col-sm-4" for="prjId">Id:</label>
					<div class="col-sm-8 input-group">
						<sf:input type="input" class="form-control" id="prjIdd" path="id"
							readonly="true" placeholder="Project id" />
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-user"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<label class=" col-sm-4" for="prjName">Name:</label>
					<div class="col-sm-8 input-group">
						<sf:input type="input" class="form-control" id="prjName"
							path="name" placeholder="Enter project name" />
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-pencil"></span>
						</div>
					</div>
				</div>
				<!-- 
				 -->
				<div class="row">
					<label class=" col-sm-4" for="prjFinishingDate">Start
						Date:</label>
					<div class="col-sm-8 input-group">
						<sf:input type="input" class="form-control" id="prjFinishingDate"
							readonly="true" path="startDate"
							placeholder="Enter project finishing date" />
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-th"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="row"></div>
			<div class="form-group col-sm-5">
				<div class="col-sm-offset-4 col-sm-8 input-group">
					<sf:errors path="*" cssClass="error" />
				</div>
				<div class="col-sm-offset-4 col-sm-8 input-group">
					<sf:button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-floppy-disk"></span> Save</sf:button>
				</div>
			</div>
		</sf:form>
	</div>

</body>
</html>