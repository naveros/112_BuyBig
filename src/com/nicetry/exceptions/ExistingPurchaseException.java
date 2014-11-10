package com.nicetry.exceptions;

public class ExistingPurchaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingPurchaseException (String message){
		
		
		super(message);
	}
}