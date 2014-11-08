package com.buybig.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.buybig.dao.implementations.PurchaseDAO;
import com.buybig.dto.Book;
import com.buybig.dto.Purchase;
import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
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

public class PurchaseService/* extends Service */{ // TODO Service class

	private PurchaseDAO purchaseDAO;

	PurchaseService(PurchaseDAO purchaseDAO) throws InvalidDAOException {
		super();
		if (purchaseDAO == null) {
			throw new InvalidDAOException("Le DAO de prêt ne peut être null");
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

	public List<Purchase> findByDatePurchase(Session session,
			Timestamp datePurchase, String sortByPropertyName)
			throws InvalidHibernateSessionException, InvalidCriterionException,
			InvalidSortByPropertyException, ServiceException,
			InvalidCriterionValueException {
		try {
			return getpurchaseDAO().findByDatePurchase(session, datePurchase,
					sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	// sell,: c'était "commencer" dans biblio
	public void sell(Session session, Purchase purchase)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidPrimaryKeyException, MissingDTOException,
			InvalidCriterionException, InvalidSortByPropertyException,
			ExistingPurchaseException, InvalidDTOClassException,
			ServiceException, InvalidCriterionValueException, DAOException {
		if (session == null) {
			throw new InvalidHibernateSessionException(
					"La session ne peut être null");
		}
		if (purchase == null) {
			throw new InvalidDTOException("Le prêt ne peut être null");
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
