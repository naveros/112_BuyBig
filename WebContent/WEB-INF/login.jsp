<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
YOOOOOOOOOOOOOOOOOOOOOOOOOOOO


<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">Session Info</TABLE>
<P>

<jsp:useBean class="com.buybig.listeners.SessionCounter"
          id="sessionCounter" scope="application" />
<UL>
<LI>Total number of sessions in the life of this
   Web application:
   <jsp:getProperty name="sessionCounter"
               property="totalSessionCount" />.
<LI>Number of sessions currently in memory:
  <jsp:getProperty name="sessionCounter"
              property="currentSessionCount" />.
<LI>Maximum number of sessions that have ever been in
    memory at any one time:
   <jsp:getProperty name="sessionCounter"
               property="maxSessionCount" />.
</UL>


  <form method="POST" action="Login">
            Username :
            <input type="text" name="username"/>
            <br/>
            Password :
            <input type="password" name="password"/>
            <br/>
            <input type="submit" value="Login"/>
        </form>
  <c:if test="${ !empty erreurLogin }"><p style="color:red;"><c:out value="${ erreurLogin }" /></p></c:if>


<p>
<a href="/112_BuyBig/Register">Vous n'êtes pas membre? Inscrivez-vous ! </a>
</p>
</body>
</html>