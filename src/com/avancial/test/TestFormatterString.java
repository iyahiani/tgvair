package com.avancial.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

public class TestFormatterString {

   @Test 
   
   public void formatter() {
      List<String> chaine = new ArrayList<>() ; 
      StringBuilder temp = new StringBuilder() ; 
      
      chaine.add("1") ; 
      chaine.add("2") ; 
      chaine.add("4") ;
    for (int i = 0 ; i < chaine.size() ; i++) {
       temp.append(chaine.get(i), Integer.valueOf(chaine.get(i)), Integer.valueOf(chaine.get(i))) ;
    } 
    
    System.out.println(temp);
   }
}
