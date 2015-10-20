
package com.avancial.app.resources.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
 
   
   public static SessionFactory getSessionFactory() {
      Configuration configuration = new Configuration().configure();
      
       StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
       applySettings(configuration.getProperties());
       SessionFactory factory = configuration.buildSessionFactory(builder.build()); 
       return factory ;
   }
}
