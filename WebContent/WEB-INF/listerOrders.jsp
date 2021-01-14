<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lister les commandes</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>"/>
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		<div id="corps">
			<c:choose>
				<c:when test="${empty sessionScope.listeOrders}">
					<p class="erreur">Aucune commande enregistrée.</p>
				</c:when>
				<c:otherwise>
					<table>
						<tr>
							<th>Client</th>
							<th>Date</th>
							<th>Montant</th>
							<th>Mode de paiement</th>
							<th>Statut de paiement</th>
							<th>Mode de livraison</th>
							<th>Statut de livraison</th>
							<th class="action">Action</th>
						</tr>
	  
						<c:forEach items="${sessionScope.listeOrders}" var="liste" varStatus="boucle">
							<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair' }">
								<td><c:out value="${liste.value.client.nom}"/></td>
								<td><c:out value="${liste.value.dateOrder}"/></td>
								<td><c:out value="${liste.value.montant}"/></td>
								<td><c:out value="${liste.value.modePaiement}"/></td>
								<td><c:out value="${liste.value.statutPaiement}"/></td>
								<td><c:out value="${liste.value.modeLivraison}"/></td>
								<td><c:out value="${liste.value.statutLivraison}"/></td>
			                    <td class="action">
			                        <a href="<c:url value="/suppressionOrder"><c:param name="dateCommande" value="${ liste.key }" /></c:url>">
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