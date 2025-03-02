<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="ISO-8859-1"
         contentType="text/html;charset=ISO-8859-1"%>
<%@ page isErrorPage="true"%>

<%
    response.setStatus(200);
%>

<html>
<head>
    <title>MVC - Personnes</title>
</head>
<body>
<h2>MVC - personnes</h2>
L'exception suivante s'est produite :
<%=exception.getMessage()%>
<br>
<br>
<a href="${pageContext.servletContext.contextPath}/do/list">Retour ? la liste</a>
</body>
</html>