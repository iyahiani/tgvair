package com.avancial.app.business.train.circulation;

import java.util.Collection;

/**
 * 
 * Cette interface sert � impl�menter la classe qui va servir � construire des
 * objets Circulation � la semaine Elle est bas�e sur le design pattern
 * Observateur. A chaque notification, la classe va appeler chacun des
 * observateurs. Si aucun d'eux n'a trait� le jour en revoyant vrai , elle va
 * alors cr�er un nouvel observateur et l'ajouter � sa liste. A la fin de la
 * notifcation, elle parcourera la liste des observateurs qui auront demand� �
 * �tre enlev� et elle r�cup�rera la circulation ainsi cr��e.
 * 
 * 
 * @author bruno
 *
 */
public interface IObservableCirculationSemaineBuilder {
   /**
    * Permet de notifier tous les observateurs. Si aucun d'eux ne r�pond vrai,
    * alors on cr�e un nouvel observateur et on l'ajoute � la liste. Apr�s avoir
    * notifi�, on parcourt la liste des observateurs � supprimer, on r�cup�re la
    * circulation et on les supprime.
    * 
    * @param jourCirculation
    */
   public void notifierObservateurs(JourCirculation jourCirculation);

   /**
    * Sert � stocker la liste des observateurs qui ont demand� � �tre supprim�
    * 
    * @param observateurSemainBuilder
    */
   public void supprimerObservateur(ICirculationSemaineBuilder observateurSemainBuilder);

   /**
    * Permet de r�cup�rer toutes les circulations cr��es par les observateurs
    * 
    * @return
    */
   public Collection<? extends Circulation> getListeCirculation();

}
