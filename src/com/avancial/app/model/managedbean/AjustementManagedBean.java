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
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
import com.avancial.app.business.train.TrainFactory;
import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.business.train.circulation.JourCirculation;
import com.avancial.app.data.controller.dao.CirculationDAO;
import com.avancial.app.data.controller.dao.TrainCatalogueDAO;
import com.avancial.app.data.model.databean.CirculationAdapterDataBean;
import com.avancial.app.data.model.databean.PointArretDataBean;
import com.avancial.app.data.model.databean.TrainCatalogueDataBean;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.test.TrainCatalogueFactoryTest;
import com.avancial.test.TrainFactoryTest;

/**
 * 
 * @author Hamza.laterem managed bean relative a la gestion des traincirculation
 */

@SuppressWarnings("unused")
@Named("ajustement")
@ViewScoped
public class AjustementManagedBean extends AManageBean implements Serializable {
	private static final long serialVersionUID = 1L;	
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
	private List<TrainCatalogue> listTrains ;
   private List<TrainCatalogue> listTrainOrigine;
   private TrainCatalogue modifedTrainCatalogue;
   private TrainCatalogue modifedTrainCatalogueOrigine;
	
	
	public void valueChanged(ValueChangeEvent event) {
	   
	   for (TrainCatalogue trainCatalogue : this.listTrains) {
	      if (event.getNewValue().equals(String.valueOf(trainCatalogue.getIdTrain()))) {
	         this.modifedTrainCatalogue  = new TrainCatalogue() ;
	         this.modifedTrainCatalogue = trainCatalogue;
	         for (TrainCatalogue trainCatalogueOrigine : this.listTrainOrigine) {
               if (this.modifedTrainCatalogue.getIdTrain() == trainCatalogueOrigine.getIdTrain()) {
                  this.modifedTrainCatalogueOrigine = trainCatalogueOrigine;
                  break;
               }
            }
	         this.generatSchedules();
	         break;
	      }
      }
	}
	
	private void generatSchedules() {
	   try {         
         // Remplire ou a tester         
	      this.modifedTrainCatalogue.remplirJoursCirculations();
         /*
          * @todo : recupération de la date de départ d'affichage
          */
         this.initialDate = new SimpleDateFormat("dd/MM/yyyy").parse(
               StringToDate.toStringByFormat(this.modifedTrainCatalogue.getDateDebutValidite(), "dateBySlashSansHeure"));
         this.getDateInitial();
         // Initialisation des plannings
         int num = 0;
         Class[] args = new Class[1];
         args[0] = CustomerSchedule.class;
         for (int i = this.initialDate.getMonth(); i < this.initialDate.getMonth() + 12; i++) {
            String methode = "setSchedule_"+ num;
            Method action = this.getClass().getMethod(methode, args);         
            action.invoke(this, (new CustomerSchedule(StringToDate.moisSuivant(this.initialDate, num), 
                  StringToDate.toStringByFormat(StringToDate.moisSuivant(this.initialDate, num), "dateFrenchAffichage"))));                 
            num++;
         }        
         this.initCirculationEvent();        
      } catch (ParseException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public AjustementManagedBean() { 
	   this.listTrains = new ArrayList<TrainCatalogue>();
	   this.listTrainOrigine = new ArrayList<TrainCatalogue>();
	   getTrains(); 
   } 
   
	private void getDateInitial() throws ParseException {
		this.setInitialDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime())));
		
		if (this.modifedTrainCatalogue.getDateDebutValidite() != null && 
				this.modifedTrainCatalogue.getDateDebutValidite().compareTo(Calendar.getInstance().getTime()) >= 0) {
			this.setInitialDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(this.modifedTrainCatalogue.getDateDebutValidite())));
		}
	}
	
	private void initCirculationEvent() throws Exception {
		//Long j = (long) 1000;
		// parcour de la map train.listeCirculation
	   // foreach debutvalidé jusqu'a la fin
	   // verifier si date T existe dans train.listeCirculation *
	   // Non : creer un even
	   this.modifedTrainCatalogue.remplirJoursValidite();
	   
	   this.modifedTrainCatalogue.calculeDeNbreDeMoisDeValidite();
	   
	   // comparer les jours ou le train est valide mais il ne circule pas 
	   
	   for (Date d : this.modifedTrainCatalogue.getListJoursValides()) {
	      if(!this.modifedTrainCatalogue.getListeJoursCirculation().containsKey(d) && !d.before(this.initialDate)) {
	         EventJourCirculation eventJourCirculation = new EventJourCirculation(this.modifedTrainCatalogue.getIdTrain(),
                  d, d, this.modifedTrainCatalogue.getListeCirculations().get(0).getHeureDepart(),this.modifedTrainCatalogue.getListeCirculations().get(0).getHeureDepart(),false,false);
            for (int i = 0; i < 12; i++) {
               String methode = "getSchedule_"+ i;
               Method action = this.getClass().getMethod(methode, null);
               CustomerSchedule schedul = ((CustomerSchedule) (action.invoke(this, null)));
               if (schedul.getName().equals(StringToDate.toStringByFormat(d, "dateFrenchAffichage"))) {
                  schedul.getSchedule().addEvent(eventJourCirculation);
               }
            }     
	      }
	   }
	   
	 
	   
	   
		for (Entry<Date, JourCirculation> jourCirculation : this.modifedTrainCatalogue.getJoursCirculation().entrySet()) {
			// verification du mois
			//String  date = (new SimpleDateFormat("yyyy").format(jourCirculation.getKey())).toString();
	       
		   
		   if (jourCirculation.getKey().compareTo(this.initialDate) >= 0 && jourCirculation.getKey().compareTo(StringToDate.moisSuivant(this.initialDate, 12)) < 0  ) {
				Boolean isAdapted = false;
				
			  /* if (!compareJourCirculationWithTrainCatalaogueOrigine(this.modifedTrainCatalogueOrigine, jourCirculation)) {
			      isAdapted = true;
			   }*/
				if (jourCirculation.getValue().isFlagCirculation() &&
				      !jourCirculation.getValue().compare(this.modifedTrainCatalogueOrigine.getListeJoursCirculation().get(jourCirculation.getKey()))) {
				   isAdapted = true;
				}
			   
				// * Si oui
			   EventJourCirculation eventJourCirculation = new EventJourCirculation(this.modifedTrainCatalogue.getIdTrain(),
						jourCirculation.getValue().getDateCircul(), jourCirculation.getValue().getDateCircul(),
						jourCirculation.getValue().getHeureDepart(), jourCirculation.getValue().getHeureArrivee(),
						jourCirculation.getValue().isFlagCirculation(), isAdapted);
				
			   // * Si non
			   /*
			    EventJourCirculation eventJourCirculation = new EventJourCirculation(this.modifedTrainCatalogue.getIdTrain(),
                  T.getDate, T.getDate,
                  heureDepartTrainOriginal, heureArriveeTrainOriginal,
                  false, false);
			    */
			   
				for (int i = 0; i < 12; i++) {
					String methode = "getSchedule_"+ i;
					Method action = this.getClass().getMethod(methode, null);
					CustomerSchedule schedul = ((CustomerSchedule) (action.invoke(this, null)));
					if (schedul.getName().equals(StringToDate.toStringByFormat(jourCirculation.getKey(), "dateFrenchAffichage"))) {
						schedul.getSchedule().addEvent(eventJourCirculation);
					}
				}				
			}	
	
	}
	}
	
	private boolean compareJourCirculationWithTrainCatalaogueOrigine(TrainCatalogue modifedTrainCatalogueOrigine2, Entry<Date, JourCirculation> jourCirculation) {
      
      return false;
   }

   public void onEventSelect(SelectEvent selectEvent) {		
		this.setEvent((EventJourCirculation) selectEvent.getObject());
	}
	
	public void addEvent( ) throws Exception {		
		if (this.getEvent().getIdCatalogueTrain() != null) {
			if (!this.getEvent().compareJourCirculation(this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).getHeureArrivee(),
					this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).getHeureDepart(), 
					this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).isFlagCirculation())) {								
				for (int i = 0; i < 12; i++) {
					String methode = "getSchedule_"+ i;
					Method action = this.getClass().getMethod(methode, null);
					CustomerSchedule schedul = ((CustomerSchedule) (action.invoke(this, null)));
					if (schedul.getName().equals(StringToDate.toStringByFormat(this.event.getDateDebut(), "dateFrenchAffichage"))) {
					   this.getEvent().update();
						schedul.getSchedule().updateEvent(this.event);
						
						// Modification de jourCirculation métier
						this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).setHeureArrivee(Integer.valueOf( StringToDate.toFormatedString(this.getEvent().getHeureArrivee())));
						this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).setHeureDepart(Integer.valueOf( StringToDate.toFormatedString(this.getEvent().getHeureDepart())));
						this.getModifedTrainCatalogue().getListeJoursCirculation().get(this.getEvent().getDateDebut()).setFlagCirculation(this.getEvent().isFlagCirculation());						
					}
				}
				
				this.getModifedTrainCatalogue().calculeCirculationFromJoursCirculation();		
			}
		}
		
		this.setEvent(new EventJourCirculation());		
	} 
	
	
	//Sauvegarder
	public void saveAdaptation() {
	   try {
	      new TrainCatalogueDAO().updateCirculation(this.getModifedTrainCatalogue()); 
	      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_INFO, "message", " Train enregistré"));
	   } catch (Exception e) {
	      FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), 
               new FacesMessage(FacesMessage.SEVERITY_FATAL, "message", " Erreur d'enregistrement"));
	   }
	}

	
	// Annule la sauvgarde
	public void initSelectedTrain() {
	   int idTrain = this.getModifedTrainCatalogue().getIdTrain();
	   this.listTrains = new ArrayList<TrainCatalogue>();
      getTrains();
      for (TrainCatalogue trainCatalogue : this.listTrains) {
         if (idTrain == trainCatalogue.getIdTrain()) {
            this.modifedTrainCatalogue  = new TrainCatalogue() ;
            this.modifedTrainCatalogue = trainCatalogue;
            this.generatSchedules();
         }
      }
      this.setEvent(new EventJourCirculation());
	   
/*	   for (TrainCatalogue trainCatalogue : this.listTrains) {
         if (this.getModifedTrainCatalogue() != null &&
               this.getModifedTrainCatalogue().getIdTrain() == trainCatalogue.getIdTrain()) {
            this.setModifedTrainCatalogue(null);
            this.setModifedTrainCatalogue(trainCatalogue);
            
            this.generatSchedules();
            break;
         }
      }
	*/   
	}
	
	public void getTrains() {
	   
	   List<CirculationAdapterDataBean> listCirculationAdapter = new CirculationDAO().getDistinctCirculation() ; 
	   
	   for (CirculationAdapterDataBean c : listCirculationAdapter) { 	      
         List<CirculationAdapterDataBean> list = new CirculationDAO().getLastCircul(c.getTrainCatalogueDataBean().getIdTrainCatalogue()) ;
         
         TrainCatalogue train = TrainFactory.createTrainCatalogueFromBeans(list);
         TrainCatalogue trainOrigine =  TrainFactory.createTrainCatalogueFromOriginal(c.getTrainCatalogueDataBean()); 
        // train.remplirJoursCirculations();
         this.listTrains.add(train);
         this.listTrainOrigine.add(trainOrigine);
      }	   
	}
	
	public void dialogClose(CloseEvent closeEvent) {
		this.setEvent(new EventJourCirculation());
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
   public List<TrainCatalogue> getListTrains() {
      return this.listTrains;
   }
   public void setListTrains(List<TrainCatalogue> listTrains) {
      this.listTrains = listTrains;
   }
}
