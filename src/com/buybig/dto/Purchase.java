package com.buybig.dto;

import java.sql.Timestamp;

public class Purchase extends DTO {
	private static final long serialVersionUID = 1L;

	private String idPurchase;
	private Timestamp purchaseDate;
	private User user;
	private Book book;

	public Purchase() {
		super();
	}

	public String getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(String idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

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

}
