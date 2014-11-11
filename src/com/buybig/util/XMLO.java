package com.buybig.util;


import com.buybig.dto.Categorie;
import com.buybig.dto.Livre;
import com.buybig.dto.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLO {
    DocumentBuilder dBuilder = null;
    File file = null;
    Document doc = null;
    Vector<Categorie> categories = new Vector<Categorie>();
    Vector<User> users = new Vector<User>();

    public XMLO(File file) {
        this.file = file;
        try {
            this.dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = dBuilder.parse(file);
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            e.printStackTrace();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            JOptionPane.showMessageDialog(null, "ERROR: lecture fichier detail:\n" + e.getStackTrace().toString());
        }
    }

    public Vector<Categorie> getCategories() {
        return this.categories;
    }

    public void setCategories() {
        NodeList nList = doc.getElementsByTagName("categorie");
        int count = nList.getLength();
        for (int i = 0; i < count; i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String nom = eElement.getAttribute("nom");
                Categorie cat = new Categorie(nom);
                NodeList nList2 = eElement.getElementsByTagName("livre");
                //JOptionPane.showMessageDialog(null, "Nb element livre dans cat:"+nList2.getLength());
                int count2 = nList2.getLength();
                for (int b = 0; b < count2; b++) {
                    Node nNode2 = nList2.item(b);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement2 = (Element) nNode2;
                        String titre = eElement2.getAttribute("titre");
                        String auteur = eElement2.getAttribute("auteur");
                        String isbn = eElement2.getAttribute("isbn");
                        double prix = Double.parseDouble(eElement2.getAttribute("prix"));
                        String onSale = eElement2.getAttribute("onSale");
                        Livre livre = new Livre(titre, auteur, isbn, prix, onSale);
                        cat.add(livre);
                    }
                }
                categories.add(cat);
            }
        }
    }


	public void saveCategories(){
		   System.out.println("function saveCATEGORIES");
		PrintWriter writer;
		try {
			System.out.println("SAVE USER FILE : "+file);
			writer = new PrintWriter(file, "UTF-8");
		
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println("<librairie>");
			System.out.println("<librairie>");
			for(int i=0; i < categories.size(); i ++){
				writer.println(categories.elementAt(i).toXmlString());
				System.out.println(categories.elementAt(i).toXmlString());
			}
			writer.println("</librairie>");
			System.out.println("</librairie>");
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
   public void setOnSaleStatus(String isbn){
	   System.out.println("function SetOnSaleStatus");
		for (int i = 0; i < categories.size(); i++) {

			for (int y = 0; y < categories.elementAt(i).getLivres().size(); y++) {

				if  (categories.elementAt(i).getLivres().elementAt(y).isbn == isbn) {
					   System.out.println(categories.elementAt(i).getLivres().elementAt(y).isbn);
					if(categories.elementAt(i).getLivres().elementAt(y).onSale == "true")
						categories.elementAt(i).getLivres().elementAt(y).onSale ="false";
					else
						categories.elementAt(i).getLivres().elementAt(y).onSale = "true";
				}

			}
		}
	   
		saveCategories();
   }
}
