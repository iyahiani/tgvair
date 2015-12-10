package com.avancial.socle.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.avancial.app.data.controller.dao.ServiceDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.EKeys;
import com.avancial.socle.resources.constants.ERegexUser;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * 
 * @author caglar.erdogan
 *
 */
@Named("userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codePostal;
	private String commentaire;
	private String email;
	private String fax;
	private String login; 
	private String password;
	private String nom;
	private String numero;
	private String prenom;
	private boolean robot;
	private String rue;
	private UserDataBean selectedUser;
	private String telephone;
	private List<UserDataBean> users;
	private String ville; 
	@Inject 
	private IhmManagedBean ihmManagedBean ; 
	 private List<UserDataBean> filtredUser;
	

	public UserManagedBean() {
		this.users = new ArrayList<>();
		this.users.addAll(new UserDao().getAll());
	}
	
	 @PostConstruct 
	   public void init() {
	     
	      this.users= new UserDao().getAll() ;
	   }

	  public void selectedUser(SelectEvent event) {

	      this.selectedUser = (UserDataBean) event.getObject(); 
	      
	   } 
	
	  public void addRule(String detail) {
		FacesContext.getCurrentInstance().addMessage("growlUser", new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", detail));
	}

/*	public void addUser(ActionEvent event) {
		System.out.println("Ajout user!!");
	} */
	
	public void add() {
	   UserDataBean user = new UserDataBean() ; 
	   UserDao dao = new UserDao() ; 
	   user.setNomUser(this.nom);
	   user.setPrenomUser(this.prenom); 
	   user.setLoginUser(this.login); 
	   user.setPasswordUser(this.password); 
	   user.setDateCreateUser(new Date());
	   user.setDateUpdateUser(new Date());
	   user.setUserCreateUser(this.ihmManagedBean.getCurrentUser());
	   user.setUserUpdateUser(this.ihmManagedBean.getCurrentUser()); 
	   user.setRobotUser(false); 
	   user.setTomcatRoleUser("user"); 
	   try {
         dao.save(user);
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Utilisateur Créer."));
         RequestContext.getCurrentInstance().update(":formTableUsers");
         
      } catch (ASocleException e) {
         RequestContext.getCurrentInstance().
         showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         e.getClientMessage();
         RequestContext.getCurrentInstance().addCallbackParam("notValid", true); 
         
      }
	}
   
	public void reload() { 
	   
	   this.users.clear();
	   this.users = new UserDao().getAll();
	}
	
	public void cancelUser() {
		this.codePostal = null;
		this.commentaire = null;
		this.email = null;
		this.fax = null;
		this.login = null;
		this.nom = null;
		this.prenom = null;
		this.telephone = null;
		this.nom = null;
		this.numero = null;
		this.rue = null;
		this.ville = null;
	}

	public void deleteUser(UserDataBean user) {
		this.users.remove(user);
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public String getEmail() {
		return this.email;
	}

	public String getExpression(String name) {
		return ERegexUser.valueOf(name).getExpression();
	}

	public String getFax() {
		return this.fax;
	}

	public String getInvalidInputKeys() {
		return Arrays.toString(EKeys.getInvalidInputKeys());
	}

	public String getLogin() {
		return this.login;
	}

	public String getNom() {
		return this.nom;
	}

	public String getNumero() {
		return this.numero;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getRue() {
		return this.rue;
	}

	public UserDataBean getSelectedUser() {
		return this.selectedUser;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public List<UserDataBean> getUsers() {
		return this.users;
	}

	public String getVille() {
		return this.ville;
	}

	public boolean isRobot() {
		return this.robot;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setRobot(boolean robot) {
		this.robot = robot;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void updateUser(UserDataBean user) {
		this.selectedUser = user;
	}

   public void setSelectedUser(UserDataBean selectedUser) {
      this.selectedUser = selectedUser;
   }

   public void setUsers(List<UserDataBean> users) {
      this.users = users;
   }

   public List<UserDataBean> getFiltredUser() {
      return filtredUser;
   }

   public void setFiltredUser(List<UserDataBean> filtredUser) {
      this.filtredUser = filtredUser;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public IhmManagedBean getIhmManagedBean() {
      return ihmManagedBean;
   }

   public void setIhmManagedBean(IhmManagedBean ihmManagedBean) {
      this.ihmManagedBean = ihmManagedBean;
   }
}