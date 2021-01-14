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
import com.tomcat.forms.CreateClientForm;;

public class CreateClient extends HttpServlet {

    public static final String ATT_CLIENT      = "client";
    public static final String ATT_FORMCLIENT  = "form";

    public static final String SESSION_CLIENTS = "listeClients";

    public static final String VUE_AFFICHE     = "/WEB-INF/displayClient.jsp";
    public static final String VUE_CREER       = "/WEB-INF/createClient.jsp";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* transmission request/response to createClient.jsp */
        this.getServletContext().getRequestDispatcher( VUE_CREER ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Verification des informations du client
        CreateClientForm form_user = new CreateClientForm();
        Client user = form_user.creerClient( request );

        // Enregistrer en requete la validation des info du client
        request.setAttribute( ATT_FORMCLIENT, form_user );
        request.setAttribute( ATT_CLIENT, user );

        /*
         * Afficher la page de creation du client tant que l'utilisateur entre
         * les mauvaises informations
         */
        if ( form_user.getErreurs().isEmpty() ) {

            HttpSession session = request.getSession();
            Map<String, Client> listeClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

            if ( listeClients == null ) {
                listeClients = new HashMap<String, Client>();
            }
            listeClients.put( user.getNom(), user );

            session.setAttribute( SESSION_CLIENTS, listeClients );

            this.getServletContext().getRequestDispatcher( VUE_AFFICHE ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_CREER ).forward( request, response );
        }

    }
}
