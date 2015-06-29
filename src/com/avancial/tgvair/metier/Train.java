package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain {

	private String[] numTrain	;

	private List<Circulation> circulation	;
	
	
	public Train(String[] numTrain) {
		super();
		this.numTrain = numTrain;
		this.circulation = new ArrayList<Circulation>();
	}

	public String[] getNumTrain() {
		return numTrain;
	}
	
	public void setNumTrain(String[] numTrain) {
		this.numTrain = numTrain	;
	}
	
	public List<Circulation> getCirculation() {
		return this.circulation	;
	}

	public void setCirculation(List<Circulation> circulation) {
		this.circulation = circulation	;
	}
	
	public String getChaineCompare() {

		StringBuilder sb = new StringBuilder();

		for (Circulation circulation : this.circulation) {
			sb.append(circulation.getChaineCircu())	;
			sb.append("\n")	;
		}
		return sb.toString()	;
	}
	
	public Train() {
		this.circulation = new ArrayList<>();
	}

	@Override
	public void addCirculation(Circulation circultation) {
		this.circulation.add(circultation);
	}
	
	@Override
	public List<Circulation> getCirculations() {
		return this.circulation	;
	}
	
	@Override
	public boolean compare(TrainCatalogue train) {
		boolean adapter = false	;
		
		for (Circulation ssimcircul : this.getCirculations()) {

			for (Circulation catalCircul : train.getCirculations()) {

				if (ssimcircul.compare(catalCircul)) return adapter=true	; 
			}
		}
			return adapter	;
	}
    
	@Override
	public boolean compare(ITrain train) {
		return false;
	}
	
	@Override
	public void adapt(TrainCatalogue train) {
		
	}

}
