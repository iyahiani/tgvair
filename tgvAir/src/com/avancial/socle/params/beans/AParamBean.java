/**
 * 
 */
package com.avancial.socle.params.beans;


/**
 * @author bruno
 *
 */
public abstract class AParamBean implements IParamBean {
   protected String name;
   protected String value;

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String getValue() {
      return this.value;
   }

   @Override
   public void SetValue(String value) {
      this.value = value;
   }
}
