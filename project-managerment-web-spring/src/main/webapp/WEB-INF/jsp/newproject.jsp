<!DOCTYPE html>
<html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Format date -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<head>

<meta charset="utf-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css">

<!-- Optional theme CSS -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- Date Picker CSS -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
<!-- Private CSS -->

<link href="/css/common.css" rel="stylesheet">
<link href="/css/newproject1.css" rel="stylesheet">
<!-- JS Date Picker -->


<!-- JQuery JS -->

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type='text/javascript'
	src='http://ajax.aspnetcdn.com/ajax/knockout/knockout-3.1.0.js'></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.min.js"></script> -->
<!-- Private JS -->

<script type='text/javascript' src='/js/common.js'></script>
<script type='text/javascript' src='/js/newproject.js'></script>

<title><spring:message code="application.title" /></title>


</head>
<body>
	<div class="container-fluid">
		<div class="navbar-header">
			<img class="main-logo--plain" alt="ELCA Vietnam"
				src="/image/elca.PNG" width="142" height="50"> <strong>Project
				Information Management</strong>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="list-inline">
				<li><a class="languageEN current"
					href="${pageContext.request.contextPath}/newproject?lang=en"
					class="black-link">EN</a> | <a class="language"
					href="${pageContext.request.contextPath}/newproject?lang=fr">FR</a></li>
				<li></li>
				<li></li>
				<li><a class="help" href="#"><strong><spring:message
								code="banner.help" />${message}</strong></a></li>
				<li><a onclick="document.forms['logoutForm'].submit()" href="#"><spring:message
							code="banner.Logout" />${message}</a></li>
			</ul>
			<form id="logoutForm" method="POST" action="/logout"></form>
		</div>
	</div>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav text-left">
				<ul class="nav flex-column linkactive">
					<li class="nav-item"><a class="nav-link title"
						href="projectlist"><spring:message code="menu.projectlist" />${message}</a></li>
					<li><br></li>
					<li class="nav-item"><a class="nav-link " href="newproject"
						style="font-size: 16px;"><spring:message code="menu.new" />${message}</a></li>
					<li class="nav-item"><a class="nav-link black-link" href="#"><spring:message
								code="menu.project" />${message}</a></li>
					<li class="nav-item"><a class="nav-link disabled" href="#"><spring:message
								code="menu.customer" />${message}</a></li>
					
			</ul>
			
			</div>

			<div class="col-sm-7 text-left">
				<h3>
					<spring:message code="menu.newproject" />${message}</h3>
				<hr>

				<form:form class="formNew" method="POST"
					modelAttribute="newprojectForm" id="newForm" >
					<div class="form-group row ">

						<label for="projectnumber"
							class="col-sm-2 col-form-label required"><spring:message
								code="new.numberproject" />${message}</label>
						<div class="col-sm-4">
							<form:input type="text" path="projectNumber" class="form-control"
								cssErrorClass="form-control has-error" id="projectNumberN"
								name="projectNumberN"></form:input>
								<div class="error">${errorNEmpty}</div>
							<div class="error">${errorDupl}</div>
						</div>

					</div>
					<div class="form-group row">
						<label for="projectname" class="col-sm-2 col-form-label required"><spring:message
								code="new.nameproject" />${message}</label>
						<div class="col-sm-10">

							<form:input type="text" path="projectName" class="form-control "
								cssErrorClass="form-control has-error" id="projectName"></form:input>
							<form:errors path="projectName" class="error" />

						</div>
					</div>
					<div class="form-group row">
						<label for="customer" class="col-sm-2 col-form-label required"><spring:message
								code="new.customer" />${message}</label>
						<div class="col-sm-10">

							<form:input type="text" path="customer" class="form-control"
								cssErrorClass="form-control has-error" id="customer" maxlength="255"></form:input>
							<form:errors path="customer" class="error" />

						</div>
					</div>
					<div class="form-group row">
						<label for="gender1" class="col-sm-2 col-form-label required"><spring:message
								code="new.group" />${message}</label>
						<div class="col-sm-4">
							<form:select class="form-control"
								cssErrorClass="form-control has-error" id="group" path="group">
								<form:options items="${groups}" itemValue="id" itemLabel="id" />

							</form:select>
							<form:errors path="group" class="error" />
						</div>
					</div>
					<div class="form-group row">

						<label for="members" class="col-sm-2 col-form-label"><spring:message
								code="new.member" />${message}</label>

						<div class="col-sm-10">
							<input type="text" class="form-control autocomplete" id="members"
								name="members" value="" />
						</div>
					</div>
					<div class="form-group row">
						<label for="status" class="col-sm-2 col-form-label required"><spring:message
								code="new.status" />${message}</label>
						<div class="col-sm-4">
							<form:select class="form-control"
								cssErrorClass="form-control has-error" id="status" path="status">
								<form:option value="NEW">New</form:option>
								<form:option value="PLA">Planned</form:option>
								<form:option value="INP">In progress</form:option>
								<form:option value="FIN">Finished</form:option>
							</form:select>
							<form:errors path="status" class="error" />
						</div>
					</div>
					<div class="form-group row">
						<label for="" class="col-sm-2 col-form-label required"><spring:message
								code="new.startdate" />${message}</label>
						<div class="col-sm-4">
							<div class='input-group date'>
								<form:input type='text' class="form-control  datetimepicker"
									cssErrorClass="form-control datetimepicker has-error"
									path="startDate" />
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>

							</div>
							<form:errors path="startDate" class="error" />
						</div>
						<label for="" class="col-sm-2 col-form-label end-date-label"><spring:message
								code="new.enddate" />${message}</label>
						<div class="col-sm-4">
							<div class='input-group date'>
								<form:input type='text' class="form-control  datetimepicker"
									cssErrorClass="form-control datetimepicker has-error"
									path="finishingDate" />
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar "></span>
								</span>

							</div>
							<form:errors path="finishingDate" class="error" />
						</div>
					</div>
					<div class="form-group row"></div>

					<hr>
					<div class="form-group row">
						<div class="col-sm-6 btn-float-left">
							<button type="button" class="btn btn-light"
								onclick="location.href = '/projectlist';">
								<spring:message code="btn.cancel" />${message}</button>
							<button type="submit" class="btn btn-primary ">
								<spring:message code="btn.createproject" />${message}</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<label for="potatos">How many you got? </label>
	<input name="potatos" id="potatos" type="text"></input>