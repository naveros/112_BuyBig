package com.buybig.dto;

import java.sql.Timestamp;
import java.util.Set;

public class User extends DTO {
	private static final long serialVersionUID = 1L;

	public final static String USERNAME_USER_COLUMN_NAME = "username";

	private String idUser;
	private String userName;
	private String lastName;
	private String firstName;
	private String adresse;
	private Timestamp birthDate;
	private Set<Purchase> purchaseHistory;
	private Set<Order> cart;

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Set<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(Set<Purchase> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public Set<Order> getCart() {
		return cart;
	}

	public void setCart(Set<Order> cart) {
		this.cart = cart;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public Timestamp getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

}
