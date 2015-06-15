package com.avancial.test;

import java.io.IOException;

import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;

public class LunchCatalogueReader {

	public static void main(String[] args) {

		try {
			String ligne;
			IReader reader = new ReaderSSIM(
					"D:/Users/ismael.yahiani/Documents/ProgrammeTGVAIRAF.csv");
			
			while ((ligne = reader.readLine()) != null) {
				System.out.println(ligne);
					String[] rslt = ligne.split(";");
				
				for (int i = 0; i < rslt.length; i++) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
