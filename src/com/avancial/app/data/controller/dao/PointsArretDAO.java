package com.avancial.app.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.socle.data.controller.dao.AbstractDao;

public class PointsArretDAO extends AbstractDao implements CrudDAO {

	@Override
	public List<PointArretDataBean> getAll() {
	/*
		String sql = "From tgvair_point_arret";
	      Query requete = this.getEntityManager().createQuery(sql);
	      return requete.getResultList();
		*/ return  null ;
	}

	@Override
	public Object create(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upDate(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		
	}

}
