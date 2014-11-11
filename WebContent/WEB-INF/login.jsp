<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<%@ include file="/resources/css/login.css" %>
</style>
<title>Insert title here</title>
</head>
<body>
<form class="form-container" method="POST" action="Login" >
<div class="form-title"><h2>Login</h2></div>
<div class="form-title">Username</div>
<input class="form-field" type="text" name="username" /><br />
<div class="form-title">Password</div>
<input class="form-field" type="password" name="password" /><br />
<div class="submit-container">
<input class="submit-button" type="submit" value="Submit" />
<div class="form-title"> <c:if test="${ !empty erreurLogin }"><p style="color:red;"><c:out value="${ erreurLogin }" /></p></c:if></div>
<a href="/112_BuyBig/Register">Vous n'êtes pas membre? Inscrivez-vous!</a>
</div>
</form>
</body>
</html>