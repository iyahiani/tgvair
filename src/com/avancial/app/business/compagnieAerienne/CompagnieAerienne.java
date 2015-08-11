package com.avancial.app.business.compagnieAerienne;

import java.util.List;

import com.avancial.app.business.train.Train;

public class CompagnieAerienne {

   private String libelléCompagnie ; 
   private String codeCompagnie ;
   private List<Train> trains ; 
   
   
   public String getLibelléCompagnie() {
      return libelléCompagnie;
   }
   public void setLibelléCompagnie(String libelléCompagnie) {
      this.libelléCompagnie = libelléCompagnie;
   }
   public String getCodeCompagnie() {
      return codeCompagnie;
   }
   public void setCodeCompagnie(String codeCompagnie) {
      this.codeCompagnie = codeCompagnie;
   }
}
