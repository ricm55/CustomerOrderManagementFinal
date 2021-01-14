<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Affichage du client</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>" />
	</head>
	<body>	
		<c:import url="/inc/menu.jsp" />
		<div id="corps">
			<p class="info">${requestScope.form.resultat}</p>
			
			<p>Nom: <c:out value="${requestScope.client.nom}"/></p>
			<p>Prénom: <c:out value="${requestScope.client.prenom}"/></p>
			<p>Adresse de livraison: <c:out value="${requestScope.client.adresseLivraison}"/></p>
			<p>Numéro de téléphone: <c:out value="${requestScope.client.numTelephone}"/></p>
			<p>Email: <c:out value="${requestScope.client.courriel}"/></p>
			
		</div>
	</body>
</html>