package com.avancial.tgvair.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.tgvair.data.model.databean.PointArretDataBean;

public class PointsArretDAO extends ADAO{

	@Override
	public List<PointArretDataBean> getAll() {
	/*
		String sql = "From tgvair_point_arret";
	      Query requete = this.getEntityManager().createQuery(sql);
	      return requete.getResultList();
		*/ return  null ;
	}

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	} 

}
