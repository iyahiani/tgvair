package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.UtilisateurDataBean;

public class UtilisateurDao extends AbstractDao {
	public UtilisateurDataBean getUserByLogin(String login) {
		return (UtilisateurDataBean) this.getEntityManager().createQuery("SELECT user FROM UtilisateurDataBean user WHERE user.loginUser = :login").setParameter("login", login).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurDataBean> getAll() {
		return this.getEntityManager().createQuery("FROM UtilisateurDataBean").getResultList();
	}
}