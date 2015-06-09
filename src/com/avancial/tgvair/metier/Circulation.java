package com.avancial.tgvair.metier;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.avancial.metier.parser.TGVAIR_enumParserCirculation;
import com.avancial.tgvair.DataBeans.CirculationDataBean;

public class Circulation implements ICirculation {

	private Date dateDebut;
	private Date dateFin;
	private int heureArrivee;
	private int heureDepart;
	private String origine;
	private String destination;
	private String joursCirculation;
	private String indicateurFer;
	private String compagnieTrain;
	private String numeroTrain;
	
	public String getIndicateurFer() {
		return indicateurFer;
	}

	public void setIndicateurFer(String indicateurFer) {
		this.indicateurFer = indicateurFer;
	}

	public String getCompagnieTrain() {
		return compagnieTrain;
	}

	public void setCompagnieTrain(String compagnieTrain) {
		this.compagnieTrain = compagnieTrain;
	}

	public String getNumeroTrain() {
		return numeroTrain;
	}

	public void setNumeroTrain(String numeroTrain) {
		this.numeroTrain = numeroTrain;
	}

	

	private CirculationDataBean circulationDataBean;

	public Circulation() {

	}

	@SuppressWarnings("deprecation")
	public String getChaineCircu() {
		
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
		
		sb.append(sdf.format(this.getDateDebut()));
		sb.append(sdf.format(this.getDateFin()));
		sb.append(this.getHeureDepart());
		sb.append(this.getHeureArrivee());
		sb.append(this.getOrigine());
		sb.append(this.getDestination());
		sb.append(this.getJoursCirculation());
		sb.append(this.getIndicateurFer());
		sb.append(this.getCompagnieTrain());
		sb.append(this.getNumeroTrain());
		return sb.toString();
	}

	public Circulation getCirculation(String chaine) throws ParseException {
		
		Circulation circul = new Circulation();
		if (chaine.length() > 0) {

			circul.setDateDebut(ParsingDateCirculSSIM.toDate(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_DEBUT_CIRCU
							.getPostionFin())));
			circul.setDateFin(ParsingDateCirculSSIM.toDate(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DATE_FIN_CIRCU
							.getPostionFin())));
			circul.setDestination(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_DESTINATION
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_DESTINATION
							.getPostionFin()));
			circul.setOrigine(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_ORGINE
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_ORGINE
							.getPostionFin()));
			circul.setHeureDepart(Integer.parseInt(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_DEPART
							.getPostionFin())));
			circul.setHeureArrivee(Integer.valueOf(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_HEURE_ARRIVER
							.getPostionFin())));
			circul.setJoursCirculation(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_JOURS_CIRCULATION
							.getPostionFin()));
			circul.setIndicateurFer(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_INDICATEUR_FER
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_INDICATEUR_FER
							.getPostionFin()));
			circul.setCompagnieTrain(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_COMPAGNIE_TRAIN
							.getPostionFin()));
		circul.setNumeroTrain(chaine.substring(
					TGVAIR_enumParserCirculation.POSITION_NUMERO_TRAIN
							.getPostionDebut(),
					TGVAIR_enumParserCirculation.POSITION_NUMERO_TRAIN
							.getPostionFin()));
		}

		return circul;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(int heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public int getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(int heureDepart) {
		this.heureDepart = heureDepart;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getJoursCirculation() {
		return joursCirculation;
	}

	public void setJoursCirculation(String joursCirculation) {
		this.joursCirculation = joursCirculation;
	}

	public CirculationDataBean getCirculationDatabean(Circulation circulation) {
		circulationDataBean = new CirculationDataBean();
		circulationDataBean.setDestination(circulation.getDestination());
		//circulationDataBean.setHeureArriver(circulation.getHeureArrivee());
		//circulationDataBean.setHeureDepart(circulation.getHeureDepart());
		return circulationDataBean;
	}
}