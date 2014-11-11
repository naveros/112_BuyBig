package com.buybig.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buybig.util.XMLO;

/**
 * Servlet implementation class UserInfo
 */
// @WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	XMLO xmlo;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/userInfo.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        ServletContext context = getServletContext();
        String path = context.getRealPath("librairie.xml");
        File file = new File(path);
        xmlo = new XMLO(file);
        System.out.println("operation ===="+ request.getParameter("operation"));
		if (request.getParameter("operation").equals("onSaleUpdate")) {
			System.out.println("OKK Change sale status");
		
			xmlo.setOnSaleStatus((String) request.getParameter("isbnPromo"));
		}
		if (request.getParameter("operation") == "onInfoUpdate") {

	//TODO : 
			
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp")
				.forward(request, response);
	}

}
