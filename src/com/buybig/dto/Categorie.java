package com.buybig.dto;

import java.util.Vector;

public class Categorie {
    String nom;
    Vector<Livre> livres = new Vector<Livre>();

    public Categorie(String nom) {
        this.nom = nom;
    }
    public void add(Livre livre){
        this.livres.add(livre);
    }
    public String toXmlString(){
        String result = "<categorie nom=\""+this.nom+"\">";
        int count = livres.size();
        for(int i =0;i<count;i++){
            result+= livres.elementAt(i).toXmlString();
        }
        result += "</categorie>";
        return result;
    }

    public String getNom() {
        return nom;
    }

    public Vector<Livre> getLivres() {
        return livres;
    }
}
