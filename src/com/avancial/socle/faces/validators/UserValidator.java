package com.avancial.socle.faces.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import com.avancial.socle.faces.validators.AValidator;

/**
 * 
 * @author caglar.erdogan
 *
 */
@FacesValidator("userValidator")
public class UserValidator extends AValidator {
	@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    this.validate(component.getClientId(context), context, (String) component.getAttributes().get("validatorExpression"), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", (String) component.getAttributes().get("validatorMessage")), (CharSequence) value);
    }
}