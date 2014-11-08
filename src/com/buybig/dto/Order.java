package com.buybig.dto;

import java.sql.Timestamp;

public class Order extends DTO{
	private static final long serialVersionUID = 1L;
	
	private String idOrder;
	private Timestamp dateAdded;
	private User user;
	private Book book;
	
	public Order(){}

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public Timestamp getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
