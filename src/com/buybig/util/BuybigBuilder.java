package com.buybig.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.buybig.dto.Book;
import com.buybig.exceptions.BuybigException;
import com.buybig.exceptions.InvalidHibernateSessionException;
import com.buybig.exceptions.InvalidSortByPropertyException;
import com.buybig.exceptions.ServiceException;
import com.buybig.service.BookService;
import com.buybig.service.OrderService;
import com.buybig.service.PurchaseService;
import com.buybig.service.UserService;

public class BuybigBuilder {
	private static final String SESSION_FACTORY_NAME = "sessionFactory";
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	UserService userService;
	BookService bookService;
	PurchaseService purchaseService;
	OrderService orderService;
	public BuybigBuilder(SessionFactory sf, UserService us, BookService bs ,PurchaseService ps, OrderService os){
		this.sessionFactory = sf;
		this.userService = us;
		this.bookService = bs;
		this.purchaseService = ps;
		this.orderService = os;		
	}
	
	private UserService getUserService() {
		return userService;
	}
	private void setUserService(UserService userService) {
		this.userService = userService;
	}
	private BookService getBookService() {
		return bookService;
	}
	private void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	private PurchaseService getPurchaseService() {
		return purchaseService;
	}
	private void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	private OrderService getOrderService() {
		return orderService;
	}
	private void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
		
	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return session;
	}

	private void setSession(Session session) {
		this.session = session;
	}

	private Transaction getTransaction() {
		return transaction;
	}

	private void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
    private Session openSession() throws BuybigException {
        try {
            setSession(getSessionFactory().openSession());
        } catch(HibernateException hibernateException) {
            //throw new BuybigException(hibernateException);
        }
        return getSession();
    }

   
    private void closeSession() throws BuybigException {
        try {
            getSession().close();
        } catch(HibernateException hibernateException) {
            //throw new BibliothequeException(hibernateException);
        }
    }

    public void beginTransaction() throws BuybigException {
        try {
            setTransaction(openSession().beginTransaction());
        } catch(HibernateException hibernateException) {
            //throw new BibliothequeException(hibernateException);
        }
    }

   
    public void commitTransaction() throws BuybigException {
        try {
            getTransaction().commit();
            closeSession();
        } catch(HibernateException hibernateException) {
            throw new BuybigException(hibernateException);
        }
    }

  
    public void rollbackTransaction() throws BuybigException {
        try {
            getTransaction().rollback();
            closeSession();
        } catch(HibernateException hibernateException) {
            //throw new BuybigException(hibernateException);
        }
    }
	
	public List<Book> getCategoryByName(String categoryName){
		List<Book> result = new ArrayList<Book>();
		try {
			beginTransaction();
			List<Book> allBooks = (List<Book>) getBookService().getAllBooks(this.getSession(), "TITLE");
			for(int i = 0;i<allBooks.size(); i++){
				if(allBooks.get(i).getCategory().equals(categoryName)){
					result.add(allBooks.get(i));
				}
			}
			commitTransaction();
		} catch (BuybigException 
				| InvalidHibernateSessionException 
				| InvalidSortByPropertyException 
				| ServiceException e) {
			try {
				rollbackTransaction();
			} catch (BuybigException e1) {
				e1.printStackTrace();
			}
		}
		//List<Book> allBooks = bookService.getAllBooks(, sortByPropertyName);
		return result;
	}
	
	
}
