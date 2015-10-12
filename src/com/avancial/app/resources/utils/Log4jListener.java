package com.avancial.app.resources.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.avancial.app.resources.constants.APP_TgvAir;

@WebListener("application context listener")
public class Log4jListener implements ServletContextListener {

   private static final long serialVersionUID = 1L;
   private static Logger log = Logger.getLogger(Log4jListener.class);

   @Override
   public void contextDestroyed(ServletContextEvent arg0) {

   }

   @Override
   public void contextInitialized(ServletContextEvent event) {
      Logger log = Logger.getLogger("org.hibernate");
      log.setLevel(Level.ERROR);
      ServletContext context = event.getServletContext()                           ;
      String log4jConfigFile = context.getInitParameter("log4j-config-location")   ;
      String fullPath = context.getRealPath("") + File.separator + log4jConfigFile ;
      PropertyConfigurator.configure(fullPath);
      System.out.println("Log4j Configuré");
   }

}
