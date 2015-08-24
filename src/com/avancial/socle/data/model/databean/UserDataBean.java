package com.avancial.socle.data.model.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author caglar.erdogan
 *
 */
@Entity
@Table(name = "socle_user")
public class UserDataBean implements Serializable { 
   private static Long serialVersion= 1L;
	@Id
	protected Long idUser;
	protected String nomUser;
	protected String prenomUser;
	protected String loginUser;
	protected String passwordUser;
	protected String mailUser;
	protected String cpUser;
	protected String telephonePro1User;
	protected String telephonePro2User;
	protected String telephonePortable1User;
	protected String fax1User;
	protected String adresseNumeroRueUser;
	protected String adresseNomRueUser;
	protected String adresseComplement1User;
	protected String adresseComplement2User;
	protected String adresseCodePostalUser;
	protected String adresseVilleUser;
	protected String attribut1User;
	protected String attribut2User;
	protected String attribut3User;
	protected String attribut4User;
	protected String attribut5User;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateCreateUser;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateUpdateUser;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateLastUpdateUtilisateurUser;
	@OneToOne
	@JoinColumn(name = "idUserCreateUser")
	protected UserDataBean userCreateUser;
	@OneToOne
	@JoinColumn(name = "idUserUpdateUser")
	protected UserDataBean userUpdateUser;
	protected boolean robotUser;
	protected String tomcatRoleUser;
	protected String commentaireUtilisateurUser;

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPasswordUser() {
		return this.passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getNomUser() {
		return this.nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getPrenomUser() {
		return this.prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}

	public String getCpUser() {
		return this.cpUser;
	}

	public void setCpUser(String cpUser) {
		this.cpUser = cpUser;
	}

	public String getMailUser() {
		return this.mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getTelephonePro1User() {
		return this.telephonePro1User;
	}

	public void setTelephonePro1User(String telephonePro1User) {
		this.telephonePro1User = telephonePro1User;
	}

	public String getTelephonePro2User() {
		return this.telephonePro2User;
	}

	public void setTelephonePro2User(String telephonePro2User) {
		this.telephonePro2User = telephonePro2User;
	}

	public String getTelephonePortable1User() {
		return this.telephonePortable1User;
	}

	public void setTelephonePortable1User(String telephonePortable1User) {
		this.telephonePortable1User = telephonePortable1User;
	}

	public String getFax1User() {
		return this.fax1User;
	}

	public void setFax1User(String fax1User) {
		this.fax1User = fax1User;
	}

	public String getAdresseNumeroRueUser() {
		return this.adresseNumeroRueUser;
	}

	public void setAdresseNumeroRueUser(String adresseNumeroRueUser) {
		this.adresseNumeroRueUser = adresseNumeroRueUser;
	}

	public String getAdresseNomRueUser() {
		return this.adresseNomRueUser;
	}

	public void setAdresseNomRueUser(String adresseNomRueUser) {
		this.adresseNomRueUser = adresseNomRueUser;
	}

	public String getAdresseComplement1User() {
		return this.adresseComplement1User;
	}

	public void setAdresseComplement1User(String adresseComplement1User) {
		this.adresseComplement1User = adresseComplement1User;
	}

	public String getAdresseComplement2User() {
		return this.adresseComplement2User;
	}

	public void setAdresseComplement2User(String adresseComplement2User) {
		this.adresseComplement2User = adresseComplement2User;
	}

	public String getAdresseCodepostalUser() {
		return this.adresseCodePostalUser;
	}

	public void setAdresseCodepostalUser(String adresseCodepostalUser) {
		this.adresseCodePostalUser = adresseCodepostalUser;
	}

	public String getAdresseVilleUser() {
		return this.adresseVilleUser;
	}

	public void setAdresseVilleUser(String adresseVilleUser) {
		this.adresseVilleUser = adresseVilleUser;
	}

	public String getAttribut1User() {
		return this.attribut1User;
	}

	public void setAttribut1User(String attribut1User) {
		this.attribut1User = attribut1User;
	}

	public String getAttribut2User() {
		return this.attribut2User;
	}

	public void setAttribut2User(String attribut2User) {
		this.attribut2User = attribut2User;
	}

	public String getAttribut3User() {
		return this.attribut3User;
	}

	public void setAttribut3User(String attribut3User) {
		this.attribut3User = attribut3User;
	}

	public String getAttribut4User() {
		return this.attribut4User;
	}

	public void setAttribut4User(String attribut4User) {
		this.attribut4User = attribut4User;
	}

	public String getAttribut5User() {
		return this.attribut5User;
	}

	public void setAttribut5User(String attribut5User) {
		this.attribut5User = attribut5User;
	}

	public Date getDateCreateUser() {
		return this.dateCreateUser;
	}

	public void setDateCreateUser(Date dateCreateUser) {
		this.dateCreateUser = dateCreateUser;
	}

	public Date getDateUpdateUser() {
		return this.dateUpdateUser;
	}

	public void setDateUpdateUser(Date dateUpdateUser) {
		this.dateUpdateUser = dateUpdateUser;
	}

	public UserDataBean getUserCreateUser() {
		return this.userCreateUser;
	}

	public void setUserCreateUser(UserDataBean userCreateUser) {
		this.userCreateUser = userCreateUser;
	}

	public UserDataBean getUserUpdateUser() {
		return this.userUpdateUser;
	}

	public void setUserUpdateUser(UserDataBean userUpdateUser) {
		this.userUpdateUser = userUpdateUser;
	}

	public boolean getRobotUser() {
		return this.robotUser;
	}

	public void setRobotUser(boolean robotUser) {
		this.robotUser = robotUser;
	}

	public String getTomcatRoleUser() {
		return this.tomcatRoleUser;
	}

	public void setTomcatRoleUser(String tomcatRoleUser) {
		this.tomcatRoleUser = tomcatRoleUser;
	}

	public String getCommentaireUtilisateurUser() {
		return this.commentaireUtilisateurUser;
	}

	public void setCommentaireUtilisateurUser(String commentaireUtilisateurUser) {
		this.commentaireUtilisateurUser = commentaireUtilisateurUser;
	}

	public String getAdresseCodePostalUser() {
		return this.adresseCodePostalUser;
	}

	public void setAdresseCodePostalUser(String adresseCodePostalUser) {
		this.adresseCodePostalUser = adresseCodePostalUser;
	}

	public Date getDateLastUpdateUtilisateurUser() {
		return this.dateLastUpdateUtilisateurUser;
	}

	public void setDateLastUpdateUtilisateurUser(Date dateLastUpdateUtilisateurUser) {
		this.dateLastUpdateUtilisateurUser = dateLastUpdateUtilisateurUser;
	}
}