package com.avancial.tgvair;

import java.util.List;

public class Circulation {

	private Desserte desserte ; 
	private List<PointsArret>  pointsArret ;
	public Desserte getDesserte() {
		return desserte;
	}
	public void setDesserte(Desserte desserte) {
		this.desserte = desserte;
	}
	public List<PointsArret> getPointsArret() {
		return pointsArret;
	}
	public void setPointsArret(List<PointsArret> pointsArret) {
		this.pointsArret = pointsArret;
	} 
	
}
