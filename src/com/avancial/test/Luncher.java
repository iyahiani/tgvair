package com.avancial.test;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.metier.parser.TGVAIR_enumParserCirculation;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.socle.exceptions.ASocleException;
import com.avancial.tgvair.DataBeans.CirculationDataBean;
import com.avancial.tgvair.metier.Circulation;
import com.avanciale.TGVAIR.DAO.CirculationDAO;

public class Luncher {

	public static void main(String[] args) throws IOException, ASocleException {
		/*
		 * IReader reader = new ReaderSSIM() ; String line =
		 * reader.readLine("D:/Users/ismael.yahiani/Documents/SN5209") ;
		 * ParserSSIM parsSSim = new ParserSSIM(new FilterSSIM(new
		 * FilterEncodage(null))); String
		 * chaine=parsSSim.parse("je suis une chaine.") ;
		 */

		IReader read = new ReaderSSIM(
				"D:/Users/ismael.yahiani/Documents/SN5209.txt");
		ParserSSIM par = new ParserSSIM(new FilterSSIMTypeEnr(
				new FilterEncodage(null)));
		String file;
		String chaine;
		Circulation circul = new Circulation();
		CirculationDataBean circulationDataBean = new CirculationDataBean() ; 
		CirculationDAO dao = new CirculationDAO() ;  
		
		//CirculationDAO circulDAO = new CirculationDAO(circul);
		while ((file = read.readLine()) != null) {
			chaine = par.parse(file);

			if (chaine.length() > 0) {
				circul = circul.getCirculation(chaine) ;
				
				dao.save(circul.getCirculationDatabean(circul));
			}
		}
	}

}
/*
 * System.out.println(circul.getDateDebut() + "\t"
						+ circul.getDateFin() + "\t" + circul.getOrigine()
						+ "\t" + circul.getHeureDepart() + "\t"
						+ circul.getDestination() + "\t"
						+ circul.getHeureArrivee()+ "\t" 
						);
 */
