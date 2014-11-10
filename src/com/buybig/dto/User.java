package com.buybig.dto;

import java.util.Vector;

public class User {
    String username, prenom, nom, adresse, pass, isAdmin;
    Vector<Livre> livresAchete = new Vector<Livre>();

    public User(String username, String prenom, String nom, String adresse, String pass, String isAdmin,Vector<Livre> livresAchete ) {
        this.username = username;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.pass = pass;
        this.isAdmin = isAdmin;
        this.livresAchete = livresAchete;
    }

    public String getUsername() {
        return this.username;
    }

    public String toXmlString() {
        return "<user username=\"" + this.username + "\" nom=\"" + nom + "\" prenom=\"" + this.prenom +
               "\" adresse=\"" + adresse + "\"pass=\"" + pass + "\"isAdmin=\"" + isAdmin + "\"></user>";
    }

	public String getPassword() {
		return this.pass;
	}
	public Boolean isAdmin(){
		
		if(this.isAdmin.equals("false"))
			return false;
		else
			return true;
	}
}
