package com.avancial.app.business.train;

import java.util.List;

/**
 * @author bruno
 *
 */
public class TrainCatalogue extends Train implements ITrainCatalogue {

   private String nomCompagnie;
   private String numeroVol;

   public TrainCatalogue(List<String> listeNumeros, List<Circulation> listeCirculations) {
      super(listeNumeros, listeCirculations);

   }
   public TrainCatalogue() {
   }

   public Train getTrain() {

      return TrainFactory.createTrainComplet(this);
   }

   @Override
   public String getGareOrigine() {

      return this.getCirculations().get(0).getOrigine();
   }

   @Override
   public String getGareDestination() {

      return getCirculations().get(getCirculations().size() - 1).getDestination();
   }

   @Override
   public String[] getAllTrainID() {

      return null;
   }

   @Override
   public void compareSSIM(ITrain train) {

   }

   public ITrain getTrainCatalogueComplet() {

      return null;
   }

  
   /**
    * @return the nomCompagnie
    */
   public String getNomCompagnie() {
      return this.nomCompagnie;
   }

   /**
    * @param nomCompagnie
    *           the nomCompagnie to set
    */
   public void setNomCompagnie(String nomCompagnie) {
      this.nomCompagnie = nomCompagnie;
   }

}
