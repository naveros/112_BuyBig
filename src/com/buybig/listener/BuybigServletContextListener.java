package com.buybig.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BuybigServletContextListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}
 
        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent sce) {
        //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //sce.getServletContext().setAttribute("applicationContext", ac);
		System.out.println("ServletContextListener Started");
    }
}
