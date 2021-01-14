<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style1.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>

        	<form method="post" action="<c:url value="client"/>">
        		<fieldset>
        			<legend>Information du client</legend>
        			<c:import url="/inc/inc_client_form.jsp" />
        		</fieldset>
        		<p class="info">${requestScope.form.resultat}</p>
        		<input type="submit" value="valider"/>
        		<input type="reset" value="reset"/><br />
        	</form>

        </div>
    </body>
</html>