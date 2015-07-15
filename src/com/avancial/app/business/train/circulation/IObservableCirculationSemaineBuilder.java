package com.avancial.app.business.train.circulation;

import java.util.Collection;

/**
 * 
 * Cette interface sert à implémenter la classe qui va servir à construire des
 * objets Circulation à la semaine Elle est basée sur le design pattern
 * Observateur. A chaque notification, la classe va appeler chacun des
 * observateurs. Si aucun d'eux n'a traité le jour en revoyant vrai , elle va
 * alors créer un nouvel observateur et l'ajouter à sa liste. A la fin de la
 * notifcation, elle parcourera la liste des observateurs qui auront demandé à
 * être enlevé et elle récupérera la circulation ainsi créée.
 * 
 * 
 * @author bruno
 *
 */
public interface IObservableCirculationSemaineBuilder {
   /**
    * Permet de notifier tous les observateurs. Si aucun d'eux ne répond vrai,
    * alors on crée un nouvel observateur et on l'ajoute à la liste. Après avoir
    * notifié, on parcourt la liste des observateurs à supprimer, on récupère la
    * circulation et on les supprime.
    * 
    * @param jourCirculation
    */
   public void notifierObservateurs(JourCirculation jourCirculation);

   /**
    * Sert à stocker la liste des observateurs qui ont demandé à être supprimé
    * 
    * @param observateurSemainBuilder
    */
   public void supprimerObservateur(ICirculationSemaineBuilder observateurSemainBuilder);

   /**
    * Permet de récupérer toutes les circulations créées par les observateurs
    * 
    * @return
    */
   public Collection<? extends Circulation> getListeCirculation();

}
