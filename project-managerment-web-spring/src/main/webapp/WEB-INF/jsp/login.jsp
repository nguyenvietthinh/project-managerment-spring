<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    

    <title>Log in with your account</title>
	
  <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css">
</head>

<body>

<div class="container">

    <form style="margin: 120px;" method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span style='color: red'>${msg}</span>
            <input style="margin: 10px 0px 10px 0px" name="username" type="text" class="form-control login" placeholder="Username"
                   autofocus="true"/>
            <input style="margin: 10px 0px 10px 0px" name="password" type="password" class="form-control login" placeholder="Password"/>
            <span style="color: red">${errorMsg}</span>
           <button class="btn btn-lg btn-primary btn-block login" type="submit">Log In</button>
        </div>

    </form>



</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></body>
</html>
