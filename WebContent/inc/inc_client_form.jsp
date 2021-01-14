<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<label for="nomClient">Nom <span class="requis">*</span></label>
<input type="text" id="nomClient" name="nomClient" value="<c:out value="${requestScope.client.nom}"/>" size="20" maxlength="20" />
<span class=erreur>${requestScope.form.erreurs["nomClient"]}</span>
<br />

<label for="prenomClient">Prénom </label>
<input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${requestScope.client.prenom}"/>" size="20" maxlength="20" />
<span class=erreur>${requestScope.form.erreurs["prenomClient"]}</span>
<br />
    
<label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
<input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${requestScope.client.adresseLivraison}"/>" size="20" maxlength="20" />
<span class=erreur>${requestScope.form.erreurs["adresseClient"]}</span>
<br />
    
<label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
<input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${requestScope.client.numTelephone}"/>" size="20" maxlength="20" />
<span class=erreur>${requestScope.form.erreurs["telephoneClient"]}</span>
<br />
                    
<label for="emailClient">Adresse email</label>
<input type="email" id="emailClient" name="emailClient" value="<c:out value="${requestScope.client.courriel}"/>" size="20" maxlength="60" />
<span class=erreur>${requestScope.form.erreurs["emailClient"]}</span>
<br />

