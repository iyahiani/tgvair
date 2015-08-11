package com.avancial.exportFile;

/**
 * 
 * @author ismael.yahiani
 *  définit la methode ce construction des export de type texte    
 */
public abstract class AExportTxt implements IExportFile{

   private String fileName  ;
   
  
   
   /**
    * 
    @author Yahiani Ismail 
    * @param fileName Chemin & nom du fichier destination
    */
   public AExportTxt(String fileName) { 
           this.fileName = fileName ;
   } 
   
}
