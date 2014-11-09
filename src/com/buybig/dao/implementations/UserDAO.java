package com.buybig.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Book;
import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;

public class UserDAO extends DAO{
	UserDAO() throws InvalidDTOClassException{
		super(Book.class);
	}
	@SuppressWarnings("unchecked")
	public List<User> findByName(Session session,
	        String username,
	        String sortByPropertyName) throws InvalidHibernateSessionException,
	        InvalidCriterionException,
	        InvalidCriterionValueException,
	        InvalidSortByPropertyException,
	        DAOException {
	        return (List<User>) super.find(session,
	            User.USERNAME_USER_COLUMN_NAME,
	            username,
	            sortByPropertyName);
	    }

}
