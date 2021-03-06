package com.buybig.listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounter
 *
 */
@WebListener
public class SessionCounter implements HttpSessionListener {
	  private int totalSessionCount = 0;
	  private int currentSessionCount = 0;
	  private int maxSessionCount = 0;
	  private ServletContext context = null;
	  
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event) {
    	totalSessionCount++;
    	   currentSessionCount++;
    	   if (currentSessionCount > maxSessionCount) {
    	     maxSessionCount = currentSessionCount;
    	   }
    	   if (context == null) {
    	    storeInServletContext(event);
    	   }
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	currentSessionCount--;
    }

    
    /** The total number of sessions created. */

    public int getTotalSessionCount() {
      return(totalSessionCount);
    }

    /** The number of sessions currently in memory. */

    public int getCurrentSessionCount() {
      return(currentSessionCount);
    }
       /** The largest number of sessions ever in memory
        * at any one time.
        */

       public int getMaxSessionCount() {
        return(maxSessionCount);
      }

      // Register self in the servlet context so that
      // servlets and JSP pages can access the session counts.

      private void storeInServletContext(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        context = session.getServletContext();
        context.setAttribute("sessionCounter", this);
      }
}
