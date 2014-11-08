package com.buybig.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Book;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;

public class BookDAO extends DAO{

	BookDAO(Class<Book> BookDTPCLass) throws InvalidDTOClassException{
		super(BookDTPCLass);
	}
	@SuppressWarnings("unchecked")
	public List<Book> findByTitle(Session session,
	        String username,
	        String sortByPropertyName) throws InvalidHibernateSessionException,
	        InvalidCriterionException,
	        InvalidCriterionValueException,
	        InvalidSortByPropertyException,
	        DAOException {
	        return (List<Book>) super.find(session,
	            Book.TITLE_BOOK_COLUMN_NAME, //TODO change it ?
	            username,
	            sortByPropertyName);
	    }

}
