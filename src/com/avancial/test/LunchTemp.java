package com.avancial.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.avancial.app.business.parser.APP_enumParserSSIM;
import com.avancial.app.business.parser.FilterEncodage;
import com.avancial.app.business.parser.FilterSSIMTypeEnr;
import com.avancial.app.business.parser.FiltreCatalogue;
import com.avancial.app.business.parser.FiltreSSIMCompagnieTrain;
import com.avancial.app.business.reader.ReaderSSIM;
import com.avancial.app.business.train.Circulation;
import com.avancial.app.business.train.Train;
import com.avancial.app.resources.utils.StringToDate;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;
import com.avancial.reader.IReader;

public class LunchTemp {

   public static void main(String[] args) throws IOException, ParseException {

      String path = "D:/was_tmp/RESARAIL_200713.txt";
      IReader reader = new ReaderSSIM(path);// "D:/Users/ismael.yahiani/Documents/ssim_6.txt"
      String chaine;
      String[] num = { "005211", "005214", "005215","005225", "005226","005227" }; // , "005225", "005226",
      // "005227","005211"
      //
      // ///////// Instantiation Des Trains SSIM

      Train trainsSSIM = new Train();
      Train trainCat = new Train();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
      IParser par = new ParserFixedLength(new FilterEncodage(new FilterSSIMTypeEnr(new FiltreSSIMCompagnieTrain(new FiltreCatalogue(null, num)))), APP_enumParserSSIM.getNames(),
            APP_enumParserSSIM.getBegins(), APP_enumParserSSIM.getEnds());
      StringBuilder sb = new StringBuilder()  ;
      while ((chaine = reader.readLine()) != null) {

       if(!par.parse(chaine).isEmpty()) { 
        sb.append(par.parse(chaine));
        sb.append("\n");
       }
      }
      System.out.println(sb.toString());
   }
}
