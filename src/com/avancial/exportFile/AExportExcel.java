package com.avancial.exportFile;

public abstract class AExportExcel implements IExportFile { 
   
   IExportFile exportFile ; 
   
   public AExportExcel(IExportFile iExport) { 
      
      this.exportFile =iExport ; 
   }

}
