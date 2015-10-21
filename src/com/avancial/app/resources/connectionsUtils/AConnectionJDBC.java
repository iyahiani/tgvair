package com.avancial.app.resources.connectionsUtils;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.* ;

/**
 * 
 * @author ismael.yahiani
 * JDBC Configurations 
 */

public abstract class AConnectionJDBC {

   /**
    * 
    * @return  JDBC Connection 
    */
   public  java.sql.Connection getDBConnection() {

      
      Connection dbConnection = null;
        
         try {
            InitialContext initContext = new InitialContext() ; 
            Context cont = (Context) initContext.lookup("java:comp/env")  ; 
            DataSource dataSource = (DataSource) cont.lookup("jdbc/socle"); 
            dbConnection = dataSource.getConnection() ;
         } catch (NamingException | SQLException e) {
    //        this.logger.error("Erreur Instantiation JDBC pour ImportSSIM"+e.getMessage());    
            e.printStackTrace();
         } 
               return dbConnection;
   }

}
