package com.buybig.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buybig.dto.Livre;
import com.buybig.util.Statistic;

/**
 * Servlet implementation class Shop
 */
//@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Statistic stats ;
    


    public Shop() {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
       super.init(config);
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		HttpSession session = request.getSession(true);

		
		 
	       if (session.isNew() || session.getAttribute("username") == null) {
	    	   ArrayList<Livre> panier  = new ArrayList<Livre>();
	            session.setAttribute("panier", panier);
	            
	    	   this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	    	   System.out.println("session is new ");

	        } else {
		    	   System.out.println("session is NOT new ");
	        	 this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	        }
    
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
