package com.avancial.filter;

import com.avancial.parser.IParser;
import com.avancial.parser.ParserSSIM;

public class FilterSSIM extends AFilter {

	public FilterSSIM(IParser pars) {
		super(pars) ;
	}

	@Override
	public String parse(String ligne) {
		
		return ligne+"Je suis le filtre SSIM.";
	}
	
	
}
