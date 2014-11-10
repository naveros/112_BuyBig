package com.nicetry.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Book;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.InvalidCriterionException;
import com.nicetry.exceptions.InvalidCriterionValueException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidSortByPropertyException;

public class BookDAO extends DAO{

	public BookDAO() throws InvalidDTOClassException{//Class<Book> BookClass
		super(Book.class);
		//super(BookClass);
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
