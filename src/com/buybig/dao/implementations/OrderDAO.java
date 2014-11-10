package com.buybig.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Order;
import com.buybig.exceptions.DAOException;
import com.buybig.exceptions.InvalidCriterionException;
import com.buybig.exceptions.InvalidCriterionValueException;
import com.buybig.exceptions.InvalidDTOClassException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;
public class OrderDAO extends DAO {
    
    public OrderDAO() throws InvalidDTOClassException {
        super(Order.class);
    }

    @SuppressWarnings("unchecked")
	public List<Order> findByUser(Session session,
        String idUser,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidSortByPropertyException,
        DAOException,
        InvalidCriterionValueException {
        return (List<Order>) super.find(session,
            Order.ID_USER_COLUMN_NAME,
            idUser,
            sortByPropertyName);
    }

  
    @SuppressWarnings("unchecked")
	public  List<Order> findByBook(Session session,
        String idBook,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidSortByPropertyException,
        DAOException,
        InvalidCriterionValueException {
        return ( List<Order>) super.find(session,
        		Order.ID_BOOK_COLUMN_NAME,
            idBook,
            sortByPropertyName);
    }
}