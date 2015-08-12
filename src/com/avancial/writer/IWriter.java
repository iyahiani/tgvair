package com.avancial.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author ismael.yahiani
 * interface pour l'ecriture de fichier 
 */
public interface IWriter {
   
   public void write( ArrayList<String> liste) throws IOException;  
   public void close () throws IOException; 
   public IFormaterStrategy getFormaterStrategy();
   public void setFormaterStrategy(IFormaterStrategy formaterStrategy);

}
