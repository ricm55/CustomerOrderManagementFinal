<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Affichage de la commande</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>" />
	</head>
	<body>
		<c:import url="/inc/menu.jsp" />
		<div id="corps">
			<p class="info">${requestScope.form.resultat}</p>
			<p>Client</p>
			<p>Nom: <c:out value="${requestScope.order.client.nom}"/></p>
			<p>Prénom: <c:out value="${requestScope.order.client.prenom}"/></p>
			<p>Adresse de livraison: <c:out value="${requestScope.order.client.adresseLivraison}"/></p>
			<p>Numéro de téléphone: <c:out value="${requestScope.order.client.numTelephone}"/></p>
			<p>Email: <c:out value="${requestScope.order.client.courriel}"/></p>
				
			<p>Commande</p>
			<p>Date: <c:out value="${requestScope.order.dateOrder}"/></p>
			<p>Montant: <c:out value="${requestScope.order.montant}"/></p>
			<p>Mode de paiement: <c:out value="${requestScope.order.modePaiement}"/></p>
			<p>Statut du paiement: <c:out value="${requestScope.order.statutPaiement}"/></p>
			<p>Mode de livraison: <c:out value="${requestScope.order.modeLivraison}"/></p>
			<p>Status de livraison: <c:out value="${requestScope.order.statutLivraison}"/></p>
		</div>
	</body>
</html>