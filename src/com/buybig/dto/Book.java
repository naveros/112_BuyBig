package com.buybig.dto;

import java.util.List;

public class Book {
	
	
	
	private String ISBN;
	private String name;
	private List<String> author;
	private double prix;
	
	
	public Book(){
		
		
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<String> getAuthor() {
		return author;
	}


	public void setAuthor(List<String> author) {
		this.author = author;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}

	
	
}
