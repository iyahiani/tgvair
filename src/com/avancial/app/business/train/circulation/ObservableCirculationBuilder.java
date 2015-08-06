package com.avancial.app.business.train.circulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implémentation de l'interface IObservableCirculationSemaineBuilder
 * 
 * @author bruno
 *
 */
public class ObservableCirculationBuilder implements IObservableCirculationSemaineBuilder {
   // Contient la liste des observateurs
   private ArrayList<ICirculationSemaineBuilder> listeObservateurs       = new ArrayList<>();
   // Contient la liste des observateurs ayant demandé à être supprimés
   private ArrayList<ICirculationSemaineBuilder> listeObservateur2delete = new ArrayList<>();
   private ArrayList<Circulation>                listeCirculations       = new ArrayList<>();

   @Override
   public void notifierObservateurs(JourCirculation jourCirculation) {
      // Permet de savoir si le jour de circulation a été traité par l'un des
      // observateurs
      boolean dayProcessed = false;

      // Il faut transformer le jour de circulation en circulation
      Circulation circulation = new Circulation(jourCirculation);

      // On parcours la liste des observateurs
      for (ICirculationSemaineBuilder circulationSemaineBuilder : this.listeObservateurs) {
         dayProcessed = dayProcessed || circulationSemaineBuilder.refresh(circulation);
         if (dayProcessed)
            break;
      }
      if (!dayProcessed) {
         // Aucun observateur n'a traité le jour, on en crée un nouveau
         ICirculationSemaineBuilder semaineBuilder = new CirculationSemaineBuilder(jourCirculation);
         this.listeObservateurs.add(semaineBuilder);
      }
      // // On parcours la liste des observateurs ayant demandé à être supprimé
      // for (ICirculationSemaineBuilder semaineBuilder : this.listeObservateur2delete) {
      // this.listeCirculations.add(semaineBuilder.getCirculation());
      // this.listeObservateurs.remove(semaineBuilder);
      // }
      // this.listeObservateur2delete.clear();

   }

   @Override
   public void supprimerObservateur(ICirculationSemaineBuilder observateurSemainBuilder) {
      this.listeObservateur2delete.add(observateurSemainBuilder);
   }

   @Override
   public Collection<? extends Circulation> getListeCirculation() {

      // Avant de retourner la liste des circulations, on parcourt la liste des
      // observateurs afin de récupérer les dernières circulation
      for (ICirculationSemaineBuilder builder : this.listeObservateurs) {
         this.listeCirculations.add(builder.getCirculation());
      }

      // Il faut compacter les circulations

      // this.factoriseCirculations();

      return this.listeCirculations;
   }

   private void factoriseCirculations() {
      List<Circulation> listeTemp = new ArrayList<>();
      List<Circulation> listeSave = new ArrayList<>();
      // for (int i = 0; i < this.listeCirculations.size(); i++) {
      while (this.listeCirculations.size() > 0) {
         Circulation circulation = this.listeCirculations.get(0);
         this.listeCirculations.remove(circulation);
         listeTemp.clear();
         listeTemp.addAll(this.listeCirculations);
         // On la compare aux circulations restantes
         for (int j = 0; j < listeTemp.size(); j++) {
            Circulation circulation2 = listeTemp.get(j);
            if (circulation.getJoursCirculation().equalsIgnoreCase(circulation2.getJoursCirculation())) {
               if (circulation.compareCirculation(circulation2)) {
                  if (!circulation.getDateDebut().before(circulation2.getDateDebut()))
                     circulation.setDateDebut(circulation2.getDateDebut());
                  if (!circulation.getDateFin().after(circulation2.getDateFin()))
                     circulation.setDateFin(circulation2.getDateFin());
                  this.listeCirculations.remove(circulation2);
               }
            }
         }
         listeSave.add(circulation);
      }
      this.listeCirculations.clear();
      this.listeCirculations.addAll(listeSave);
   }
}
