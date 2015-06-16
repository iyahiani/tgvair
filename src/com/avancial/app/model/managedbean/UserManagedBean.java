package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.avancial.socle.data.controller.dao.UtilisateurDao;
import com.avancial.socle.data.model.databean.UtilisateurDataBean;

@Named("userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<UtilisateurDataBean> users;

	public UserManagedBean() {
		this.users = new ArrayList<>();
		this.users.addAll(new UtilisateurDao().getAll());
	}

	public List<UtilisateurDataBean> getUsers() {
		return this.users;
	}
	
	

}