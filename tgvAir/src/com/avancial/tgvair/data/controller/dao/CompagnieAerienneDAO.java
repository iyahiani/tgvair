package com.avancial.tgvair.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.tgvair.data.model.databean.CompagnieAerienneDataBean;

public class CompagnieAerienneDAO extends ADAO{

	@Override
	public List<CompagnieAerienneDataBean> getAll() {
		
		String sql = "From tgvair_compagnie_aerienne";
	      Query requete = this.getEntityManager().createQuery(sql);
	      return requete.getResultList();
	}

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
