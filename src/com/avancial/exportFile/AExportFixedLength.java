package com.avancial.exportFile;

public abstract class AExportFixedLength extends AExportTxt {
   
   private int[] begins;
   private int[] ends;
   private String[] colNames;
   private boolean bWriteHeaders;
   
   
   

   /**
    * 
    @author Yahiani Ismail 
    * @param fileName Chemin & nom du fichier destination
    * @param begins tableau d'entiers indiquant le d�but de chaque champs
    * @param ends tableau d'entiers indiquant la fin de chaque champs
    * @param colNames nom des ent�tes de colonnes
    * @param bWriteHeaders indique si on doit �crire les ent�tes dans le fichier
    */
   public AExportFixedLength(String fileName,int[] begins,int[] ends,String[] colNames,boolean bWriteHeaders) {
      super(fileName);
      this.begins=begins;
      this.ends=ends;
      this.colNames=colNames;
      this.bWriteHeaders=bWriteHeaders;
      
   }

 /**
  * Ecrit les ent�tes dans le fichier
  */
   public void writeHeaders() {
      //A Impl�menter
   }
   

}
