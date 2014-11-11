<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Compte</title>

<nav class="navbar navbar-default">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Buybig</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="Shop">Shop</a></li>
			<li class="active"><a href="UserInfo">Mon compte</a></li>
			<li><a href="#">Logout</a></li>
		</ul>
	</div>
</div>
</nav>
<br />
<c:if test="${isAdmin}" var="res" scope="session">

	<jsp:useBean class="com.buybig.listeners.SessionCounter"
		id="sessionCounter" scope="application" />
	<UL>
		<LI>Total number of sessions in the life of this Web application:
			<jsp:getProperty name="sessionCounter" property="totalSessionCount" />.



		
		<LI>Number of sessions currently in memory: <jsp:getProperty
				name="sessionCounter" property="currentSessionCount" />.
		<LI>Maximum number of sessions that have ever been in memory at
			any one time: <jsp:getProperty name="sessionCounter"
				property="maxSessionCount" />.
	</UL>
    <div class="adminpromo">
      	<h2>Special status</h2>
		<form role="form" method="POST" action="UserInfo">
		 <div class="form-group">
          <label for="email">Email:</label>
          <input type="text" name="isbnPromo" class="form-control" placeholder="Enter isbn code">
          <input type="hidden" name="operation" value="onSaleUpdate" /><br>	
          <button type="submit" class="btn btn-default">Submit</button>
        </div>
		</form>
	</div>
	<br />
</c:if>
<div class="table-responsive">
	<Table class="table">
		<c:forEach items="${LivresUser}" var="livre">
			<tr>
				<td><c:out value="${livre.titre}" /></td>
				<td><c:out value="${livre.prix}" /></td>
			</tr>
		</c:forEach>
	</Table>
</div>

<p>Modifier votre profil</p>
<form method="POST" action="UserInfo">
	Username: <input type="text" name="username" /> <br /> Nom: <input
		type="text" name="name" /> <br /> Prenom: <input type="text"
		name="prenom" /> <br /> Adresse: <input type="text" name="adresse" />
	<br /> Password : <input type="password" name="pass" /> <br />
	Confirm Password : <input type="password" name="passConfirm" /> <br />
	<input type="hidden" name="operation" value="onInfoUpdate" /> <input
		type="submit" value="Modifier" />
</form>
</div>
</body>
</html>