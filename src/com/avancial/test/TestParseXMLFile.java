package com.avancial.test;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;

import com.avancial.app.business.train.Guichet;


public class TestParseXMLFile {

   @Test
   public void readXML() {
   org.jdom.Document  document ;
   Element element ; 
 
   String path ="D:/Users/ismael.yahiani/Documents/points_arret.xml" ;
   SAXBuilder builder = new SAXBuilder() ;
   File xmlFile = new File(path) ; 
   
   try {
      document = (Document) builder.build(xmlFile);
      Element rootNode = document.getRootElement();
      List list = rootNode.getChildren("PointArret"); 
     
      for (int i = 0; i < list.size(); i++) {
         
         Element node = (Element) list.get(i);
         System.out.println("CodeResarail : " + node.getChildText("CodeResarail"));
         System.out.println("CodeGDS : " + node.getChildText("CodeGDS"));
         System.out.println("Libelle : " + node.getChildText("Libelle"));
         
         List listHorraire = node.getChildren("Guichet"); 
         
         if (listHorraire.size() > 0 ) {
            for (int j = 0 ; j < listHorraire.size() ; j++ ) {
               
               Element node2 = (Element) listHorraire.get(j);
               //System.out.println(node2.getChildren().size()); 
               List mesheuresEtJour = node2.getChildren("MesHorairesOuverture") ; 
               
               if (mesheuresEtJour.size()>0) {
                  for (int a = 0 ; a < mesheuresEtJour.size() ; a++ ) {
                     Element e = (Element) mesheuresEtJour.get(a);
                     List jourEtHeure = e.getChildren("HorairesOuverture");
                     for (int x = 0 ; x < jourEtHeure.size() ; x ++ ) {
                        Element z = (Element) jourEtHeure.get(x) ; 
                        System.out.println(z.getChildText("DayOfWeek"));
                     }
                  }
               }
            }
         }
         
      }
   } 
   catch (IOException io) {
      System.out.println(io.getMessage());
     } catch (JDOMException jdomex) {
      System.out.println(jdomex.getMessage());
    }
   } 
   
}

