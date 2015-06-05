package com.avanciale.TGVAIR.DAO;

import java.util.List;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.tgvair.DataBeans.CirculationDataBean;
import com.avancial.tgvair.metier.Circulation;

/**
 * 
 * @author ismael.yahiani cette Classe offre des fonctionnalités pour l'objet
 *         Circulation
 */
public class CirculationDAO extends AbstractDao implements IDAO {

	private Circulation circul ;  
	public CirculationDAO() {
	}
	@Override
	public void save(Object t) throws ASocleException {
		
		try {
	         this.getEntityManager().getTransaction().begin();
	         this.getEntityManager().persist(t );
	         this.getEntityManager().flush();
	         this.getEntityManager().getTransaction().commit();
	      } catch (Exception e) {
	         this.getEntityManager().getTransaction().rollback();
	         throw SocleExceptionManager.getException(e);
	      }
	}

	@Override
	public void update(Object t) {
		
	}

	@Override
	public void delete(Object t) {
		
	}

	@Override
	public List<?> getAll() {
		
		return null;
	}
	
	

}
