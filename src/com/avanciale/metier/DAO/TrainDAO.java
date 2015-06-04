package com.avanciale.metier.DAO;

import java.util.List;

import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.Train;

public class TrainDAO implements ITrain {

	
	public TrainDAO() {
		
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
	public List<Train> getTrainByPeriodes() {
		
		return null;
	}

	@Override
	public ITrain findTrainByID(ITrain train) {
		
		return null;
	}

	@Override
	public ITrain findTrainByPerdiode(String periode) {
		
		return null;
	}

	@Override
	public ITrain findTrainByPerdiode(ITrain train) {
		
		return null;
	}

	@Override
	public void setCirculation(List<Circulation> circulation) {
	}

	@Override
	public String getChaineCompare() {
		
		return null;
	}
	
}
