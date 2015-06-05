package com.avancial.tgvair.DataBeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tgvair_circulation") 
public class CirculationDataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name="idCirculation",nullable=true)
	private int id_circul ;
	
	@Column(name="originePointArret") 
	private String origine ; 
	
	@Column (name ="destinationPointArret") 
	private String destination ; 
	
	@Column (name = "heureDepartCiculation") 
	private String heureDepart ; 
	
	@Column (name ="heureArriveeDestination") 
	private String heureArriver ;

	public int getId_circul() {
		return id_circul;
	}

	public void setId_circul(int id_circul) {
		this.id_circul = id_circul;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}

	public String getHeureArriver() {
		return heureArriver;
	}

	public void setHeureArriver(String heureArriver) {
		this.heureArriver = heureArriver;
	}
	
	
}
