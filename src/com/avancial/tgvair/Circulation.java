package com.avancial.tgvair;

import java.util.List;

public class Circulation implements ICirculation {

	private String dateDebut ; 
	private String dateFin ;
	private String heureArrivee ; 
	private String heureDepart ; 
	private String origine ; 
	private String destination ;
	private String joursCirculation ;
	
	public Circulation() {
		
	}
	
	public String getChaineCircu() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(this.getDateDebut());
		sb.append(this.getDateFin());
		sb.append(this.getHeureArrivee());
		sb.append(this.getHeureDepart());
		sb.append(this.getOrigine());
		sb.append(this.getDestination());
		sb.append(this.getJoursCirculation());
		return sb.toString() ; 
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(String heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public String getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
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

	public String getJoursCirculation() {
		return joursCirculation;
	}

	public void setJoursCirculation(String joursCirculation) {
		this.joursCirculation = joursCirculation;
	}
	
	
	
 }
/**
 * 
 *private Desserte desserte ; 
private RegimeCirculation regime ; 	

	public Circulation() {

}
	public RegimeCirculation getRegime() {
	return regime;
}
public void setRegime(RegimeCirculation regime) {
	this.regime = regime;
}
	public Desserte getDesserte() {
		return desserte;
	}
	public void setDesserte(Desserte desserte) {
		this.desserte = desserte;
	}
	
	public String SB() {
		String chaine ="" ; 
		return chaine;
	}/
 */