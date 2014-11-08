package com.buybig.dto;

public class Order {

	private String idOrder;
	private Book book; 
	private User user;
	
	public Order(){}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getIdOrder() {
		return idOrder;
	}


	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}
}
