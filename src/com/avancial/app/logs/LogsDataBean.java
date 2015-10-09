package com.avancial.app.logs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ismael.yahiani
 * data du journal des logs 
 */
@Entity 
@Table(name="logs")
public class LogsDataBean implements Serializable {

   
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UserIdLogs_tgvAir", unique = true, nullable = false)
   private int userID ;  
   
   @Column(name="dateLogs_tgvair")  
   private Date dateLog ; 
    
   @Column(name="Logger_tgvair") 
   private String loggerClass ; 
   
   @Column(name="Level_tgvair")
   private String levelLog ;  
   
   @Column (name="Message_tgvair") 
   private String message ;

   public int getUserID() {
      return userID;
   }

   public void setUserID(int userID) {
      this.userID = userID;
   }

   public Date getDateLog() {
      return dateLog;
   }

   public void setDateLog(Date dateLog) {
      this.dateLog = dateLog;
   }

   public String getLoggerClass() {
      return loggerClass;
   }

   public void setLoggerClass(String loggerClass) {
      this.loggerClass = loggerClass;
   }

   public String getLevelLog() {
      return levelLog;
   }

   public void setLevelLog(String levelLog) {
      this.levelLog = levelLog;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
   
}
