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
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDAO userDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		ServletContext context = getServletContext();
		String path = context.getRealPath("librairie.xml");
	        File file = new File(path);
		userDAO = new UserDAO(file);
		
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if(userDAO.testLogin(username, pass)){
			request.setAttribute("username", username);
			request.setAttribute("isAdmin", userDAO.isAdmin(username));
			System.out.println("LOGIN OKK");

			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
		}else{
			System.out.println("LOGIN incorrect");
			 request.setAttribute("erreurLogin", "Login ou Mot de passe invalide");
				this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}

	}

}
