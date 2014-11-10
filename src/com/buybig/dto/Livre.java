package com.buybig.dto;


public class Livre {
	String titre, auteur;
    String isbn;
    double prix;
    String onSale;

    public Livre(String titre, String auteur, String isbn, double prix, String onSale) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.prix = prix;
        this.onSale = onSale;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toXmlString(){
        return "<livre isbn=\""+this.isbn
               +"\" titre=\""+titre
               +"\" auteur=\""+this.auteur
               +"\" prix=\""+prix
                +"\" onSale=\""+onSale
               +"\"></livre>";       
    }
}
