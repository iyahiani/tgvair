package com.avancial.app.business.parser;

public enum APP_Enum_Date_Parser {

	POSITION_JOUR_debPeriode(0,2) ,
	POSITION_MOIS_debPeriode(2,5) ,
	POSITION_ANNEES_debPeriode(5,7) ;
	
	private int positionDebut ; 
	private int positionFin ;
	
	
	APP_Enum_Date_Parser(int deb, int fin) {
		this.positionDebut = deb ; 
		this.positionFin = fin ;	
	}


	public int getPositionDebut() {
		return positionDebut;
	}


	public void setPositionDebut(int positionDebut) {
		this.positionDebut = positionDebut;
	}


	public int getPositionFin() {
		return positionFin;
	}


	public void setPositionFin(int positionFin) {
		this.positionFin = positionFin;
	}
	
	
}
