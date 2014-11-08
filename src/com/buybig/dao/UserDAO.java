package com.buybig.dao;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;

public class UserDAO extends DAO{
	UserDAO(Class<User> BookDTPCLass) throws InvalidDTOClassException{
		super(BookDTPCLass);
	}
	@SuppressWarnings("unchecked")
	public List<User> findByNom(Session session,
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
