package com.buybig.dto;


public class Livre {
    String titre, auteur;
    String isbn;
    double prix;

    public Livre(String titre, String auteur, String isbn, double prix) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.prix = prix;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toXmlString(){
        return "<livre isbn=\""+this.isbn
               +"\" titre=\""+titre
               +"\" auteur=\""+this.auteur
               +"\" prix=\""+prix
               +"\"></livre>";       
    }
}
