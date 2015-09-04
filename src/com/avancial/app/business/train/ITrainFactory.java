package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;

public interface ITrainFactory {

	public ITrain createTrain ();

	public ITrain createTrainByListCirculation(List<Circulation> listCircu); 
	public TrainCatalogue createTrainCatalgueFromBean(TrainCatalogueDataBean bean) ; 
	public TrainToCompagnie createTrain2cFromBean(TrainCatalogueToCompagnieDataBean bean) ; 
	public TrainCatalogueDataBean createBeanFromTrainCatalogue(TrainCatalogue tc) ;
	public TrainCatalogueToCompagnieDataBean createT2CBeanFromTrain2Compagnie(TrainToCompagnie t2c) ;
	
	}
