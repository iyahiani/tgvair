package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.primefaces.component.schedule.Schedule;
import org.primefaces.component.schedule.ScheduleRenderer;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.avancial.app.business.train.TrainCatalogue;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.JourCirculation;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TestDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.test.TrainCatalogueFactoryTest;
import com.avancial.test.TrainFactoryTest;

@SuppressWarnings("unused")
@Named("ajustement")
@ViewScoped
public class AjustementManagedBean extends AManageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private TrainCatalogue modifedTrainCatalogue;
	private EventJourCirculation event = new EventJourCirculation();
	private Date initialDate;		
	private CustomerSchedule schedule_0;
	private CustomerSchedule schedule_1;
	private CustomerSchedule schedule_2;
	private CustomerSchedule schedule_3;
	private CustomerSchedule schedule_4;
	private CustomerSchedule schedule_5;
	private CustomerSchedule schedule_6;
	private CustomerSchedule schedule_7;
	private CustomerSchedule schedule_8;
	private CustomerSchedule schedule_9;
	private CustomerSchedule schedule_10;
	private CustomerSchedule schedule_11;
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	private void initTrainCatalogue() {
		try {
			this.modifedTrainCatalogue = TrainCatalogueFactoryTest.create("01/01/2015#02/02/2017#135#CDG#MONTPELLIER#0928#1551");
			this.modifedTrainCatalogue.setNomCompagnie("Air France");
			this.modifedTrainCatalogue.remplirJoursCirculations();
			/*
			 * @todo : recupération de la date de départ d'affichage
			 */
			this.initialDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/08/2016");
			this.getdateInitial();
			// Initialisation des plannings
			int num = 0;
			Class[] args = new Class[1];
			args[0] = CustomerSchedule.class;
			for (int i = this.initialDate.getMonth(); i < this.initialDate.getMonth() + 12; i++) {
				String methode = "setSchedule_"+ num;
				Method action = this.getClass().getMethod(methode, args);			
				action.invoke(this, (new CustomerSchedule(StringToDate.moisSuivant(this.initialDate, num), StringToDate.toStringByFormat(StringToDate.moisSuivant(this.initialDate, num), "dateFrenchAffichage"))));						
				num++;
			}			
			this.initCirculationEvent();			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getdateInitial() throws ParseException {
		this.setInitialDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime())));
		
		if (this.modifedTrainCatalogue.getDateDebutValidite() != null && 
				this.modifedTrainCatalogue.getDateDebutValidite().compareTo(Calendar.getInstance().getTime()) >= 0) {
			this.setInitialDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(this.modifedTrainCatalogue.getDateDebutValidite())));
		}
	}
	
	private void initCirculationEvent() throws Exception {
		Long j = (long) 1000;
		
		for (Entry<Date, JourCirculation> jourCirculation : this.modifedTrainCatalogue.getJoursCirculation().entrySet()) {
			// verification du mois
			//String  date = (new SimpleDateFormat("yyyy").format(jourCirculation.getKey())).toString();
			if (jourCirculation.getKey().compareTo(this.initialDate) >= 0 && jourCirculation.getKey().compareTo(StringToDate.moisSuivant(this.initialDate, 12)) < 0 ) {
				EventJourCirculation eventJourCirculation = new EventJourCirculation(j,
						jourCirculation.getValue().getDateCircul(), jourCirculation.getValue().getDateCircul(),
						jourCirculation.getValue().getHeureDepart(), jourCirculation.getValue().getHeureArrivee(),
						jourCirculation.getValue().isFlagCirculation());
				
				for (int i = 0; i < 12; i++) {
					String methode = "getSchedule_"+ i;
					Method action = this.getClass().getMethod(methode, null);
					CustomerSchedule schedul = ((CustomerSchedule) (action.invoke(this, null)));
					if (schedul.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
						schedul.getSchedule().addEvent(eventJourCirculation);
					}
				}				
				
			/*	
				
				if (this.schedule_0.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_0.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_1.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_1.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_2.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_2.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_3.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_3.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_4.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_4.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_5.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_5.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_6.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_6.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_7.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_7.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_8.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_8.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_9.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_9.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_10.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_10.getSchedule().addEvent(eventJourCirculation);
				} else if (this.schedule_11.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
					this.schedule_11.getSchedule().addEvent(eventJourCirculation);
				} */					
			}		
		}
	}
	
	public void onEventSelect(SelectEvent selectEvent) {		
		this.setEvent((EventJourCirculation) selectEvent.getObject());
	}
	
	public void addEvent( ) throws Exception {		
		if (this.getEvent().getIdCatalogueTrain() != null) {
			if (!this.getEvent().compareJourCirculation(this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).getHeureArrivee(),
					this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).getHeureDepart(), 
					this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).isFlagCirculation())) {
				
				this.getEvent().update();
				for (int i = 0; i < 12; i++) {
					String methode = "getSchedule_"+ i;
					Method action = this.getClass().getMethod(methode, null);
					CustomerSchedule schedul = ((CustomerSchedule) (action.invoke(this, null)));
					if (schedul.getName().equals(StringToDate.toStringByFormat(this.event.getDateDebut(), "dateFrenchAffichage"))) {
						schedul.getSchedule().updateEvent(this.event);
						// Modification de jourCirculation métier
					}
				}
			}
		}

		this.setEvent(new EventJourCirculation());		
	}
	
	public void dialogClose(CloseEvent closeEvent) {
		this.setEvent(new EventJourCirculation());
	}
	
	public void Modification() {
		
	}
	
	public TrainCatalogue getModifedTrainCatalogue() {
		return this.modifedTrainCatalogue;
	}

	public void setModifedTrainCatalogue(TrainCatalogue modifedTrainCatalogue) {
		this.modifedTrainCatalogue = modifedTrainCatalogue;
	}

	public Date getInitialDate() {
		return this.initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public CustomerSchedule getSchedule_0() {
		return this.schedule_0;
	}

	public void setSchedule_0(CustomerSchedule schedule_0) {
		this.schedule_0 = schedule_0;
	}

	public CustomerSchedule getSchedule_1() {
		return this.schedule_1;
	}

	public void setSchedule_1(CustomerSchedule schedule_1) {
		this.schedule_1 = schedule_1;
	}

	public CustomerSchedule getSchedule_2() {
		return this.schedule_2;
	}

	public void setSchedule_2(CustomerSchedule schedule_2) {
		this.schedule_2 = schedule_2;
	}

	public CustomerSchedule getSchedule_3() {
		return this.schedule_3;
	}

	public void setSchedule_3(CustomerSchedule schedule_3) {
		this.schedule_3 = schedule_3;
	}

	public CustomerSchedule getSchedule_4() {
		return this.schedule_4;
	}

	public void setSchedule_4(CustomerSchedule schedule_4) {
		this.schedule_4 = schedule_4;
	}

	public CustomerSchedule getSchedule_5() {
		return this.schedule_5;
	}

	public void setSchedule_5(CustomerSchedule schedule_5) {
		this.schedule_5 = schedule_5;
	}

	public CustomerSchedule getSchedule_6() {
		return this.schedule_6;
	}

	public void setSchedule_6(CustomerSchedule schedule_6) {
		this.schedule_6 = schedule_6;
	}

	public CustomerSchedule getSchedule_7() {
		return this.schedule_7;
	}

	public void setSchedule_7(CustomerSchedule schedule_7) {
		this.schedule_7 = schedule_7;
	}

	public CustomerSchedule getSchedule_8() {
		return this.schedule_8;
	}

	public void setSchedule_8(CustomerSchedule schedule_8) {
		this.schedule_8 = schedule_8;
	}

	public CustomerSchedule getSchedule_9() {
		return this.schedule_9;
	}

	public void setSchedule_9(CustomerSchedule schedule_9) {
		this.schedule_9 = schedule_9;
	}

	public CustomerSchedule getSchedule_10() {
		return this.schedule_10;
	}

	public void setSchedule_10(CustomerSchedule schedule_10) {
		this.schedule_10 = schedule_10;
	}

	public CustomerSchedule getSchedule_11() {
		return this.schedule_11;
	}

	public void setSchedule_11(CustomerSchedule schedule_11) {
		this.schedule_11 = schedule_11;
	}
	
	public EventJourCirculation getEvent() {
		return this.event;
	}

	public void setEvent(EventJourCirculation event) {
		this.event = event;
	}
}
