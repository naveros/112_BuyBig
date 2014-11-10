package com.nicetry.service;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.User;
import com.nicetry.dao.implementations.UserDAO;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.InvalidDAOException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidDTOException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidPrimaryKeyException;
import com.nicetry.exceptions.InvalidSortByPropertyException;
import com.nicetry.exceptions.MissingDTOException;
import com.nicetry.exceptions.ServiceException;

public class UserService {
	private UserDAO userDAO;

	public UserService(UserDAO userDAO) throws InvalidDAOException {
		super();
		if (userDAO == null) {
			throw new InvalidDAOException("Le DAO de user ne peut être null");
		}
		setUserDAO(userDAO);
	}

	private UserDAO getUserDAO() {
		return userDAO;
	}

	private void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
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

	public void unsubscribe(Session session, User user)
			throws InvalidHibernateSessionException, InvalidDTOException,
			InvalidDTOClassException, InvalidPrimaryKeyException,
			MissingDTOException,
			ServiceException {
		if (session == null) {
			throw new InvalidHibernateSessionException(
					"La session ne peut être null");
		}
		if (user == null) {
			throw new InvalidDTOException("Le membre ne peut être null");
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
