package com.avancial.app.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author ismael.yahiani
 *  le bean de la table  tgvair_train_catalogue
 */
@Entity 
@Table (name="tgvair_train_catalogue")

public class TrainCatalogueBean implements Serializable  {

private static final Long serialVersionID = 1L ;
	
	public TrainCatalogueBean() {	
	}
	
	@Id 
	@Column(unique=true, nullable=false) 
	private int idTrainCatalogue ; 
	
	@Column (nullable=false) 
	private int numeroTrainCatalogue ;
	
	@Column (nullable=false) 
	private int originePointArret ;
	
	@Column (nullable=false) 
	private int destinationPointArret ;
	
	@Column (nullable=false) 
	private Date heureDepartTrainCatalogue ;
	
	@Column (nullable=false) 
	private Date heureArriveeTrainCatalogue ;
	
	@Column (nullable=false, length=100) 
	private Date regimeJoursTrainCatalogue ;

	public int getIdTrainCatalogue() {
		return idTrainCatalogue;
	}

	public void setIdTrainCatalogue(int idTrainCatalogue) {
		this.idTrainCatalogue = idTrainCatalogue;
	}

	public int getNumeroTrainCatalogue() {
		return numeroTrainCatalogue;
	}

	public void setNumeroTrainCatalogue(int numeroTrainCatalogue) {
		this.numeroTrainCatalogue = numeroTrainCatalogue;
	}

	public int getOriginePointArret() {
		return originePointArret;
	}

	public void setOriginePointArret(int originePointArret) {
		this.originePointArret = originePointArret;
	}

	public int getDestinationPointArret() {
		return destinationPointArret;
	}

	public void setDestinationPointArret(int destinationPointArret) {
		this.destinationPointArret = destinationPointArret;
	}

	public Date getHeureDepartTrainCatalogue() {
		return heureDepartTrainCatalogue;
	}

	public void setHeureDepartTrainCatalogue(Date heureDepartTrainCatalogue) {
		this.heureDepartTrainCatalogue = heureDepartTrainCatalogue;
	}

	public Date getHeureArriveeTrainCatalogue() {
		return heureArriveeTrainCatalogue;
	}

	public void setHeureArriveeTrainCatalogue(Date heureArriveeTrainCatalogue) {
		this.heureArriveeTrainCatalogue = heureArriveeTrainCatalogue;
	}

	public Date getRegimeJoursTrainCatalogue() {
		return regimeJoursTrainCatalogue;
	}

	public void setRegimeJoursTrainCatalogue(Date regimeJoursTrainCatalogue) {
		this.regimeJoursTrainCatalogue = regimeJoursTrainCatalogue;
	}

		
}

