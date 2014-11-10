package com.buybig.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * Servlet implementation class Shop
 */
//@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // private Statistic stats ; 
    
  //  private static final String CONTENT_TYPE = "text/html; charset=windows-1252"; ////////////////////////////////
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
       //ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
       //this.stats = (Statistic)ac.getBean("statistic");
       //this.buybigManager = (BuybigBuilder)ac.getBean("buybigManager");
      // this.buybigManager = new BuybigBuilder();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// List<Book> booksSexe = buybigManager.getCategoryByName("SEXE");
		//Book book =  booksSexe.get(0);
	//	int nbBooks = buybigManager.getNbBooks();
	//	 request.setAttribute("nbBooks", nbBooks);
		
	 /*       response.setContentType(CONTENT_TYPE);
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head><title>ServletLab2</title></head>");
	        out.println("<body>");
	        out.println("<p>The servlet has received a POST. This is the reply.</p>");
	        out.println("</body></html>");
	        out.close();*/
	    
	        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
