package com.avancial.app.business.parser;

import java.util.Map;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

/**
 * 
 * @author ismael.yahiani
 *ce filtre supprime les tranches de circulation ayants l'information TRANCHE OPTIONNEL non vide 
 */
public class FiltreTrancheOptionnel extends AFilter{

   public FiltreTrancheOptionnel(IParser pars) {
      super(pars);
      
   }

   public Map<String, String> getParsedResult() {
      
      return null;
   }
   @Override
   public String parse(String ligne) {
      
      
      if (!ligne.substring(
            APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionDebut(),
            APP_enumParserSSIM.POSITION_TRANCHE_FACULTATIF.getPositionFin()).equals(" "))
         ligne = "";
      

      if (this.parser != null && !ligne.isEmpty())
         return this.parser.parse(ligne);

      return ligne;
   } 
   

}
