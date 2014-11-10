package com.buybig.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.buybig.dto.Livre;
import com.buybig.dto.User;

public class UserDAO {
	DocumentBuilder dBuilder = null;
	File file = null;
	Document doc = null;
	Vector<User> users = new Vector<User>();

	public UserDAO(File file) {
		this.file = file;
		try {
			this.dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"ERROR: lecture fichier detail:\n"
							+ e.getStackTrace().toString());
		}
	}

	public void setUsers() {
		NodeList nList = doc.getElementsByTagName("user");
		int count = nList.getLength();
		for (int b = 0; b < count; b++) {
			User user;
			Node nNode = nList.item(b);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String username = eElement.getAttribute("username");
				String nom = eElement.getAttribute("nom");
				String prenom = eElement.getAttribute("prenom");
				String adresse = eElement.getAttribute("adresse");
				String pass = eElement.getAttribute("pass");
				String isAdmin = eElement.getAttribute("isAdmin");
				Vector<Livre> tmpLivres = new Vector<Livre>();
				NodeList nList2 = eElement.getElementsByTagName("livre");
				// JOptionPane.showMessageDialog(null,
				// "Nb element livre dans cat:"+nList2.getLength());
				int count2 = nList2.getLength();
				for (int c = 0; b < count2; c++) {
					Node nNode2 = nList2.item(b);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement2 = (Element) nNode2;
						String titre = eElement2.getAttribute("titre");
						String auteur = eElement2.getAttribute("auteur");
						String isbn = eElement2.getAttribute("isbn");
						double prix = Double.parseDouble(eElement2
								.getAttribute("prix"));
						String onSale = eElement2.getAttribute("onSale");
						Livre livre = new Livre(titre, auteur, isbn, prix,
								onSale);
						tmpLivres.add(livre);
					}
				}
				user = new User(username, prenom, nom, adresse, pass, isAdmin,
						tmpLivres);
				users.add(user);
			}
		}
	}

	public void inscription(String username, String prenom, String nom,
			String adresse, String pass) {
		Vector<Livre> livresAchete = new Vector<Livre>();
		users.add(new User(username, prenom, nom, adresse, pass, "false",
				livresAchete));
		saveUsers();
	}

	public Boolean isAdmin(String username) {
		Boolean result = false;
		for (int i = 0; i < users.size(); i++) {

			if  (users.elementAt(i).getUsername().equals(username) && users.elementAt(i).isAdmin()) {

				result = true;
			}

		}
		return result;

	}

	public Boolean testLogin(String username, String pass) {
		Boolean result = false;

		for (int i = 0; i < users.size(); i++) {

			if (users.elementAt(i).getUsername().equals(username)
					&& users.elementAt(i).getPassword().equals(pass)) {

				result = true;
			}

		}

		return result;
	}

	public void saveUsers(){
		
		PrintWriter writer;
		try {
			System.out.println(file);
			writer = new PrintWriter(file, "UTF-8");
			writer.println("<users>");
			
			for(int i=0; i < users.size(); i ++){
				writer.println(users.elementAt(i).toXmlString());
			}
			writer.println("</users>");
			
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.users.elementAt(1).toXmlString();
		
		
	}
	public boolean testRegister(String username, String pass, String nom, String prenom, String adresse) {
	if (username.length()>3 && pass.length()>3 && nom.length()>3 && prenom.length()>3 && adresse.length()>3)
		return true;
	
		return false;
	}
}
