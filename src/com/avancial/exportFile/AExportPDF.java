package com.avancial.exportFile;

public abstract class AExportPDF implements IExportFile { 

   protected IExportFile export ; 
   
   public AExportPDF(IExportFile export) { 
      
      this.export = export ; 
   } 
}
