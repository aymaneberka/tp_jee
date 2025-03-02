<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" pageEncoding="ISO-8859-1"
         contentType="text/html;charset=ISO-8859-1"%>
<head>
    <title>MVC - Personnes</title>
</head>
<body>
<h2>Ajout/Modification d'une personne</h2>

<c:if test="${not empty erreurEdit}">
    <h3>Echec de la mise ? jour :</h3>
    L'erreur suivante s'est produite : ${erreurEdit}
    <hr>
</c:if>

<form method="post"
      action="${pageContext.servletContext.contextPath}/do/validate">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>${thePersonne.id}</td>
        </tr>
        <tr>
            <td>Pr?nom</td>
            <td><input type="text" value="${thePersonne.prenom}"
                       name="prenom" size="20"></td>
            <td><c:if test="${not empty erreurPrenom}">
                ${erreurPrenom}
            </c:if></td>
        </tr>
        <tr>
            <td>Nom</td>
            <td><input type="text" value="${thePersonne.nom}" name="nom"
                       size="20"></td>
            <td><c:if test="${not empty erreurNom}">
                ${erreurNom}
            </c:if></td>
        </tr>
        <tr>
            <td>Date de naissance (AAAA-MM-JJ)</td>
            <td><input type="text"
                       value="<fmt:formatDate pattern="yyyy-MM-dd" value="${thePersonne.dateNaissance}" />"
                       name="dateNaissance"></td>
            <td><c:if test="${not empty erreurDateNaissance}">
                ${erreurDateNaissance}
            </c:if></td>
        </tr>
        <tr>
            <td>Mari?</td>
            <td><c:if test="${thePersonne.marie}">

                <input type="radio" name="marie" value="true" checked>Oui
                <input type="radio" name="marie" value="false">Non

            </c:if> <c:if test="${!thePersonne.marie}">

                <input type="radio" name="marie" value="true" checked>Oui
                <input type="radio" name="marie" value="false">Non

            </c:if></td>
        </tr>
        <tr>
            <td>Nombre d'enfants</td>
            <td><input type="text" value="${thePersonne.nbEnfants}"
                       name="nbEnfants"></td>
            <td>${thePersonne.nbEnfants}</td>
        </tr>
    </table>
    <br> <input type="hidden" value="${thePersonne.id}" name="id" />
    <input type="submit" value="Valider" /> <a
        href="${pageContext.servletContext.contextPath}/do/list">Annuler</a>
</form>
</body>
</html>