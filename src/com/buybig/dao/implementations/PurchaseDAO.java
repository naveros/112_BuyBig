package com.buybig.dao.implementations;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Purchase;
import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;

public class PurchaseDAO extends DAO {

	PurchaseDAO(Class<Purchase> pretDTOClass) throws InvalidDTOClassException {
		super(pretDTOClass);
	}

	@SuppressWarnings("unchecked")
	public List<Purchase> findByUser(Session session, String idUser,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidCriterionException, InvalidSortByPropertyException,
			DAOException, InvalidCriterionValueException {

		return (List<Purchase>) super.find(session,
				User.USERNAME_USER_COLUMN_NAME, idUser, sortByPropertyName);
	}

	@SuppressWarnings("unchecked")
	public List<Purchase> findByBook(Session session, String idBook,
			String sortByPropertyName) throws InvalidHibernateSessionException,
			InvalidCriterionException, InvalidSortByPropertyException,
			DAOException, InvalidCriterionValueException {

		return (List<Purchase>) super.find(session,
				User.USERNAME_USER_COLUMN_NAME, idBook, sortByPropertyName);
	}

	@SuppressWarnings("unchecked")
	public List<Purchase> findByPurchaseDate(Session session,
			Timestamp datePurchase, String sortByPropertyName)
			throws InvalidHibernateSessionException, InvalidCriterionException,
			InvalidSortByPropertyException, DAOException,
			InvalidCriterionValueException {

		return (List<Purchase>) super.find(session,
				User.USERNAME_USER_COLUMN_NAME, datePurchase,
				sortByPropertyName);
	}

}
