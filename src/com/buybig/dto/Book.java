package com.buybig.dto;

import java.util.Set;

public class Book extends DTO{
	
	private String idbook;	
	private String title;
	private String author;
	private String isbn;
	private double price;
	private String category;
	Set<Purchase> purchases;
	Set<Order> orders;
	
	public Book(){
		super();		
	}
	public String getIdbook() {
		return idbook;
	}
	

	public void setIdbook(String idbook) {
		this.idbook = idbook;
	}

		
	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Set<Purchase> getPurchases() {
		return purchases;
	}


	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}


	public Set<Order> getOrders() {
		return orders;
	}


	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
