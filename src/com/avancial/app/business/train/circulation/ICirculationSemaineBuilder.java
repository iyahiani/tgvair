package com.avancial.app.business.train.circulation;

/**
 * La classe SemaineBuilder crée des circulations d'une semaine
 * 
 * @author bruno
 *
 */
public interface ICirculationSemaineBuilder {
   /**
    * On envoie le jour de circulation à l'observateur qui va vérifier qu'il
    * peut l'intégrer ou non à la semaine de circulation qu'il est en train de
    * construire
    * 
    * @param jourCirculation
    * @return vrai si le jour de circulation a été intégré par l'observateur.
    *         Faux sinon.
    */
   boolean refresh(JourCirculation jourCirculation);

   /**
    * @return la circulation
    */
   Circulation getCirculation();

}
