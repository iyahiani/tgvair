package com.avancial.app.business.train.circulation;

/**
 * La classe SemaineBuilder cr�e des circulations d'une semaine
 * 
 * @author bruno
 *
 */
public interface ICirculationSemaineBuilder {
   /**
    * On envoie le jour de circulation � l'observateur qui va v�rifier qu'il
    * peut l'int�grer ou non � la semaine de circulation qu'il est en train de
    * construire
    * 
    * @param jourCirculation
    * @return vrai si le jour de circulation a �t� int�gr� par l'observateur.
    *         Faux sinon.
    */
   boolean refresh(JourCirculation jourCirculation);

   /**
    * @return la circulation
    */
   Circulation getCirculation();

}
