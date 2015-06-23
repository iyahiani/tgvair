package com.avancial.tgvair.util;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.primefaces.component.log.Log;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.avancial.socle.model.managedbean.LoginManagedBean;

public class MyLog4jListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext ctx = e.getServletContext();

		String prefix = ctx.getRealPath("/");
		String file = "WEB-INF" + System.getProperty("file.separator")
				+ System.getProperty("file.separator") + "log4j.properties";

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
