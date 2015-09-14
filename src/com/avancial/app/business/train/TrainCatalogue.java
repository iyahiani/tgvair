package com.avancial.app.business.train;

import java.util.List;

import com.avancial.app.business.train.circulation.Circulation;
import com.avancial.app.data.model.databean.PointArretDataBean;

/**
 * @author bruno
 *
 */
public class TrainCatalogue extends Train implements ITrainCatalogue {

   private int idTrainCatalogue ;
   private String nomCompagnie;
   private String numeroVol; 
   private String CodeCompagnie ;
   private int quota1er ; 
   private int quota2eme ; 
   private PointArretDataBean pointArretOrigine ; 
   private PointArretDataBean pointArretDestination ;
   private String marketingFlight ;
   public TrainCatalogue(List<String> listeNumeros, List<Circulation> listeCirculations) {
      super(listeNumeros, listeCirculations);

   }
   public TrainCatalogue() {
   }

   public Train getTrain() {

      return TrainFactory.createTrainComplet(this);
   }
   public Train getTrain2() {

      return TrainFactory.createTrainComplet2(this);
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
   public int getIdTrainCatalogue() {
      return idTrainCatalogue;
   }
   public void setIdTrainCatalogue(int idTrainCatalogue) {
      this.idTrainCatalogue = idTrainCatalogue;
   }
   public String getCodeCompagnie() {
      return CodeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      CodeCompagnie = codeCompagnie;
   }
   public int getQuota1er() {
      return quota1er;
   }
   public void setQuota1er(int quota1er) {
      this.quota1er = quota1er;
   }
   public int getQuota2eme() {
      return quota2eme;
   }
   public void setQuota2eme(int quota2eme) {
      this.quota2eme = quota2eme;
   }
   public PointArretDataBean getPointArretOrigine() {
      return pointArretOrigine;
   }
   public void setPointArretOrigine(PointArretDataBean pointArretOrigine) {
      this.pointArretOrigine = pointArretOrigine;
   }
   public PointArretDataBean getPointArretDestination() {
      return pointArretDestination;
   }
   public void setPointArretDestination(PointArretDataBean pointArretDestination) {
      this.pointArretDestination = pointArretDestination;
   }
   public String getMarketingFlight() {
      return marketingFlight;
   }
   public void setMarketingFlight(String marketingFlight) {
      this.marketingFlight = marketingFlight;
   }

}
