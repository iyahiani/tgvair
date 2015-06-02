package com.avancial.test;

import com.avancial.filter.FilterEncodage;
import com.avancial.filter.FilterSSIM;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserSSIM;

public class Luncher {

	public static void main(String[] args) {
		ParserSSIM parsSSim = new ParserSSIM(new FilterSSIM(new FilterEncodage(null))) ; 
		String chaine=parsSSim.parse("je suis une chaine.") ;
		System.out.println(chaine);

	}

}
