package com.tomcat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListerOrders extends HttpServlet {

    public static final String ATT_ORDER = "order";
    public static final String ATT_FORM  = "form";

    public static final String VUE       = "/WEB-INF/listerOrders.jsp";

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE ).forward( req, resp );
    }

    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

    }
}
