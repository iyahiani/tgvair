package com.avancial.app.data.model.databean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author ismael.yahiani
 *  le bean de la table  tgvair_point_arret
 */
@Entity 
@Table (name="tgvair_point_arret")

public class PointArretDataBean implements Serializable {

	private static final Long serialVersionID =1L; 
	
	public PointArretDataBean() {
	} 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false) 
	private Long idPointArret ; 
	
	@Column(nullable=false, length=100) 
	private String codeResarailPointArret ;
	
	@Column(nullable=false, length=100) 
	private String codeGDSPointArret ;
	
	@Column(nullable=false, length=100) 
	private String libellePointArret ;

	public Long getIdPointArret() {
		return idPointArret;
	}

	public void setIdPointArret(Long idPointArret) {
		this.idPointArret = idPointArret;
	}

	public String getCodeResarailPointArret() {
		return codeResarailPointArret;
	}

	public void setCodeResarailPointArret(String codeResarailPointArret) {
		this.codeResarailPointArret = codeResarailPointArret;
	}

	public String getCodeGDSPointArret() {
		return codeGDSPointArret;
	}

	public void setCodeGDSPointArret(String codeGDSPointArret) {
		this.codeGDSPointArret = codeGDSPointArret;
	}

	public String getLibellePointArret() {
		return libellePointArret;
	}

	public void setLibellePointArret(String libellePointArret) {
		this.libellePointArret = libellePointArret;
	} 
	
	
}
