package com.avancial.app.resources.connectionsUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.CirculationSSIMDataBean;

/**
 * 
 * @author ismael.yahiani DAO insertion utilisant JDBC
 */
public class InsertWithJDBC extends AConnectionJDBC {

   Logger logger = Logger.getLogger(InsertWithJDBC.class);
   private Context context;
   private DataSource dataSource;

   public InsertWithJDBC() {
      super();
   }

   /**
    * 
    * @param CirculationSSIMDataBean
    * @throws SQLException
    *            insertion de l'import fichier SSIM vers la base de données
    *            'Table CirculationSSIM' avec JDBC
    */
   public void insertIntoImportSSIMTable(CirculationSSIMDataBean c) throws SQLException {
      Connection dbConnection = null;
      PreparedStatement preparedStatement = null;

      String insertTableSQL = "INSERT INTO tgvair_import_ssim"
            + "(numeroTrain, originePointArret, destinationPointArret, GMTDepart,GMTArriver,dateDebutCirculation,dateFinCirculation,joursCirculation,rangTroncon,trancheFacultatif,restrictionTrafic,heureArriverCirculation,heureDepartCirculation) VALUES"
            + "(?,?,?,?,?,?,?,?,?,?,?,?,?)"; // dateDebutCirculation,dateFinCirculation,

      try {

         dbConnection = getDBConnection();
         preparedStatement = dbConnection.prepareStatement(insertTableSQL);
         preparedStatement.setString(1, c.getNumeroTrain());
         preparedStatement.setString(2, c.getOriginePointArret());
         preparedStatement.setString(3, c.getDestinationPointArret());
         preparedStatement.setString(4, c.getGMTDepart());
         preparedStatement.setString(5, c.getGMTArriver());
         preparedStatement.setDate(6, new Date(c.getDateDebutCirculation().getTime()));
         preparedStatement.setDate(7, new Date(c.getDateFinCirculation().getTime()));
         preparedStatement.setString(8, c.getJoursCirculation());
         preparedStatement.setInt(9, c.getRangTroncon());
         preparedStatement.setString(10, c.getTrancheFacultatif());
         preparedStatement.setString(11, c.getRestrictionTrafic());
         preparedStatement.setString(12, c.getHeureArriverCirculation());
         preparedStatement.setString(13, c.getHeureDepartCirculation());
         preparedStatement.executeUpdate();

      } catch (SQLException e) {
         this.logger.error("Erreur Insertion ImportSSIM" + e.getMessage());
      } finally {
         
         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         }

      }
   }

   /**
    * 
    * @param CirculationAdapterDataBean
    * @throws SQLException
    *            insertion des Ajustement de circulation dans la base de données
    *            'table Circulation' avec JDBC
    */

   public void insertRecordIntoTable(CirculationAdapterDataBean c) throws SQLException {
      java.sql.Connection dbConnection = null;
      java.sql.PreparedStatement preparedStatement = null;

      String insertTableSQL = "INSERT INTO tgvair_circulation"
            + "(idTrainCatalogue, idTraitementImport, idTraitementExport, dateDebutCirculation,dateFinCirculation,heureDepart,heureArriver,regimeCirculation,dateCreationLigneTrain) VALUES"
            + "(?,?,?,?,?,?,?,?,?)"; // dateDebutCirculation,dateFinCirculation,

      try {
         dbConnection = getDBConnection();

         preparedStatement = dbConnection.prepareStatement(insertTableSQL);
         preparedStatement.setInt(1, c.getTrainCatalogueDataBean().getIdTrainCatalogue());
         preparedStatement.setLong(2, c.getTraitementImport().getIdTraitementImport());
         preparedStatement.setInt(3, c.getTraitementExport().getIdTraitementExport());
         preparedStatement.setDate(4, new Date(c.getDateDebutCirculation().getTime()));
         preparedStatement.setDate(5, new Date(c.getDateFinCirculation().getTime()));
         preparedStatement.setString(6, c.getHeureDepart());
         preparedStatement.setString(7, c.getHeureArriver());
         preparedStatement.setString(8, c.getRegimeCirculation());
         preparedStatement.setDate(9, new Date(c.getDateCreationLigneTrain().getTime()));
         preparedStatement.executeUpdate();

      } catch (SQLException e) {
         this.logger.error("Erreur Insertion ImportSSIM" + e.getMessage());
      } finally {

         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (dbConnection != null) {
            dbConnection.close();
         }
      }
   }

   public Context getContext() {
      return context;
   }

   public void setContext(Context context) {
      this.context = context;
   }

   public DataSource getDataSource() {
      return dataSource;
   }

   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
   }
}
