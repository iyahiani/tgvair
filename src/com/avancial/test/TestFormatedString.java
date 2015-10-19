package com.avancial.test;

import org.junit.Test;

import com.avancial.app.resources.utils.FormatterInteger2String;
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
 
 @Test 
 
 public void test3() {
    int a = 340  ; 
    
    System.out.println(FormatterInteger2String.getFormatedIntToStr(a));
 } 
 
 
 @Test
 public void test4() {
    StringToFormatedString.formaterMatketingFlight("AF4125") ;
 }
 
}
 