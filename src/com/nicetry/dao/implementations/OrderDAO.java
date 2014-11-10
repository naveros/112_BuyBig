package com.nicetry.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import com.buybig.dto.Order;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.InvalidCriterionException;
import com.nicetry.exceptions.InvalidCriterionValueException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidSortByPropertyException;
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