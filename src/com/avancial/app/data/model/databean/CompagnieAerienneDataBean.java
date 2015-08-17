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
 *  le bean de la table  tgvair_compagnie_aerienne
 */
@Entity 
@Table(name="tgvair_compagnie_aerienne")
public class CompagnieAerienneDataBean implements Serializable { 
	private static final Long serialVersionID = 1L ; 
	public CompagnieAerienneDataBean() {
		// TODO Auto-generated constructor stub
	}

	@Id 
	@Column(name="idCompagnieAerienne", unique = true, nullable = false)  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompagnieAeriennne ; 
	
	@Column(nullable=false, length=100 ) 
	private String CodeCompagnieAerienne ; 
	
	@Column(nullable=false, length=100 )
	private String libelleCompagnieAerienne ;

	public int getIdCompagnieAeriennne() {
		return idCompagnieAeriennne;
	}

	public void setIdCompagnieAeriennne(int idCompagnieAeriennne) {
		this.idCompagnieAeriennne = idCompagnieAeriennne;
	}

	public String getCodeCompagnieAerienne() {
		return CodeCompagnieAerienne;
	}

	public void setCodeCompagnieAerienne(String codeCompagnieAerienne) {
		CodeCompagnieAerienne = codeCompagnieAerienne;
	}

	public String getLibelleCompagnieAerienne() {
		return libelleCompagnieAerienne;
	}

	public void setLibelleCompagnieAerienne(String libelleCompagnieAerienne) {
		this.libelleCompagnieAerienne = libelleCompagnieAerienne;
	} 

	
}
