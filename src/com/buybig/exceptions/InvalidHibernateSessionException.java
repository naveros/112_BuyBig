package com.buybig.exceptions;

public class InvalidHibernateSessionException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidHibernateSessionException(String message){
		
		super(message);
	}
}
