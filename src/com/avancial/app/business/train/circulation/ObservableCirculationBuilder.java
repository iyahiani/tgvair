package com.avancial.app.business.train.circulation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implémentation de l'interface IObservableCirculationSemaineBuilder
 * 
 * @author bruno
 *
 */
public class ObservableCirculationBuilder implements IObservableCirculationSemaineBuilder {
   // Contient la liste des observateurs
   private ArrayList<ICirculationSemaineBuilder> listeObservateurs = new ArrayList<>();;
   // Contient la liste des observateurs ayant demandé à être supprimés
   private ArrayList<ICirculationSemaineBuilder> listeObservateur2delete = new ArrayList<>();
   private ArrayList<Circulation> listeCirculations = new ArrayList<>();

   @Override
   public void notifierObservateurs(JourCirculation jourCirculation) {
      // Permet de savoir si le jour de circulation a été traité par l'un des
      // observateurs
      boolean dayProcessed = false;

      // On parcours la liste des observateurs
      for (ICirculationSemaineBuilder circulationSemaineBuilder : this.listeObservateurs) {
         dayProcessed = dayProcessed || circulationSemaineBuilder.refresh(jourCirculation);
      }
      if (!dayProcessed) {
         // Aucun observateur n'a traité le jour, on en crée un nouveau
         ICirculationSemaineBuilder semaineBuilder = new CirculationSemaineBuilder(jourCirculation);
         this.listeObservateurs.add(semaineBuilder);
      }
      // On parcours la liste des observateurs ayant demandé à être supprimé
      for (ICirculationSemaineBuilder semaineBuilder : this.listeObservateur2delete) {
         this.listeCirculations.add(semaineBuilder.getCirculation());
         this.listeObservateurs.remove(semaineBuilder);
      }
      this.listeObservateur2delete.clear();

   }

   @Override
   public void supprimerObservateur(ICirculationSemaineBuilder observateurSemainBuilder) {
      this.listeObservateur2delete.add(observateurSemainBuilder);
   }

   @Override
   public Collection<? extends Circulation> getListeCirculation() {

      // Avant de retourner la liste des circulations, on parcourt la liste des
      // observateurs afin de récupérer les dernières circulation
      for (ICirculationSemaineBuilder builder : listeObservateurs) {
         this.listeCirculations.add(builder.getCirculation());
      }

      return this.listeCirculations;
   }

}
