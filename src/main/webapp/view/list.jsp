<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" pageEncoding="ISO-8859-1"
         contentType="text/html;charset=ISO-8859-1"%>

<html>

<head>
    <title>MVC - Personnes</title>
</head>
<body>
<c:if test="${not empty erreurs}">
    <h3>Les erreurs suivantes se sont produites :</h3>
    <ul>
        <c:forEach items="${erreurs}" var="item">
            <li>${item}</li>
        </c:forEach>
    </ul>
</c:if>


<ul>
    <hr>
    <h2>Liste des personnes</h2>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Date de naissance</th>
            <th>Marié</th>
            <th>Nombre d'enfants</th>
            <th></th>
        </tr>

        <c:forEach items="${personnes}" var="personne">
            <tr>
                <td>${personne.id}</td>
                <td>${personne.nom}</td>
                <td>${personne.prenom}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${personne.dateNaissance}" /></td>
                <td>${personne.marie}</td>
                <td>${personne.nbEnfants}</td>
                <td><a
                        href="${pageContext.servletContext.contextPath}/do/edit?id=${personne.id}">Modifier</a></td>
                <td><a
                        href="${pageContext.servletContext.contextPath}/do/delete?id=${personne.id}">Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.servletContext.contextPath}/do/edit?id=-1">Ajout</a>
</ul>


</body>
</html>