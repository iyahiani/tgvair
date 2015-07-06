package com.avancial.app.resources.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * 
 * @author ismael.yahiani
 * initialisation de l'api de logging "Log4j"  
 */

@WebListener
public class Log4jListener implements ServletContextListener {

	private static Logger log = Logger.getLogger(Log4jListener.class) ;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext ctx = e.getServletContext();
		String pref = ctx.getRealPath("/") ;
		Properties props = new Properties();
		try {
			   props.load(new FileInputStream("log4j.properties"));
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		   PropertyConfigurator.configure(props);
		}
	}

