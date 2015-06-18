package com.avancial.tgvair.metier;

import java.util.Date;
import java.util.Timer;

public class TrainCatalogue {

	private String Nom_compagnie;
	private int[] Numero_Train_Cat;
	private String flight_number;
	private String origine;
	private String depart;
	private Date heure_Depart;
	private Date heure_Arriver;
	private String jours_Circulation_Compagnie;
	private Date Exception;

	public TrainCatalogue() {
	}

	public String getNom_compagnie() {
		return Nom_compagnie;
	}

	public void setNom_compagnie(String nom_compagnie) {
		Nom_compagnie = nom_compagnie;
	}

	public int[] getNumero_Train_Cat() {
		return Numero_Train_Cat;
	}

	public void setNumero_Train_Cat(int[] numero_Train_Cat) {
		Numero_Train_Cat = numero_Train_Cat;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Date getHeure_Depart() {
		return heure_Depart;
	}

	public void setHeure_Depart(Date heure_Depart) {
		this.heure_Depart = heure_Depart;
	}

	public Date getHeure_Arriver() {
		return heure_Arriver;
	}

	public void setHeure_Arriver(Date heure_Arriver) {
		this.heure_Arriver = heure_Arriver;
	}

	public String getJours_Circulation_Compagnie() {
		return jours_Circulation_Compagnie;
	}

	public void setJours_Circulation_Compagnie(
			String jours_Circulation_Compagnie) {
		this.jours_Circulation_Compagnie = jours_Circulation_Compagnie;
	}

	public Date getException() {
		return Exception;
	}

	public void setException(Date exception) {
		Exception = exception;
	}

}
