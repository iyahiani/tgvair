package com.avancial.socle.faces.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.text.WordUtils;

/**
 * 
 * @author caglar.erdogan
 *
 */
@FacesConverter("capitalizeConverter")
public class CapitalizeConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return WordUtils.capitalize(value.toString());
	}
}