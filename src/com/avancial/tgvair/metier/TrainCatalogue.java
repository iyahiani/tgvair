package com.avancial.tgvair.metier;

import java.util.List;

public class TrainCatalogue implements ITrainCatalogue {

	private String Nom_compagnie;
	private String[] Numero_Train_Cat;
	private String flight_number;
	private List<Circulation> circulations;

	public TrainCatalogue() {

	}

	public String getNom_compagnie() {
		return Nom_compagnie;
	}

	public List<Circulation> getCirculations() {
		return circulations;
	}

	public void setCirculations(List<Circulation> circulations) {
		this.circulations = circulations;
	}

	public void setNom_compagnie(String nom_compagnie) {
		Nom_compagnie = nom_compagnie;
	}

	public String[] getNumero_Train_Cat() {
		return Numero_Train_Cat;
	}

	public void setNumero_Train_Cat(String[] numero_Train_Cat) {
		Numero_Train_Cat = numero_Train_Cat;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public void addCirculation(Circulation circulation) {
		this.circulations.add(circulation);
	}
}
