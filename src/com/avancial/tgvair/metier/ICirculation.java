package com.avancial.tgvair.metier;

import java.text.ParseException;

public interface ICirculation {

	

	public boolean comparePeriode(Circulation circul); 
	public boolean compareOrigine(Circulation circul) ;
	public boolean compareDestination(Circulation circul) ;
	public boolean compareJoursCircul(Circulation circul) ; 
	public boolean compareHeureArriver(Circulation circul) ;
	public boolean compareHeuredepart(Circulation circul) ;
}
