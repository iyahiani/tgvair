package com.avancial.app.resources.connectionsUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.log4j.Logger;

import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.StringToDate;
import com.mysql.jdbc.Statement;
/**
 * 
 * @author ismael.yahiani
 * JDBC Requetes SQL
 */
public class SelectWithJDBC extends AConnectionJDBC { 

   Logger logger = Logger.getLogger(SelectWithJDBC.class);
   public SelectWithJDBC() { 
      super();
   }
   
  
  
   public TrainCatalogueDataBean getCirculsFromCirculTable(int value) throws SQLException {
      TrainCatalogueDataBean tcdb = new TrainCatalogueDataBean() ;
      
      Connection dbConnection = null;
      PreparedStatement preparedStatement = null;
      ResultSet rs = null ;
      String selectFromTableSQL = "SELECT * FROM tgvair_train_catalogue WHERE idTrainCatalogue="+String.valueOf(value) ;  
      try {
         dbConnection =  getDBConnection()                              ;    
         preparedStatement = dbConnection.prepareStatement(selectFromTableSQL) ; 
       
         rs = preparedStatement.executeQuery(selectFromTableSQL) ; 
         if (rs.next()) {
            tcdb.setIdTrainCatalogue(rs.getInt("idTrainCatalogue"));
            tcdb.setHeureArriveeTrainCatalogue(StringToDate.getDateFromString(rs.getString("heureArriveeTrainCatalogue"), "HH:mm")  );
            tcdb.setHeureDepartTrainCatalogue(StringToDate.getDateFromString(rs.getString("heureDepartTrainCatalogue"), "HH:mm")  );
            tcdb.setDateDebutValidite(rs.getDate("dateDebutValidite")); 
            tcdb.setDateFinValidite(rs.getDate("dateFinValidite"));
            tcdb.setNumeroTrainCatalogue(rs.getString("numeroTrainCatalogue")); 
            tcdb.setNumeroTrainCatalogue1(rs.getString("numeroTrainCatalogue1"));
            tcdb.setNumeroTrainCatalogue2(rs.getString("numeroTrainCatalogue2"));
            tcdb.setRegimeJoursTrainCatalogue(rs.getString("regimeJoursTrainCatalogue"));
            tcdb.setOperatingFlight(rs.getString("operatingFlight"));
            
            }
         
      }
      catch(SQLException  e) { 
         this.logger.error("Erreur du Select JDBC" + e.getMessage());
         e.printStackTrace();
      } 
      
      finally { 
         
         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         } 
         return tcdb ; 
      }
      
     
   }
   
}
