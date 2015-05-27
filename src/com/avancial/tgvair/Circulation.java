package com.avancial.tgvair;

import java.util.List;

public class Circulation implements ICirculation {

private Desserte desserte ; 
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
	}
		
}
