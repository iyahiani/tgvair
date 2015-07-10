package com.avancial.app.resources.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jListener implements ServletContextListener {

   private static Logger log = Logger.getLogger(Log4jListener.class) ;
   @Override
   public void contextDestroyed(ServletContextEvent arg0) {

   }

   @Override
   public void contextInitialized(ServletContextEvent e) {
       ServletContext ctx = e.getServletContext();
       Properties prop = new Properties() ; 
       String pref = ctx.getRealPath("/") ;
       Properties props = new Properties();
       try {
           prop.load(new FileInputStream("D:/log4j.proporties"));
       } catch (FileNotFoundException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
           try {
            props.load(new FileInputStream("log4j.properties"));
         } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
         }
       } catch (IOException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       } 
       
       PropertyConfigurator.configure(prop);
       System.out.println("context Log4j charg?");
   }
       
         
       }
   