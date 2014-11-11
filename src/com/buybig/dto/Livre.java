package com.buybig.dto;


public class Livre {
	public String titre, auteur;
	public String isbn;
	public  double prix;
	public String onSale;

    public Livre(String titre, String auteur, String isbn, double prix, String onSale) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.prix = prix;
        this.onSale = onSale;
    }

    public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getOnSale() {
		return onSale;
	}

	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
