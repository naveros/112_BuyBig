package com.buybig.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buybig.dto.Livre;

/**
 * Servlet implementation class Panier
 */
//@WebServlet("/Panier")
public class Panier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    /**
     * @see HttpServlet#HttpServlet()
     */
	 ArrayList<Livre> panier ;
    public Panier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String userID = "ABCD";
        String result = "<panier>";
        HttpSession session = request.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());


        String listePanierAchatsID = "panier";

        if (session.getAttribute("panier") == null) {
            panier = new ArrayList<Livre>();
            session.setAttribute("panier", panier);
        } else {
            panier = (ArrayList<Livre>) session.getAttribute("panier");
        }
        int count;
        count = panier.size();
        for (int i = 0; i < count; i++) {
            result += panier.get(i).toXmlString();
        }
        session.setAttribute(listePanierAchatsID, panier);
        result += "</panier>";
        out.print(result);
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        panier = (ArrayList<Livre>) session.getAttribute("panier");

        String option = request.getParameter("action");
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String isbn = request.getParameter("isbn");
        double prix = Double.parseDouble(request.getParameter("prix"));
        String onSale= request.getParameter("onSale");
        System.out.println("infos: {titre:"+titre+", auteur:"+auteur+", isbn:"+isbn);
        if (option.equals("add")) {
            Livre l = new Livre(titre, auteur, isbn, prix, onSale);
            panier.add(l);
        }
        if (option.equals("remove")) {
            Livre l = new Livre(titre, auteur, isbn, prix, onSale);
            for(int i= 0;i<panier.size();i++){
                if(panier.get(i).getIsbn().equals(l.getIsbn())){
                    panier.remove(i);
                }
            }            
        }
        session.setAttribute("panier", panier);
        out.close();
	}

}
