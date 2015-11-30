package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.ParserSSIM;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.TGVAIRFactory;
import com.avancial.app.resources.utils.HeureFormattage;
import com.avancial.parser.IParser;
import com.avancial.reader.IReader;

public class CirculationTest {

  // @Test
   public void circulTest() throws IOException, ParseException {
      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt");
      String ligneSSIM;
      TGVAIRFactory circul = new TGVAIRFactory();

      IParser par = new ParserSSIM(new FilterSSIMTypeEnr(new FilterEncodage(null)));
      while ((ligneSSIM = reader.readLine()) != null) {
         String ligneParser = par.parse(ligneSSIM);
         if (ligneParser.length() > 0)
            System.out.println(circul.getCirculation(ligneParser));
      } 
   } 
   
   @Test 
   public void testFormatageHeure() {
      int xw = 1 ; 
      System.out.println(HeureFormattage.heureToString(xw)); 
   }
}
