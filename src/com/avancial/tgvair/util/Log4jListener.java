package com.avancial.tgvair.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class Log4jListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext ctx = e.getServletContext();
		//PropertyConfigurator.configure("log4j.properties"); 
		//PropertyConfigurator.configure(getClass().getResource("log4j.properties")); 
		Properties props = new Properties();
		try {
			//props.load(new FileInputStream("WEB-INF/log4j.properties")); 
			props.load(getClass().getResourceAsStream("log4j.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		PropertyConfigurator.configure(props);
	    System.out.println("Log4J Logging started for: ");
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
