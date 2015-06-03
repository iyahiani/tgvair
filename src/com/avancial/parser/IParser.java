package com.avancial.parser;

import com.avancial.reader.IReader;


/**
 * Interface permettant d'impl�menter des parser de fichiers.
 * 
 * @author ismael.yahiani
 *
 */
public interface IParser {

	public String parse(String ligne) ;
}
