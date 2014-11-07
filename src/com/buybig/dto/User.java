package com.buybig.dto;

import java.util.Set;

public class User {

	private String userName;
	private String lastName;
	private String firstName;
	private String adresse;
	private String age;
	private Set<Purchase> purchaseHistory; 
	private Set<Order> cart;
	
	public User(){}
	
	
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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


	
	
}
