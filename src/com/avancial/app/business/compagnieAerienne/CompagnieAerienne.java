package com.avancial.app.business.compagnieAerienne;

import java.util.List;

import com.avancial.app.business.train.Train;

public class CompagnieAerienne {

   private String libell�Compagnie ; 
   private String codeCompagnie ;
   private List<Train> trains ; 
   
   
   public String getLibell�Compagnie() {
      return libell�Compagnie;
   }
   public void setLibell�Compagnie(String libell�Compagnie) {
      this.libell�Compagnie = libell�Compagnie;
   }
   public String getCodeCompagnie() {
      return codeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }
}
