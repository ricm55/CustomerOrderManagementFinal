<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<div id="menu">
    <p><a href="<c:url value="/client"/>">Créer un nouveau client</a></p>
    <p><a href="<c:url value="/order"/>">Créer une nouvelle commande</a></p>
    <p><a href="<c:url value="/listerClients"/>">Lister les clients</a></p>
    <p><a href="<c:url value="/listerOrders"/>">Lister les commandes</a></p>
</div>
