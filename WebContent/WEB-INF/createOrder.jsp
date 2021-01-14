<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>" />
    </head>
    <body>
    	<c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="order"/> ">
                <fieldset>
                    <legend>Informations client</legend>
                    
					<c:if test="${!empty sessionScope.listeClients}">
						<label for="choixNouveauClient">Nouveau Client ?<span class="requis">*</span></label>
						<input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient" checked/>
						Oui
						<input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="ancienClient"/>
						Non
						<br /><br />
					</c:if>
                	<c:set var="client" value="${order.client}" scope="session" />
					<div id="nouveauClient">
						<c:import url="/inc/inc_client_form.jsp" />
					</div>
					<c:if test="${!empty sessionScope.listeClients}">
						<div id="ancienClient">
							<select name="listeClients" id="listeClients">
								<option value="">Choisissez un client...</option>
								<c:forEach items="${sessionScope.listeClients}" var="mapClients">
									<option value="${mapClients.value.nom }">
										${mapClients.value.prenom} ${mapClients.value.nom}
									</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
                </fieldset>
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="<c:out value="${ requestScope.order.dateOrder}"/>" size="20" maxlength="20" disabled />
                    <span class=erreur>${requestScope.form.erreurs["dateCommande"]}</span>
                    <br />
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="<c:out value="${ requestScope.order.montant}"/>" size="20" maxlength="20" />
                    <span class=erreur>${requestScope.form.erreurs["montantCommande"]}</span>
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="<c:out value="${ requestScope.order.modePaiement}"/>" size="20" maxlength="20" />
                    <span class=erreur>${requestScope.form.erreurs["modePaiementCommande"]}</span>               
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="<c:out value="${ requestScope.order.statutPaiement}"/>" size="20" maxlength="20" />
                    <span class=erreur>${requestScope.form.erreurs["statutPaiementCommande"]}</span>                        
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="<c:out value="${ requestScope.order.modeLivraison}"/>" size="20" maxlength="20" />
                    <span class=erreur>${requestScope.form.erreurs["modeLivraisonCommande"]}</span>     
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="<c:out value="${ requestScope.order.statutLivraison}"/>" size="20" maxlength="20" />
                    <span class=erreur>${requestScope.form.erreurs["statutLivraisonCommande"]}</span> 
                    <br />
                </fieldset>
                <p class="info">${requestScope.form.resultat}</p>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Reset" /> <br />
            </form>
        </div>
        
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script>
        	jQuery(document).ready( function() {
        		$("div#ancienClient").hide();
        		jQuery("input[name=choixNouveauClient]:radio").click(function(){
        			$("div#nouveauClient").hide();
        			$("div#ancienClient").hide();
        			var divId = jQuery(this).val();
        			$("div#" + divId).show();
        		});
        	});
        </script>
    </body>
</html>