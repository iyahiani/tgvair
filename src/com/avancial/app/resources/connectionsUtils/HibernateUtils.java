
package com.avancial.app.resources.connectionsUtils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.avancial.app.data.model.databean.CirculationSSIMDataBean;

public class HibernateUtils {
 
   
   public static SessionFactory getSessionFactory() {
      Configuration configuration = new Configuration().configure().addPackage("com.avancial.app.data.model.databean");
      
       StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
       applySettings(configuration.getProperties());
       SessionFactory factory = configuration.buildSessionFactory(builder.build()); 
       return factory ;
   } 
   
   /**
    * retourne une session factory et ces beans 
    * @return
    */
   
   @SuppressWarnings("deprecation")
   public static AnnotationConfiguration getSFfromAnnotedBean() {
      AnnotationConfiguration sf = new AnnotationConfiguration().configure().addPackage("com.avancial.app.data.model.databean") ; 
      return sf ;
   }
}
