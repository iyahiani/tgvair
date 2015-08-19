package com.avancial.socle.faces.validators;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

/**
 * 
 * @author caglar.erdogan
 *
 */
public abstract class AValidator implements Validator {
    protected void validate(String clientId, FacesContext context, String expression, FacesMessage message, CharSequence value) {
		if(!Pattern.compile(expression).matcher(value).matches()) {
			context.addMessage(clientId, message);
			context.validationFailed();
		}
	}
}