package com.tomcat.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tomcat.beans.Order;

public class SuppressionOrder extends HttpServlet {

    public static final String PARAM_DATE_COMMANDE = "dateCommande";
    public static final String SESSION_COMMANDES   = "listeOrders";

    public static final String VUE                 = "/listerOrders";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Recuperation du parametre de commande
        String dateCommande = getValeurParametre( request, PARAM_DATE_COMMANDE );

        // Recuperation de la liste de commande
        HttpSession session = request.getSession();
        Map<String, Order> listeOrders = (HashMap<String, Order>) session.getAttribute( SESSION_COMMANDES );

        if ( dateCommande != null && listeOrders != null ) {
            listeOrders.remove( dateCommande );
            session.setAttribute( SESSION_COMMANDES, listeOrders );
        }

        response.sendRedirect( request.getContextPath() + VUE );

    }

    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
