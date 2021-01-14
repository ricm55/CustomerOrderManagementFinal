package com.tomcat.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tomcat.beans.Client;

public class SuppressionClient extends HttpServlet {

    public static final String PARAM_NOM_CLIENT = "nomClient";
    public static final String SESSIONS_CLIENTS = "listeClients";

    public static final String VUE              = "/listerClients";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Recuperer le parametre du nom du client
        String nomClient = getValeurParametre( request, PARAM_NOM_CLIENT );

        // Recuperer la liste des clients
        HttpSession session = request.getSession();
        Map<String, Client> listeClients = (HashMap<String, Client>) session.getAttribute( SESSIONS_CLIENTS );

        if ( nomClient != null && listeClients != null ) {
            listeClients.remove( nomClient );
            session.setAttribute( SESSIONS_CLIENTS, listeClients );
        }

        response.sendRedirect( request.getContextPath() + VUE );

    }

    /* Verifier que le parametre n'est pas vide */
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
