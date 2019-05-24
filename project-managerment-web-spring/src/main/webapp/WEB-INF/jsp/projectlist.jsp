<!DOCTYPE html>
<html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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

<!-- Private CSS -->

<link href="/css/common.css" rel="stylesheet">
<link href="/css/projectlist.css" rel="stylesheet">

<!-- JQuery JS -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type='text/javascript'
	src='http://ajax.aspnetcdn.com/ajax/knockout/knockout-3.1.0.js'></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- Private JS -->


<script type='text/javascript' src='/js/projectlist.js'></script>
<script type='text/javascript' src='/js/common.js'></script>
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
					href="${pageContext.request.contextPath}/projectlist?lang=en">EN</a>
					| <a class="language"
					href="${pageContext.request.contextPath}/projectlist?lang=fr">FR</a></li>
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
					<li class="nav-item"><a class="nav-link disabled" href="#"><spring:message
								code="menu.supplier" />${message}</a></li>
				</ul>
			</div>

			<div class="col-sm-7 text-left">
				<h3>
					<spring:message code="menu.projectlist" />${message}</h3>
				<hr>
				<form class="form-inline " action="" role="form">

					<h3>Welcome ${pageContext.request.userPrincipal.name}</h3>
					<div class="form-group form-search">
						<input style="width: 260px;" type="text"
							class="form-control txtsearch"
							placeholder="Project number, name, customer name"
							data-bind="value: prjName ">
					</div>
					<div class="form-group form-search">
						<select id="inputState" class="form-control"
							data-bind="   
					        value: selectedStatus,
					        options:        statusFilters,
					        optionsText:    'Name', 
					        optionsValue:   'Id',
					        
					    ">
						</select>

					</div>
					<div class="form-search form-group">
						<button class="btn btn-default btn-primary"
							data-bind="click: query">
							<spring:message code="btn.search" />${message}</button>

					</div>
					<div class="form-search form-group">
						<a href="resetsearch"><spring:message code="btn.resetsearch" />${message}</a>
					</div>
				</form>
				<input id="criterion" type="hidden" value="${criterion}" />
				<input id="sttcriterion" type="hidden" value="${sttcriterion}" />
				<form action="deleteproject" method="post" id="projectlistform">
					<input id="deletes" type="hidden" name="listIdDelete" value="">
					<table class="table0">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="list.numberproject" />${message}</th>
								<th><spring:message code="list.nameproject" />${message}</th>
								<th><spring:message code="list.status" />${message}</th>
								<th><spring:message code="list.customer" />${message}</th>
								<th><spring:message code="list.startdate" />${message}</th>
								<th><spring:message code="list.delete" />${message}</th>
							</tr>
						</thead>
						<tbody id="dataOnQuery" data-bind="foreach: projects">

							<tr>
								<td><input type="checkbox" name="ckx-delete"
									onclick='rowChkBoxClick(this)' class="ckbox"
									data-bind="value: id" id="ckx-delete"></td>

								<td class="number-td"><a style="float: right;"
									data-bind=" attr: { href: '/updateproject?id=' + id}, text: projectNumber"></a></td>
								<td style="width: 223px" class="name-td"><a
									data-bind="attr: { href: '/updateproject?id=' + id},text: projectName"></a></td>
								<td style="width: 137px" class="stt-td statusaaa"
									data-bind="value: status"></td>
								<td style="width: 190px" class="cus-td"
									data-bind="text: customer"></td>
								<td align="center" class="startDate-td"
									data-bind="text: startDate"></td>
								<td><a class="deletelist alone"
									data-bind="attr: { href: '/deleteproject?id=' + id}"> <img
										src="https://img.icons8.com/metro/26/000000/trash.png">
								</a></td>
							</tr>

						</tbody>
						<tr id="rowfooter">
							<td colspan="7"><strong id="count-checked"></strong>
								<button class="deletelist mult">
									delete selected items &nbsp<img
										src="https://img.icons8.com/metro/26/000000/trash.png">
								</button></td>
						</tr>
					</table>

				</form>
				<ul class="pagination">
					<li><a class="after">&raquo;</a></li>
				</ul>
				<ul class="pagination" data-bind="foreach: new Array(pageLength())">
					<li data-bind="click: $parent.myFunction($index())"><a
						class="lin" data-bind="text: $index()+2"></a></li>
				</ul>
				<ul class="pagination">
					<li><a class="before">&laquo;</a></li>

					<li><a class="lin hl" id="pageDefault">1</a></li>
				</ul>




			</div>
			<div class="col-sm-2 "></div>
		</div>
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