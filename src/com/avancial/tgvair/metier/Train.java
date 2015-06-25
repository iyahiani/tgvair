package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.List;

public class Train implements ITrain{

	private String[] numTrain ;
	
    private List<Circulation> circulation;

	public String[] getNumTrain() {
		return numTrain;
	}

	public void setNumTrain(String[] numTrain) {
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
		
		boolean adapt = false ; 
		for (Circulation ssimcircul : this.getCirculations()) {

			for (Circulation catalCircul : train.getCirculations()) {

				if ((ssimcircul.getDateDebut()
						.after(catalCircul.getDateDebut()))
						&& (ssimcircul.getDateFin().before(catalCircul
								.getDateFin()))) {
					if (!ssimcircul.getJoursCirculation().equals(
							catalCircul.getJoursCirculation()))
						{adapt = true; break ;} 
					if (ssimcircul.getHeureArrivee() != catalCircul
							.getHeureArrivee())
						{adapt = true ;break ; }
				}
			}
		}
			return adapt ; 
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

	@Override
	public void addCirculation(Circulation circultation) {
		this.circulation.add(circultation);
	}
	
	@Override
	public List<Circulation> getCirculations() {
		
		return this.circulation;
	}
	
}
