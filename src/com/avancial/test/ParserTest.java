package com.avancial.test;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.ParserSSIM;

public class ParserTest {
	
	
	@Test 
	public void testParser() {
		String chaine ="3 SN002340�04G01DEC1502DEC15 23     FRDJU15381538+0100  FRAET16271627+0100  TGAA B                                     OO                  87 CFSN005209                 03  T21BAHAVP                        02895902" ; 
		ParserSSIM parser = new ParserSSIM(new FilterSSIMTypeEnr(new FilterEncodage(null)));
		System.out.println(parser.parse(chaine));
		Assert.assertEquals("301DEC1502DEC15",parser.parse(chaine));
				}
}


/// new FilterSSIM(new FilterEncodage())            .Je suis le filtre encodage.