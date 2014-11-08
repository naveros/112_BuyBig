package com.buybig.util;

import com.buybig.service.BookService;
import com.buybig.service.OrderService;
import com.buybig.service.PurchaseService;
import com.buybig.service.UserService;

public class BuybigBuilder {
	UserService userService;
	BookService bookService;
	PurchaseService purchaseService;
	OrderService orderService;
	public BuybigBuilder(UserService us, BookService bs ,PurchaseService ps, OrderService os){
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
	
}
