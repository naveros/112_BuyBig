package com.buybig.dto;

import java.sql.Timestamp;
import java.util.Set;

public class User {

	private int idUser;
	private String userName;
	private String lastName;
	private String firstName;
	private String adresse;
	private Timestamp birthDay;
	private Set<Purchase> purchaseHistory;
	private Set<Order> cart;

	public User() {
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

	public Timestamp getbirthDay() {
		return birthDay;
	}

	public void Timestamp(java.sql.Timestamp birthDay) { // ??
		this.birthDay = birthDay;
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}



}
