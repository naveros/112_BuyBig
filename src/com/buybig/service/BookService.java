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
	
	BookService(BookDAO bookDAO) throws InvalidDAOException {
		super();
		if (bookDAO == null) {
			throw new InvalidDAOException("Le DAO de user ne peut être null");
		}
		setBookDAO(bookDAO);
	}

	
	  // Region Getters and Setters
    /**
     * Getter de la variable d'instance <code>this.bookDAO</code>.
     *
     * @return La variable d'instance <code>this.bookDAO</code>
     */
    private BookDAO getBookDAO() {
        return this.bookDAO;
    }

    /**
     * Setter de la variable d'instance <code>this.bookDAO</code>.
     *
     * @param bookDAO
     *            La valeur à utiliser pour la variable d'instance
     *            <code>this.bookDAO</code>
     */
    private void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    /**
     * Getter de la variable d'instance <code>this.membreDAO</code>.
     *
     * @return La variable d'instance <code>this.membreDAO</code>
     */

    // EndRegion Getters and Setters

    /**
     * {@inheritDoc}
     */
    public void addBook(Session session,
        Book bookDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        InvalidDTOClassException,
        ServiceException {
        try {
            getBookDAO().add(session,
                bookDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Book getBook(Session session,
        String idBook) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        ServiceException {
        try {
            return (Book) getBookDAO().get(session,
                idBook);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateBook(Session session,
        Book bookDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        InvalidDTOClassException,
        ServiceException {
        try {
            getBookDAO().update(session,
                bookDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteBook(Session session,
        Book bookDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        InvalidDTOClassException,
        ServiceException {
        try {
            getBookDAO().delete(session,
                bookDTO);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<Book> getAllBooks(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return (List<Book>) getBookDAO().getAll(session,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Book> findBookByTitle(Session session,
        String titre,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidCriterionValueException,
        InvalidSortByPropertyException,
        ServiceException {
        try {
            return getBookDAO().findByTitle(session,
                titre,
                sortByPropertyName);
        } catch(DAOException daoException) {
            throw new ServiceException(daoException);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void acquerir(Session session,
        Book bookDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        InvalidDTOClassException,
        ServiceException {
        addBook(session,
            bookDTO);
    }

    /**
     * {@inheritDoc}
     */
    public void vendre(Session session,
        Book bookDTO) throws InvalidHibernateSessionException,
        InvalidDTOException,
        InvalidDTOClassException,
        MissingDTOException,
        InvalidCriterionException,
        InvalidSortByPropertyException,
        InvalidPrimaryKeyException,
        ExistingPurchaseException,
        ExistingOrderException,
        ServiceException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session ne peut être null");
        }
        if(bookDTO == null) {
            throw new InvalidDTOException("Le livre ne peut être null");
        }
        Book unBook = getBook(session,
            bookDTO.getIdBook());
        if(unBook == null) {
            throw new MissingDTOException("Le livre "
                + bookDTO.getIdBook()
                + " n'existe pas");
        }
        Set<Purchase> purchases = unBook.getPurchases();
        if(!purchases.isEmpty()) {
            for(Purchase purchase : purchases) {
                if(purchase.getPurchaseDate()== null) { //TODO: était getDateRetour(),  on a vrm besoin de vérifier ca? 
                    User pledger = purchase.getUser();
                    throw new ExistingPurchaseException("Le livre " //TODO : On enleve cette exception ? 
                        + unBook.getTitle()
                        + " (ID de livre : "
                        + unBook.getIdBook()
                        + ") a été prêté à "
                        + pledger.getUserName() //TODO . user name ou autre?
                        + " (ID de membre : "
                        + pledger.getIdUser()
                        + ")");
                }
            }
        }
        List<Order> orders = new ArrayList<>(unBook.getOrders());
        if(!orders.isEmpty()) {
            Order order = orders.get(0);
            User booker = order.getUser();
            throw new ExistingOrderException("Le livre " //TODO : On enleve cette exception ? 
                + unBook.getTitle()
                + " (ID de livre : "
                + unBook.getIdBook()
                + ") est réservé pour "
                + booker.getUserName() //TODO . user name ou autre?
                + " (ID de membre : "
                + booker.getIdUser()
                + ")");
        }
        deleteBook(session,
            unBook);
    }
}
