package com.avanciale.TGVAIR.DAO;
import java.util.List;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.tgvair.metier.Circulation;
import com.avancial.tgvair.metier.ITrain;
import com.avancial.tgvair.metier.Train;

public class TrainDAO implements ITrain{

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
	public ITrain findTrainByID(int[] train,String ligne) {
		
		return null;
	}

	@Override
	public void setCirculation(List<Circulation> circulation) {
	}

	@Override
	public String getChaineCompare() {
		
		return null;
	}

	@Override
	public void addCirculation(Circulation circultation) {
	}

	@Override
	public List<Circulation> getCirculations() {
		
		return null;
	}

	
}
