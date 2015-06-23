package com.avancial.tgvair.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class MyLog4jListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext ctx = e.getServletContext();

		String prefix = ctx.getRealPath("/");
		String file = "WEB-INF" + System.getProperty("file.separator") + "log4j.properties";

		if (file != null) {
		
				PropertyConfigurator.configure(prefix + file);
			    System.out.println("Log4J Logging started for: " + prefix + file);
		} else {
			System.out
					.println("Log4J Is not configured for application Application: "
							+ prefix + file);
		}
	}

}
/*
 * 
 * @Override public void contextDestroyed(ServletContextEvent arg0) { }
 * 
 * @Override public void contextInitialized(ServletContextEvent arg0) {
 * 
 * Logger logger = null; ServletContext servletContext =
 * arg0.getServletContext(); String log4jFile =
 * servletContext.getInitParameter("log4j");
 * DOMConfigurator.configure(log4jFile); logger =
 * LogManager.getLogger(MyLog4jListener.class.getName());
 * logger.debug("Loaded: " + log4jFile);
 * 
 * }
 */
