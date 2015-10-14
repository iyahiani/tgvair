package com.avancial.test;

import org.junit.Test;

import com.avancial.app.data.controller.dao.ServiceDAO;
import com.avancial.app.data.model.databean.ServiceDataBean;

public class ServiceTests {

   @Test
   public void service() {
      
      ServiceDAO dao = new ServiceDAO() ; 
      ServiceDataBean bean = dao.getLastService() ;
      System.out.println(bean); 
   }
}
