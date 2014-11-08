package com.buybig.service;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dao.implementations.UserDAO;
import com.buybig.dto.User;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidDAOException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidDTOException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidPrimaryKeyException;
import com.buybig.exceptions.InvalidSortByPropertyException;
import com.buybig.exceptions.MissingDTOException;
import com.buybig.exceptions.ServiceException;

public class UserService {
	private UserDAO userDAO;

	UserService(UserDAO userDAO) throws InvalidDAOException {
		super();
		if (userDAO == null) {
			throw new InvalidDAOException("Le DAO de user ne peut �tre null");
		}
		setMembreDAO(userDAO);
	}

	private UserDAO getUserDAO() {
		return userDAO;
	}

	private void setMembreDAO(UserDAO membreDAO) {
		this.userDAO = membreDAO;
	}

	public void addUser(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getUserDAO().add(session, user);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public User getUser(Session session, String idUser)
			throws InvalidHibernateSessionException,
			InvalidPrimaryKeyException, ServiceException {
		try {
			return (User) getUserDAO().get(session, idUser);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void updateUser(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getUserDAO().update(session, user);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void deleteUser(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		try {
			getUserDAO().delete(session, user);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(Session session, String sortByPropertyName)
			throws InvalidHibernateSessionException,
			InvalidSortByPropertyException, ServiceException {
		try {
			return (List<User>) getUserDAO()
					.getAll(session, sortByPropertyName);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}

	public void subscribe(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, ServiceException {
		addUser(session, user);
	}

	public void desinscrire(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, InvalidPrimaryKeyException,
			MissingDTOException,
			ServiceException {
		if (session == null) {
			throw new InvalidHibernateSessionException(
					"La session ne peut �tre null");
		}
		if (user == null) {
			throw new InvalidDTOException("Le membre ne peut �tre null");
		}
		try {
			final User aUser = getUser(session, user.getIdUser());
			if (aUser == null) {
				throw new MissingDTOException("Le user " + user.getIdUser()
						+ " n'existe pas");
			}
			deleteUser(session, aUser);
		} catch (NumberFormatException numberFormatException) {
			throw new ServiceException(numberFormatException);
		}
	}
}
