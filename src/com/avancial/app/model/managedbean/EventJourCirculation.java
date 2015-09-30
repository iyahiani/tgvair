package com.avancial.app.model.managedbean;

import java.text.ParseException;
import java.util.Date;

import org.primefaces.model.DefaultScheduleEvent;

import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.HeureFormattage;
import com.avancial.app.resources.utils.StringToDate;

public class EventJourCirculation extends DefaultScheduleEvent {
	private static final long serialVersionUID = 1L;

	private Integer idCatalogueTrain;
	private Date dateDebut 		= null;
	private Date dateFin 		= null;
	private Date heureDepart 	= null;
	private Date heureArrivee 	= null;
	private boolean flagCirculation;
	private boolean flagAdapted; 
   private String numTrain;
	public EventJourCirculation() {
		super();		
		
	}
	
	public EventJourCirculation(Integer idCatalogueTrain, Date dateDebut, Date dateFin, int heureDepart,
			int heureArrivee, boolean flagCirculation, boolean isAdapted) throws Exception {
	   super(StringToDate.toStringByFormat(dateDebut, "jour"), dateDebut, dateFin);
		this.setIdCatalogueTrain(idCatalogueTrain);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);				
		this.setHeureDepart(HeureFormattage.intToDate(heureDepart));
		this.setHeureArrivee(HeureFormattage.intToDate(heureArrivee));
		this.setFlagCirculation(flagCirculation);		
		this.setFlagAdapted(isAdapted);
		this.numTrain = new TrainCatalogueDAO().getTrainCatalogueByID(this.idCatalogueTrain).getNumeroTrainCatalogue();
		this.initScheduleEvent();
	}		
	
	public void initScheduleEvent() {
		// Affichage sur l'ensemble des heures de la journée
		this.setAllDay(true);
		// Whether the event is editable or not.
		this.setEditable(true);
		// Personnalisation du CSS		
		this.setDescriptionAndStyle();
	}
	
	public void update() {
	   if (this.flagCirculation)
	      this.setFlagAdapted(true);
	   else
	      this.setFlagAdapted(false);
		this.initScheduleEvent();
	}
	
	public void setDescriptionAndStyle() {
		// Description dan la tooltip
		this.setCirculationDescriptionTooltip();
		// Set Style Cell
		this.setStyleCell();
	}
	 
	 
	
	public void setStyleCell() {
		if (this.isFlagCirculation() && !this.isFlagAdapted()) {
			this.setStyleClass("train_circule event_display");
		} else if (!this.isFlagCirculation() && !this.isFlagAdapted()) {
			this.setStyleClass("train_not_circule event_display");
		} else {
			this.setStyleClass("train_adapted event_display");
		}
	}

	public void setCirculationDescriptionTooltip() { 
	   TrainCatalogueDataBean tc = new TrainCatalogueDAO().getTrainCatalogueByID(this.getIdCatalogueTrain()) ;
		String numeroTrain = "Le train N° : " + tc.getNumeroTrainCatalogue();		
		String hDepart = "Heure départ : " + StringToDate.toFormatedString2( this.getHeureDepart());
		String hArrivee = "Heure arrivée : " +StringToDate.toFormatedString2( this.getHeureArrivee());
		String flagCirculation = "Circulant : ";
		String flagAdapted = "Adapter : ";
		
		if (this.isFlagCirculation())
			flagCirculation += "Oui";
		else
			flagCirculation += "Non";
		
		if (this.isFlagAdapted())
			flagAdapted += "Oui";
		else
			flagAdapted += "Non";
		
		this.setDescription(numeroTrain + " " + hDepart + " " + hArrivee + " " + flagCirculation + " " + flagAdapted);					
	}

	public Integer getIdCatalogueTrain() {
		return this.idCatalogueTrain;
	}

	public void setIdCatalogueTrain(Integer idCatalogueTrain) {
		this.idCatalogueTrain = idCatalogueTrain;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getHeureDepart() {
		return this.heureDepart;
	}

	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Date getHeureArrivee() {
		return this.heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public boolean isFlagCirculation() {
		return this.flagCirculation;
	}

	public void setFlagCirculation(boolean flagCirculation) {
		this.flagCirculation = flagCirculation;
	}

	public boolean isFlagAdapted() {
		return this.flagAdapted;
	}

	public void setFlagAdapted(boolean flagAdapted) {
		this.flagAdapted = flagAdapted;
	}

	public boolean compareJourCirculation(int heureArrivee, int heureDepart, boolean flagCirculation) throws ParseException {
		if (this.getHeureDepart().compareTo(HeureFormattage.intToDate(heureDepart)) == 0 && 
				this.getHeureArrivee().compareTo(HeureFormattage.intToDate(heureArrivee)) == 0 &&
				flagCirculation == this.isFlagCirculation()) {
			return true;
		}		
		
		return false;
	}

   public String getNumTrain() {
      return numTrain;
   }

   public void setNumTrain(String numTrain) {
      this.numTrain = numTrain;
   }
}
