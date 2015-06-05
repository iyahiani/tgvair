package com.avancial.tgvair.metier;

import java.util.List;

import com.avancial.metier.parser.TGVAIR_enumParserCirculation;
import com.avancial.tgvair.DataBeans.CirculationDataBean;

public class Circulation implements ICirculation {

	private String dateDebut ; 
	private String dateFin ;
	private String heureArrivee ; 
	private String heureDepart ; 
	private String origine ; 
	private String destination ;
	private String joursCirculation ;
	
	private CirculationDataBean circulationDataBean ;
	
	public Circulation() {
		
	}	
	public String getChaineCircu() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(this.getDateDebut());
		sb.append(this.getDateFin());
		sb.append(this.getHeureDepart());
		sb.append(this.getHeureArrivee());
		sb.append(this.getOrigine());
		sb.append(this.getDestination());
		sb.append(this.getJoursCirculation());
		return sb.toString() ; 
	}
	
	public Circulation getCirculation(String chaine) {

		Circulation circul = new Circulation() ;
		if (chaine.length() > 0) {

			circul.setDateDebut(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionFin()));
			circul.setDateFin(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionFin()));
			circul.setDestination(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DESTINATION
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DESTINATION
							.getPostionFin()));
			circul.setOrigine(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_ORGINE
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_ORGINE
							.getPostionFin()));
			circul.setHeureDepart(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionFin()));
			circul.setHeureArrivee(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionFin()));
			circul.setJoursCirculation(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionFin()));
		}

		return circul;
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

	public CirculationDataBean getCirculationDatabean(Circulation circulation) {
		circulationDataBean = new CirculationDataBean();
		circulationDataBean.setDestination(circulation.getDestination()); 
		circulationDataBean.setHeureArriver(circulation.getHeureArrivee());
		circulationDataBean.setHeureDepart(circulation.getHeureDepart()); 
		return circulationDataBean ;
	}
 }