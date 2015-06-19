package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain{

	private int[] numTrain ;
	
    private List<Circulation> circulation;

	public int[] getNumTrain() {
		return numTrain;
	}

	public void setNumTrain(int[] numTrain) {
		this.numTrain = numTrain;
	}
    
    public List<Circulation> getCirculation() {
		return this.circulation;
	}

	public void setCirculation(List<Circulation> circulation) {
		this.circulation = circulation;
	}

	public String getChaineCompare() {
	
		StringBuilder sb = new StringBuilder();
		
		for (Circulation circulation : this.circulation) {
			sb.append(circulation.getChaineCircu());
			sb.append("\n");
			}

		return sb.toString();
	}

	public Train() {
		this.circulation = new ArrayList<>();
	}

	@Override
	public boolean compare(ITrain train) {
		
		return false;
	}

	@Override
	public Train getTrainByID() {
		
		return null;
	}

	@Override
	public List<ITrain> getAllTrains() {
		
		return null;
	}

	@Override
	public ITrain findTrainByID(int[] train, String ligne) {
		return null ;
	}
}
