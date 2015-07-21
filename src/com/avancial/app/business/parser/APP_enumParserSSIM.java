package com.avancial.app.business.parser;

/**
 * Constantes pour parser le fichier SSIM. Contient la position et la longueur de chaque donnée les
 * 
 * @author ismael.yahiani
 *
 */
public enum APP_enumParserSSIM {
   /**
    * Position du type de l'enregistrement
    */
   POSITION_TYPE_ENR(0, 1),
   /**
    * Position de l'identifiant de la tranche
    */
   POSITION_IDENTIFIANT_TRANCHE(2, 10),
   /**
    * position du Rang du tronçon
    */
   POSITION_RANG_TRANCON(13, 15),
   /**
    * position de la Periode de circulation
    */
   POSITION_PERIODE_CIRCULATION_DEBUT(16, 23), 
   
   POSITION_PERIODE_CIRCULATION_FIN(23, 30),
   /**
    * position des Jours de Circulation
    */
   POSITION_JOURS_CIRCULATION(30, 37),
   /**
    * Position de la gare de Depart
    */
   POSITION_GARE_DEPART(38, 43),
   /**
    * Position de l'heure de Depart
    */
   POSITION_HEURE_DEPART(43, 47),
   /**
    * position de la Différence horaire G.M.T. départ
    */
   POSITION_DIFFERENCE_GMT_DEPART(51, 56),
   /**
    * Position de la gare de Arriver
    */
   POSITION_GARE_ARRIVER(58, 63),
   /**
    * Position de l'heure d'Arriver
    */
   POSITION_HEURE_ARRIVER(63, 67),
   /**
    * position de la Différence horaire G.M.T. arrivée
    */
   POSITION_DIFFERENCE_GMT_ARRIVER(71, 77),
   /**
    * position de l'indicateur FER
    */
   POSITION_INDICATEUR_FER(145, 146),
   /**
    * position de la compagnie train
    */
   POSITION_COMPAGNIE_TRAIN(146, 148),
   /**
    * position du Numéro du train réel à la gare de départ du tronçon
    */
   POSITION_NUM_TRAIN(148, 154),
   /** 
    * position code restriction de trafic 
   */
   POSITION_RESTRICTION_TRAFIC(160, 170),
   /**
    * position tranche facultatif 
    */
   POSITION_TRANCHE_FACULTATIF(174, 175),
   /**
    * Valeur du type des enregistrements de type4
    */
   VALEUR_TYPE3("3"),
   /**
	 *	 
	 */
   VALEUR_COMPAGNIE_TRAIN("SN");

   private int    postionFin;
   private int    positionDebut;
   private String typeEnr;
   private String CompagnieTrain;

   private APP_enumParserSSIM(String type) {
      this.setTypeEnr(type);
      this.setCompagnieTrain(type);
   }

   private APP_enumParserSSIM(int position, int longueur) {

      this.positionDebut = position;
      this.postionFin = longueur;
   }

   public int getPositionFin() {
      return postionFin;
   }

   public int getPositionDebut() {
      return positionDebut;
   }

   public String getTypeEnr() {
      return typeEnr;
   }

   public void setTypeEnr(String typeEnr) {
      this.typeEnr = typeEnr;
   }

   public String getCompagnieTrain() {
      return CompagnieTrain;
   }

   public void setCompagnieTrain(String compagnieTrain) {
      CompagnieTrain = compagnieTrain;
   }

   public static Integer[] getBegins() {
      Integer[] tab = new Integer[APP_enumParserSSIM.values().length];
      for (int i = 0; i < APP_enumParserSSIM.values().length; i++) {
         APP_enumParserSSIM j = APP_enumParserSSIM.values()[i];
         tab[i] = j.getPositionDebut();
      }
      return tab;
   }

   public static Integer[] getEnds() {
      Integer[] tab = new Integer[APP_enumParserSSIM.values().length];
      for (int i = 0; i < APP_enumParserSSIM.values().length; i++) {
         APP_enumParserSSIM j = APP_enumParserSSIM.values()[i];
         tab[i] = j.getPositionFin();
      }
      return tab;
   }

   public static String[] getNames() {
      String[] tab = new String[APP_enumParserSSIM.values().length];
      for (int i = 0; i < APP_enumParserSSIM.values().length; i++) {
         APP_enumParserSSIM j = APP_enumParserSSIM.values()[i];
         tab[i] = j.name();
      }
      return tab;
   }
}
