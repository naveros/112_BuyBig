package com.nicetry.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Book;
import com.buybig.dto.User;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.InvalidCriterionException;
import com.nicetry.exceptions.InvalidCriterionValueException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidSortByPropertyException;

public class UserDAO extends DAO{
	public UserDAO() throws InvalidDTOClassException{
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
