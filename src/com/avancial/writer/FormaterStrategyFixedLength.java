package com.avancial.writer;

import java.util.ArrayList;

public class FormaterStrategyFixedLength implements IFormaterStrategy {
   
   private int[] begins                         ;
   private int[] lengths                        ;
   private String[] colNames                    ;
   private boolean bWriteHeaders                ;
   private IFormaterFixedLength defaultFormater ;
   private IFormaterFixedLength[] formaters     ;
   public FormaterStrategyFixedLength(int[] begins, int[] lengths, IFormaterFixedLength[] formaters, String[] colNames,boolean bWriteHeaders,IFormaterFixedLength defaultFormater) {
      this.begins=begins                        ;
      this.lengths=lengths                      ;
      this.colNames=colNames                    ;
      this.bWriteHeaders=bWriteHeaders          ;
      this.formaters=formaters                  ;
      this.defaultFormater=defaultFormater      ;
   }   
   public String format(ArrayList<String> liste) {
      StringBuilder sb=new StringBuilder();
      for (int i = 0; i < liste.size(); i++) {
         if (null!=this.formaters[i])
            sb.append(this.formaters[i].format(liste.get(i),this.begins[i], this.lengths[i]));
         else
            sb.append(this.defaultFormater.format(liste.get(i), this.begins[i], this.lengths[i]));
      } 
      return sb.toString();
   }
}
