package com.avancial.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.avancial.parser.ParserSSIM;

public class ReaderSSIM extends AReaderTxt {

/**
 * 
 * @param fileName 
 * 
 */
	public ReaderSSIM() {
		
	}
	
	public void readLine(String fileName) {
		 		
		BufferedReader br = null;
		String line = ""; 
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				
			}  
				 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	}

}
