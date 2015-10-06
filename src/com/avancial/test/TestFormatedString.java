package com.avancial.test;

import org.junit.Test;

import com.avancial.app.resources.utils.StringToFormatedString;

public class TestFormatedString {

   

   @Test
   public void test() {

      System.out.println(StringToFormatedString.formatterString("6")); 

   } 
   
   
 @Test 
 public void test2() { 
    String  a ="123 5 7"; 
    System.out.println(StringToFormatedString.getRegimeCirculFromSelectedTrain(a)); 
    
 }
}
