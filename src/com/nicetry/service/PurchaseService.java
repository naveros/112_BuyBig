package com.nicetry.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Book;
import com.buybig.dto.Purchase;
import com.buybig.dto.User;
import com.nicetry.dao.implementations.PurchaseDAO;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.ExistingPurchaseException;
import com.nicetry.exceptions.InvalidCriterionException;
import com.nicetry.exceptions.InvalidCriterionValueException;
import com.nicetry.exceptions.InvalidDAOException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidDTOException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidPrimaryKeyException;
import com.nicetry.exceptions.InvalidSortByPropertyException;
import com.nicetry.exceptions.MissingDTOException;
import com.nicetry.exceptions.ServiceException;

public class PurchaseService {

	private PurchaseDAO purchaseDAO;

	public PurchaseService(PurchaseDAO purchaseDAO) throws InvalidDAOException {
		super();
		if (purchaseDAO == null) {
			throw new InvalidDAOException("Le DAO de pr�t ne peut �tre null");
		}
		setPurchaseDAO(purchaseDAO);
	}

	// Region Getters and Setters

	private PurchaseDAO getpurchaseDAO() {
		return this.purchaseDAO;
	}

	private void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}

	// EndRegion Getters and Setters

	public void addPurchase(Session session, Purchase purchaseDTO)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getpurchaseDAO().add(session, purchaseDTO);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public Purchase getPurchase(Session session, String idPurchase)
			throws InvalidHibernateSessionException, ServiceException,
			InvalidPrimaryKeyException {
		try {
			return (Purchase) getpurchaseDAO().get(session, idPurchase);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void updatePurchase(Session session, Purchase purchase)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getpurchaseDAO().update(session, purchase);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void deletePurchase(Session session, Purchase purchase)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getpurchaseDAO().delete(session, purchase);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Purchase> getAllPurchases(Session session,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return (List<Purchase>) getpurchaseDAO().getAll(session,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public List<Purchase> findByUser(Session session, String idUser,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidCriterionException, InvalidSortByPropertyException,
			ServiceException, InvalidCriterionValueException {
		try {
			return getpurchaseDAO().findByUser(session, idUser,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public List<Purchase> findByBook(Session session, String idBook,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidCriterionException, InvalidSortByPropertyException,
			ServiceException, InvalidCriterionValueException {
		try {
			return getpurchaseDAO().findByBook(session, idBook,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public List<Purchase> findByPurchaseDate(Session session,
			Timestamp datePurchase, String sortByPropertyName)
			throws InvalidHibernateSessionException, InvalidCriterionException,
			InvalidSortByPropertyException, ServiceException,
			InvalidCriterionValueException {
		try {
			return getpurchaseDAO().findByPurchaseDate(session, datePurchase,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void sell(Session session, Purchase purchase)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidPrimaryKeyException, MissingDTOException,
			InvalidCriterionException, InvalidSortByPropertyException,
			ExistingPurchaseException, InvalidDTOClassException,
			ServiceException, InvalidCriterionValueException, DAOException {
		if (session == null) {
			throw new InvalidHibernateSessionException(
					"La session ne peut �tre null");
		}
		if (purchase == null) {
			throw new InvalidDTOException("Le pr�t ne peut �tre null");
		}
		Purchase unPurchase = getPurchase(session, purchase.getIdPurchase());

		User unUser = unPurchase.getUser();
		if (unUser == null) {
			throw new MissingDTOException("Le membre "
					+ purchase.getUser().getIdUser() + " n'existe pas");
		}

		Book unBook = unPurchase.getBook();
		if (unBook == null) {
			throw new MissingDTOException("Le livre "
					+ purchase.getBook().getIdBook() + " n'existe pas");
		}

		purchase.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
		addPurchase(session, purchase);
	}

}
