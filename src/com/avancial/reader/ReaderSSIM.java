package com.avancial.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author ismael.yahiani class contenant les methodes pour la Lecture du
 *         fichier SSIM
 */
public class ReaderSSIM extends AReaderTxt {

	/**
	 * 
	 * @param fileName
	 *            lecture du fichier SSIM ligne par ligne
	 **/
	String path;
	BufferedReader br;

	public ReaderSSIM(String fileName) throws IOException {
		this.path = fileName;
		this.br = new BufferedReader(new FileReader(path));
	}

	public String readLine() throws IOException {
	
		return this.br.readLine() ;
	
	}
}
