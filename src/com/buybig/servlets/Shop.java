package com.buybig.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.buybig.beans.Statistic;
import com.buybig.dto.Book;
import com.buybig.util.BuybigBuilder;

/**
 * Servlet implementation class Shop
 */
//@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Statistic stats ; 
    private BuybigBuilder buybigManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
       super.init(config);
       ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
       this.stats = (Statistic)ac.getBean("statistic");
       this.buybigManager = (BuybigBuilder)ac.getBean("buybigManager");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Book> booksSexe = buybigManager.getCategoryByName("SEXE");
		
		request.setAttribute("BooksSexe", booksSexe);
		 this.getServletContext().getRequestDispatcher("/WEB-INF/Shop.jsp").forward(request, response);
		   
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
