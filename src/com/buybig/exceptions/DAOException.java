package com.buybig.exceptions;

import org.hibernate.HibernateException;

public class DAOException extends Exception{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(HibernateException hibernateException) {
	        super(hibernateException);
	    }
}
