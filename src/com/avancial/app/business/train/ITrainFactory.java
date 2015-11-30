package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.compagnieAerienne.TrainToCompagnie;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueAdapterDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueToCompagnieDataBean;

public interface ITrainFactory {

	public ITrain createTrain ();

	public ITrain createTrainByListCirculation(List<Circulation> listCircu); 
	public TrainCatalogue createTrainCatalgueFromBean(CirculationAdapterDataBean bean) ; //,List<Circulation> listCircul
	public TrainToCompagnie createTrain2cFromBean(TrainCatalogueToCompagnieDataBean bean) ; 
	public List<TrainCatalogueAdapterDataBean> createBeanFromTrainCatalogueAdapter(TrainCatalogue tc) ;
	public TrainCatalogueToCompagnieDataBean createT2CBeanFromTrain2Compagnie(TrainToCompagnie t2c) ;
	
	}
