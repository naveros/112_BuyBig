package com.buybig.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buybig.dao.UserDAO;

/**
 * Servlet implementation class Register
 */
//@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/registerForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserDAO userDAO;
		ServletContext context = getServletContext();
		String path = context.getRealPath("users.xml");
	        File file = new File(path);
		userDAO = new UserDAO(file);
		
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		if(userDAO.testRegister(username, pass, nom, prenom, adresse)){
			userDAO.inscription(username, prenom, prenom, adresse, pass);
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			
		}else{
			
			 request.setAttribute("erreurRegister", "Remplir tout les champs correctement");
				this.getServletContext().getRequestDispatcher("/WEB-INF/registerForm.jsp").forward(request, response);
		}
		
		
		
	}

}
