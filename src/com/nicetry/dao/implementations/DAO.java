package com.nicetry.dao.implementations;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.buybig.dto.DTO;
import com.nicetry.exceptions.DAOException;
import com.nicetry.exceptions.InvalidCriterionException;
import com.nicetry.exceptions.InvalidCriterionValueException;
import com.nicetry.exceptions.InvalidDTOClassException;
import com.nicetry.exceptions.InvalidDTOException;
import com.nicetry.exceptions.InvalidHibernateSessionException;
import com.nicetry.exceptions.InvalidPrimaryKeyException;
import com.nicetry.exceptions.InvalidSortByPropertyException;
import com.nicetry.util.DateImproved;

public class DAO {
    private Class<?> dtoClass;

    protected DAO(Class<?> dtoClass) throws InvalidDTOClassException {
      // super();
        if(dtoClass == null) {
            throw new InvalidDTOClassException("La classe de DTO ne peut �tre null");
        }
        setDtoClass(dtoClass);
    }

    public DAO(){
    	
    	
    }
    // Region Getters and Setters

    protected Class<?> getDtoClass() {
        return this.dtoClass;
    }

    private void setDtoClass(Class<?> dtoClass) {
        this.dtoClass = dtoClass;
    }

    // EndRegion Getters and Setters

    public void add(Session session,
        DTO dto) throws InvalidHibernateSessionException,
        InvalidDTOException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(dto == null) {
            throw new InvalidDTOException("Le DTO ne peut �tre null");
        }
        try {
            session.save(dto);
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }


    public DTO get(Session session,
        Serializable primaryKey) throws InvalidHibernateSessionException,
        InvalidPrimaryKeyException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(primaryKey == null) {
            throw new InvalidPrimaryKeyException("La clef primaire ne peut �tre null");
        }
        try {
            final DTO dto = (DTO) session.get(getDtoClass(),
                primaryKey);
            return dto;
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }

    public void update(Session session,
        DTO dto) throws InvalidHibernateSessionException,
        InvalidDTOException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(dto == null) {
            throw new InvalidDTOException("Le DTO ne peut �tre null");
        }
        try {
            session.update(dto);
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }


    public void save(Session session,
        DTO dto) throws InvalidHibernateSessionException,
        InvalidDTOException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(dto == null) {
            throw new InvalidDTOException("Le DTO ne peut �tre null");
        }
        try {
            session.saveOrUpdate(dto);
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }


    public void delete(Session session,
        DTO dto) throws InvalidHibernateSessionException,
        InvalidDTOException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(dto == null) {
            throw new InvalidDTOException("Le DTO ne peut �tre null");
        }
        try {
            session.delete(dto);
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }


    public List<?> getAll(Session session,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidSortByPropertyException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(sortByPropertyName == null) {
            throw new InvalidSortByPropertyException("La propri�t� utilis�e pour classer ne peut �tre null");
        }
        try {
            List<?> results = Collections.EMPTY_LIST;
            final Criteria criteria = session.createCriteria(getDtoClass());
            criteria.addOrder(Order.asc(sortByPropertyName));
            results = criteria.list();
            return results;
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }

    protected List<?> find(Session session,
        String propertyName,
        Object value,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidCriterionValueException,
        InvalidSortByPropertyException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(propertyName == null) {
            throw new InvalidCriterionException("La propri�t� � utiliser ne peut �tre null");
        }
        if(value == null) {
            throw new InvalidCriterionValueException("La valeur � trouver ne peut �tre null");
        }
        if(sortByPropertyName == null) {
            throw new InvalidSortByPropertyException("La propri�t� utilis�e pour classer ne peut �tre null");
        }
        try {
            List<?> results = Collections.EMPTY_LIST;
            if(value instanceof Date) {
                results = findByDate(session,
                    propertyName,
                    (Date) value,
                    sortByPropertyName);
            } else {
                final Criteria criteria = session.createCriteria(getDtoClass());
                criteria.add(Restrictions.eq(propertyName,
                    value));
                criteria.addOrder(Order.asc(sortByPropertyName));
                results = criteria.list();
            }
            return results;
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }

    private List<?> findByDate(Session session,
        String propertyName,
        Date date,
        String sortByPropertyName) throws InvalidHibernateSessionException,
        InvalidCriterionException,
        InvalidCriterionValueException,
        InvalidSortByPropertyException,
        DAOException {
        if(session == null) {
            throw new InvalidHibernateSessionException("La session Hibernate ne peut �tre null");
        }
        if(propertyName == null) {
            throw new InvalidCriterionException("La propri�t� � utiliser ne peut �tre null");
        }
        if(date == null) {
            throw new InvalidCriterionValueException("La date � trouver ne peut �tre null");
        }
        if(sortByPropertyName == null) {
            throw new InvalidSortByPropertyException("La propri�t� utilis�e pour classer ne peut �tre null");
        }
        try {
            List<?> results = Collections.EMPTY_LIST;
            final Criteria criteria = session.createCriteria(getDtoClass());
            criteria.add(Restrictions.between(propertyName,
                DateImproved.getStartDate(date),
                DateImproved.getEndDate(date)));
            criteria.addOrder(Order.asc(sortByPropertyName));
            results = criteria.list();
            return results;
        } catch(HibernateException hibernateException) {
            throw new DAOException(hibernateException);
        }
    }
}