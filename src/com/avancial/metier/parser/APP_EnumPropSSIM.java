package com.avancial.metier.parser;

public enum APP_EnumPropSSIM {

	TYPE_ENREGISTREMENT("type_enrg") ,
	IDENTIFIANT_TRANCHE("id_tranche") , 
	NUMERO_VARIANE_CIRCULATION("num_var_circul"), 
	RANG_TRONCON("rang_troncon"), 
	DATE_DEBUT_CIRCULATION("date_Debut_Circulation"),
	DATE_FIN_CIRCULATION("date_Fin_circulation"),
	JOURS_CIRCULATION("jours_circul"), 
	GARE_DEPART("gare_depart"),
	HEURE_DEPART("heure_depart"),
	HEURE_ARRIVER("heure_ARRIVER"),
	DIFFERENCE_GMT_DEPART("diff_gmt_depart"),
	GARE_ARRIVER("gare_arriver"),
	DIFFERENCE_GMT_ARRIVER("diff_gmt_arriver"),
	INDICATEUR_FER("indic_fer"),
	COMPAGNIE_TRAIN("compagnie_train"),
	NUMERO_TRAIN("num_train")	
	;
	private String nom ;
	
	private APP_EnumPropSSIM(String nom ) {
	this.nom = nom ;
	}
	
	 @Override
	   public String toString() {
	      return this.nom;
	   }
}
