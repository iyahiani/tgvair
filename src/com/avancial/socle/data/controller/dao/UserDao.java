package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.UserDataBean;

/**
 * 
 * @author caglar.erdogan
 *
 */
public class UserDao extends AbstractDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDataBean> getAll() {
		return this.getEntityManager().createQuery("FROM UserDataBean").getResultList();
	}

	public UserDataBean getUserByLogin(String login) {
		return (UserDataBean) this.getEntityManager().createQuery("SELECT user FROM UserDataBean user WHERE user.loginUser = :login").setParameter("login", login).getSingleResult();
	}

}