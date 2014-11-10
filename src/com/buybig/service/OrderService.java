package com.buybig.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.buybig.dao.implementations.OrderDAO;
import com.buybig.dto.Order;
import com.buybig.exceptions.DAOException;
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

public class OrderService {
	    private OrderDAO orderDAO;
	    public OrderService(OrderDAO orderDAO) throws InvalidDAOException {
	        super();
	        if(orderDAO == null) {
	            throw new InvalidDAOException("Le DAO de réservation ne peut être null");
	        }	       
	        setOrderDAO(orderDAO);
	    }

	   
	    private OrderDAO getOrderDAO() {
			return orderDAO;
		}


		private void setOrderDAO(OrderDAO orderDAO) {
			this.orderDAO = orderDAO;
		}

	    public void addOrder(Session session,
	        Order order) throws InvalidHibernateSessionException,
	        InvalidDTOException,
	        InvalidDTOClassException,
	        ServiceException {
	        try {
	            getOrderDAO().add(session,
	                order);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	   
	   
	    public Order getOrder(Session session,
	        String idOrder) throws InvalidHibernateSessionException,
	        InvalidPrimaryKeyException,
	        ServiceException {
	        try {
	            return (Order) getOrderDAO().get(session,
	                idOrder);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	    public void updateOrder(Session session,
	        Order order) throws InvalidHibernateSessionException,
	        InvalidDTOException,
	        InvalidDTOClassException,
	        ServiceException {
	        try {
	            getOrderDAO().update(session,
	                order);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	  
	    public void deleteOrder(Session session,
	        Order Order) throws InvalidHibernateSessionException,
	        InvalidDTOException,
	        InvalidDTOClassException,
	        ServiceException {
	        try {
	            getOrderDAO().delete(session,
	                Order);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	  
	    @SuppressWarnings("unchecked")
		public List<Order> getAllOrders(Session session,
	        String sortByPropertyName) throws InvalidHibernateSessionException,
	        InvalidSortByPropertyException,
	        ServiceException {
	        try {
	            return (List<Order>) getOrderDAO().getAll(session,
	                sortByPropertyName);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }
	    
	    public List<Order> findByUser(Session session,
	        String idUser,
	        String sortByPropertyName) throws InvalidHibernateSessionException,
	        InvalidCriterionException,
	        InvalidSortByPropertyException,
	        ServiceException,
	        InvalidCriterionValueException {
	        try {
	            return getOrderDAO().findByUser(session,
	                idUser,
	                sortByPropertyName);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	    
	    public List<Order> findByBook(Session session,
	        String idBook,
	        String sortByPropertyName) throws InvalidHibernateSessionException,
	        InvalidCriterionException,
	        InvalidSortByPropertyException,
	        ServiceException,
	        InvalidCriterionValueException {
	        try {
	            return getOrderDAO().findByBook(session,
	                idBook,
	                sortByPropertyName);
	        } catch(DAOException daoException) {
	            throw new ServiceException(daoException);
	        }
	    }

	    public void place(Session session,
	        Order Order) throws InvalidHibernateSessionException,
	        InvalidDTOException,
	        InvalidPrimaryKeyException,
	        MissingDTOException,
	        InvalidCriterionException,
	        InvalidSortByPropertyException,
	        InvalidDTOClassException,
	        ServiceException,
	        InvalidCriterionValueException {
	        if(session == null) {
	            throw new InvalidHibernateSessionException("La session ne peut être null");
	        }
	        if(Order == null) {
	            throw new InvalidDTOException("La réservation ne peut être null");
	        }      
	        Order.setDateAdded(new Timestamp(System.currentTimeMillis()));
	        addOrder(session,
	            Order);
	    }

	    public void annuler(Session session,
	        Order Order) throws InvalidHibernateSessionException,
	        InvalidDTOException,
	        InvalidPrimaryKeyException,
	        MissingDTOException,
	        InvalidDTOClassException,
	        ServiceException {
	        if(session == null) {
	            throw new InvalidHibernateSessionException("La session ne peut être null");
	        }
	        if(Order == null) {
	            throw new InvalidDTOException("La réservation ne peut être null");
	        }
	        final Order uneOrder = getOrder(session,
	            Order.getIdOrder());
	        if(uneOrder == null) {
	            throw new MissingDTOException("La réservation "
	                + Order.getIdOrder()
	                + " n'existe pas");
	        }
	        deleteOrder(session,
	            uneOrder);
	    }
	}
