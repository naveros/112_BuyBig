package com.buybig.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buybig.dto.Categorie;
import com.buybig.util.XMLO;

//@WebServlet(name = "servCatalogue", urlPatterns = { "/servcatalogue" })
public class Catalogue extends HttpServlet {


    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    XMLO xm;
    Vector<Categorie> lib;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        String path = context.getRealPath("librairie.xml");
        File file = new File(path);
        xm = new XMLO(file);
        String result = "<Librairie>";
        String option;
        option = request.getParameter("Categorie");
        //JOptionPane.showMessageDialog(null, path);
        xm.setCategories();
        xm.getCategories();
        lib = xm.getCategories();
        for (int i = 0; i < lib.size(); i++) {
            result += lib.elementAt(i).toXmlString();
        }
        result += "</Librairie>";
        out.print(result);

        out.close();
    }
}
