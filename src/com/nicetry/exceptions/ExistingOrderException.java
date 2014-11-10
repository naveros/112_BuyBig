package com.nicetry.exceptions;

public class ExistingOrderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingOrderException (String message){
		
		
		super(message);
	}
}