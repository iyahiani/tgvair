package com.avancial.app.resources.utils;

import java.util.ArrayList;
import java.util.List;

public class GetTrainsNums {
/**
 * 
 * @param numeroTrain
 * @return les numero reel du train 
 * 1111/4 -> 001111 et 001114 
 */
   public static List<String> getTrainsNums(String numeroTrain) {
      String[] split =numeroTrain.split(" - ");
      String temp = "", temp1 = "";
      List<String> buff = new ArrayList<>();
      for (String s : split) {
       /* System.out.println("-------- AVANT SPLIT-------------");
       System.out.println(s);
        System.out.println("-------- APRES SPLIT-------------");*/
         if (s.length() > 4) {

            temp = s.substring(0, 4);
            buff.add("00".concat(temp));
            temp1 = temp.substring(0, 3).concat(s.substring(5, 6));
            buff.add("00".concat(temp1));
            //System.out.println(temp+"\n"+temp1);
         } else { 
            //System.out.println(s);
            buff.add("00".concat(s));
         } 
        // System.out.println(temp +""+""+temp1);
      }

      return buff;
   }
}
