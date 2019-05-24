<!DOCTYPE html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Format date -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <html lang="en" xmlns:th="http://www.thymeleaf.org"> -->

<head>

<meta charset="utf-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css">

<!-- Optional theme CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.css">

<!-- Private CSS -->
<link href="/css/searchStyle.css" rel="stylesheet" >
<!--  <link rel="stylesheet" type="text/css" th:href="@{/css/searchStyle.css}"/> -->
<!-- JQuery JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.js"></script>

<script type='text/javascript'
	src='http://ajax.aspnetcdn.com/ajax/knockout/knockout-3.1.0.js'></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- Private JS -->
<script type='text/javascript' src='/js/ProjectViewModel.js'></script>
<!-- <script type="text/javascript" th:src="@{/js/ProjectViewModel.js}"></script> -->
<title><spring:message code="application.title" /></title>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form">
			<h2><spring:message code="total.number.of.projects" />${message}  </h2>
			<h2>AAAA</h2>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="prjName">Project
					name:</label>
				<div class="col-sm-8 input-group">
					<input type="input" class="form-control" id="prjName"
						placeholder="Enter project name" data-bind="value: prjName">
					<input id="criterion" type="hidden"  value="${criterion}" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-primary" data-bind="click: query">Search</button>
				</div>
			</div>
			<div class="form-group" >
				<div class="col-sm-offset-2 col-sm-10">
					<div class="col-sm-10">
						<table class="table table-striped form-group">
							<thead>
								<tr>
									<th>Project ID</th>
									<th>Project Name</th>
									<th>Finishing Date</th>
								</tr>
							</thead>
							<tbody id="dataOnQuery" data-bind="foreach: projects">
								<tr data-bind="attr: {title: id, href: '/detail/' + id}">
									<td data-bind="text: id" ></td>
									<td data-bind="text: name" ></td>
									<td data-bind="text: finishingDate"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- Modal -->
	<div id="alertDialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header dialog-header-error">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">X</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-warning-sign"></span> <span
							class="title-content">Modal title</span>
					</h4>
				</div>
				<div class="modal-body">
					<p>Modal body</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>