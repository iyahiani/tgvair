package com.avancial.test;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.filter.FilterEncodage;
import com.avancial.filter.FilterSSIM;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserSSIM;

public class ReaderTest {
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testParser() {
	
		IParser pars ; 
	
		ParserSSIM parsSSim = new ParserSSIM(new FilterSSIM(new FilterEncodage(null))) ; 
		String chaine=parsSSim.parse("je suis une chaine.") ;
		Assert.assertTrue(chaine.equals("je suis une chaine. Je suis le filtre encodage. Je suis le filtre SSIM."));
		
 	}
}
