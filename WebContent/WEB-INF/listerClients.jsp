<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lister les clients</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>"/>
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		<div id="corps">
		<c:choose>
			<c:when test="${empty sessionScope.listeClients}">
				<p class="erreur">Aucun client enregistré.</p>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Adresse</th>
						<th>Téléphone</th>
						<th>Email</th>
						<th class="action">Action</th>
					</tr>
					<c:forEach items="${sessionScope.listeClients}" var="liste" varStatus="boucle">
						<tr class="${boucle.index % 2 == 0 ?'pair':'impair' }">
							<td><c:out value="${liste.value.nom}"/></td>
							<td><c:out value="${liste.value.prenom}"/></td>
							<td><c:out value="${liste.value.adresseLivraison}"/></td>
							<td><c:out value="${liste.value.numTelephone}"/></td>
							<td><c:out value="${liste.value.courriel}"/></td>
		                    <td class="action">
		                        <a href="<c:url value="/suppressionClient"><c:param name="nomClient" value="${ liste.key }" /></c:url>">
		                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
		                        </a>
		                    </td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		</div>
	</body>
</html>