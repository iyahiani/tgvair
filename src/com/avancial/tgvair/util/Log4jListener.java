package com.avancial.tgvair.util;

import java.io.FileInputStream;
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
		Properties prop = new Properties() ; 
		try {
			prop.load(new FileInputStream("D:/log4j.proporties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println("context Log4j chargé");
		
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
