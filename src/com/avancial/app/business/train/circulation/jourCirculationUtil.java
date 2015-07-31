package com.avancial.app.business.train.circulation;

public class jourCirculationUtil {


   
   
 
   
   public String toSSIMString() {
      
//      StringBuilder builder=new StringBuilder();
//      
//      for (int i = 0; i < tabJours.length; i++) {
//         if (tabJours[i]==0)
//            builder.append(" ");
//         else builder.append(i+1);
//      }
//      
      return null;
   }


   public static String fusionne(String joursCirculation1, String joursCirculation2) {
      int[] tabJours=new int[7];
      
      for (int i=0 ; i<joursCirculation1.length();i++) {
         if (joursCirculation1.charAt(i)!=' ')
            tabJours[i]=1;
      }
      
      for (int i=0 ; i<joursCirculation2.length();i++) {
         if (joursCirculation1.charAt(i)!=' ')
            tabJours[i]=1;
      }
      
      StringBuilder builder=new StringBuilder();
    
    for (int i = 0; i < tabJours.length; i++) {
       if (tabJours[i]==0)
          builder.append(" ");
       else builder.append(i+1);
    }
    
    return builder.toString();
   }
}
