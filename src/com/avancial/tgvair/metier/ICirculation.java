package com.avancial.tgvair.metier;

import java.text.ParseException;

public interface ICirculation {

	

	public boolean comparePeriode(Circulation circul); 
	public boolean compareOrigine(String origine) ;
	public boolean compareDestination(String destination) ;
	public boolean compareJoursCircul(String joursCircul) ; 
	public boolean compareHeureArriver(int heureArriver) ;
	public boolean compareHeuredepart(int heureDepart) ;
}
