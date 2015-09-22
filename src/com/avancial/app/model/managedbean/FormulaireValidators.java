package com.avancial.app.model.managedbean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class FormulaireValidators implements Validator {

   private static final String PATTERN = "^[_A-Za-z0-9-]+(\\." + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

   private Pattern pattern;
   private Matcher matcher;

   public FormulaireValidators() {

      this.pattern = Pattern.compile(PATTERN);
   }

   @Override
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
     
      String val = (String) value ;
      if(val.isEmpty()){
         
         return ;
         /*
         FacesMessage msg = 
            new FacesMessage("message", 
                  component.getClientId()+"est vide ");
         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
         throw new ValidatorException(msg);*/

      }
   }

}
