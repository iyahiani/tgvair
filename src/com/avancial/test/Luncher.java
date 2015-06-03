package com.avancial.test;

import java.io.IOException;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.parser.IParser;
import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;

public class Luncher {

	public static void main(String[] args) throws IOException {
		/*IReader reader = new ReaderSSIM() ;
		String line = reader.readLine("D:/Users/ismael.yahiani/Documents/SN5209") ;
		ParserSSIM parsSSim = new ParserSSIM(new FilterSSIM(new FilterEncodage(null))); 
		String chaine=parsSSim.parse("je suis une chaine.") ;*/
		
		// IReader read = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt") ;
		IReader read = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt") ;
		ParserSSIM par = new ParserSSIM(new FilterEncodage(null));
		String file;
		while ((file=read.readLine())!=null) {
			file=par.parse(file);System.out.println(file);
			
		}
		
		
	}

}
