package com.buybig.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.buybig.dao.implementations.BookDAO;
import com.buybig.dto.Book;
import com.buybig.dto.Order;
import com.buybig.dto.Purchase;
import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.ExistingOrderException;
import com.buybig.exceptions.ExistingPurchaseException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDAOException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidDTOException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidPrimaryKeyException;
import com.buybig.exceptions.InvalidSortByPropertyException;
import com.buybig.exceptions.MissingDTOException;
import com.buybig.exceptions.ServiceException;

public class BookService {

	private BookDAO bookDAO;

	public BookService(BookDAO bookDAO) throws InvalidDAOException {
		super();
		if (bookDAO == null) {
			throw new InvalidDAOException("Le DAO de user ne peut être null");
		}
		setBookDAO(bookDAO);
	}

	private BookDAO getBookDAO() {
		return this.bookDAO;
	}

	private void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public void addBook(Session session, Book bookDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getBookDAO().add(session, bookDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public Book getBook(Session session, String idBook)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException {
		try {
			return (Book) getBookDAO().get(session, idBook);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void updateBook(Session session, Book bookDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getBookDAO().update(session, bookDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void deleteBook(Session session, Book bookDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getBookDAO().delete(session, bookDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks(Session session, String sortByPropertyName)
			throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return (List<Book>) getBookDAO()
					.getAll(session, sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public List<Book> findBookByTitle(Session session, String titre,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidCriterionException, InvalidCriterionValueException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return getBookDAO().findByTitle(session, titre, sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void add(Session session, Book bookDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		addBook(session, bookDTO);
	}

	public void remove(Session session, Book bookDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, MissingDTOException,
			InvalidCriterionException, InvalidSortByPropertyException,
			InvalidPrimaryKeyException, ExistingPurchaseException,
			ExistingOrderException, ServiceException {
		if (session == null) {
			throw new InvalidHibernateSessionException(
					"La session ne peut être null");
		}
		if (bookDTO == null) {
			throw new InvalidDTOException("Le livre ne peut être null");
		}
		Book unBook = getBook(session, bookDTO.getIdBook());
		if (unBook == null) {
			throw new MissingDTOException("Le livre " + bookDTO.getIdBook()
					+ " n'existe pas");
		}
		Set<Purchase> purchases = unBook.getPurchases();
		List<Order> orders = new ArrayList<>(unBook.getOrders());
		if (!orders.isEmpty()) {
			Order order = orders.get(0);
			User booker = order.getUser();
			throw new ExistingOrderException("Le livre " // TODO : On enleve
															// cette exception ?
					+ unBook.getTitle()
					+ " (ID de livre : "
					+ unBook.getIdBook()
					+ ") est réservé pour "
					+ booker.getUserName() // TODO . user name ou autre?
					+ " (ID de membre : " + booker.getIdUser() + ")");
		}
		deleteBook(session, unBook);
	}
}
