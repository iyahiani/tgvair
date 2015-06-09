package com.avancial.test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.parser.IParser;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.tgvair.metier.Circulation;

public class CirculationTest {

   @Test
   public void circulTest() throws IOException, ParseException {
      IReader reader = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt");
      String ligneSSIM;
      Circulation circul = new Circulation();

      IParser par = new ParserSSIM(new FilterSSIMTypeEnr(new FilterEncodage(null)));
      while ((ligneSSIM = reader.readLine()) != null) {
         String ligneParser = par.parse(ligneSSIM);
         if (ligneParser.length() > 0)
            System.out.println(circul.getCirculation(ligneParser).getChaineCircu());
      }
   }
}
