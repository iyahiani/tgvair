package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.ServiceDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.Encrypte;
import com.avancial.app.resources.utils.HashGenerationException;
import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.data.controller.dao.User2RoleDao;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.model.managedbean.IhmManagedBean;
import com.avancial.socle.resources.constants.EKeys;
import com.avancial.socle.resources.constants.ERegexUser;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * 
 * @author caglar.erdogan
 *
 */
@Named("userIhm")
@ViewScoped
public class UserManagedBean extends AManageBean {
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
	private RoleDataBean role ; 
	private List<UserDataBean> filtredUser; 
	private Boolean closeDialog ;
	private List<RoleDataBean> users2RoleDataBean ;
   private String selectedItem ; 
	int i = 0 ;
	List<SelectItem> users2roleItems ;
  
	
	public UserManagedBean() {
	   this.closeDialog = false ;
		this.users = new ArrayList<>();
		this.users.addAll(new UserDao().getAll()); 
		this.users2RoleDataBean= new RoleDao().getAll() ;
		this.users2roleItems = new ArrayList<>();
	  
	for (RoleDataBean userDataBean : this.users2RoleDataBean) {
      this.users2roleItems.add(new SelectItem(userDataBean.getLabelRole())) ;
   }
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

	public void addUser(ActionEvent event) {
		System.out.println("Ajout user!!");
	} 
	
	@Override
   public String add() {
	   UserDataBean user = new UserDataBean() ; 
	   UserDao dao = new UserDao() ; 
	   user.setNomUser(this.nom);
	   user.setPrenomUser(this.prenom); 
	   user.setLoginUser(this.login); 
	   try {
         user.setPasswordUser(Encrypte.generateSHA1(this.password));
      } catch (HashGenerationException e1) {
         e1.printStackTrace();
      } 
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
         this.closeDialog = true ;
      } catch (ASocleException e) {
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_ADD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "erreur création Utilisateur"));
         
      } 
	   return null ;
	}
   
	@Override
   public String update() {
	   UserDataBean bean = new UserDataBean()   ; 
	   UserDao dao = new UserDao() ;
	   User2RoleDataBean u2rDataBean = new User2RoleDataBean() ;
	   RoleDao roleDao = new RoleDao() ; 
	   User2RoleDao u2rDao = new User2RoleDao();
	   bean.setIdUser(this.selectedUser.getIdUser());
	   bean.setNomUser(this.selectedUser.getNomUser() );
	   bean.setPrenomUser(this.selectedUser.getPrenomUser()); 
	   bean.setLoginUser(this.selectedUser.getLoginUser() );
	   
	   try {
         bean.setPasswordUser(Encrypte.generateSHA1(this.selectedUser.getPasswordUser()));
      } catch (HashGenerationException e1) {
         e1.printStackTrace();
      }
	   
	   bean.setDateCreateUser(this.selectedUser.getDateCreateUser());
	   bean.setDateUpdateUser(this.selectedUser.getDateUpdateUser());
	   bean.setUserCreateUser(this.selectedUser.getUserCreateUser());
	   bean.setUserUpdateUser(this.selectedUser.getUserUpdateUser());
	   bean.setRobotUser(this.selectedUser.getRobotUser());
	   bean.setTomcatRoleUser(this.selectedUser.getTomcatRoleUser()); 
	   
	   if (!this.selectedItem.isEmpty()) {
	      u2rDataBean.setRoleDataBean(roleDao.getUser2RoleByRole(this.selectedItem).get(0));
	      u2rDataBean.setIdUser(bean.getIdUser()); 
	      
	   }
	   try {
	      dao.update(bean);
	      if (userExist(bean)) {
	         u2rDataBean.setIdUser2Role(u2rDao.getUser2RoleByIdUser
	               (bean.getIdUser()).get(0).getIdUser2Role());
	         u2rDao.update(u2rDataBean);
	      }
	      else {
	         u2rDao.save(u2rDataBean);
	      }
	      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "L'Utilisateur"+bean.getNomUser()+"--"+bean.getPrenomUser()+" a été modifié."));
	      RequestContext.getCurrentInstance().update(":formTableUsers");
         this.closeDialog = true;
	   } catch (ASocleException e) {
	      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Erreur lors de la mise à jour."));
	   }
	   return null ;
	}
	
	private boolean userExist(UserDataBean bean) { 
	   User2RoleDao u2rDao = new User2RoleDao();
	   if(!u2rDao.getUser2RoleByIdUser(bean.getIdUser()).isEmpty()) 
	      return true ;
	   return false ;
	}
	
	
	 @Override
	   public String delete() throws ASocleException {
	    super.delete();

	      if (null != this.selectedUser) {
	         UserDao dao = new UserDao();
	         try {
	            dao.delete(this.selectedUser);
	            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Utilisateur supprimé"));
	            RequestContext.getCurrentInstance().update(":formTableUsers");
	            this.closeDialog = true;
	         } catch (ASocleException e) {
	            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non supprimé"));
	         }
	      } else {
	         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "veuillez selectionner une ligne"));
	         this.closeDialog = true;
	      }
	      return null;
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
      return this.filtredUser;
   }

   public void setFiltredUser(List<UserDataBean> filtredUser) {
      this.filtredUser = filtredUser;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public IhmManagedBean getIhmManagedBean() {
      return this.ihmManagedBean;
   }

   public void setIhmManagedBean(IhmManagedBean ihmManagedBean) {
      this.ihmManagedBean = ihmManagedBean;
   }

   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

   public List<RoleDataBean> getUsers2RoleDataBean() {
      return this.users2RoleDataBean;
   }

   public void setUsers2RoleDataBean(List<RoleDataBean> users2RoleDataBean) {
      this.users2RoleDataBean = users2RoleDataBean;
   }

   public List<SelectItem> getUsers2roleItems() {
      return this.users2roleItems;
   }

   public void setUsers2roleItems(List<SelectItem> users2role) {
      this.users2roleItems = users2role;
   }

   public RoleDataBean getRole() {
      return role;
   }

   public void setRole(RoleDataBean u2r) {
      this.role = u2r;
   }

   public String getSelectedItem() {
      return selectedItem;
   }

   public void setSelectedItem(String selectedItem) {
      this.selectedItem = selectedItem;
   }
}