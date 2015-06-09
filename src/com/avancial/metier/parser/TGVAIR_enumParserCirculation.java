package com.avancial.metier.parser;

public enum TGVAIR_enumParserCirculation {
	/**
	 * position de la date de debut de la circulation
	 */
	POSITION_DATE_DEBUT_CIRCU(11,18),
	/**
	 * position de la date de fin de la circulation 
	 */
	POSITION_DATE_FIN_CIRCU(18,25),
	/**
	 * Position de l'heure de depart
	 */
	POSITION_HEURE_DEPART(37,41), 
	/**
	 * Position de l'heure d'arriver 
	 */
	POSITION_HEURE_ARRIVER(51,55),
	/**
	 * position de la gare d'origine
	 */
	POSITION_ORGINE(32,37),
	/**
	 * position de la gare de destinations
	 */
	POSITION_DESTINATION(46,51),
	/**
	 * position des jours de circulation
	 */
	POSITION_JOURS_CIRCULATION (25,32), 
	/**
	 * position Indicateur Fer
	 */
	
	POSITION_INDICATEUR_FER(61,62), 
	/**
	 * position Compagnie Train 
	 */
	
	POSITION_COMPAGNIE_TRAIN(62,64),
	/**
	 * position Numéro du Train
	 */
	POSITION_NUMERO_TRAIN(64,70), 
	/**
	 * valeur de la compagnie de train 
	 */
	VALEUR_COMPAGNIE_TRAIN("SN") 
	;
	 
	
	private int postionDebut ; 
	private int postionFin ;
	private String compagnieTrain ;
	
	private TGVAIR_enumParserCirculation(int debut, int fin) {
	
		this.postionDebut= debut ; 
		this.postionFin = fin ; 
	}
	
	private TGVAIR_enumParserCirculation(String compagnieTrain) {
		this.compagnieTrain = compagnieTrain ;
	}
	
	public int getPostionDebut() {
		return postionDebut;
	}

	public void setPostionDebut(int postionDebut) {
		this.postionDebut = postionDebut;
	}

	public int getPostionFin() {
		return postionFin;
	}

	public void setPostionFin(int postionFin) {
		this.postionFin = postionFin;
	}

	public String getCompagnieTrain() {
		return compagnieTrain;
	}

	public void setCompagnieTrain(String compagnieTrain) {
		this.compagnieTrain = compagnieTrain;
	}
	
	
}
