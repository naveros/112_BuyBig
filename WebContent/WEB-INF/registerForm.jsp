<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
</head>
<body>
  <form method="POST" action="Register">
            Username :
            <input type="text" name="username"/>
            <br/>
            Mot de passe :
            <input type="password" name="password"/>
            <br/>
              Prénom :
            <input type="text" name="prenom"/>
            <br/>
              Nom :
            <input type="text" name="nom"/>
            <br/>
              Adresse :
            <input type="text" name="adresse"/>
            <br/>            
            <input type="submit" value="Register"/>
        </form>
        <c:if test="${ !empty erreurRegister }"><p style="color:red;"><c:out value="${ erreurRegister }" /></p></c:if>
</body>
</html>