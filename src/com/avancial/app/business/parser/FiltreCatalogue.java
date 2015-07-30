package com.avancial.app.business.parser;

import java.util.Map;

import com.avancial.parser.AFilter;
import com.avancial.parser.IParser;

public class FiltreCatalogue extends AFilter {

   private String[] numTrains;

   public FiltreCatalogue(IParser pars) {
      super(pars);
   }

   public FiltreCatalogue(IParser pars, String[] numTrains) {
      super(pars);
      this.numTrains = numTrains;
   }

   @Override
   public String parse(String line) {

      if (!line.equals(""))
         if (this.parser != null)
            line = this.parser.parse(line);

      for (String i : this.numTrains) {
         if (i.equals(line.substring(APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionDebut(), APP_enumParserSSIM.POSITION_NUM_TRAIN.getPositionFin())))
            return line;
      }

      return "";
   }

   @Override
   public Map<String, String> getParsedResult() {

      return null;
   }

}
