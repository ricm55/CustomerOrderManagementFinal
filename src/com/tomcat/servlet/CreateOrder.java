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
import com.tomcat.beans.Order;
import com.tomcat.forms.CreateOrderForm;

public class CreateOrder extends HttpServlet {

    public static final String ATT_FORMORDER     = "form";
    public static final String ATT_ORDER         = "order";

    public static final String SESSION_CLIENTS   = "listeClients";
    public static final String SESSION_COMMANDES = "listeOrders";

    public static final String VUE_AFFICHE       = "/WEB-INF/displayOrder.jsp";
    public static final String VUE_CREER         = "/WEB-INF/createOrder.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_CREER ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        CreateOrderForm form_order = new CreateOrderForm();
        Order order = form_order.createOrder( request );

        // Enregistrer en requete la validation de la commande
        request.setAttribute( ATT_FORMORDER, form_order );
        request.setAttribute( ATT_ORDER, order );

        if ( form_order.getErreurs().isEmpty() ) {

            HttpSession session = request.getSession();
            Map<String, Client> listeClients = (HashMap<String, Client>) session.getAttribute( SESSION_CLIENTS );

            if ( listeClients == null ) {
                listeClients = new HashMap<String, Client>();
            }
            listeClients.put( order.getClient().getNom(), order.getClient() );

            session.setAttribute( SESSION_CLIENTS, listeClients );

            Map<String, Order> listeOrders = (HashMap<String, Order>) session.getAttribute( SESSION_COMMANDES );

            if ( listeOrders == null ) {
                listeOrders = new HashMap<String, Order>();
            }

            listeOrders.put( order.getDateOrder(), order );
            session.setAttribute( SESSION_COMMANDES, listeOrders );

            this.getServletContext().getRequestDispatcher( VUE_AFFICHE ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_CREER ).forward( request, response );
        }
    }
}
