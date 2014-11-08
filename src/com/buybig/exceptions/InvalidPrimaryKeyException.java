package com.buybig.exceptions;

public class InvalidPrimaryKeyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPrimaryKeyException (String message){
		
		super(message);
	}
}
