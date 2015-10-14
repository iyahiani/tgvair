package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="tgvair_services")
public class ServiceDataBean implements Serializable{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Id
   @Column(name="idServive_tgvAir")
   private int idServive_tgvAir ; 
   private Date dateDebutService_tgvAir ;
   private Date datefinService_tgvAir ;
   
   
   public int getIdServive_tgvAir() {
      return this.idServive_tgvAir;
   }
   public void setIdServive_tgvAir(int idServive_tgvAir) {
      this.idServive_tgvAir = idServive_tgvAir;
   }
   public Date getDateDebutService_tgvAir() {
      return this.dateDebutService_tgvAir;
   }
   public void setDateDebutService_tgvAir(Date dateDebutService_tgvAir) {
      this.dateDebutService_tgvAir = dateDebutService_tgvAir;
   }
   public Date getDatefinService_tgvAir() {
      return this.datefinService_tgvAir;
   }
   public void setDatefinService_tgvAir(Date datefinService_tgvAir) {
      this.datefinService_tgvAir = datefinService_tgvAir;
   }
   
   
}
