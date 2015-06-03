package com.avancial.metier.parser;

/**
 * Constantes pour parser le fichier SSIM. Contient la position et la longueur
 * de chaque donnée les
 * 
 * @author ismael.yahiani
 *
 */
public enum TGVAIR_enumParserSSIM {
	/**
	 * Potition du type de l'enregistrement
	 */
	POSITION_TYPE_ENR(0, 1),
	/**
	 * Valeur du type des enregistrements de type4
	 */
	VALEUR_TYPE4("4");
	private int postionFin;
	private int positionDebut;
	private String typeEnr;
	private String periodeCircul;
	private String joursCircul; 
	private String gareDepart;
	private int heureDepart;
	private int diffGMTDepart;
	private String gareArriver;
	private int heureArriver;
	private int diffGMTArriver;
	private String indicateurFer;
	private String compagnieTrain;
	private int numeroTrain;
	

	private TGVAIR_enumParserSSIM(String type) {
		this.setTypeEnr(type);
	}
	private TGVAIR_enumParserSSIM() {
		
	}
  
	private TGVAIR_enumParserSSIM(int position, int longueur) {

		this.positionDebut = position;
		this.postionFin = longueur;
	}

	public int getPositionFin() {
		return postionFin;
	}

	public int getPositionDebut() {
		return positionDebut;
	}

	public String getTypeEnr() {
		return typeEnr;
	}

	public void setTypeEnr(String typeEnr) {
		this.typeEnr = typeEnr;
	}

}
