package com.avancial.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.metier.parser.APP_enumParserSSIM;
import com.avancial.metier.parser.FilterEncodage;
import com.avancial.metier.parser.FilterSSIMTypeEnr;
import com.avancial.metier.parser.ParserSSIM;
import com.avancial.parser.IParser;
import com.avancial.parser.ParserFixedLength;

/**
 * 
 * @author ismael.yahiani classe des tests unitaires sur les objet Parser
 */
public class ParserTest {

	/*
	 * @Test public void testParser() {
	 * 
	 * String chaine =
	 * "3 SN002340�04G01DEC1502DEC15 23     FRDJU15381538+0100  FRAET16271627+0100  TGAA B                                     OO                  87 CFSN005209                 03  T21BAHAVP                        02895902"
	 * ; IParser parser = new ParserSSIM(new FilterEncodage( new
	 * FilterSSIMTypeEnr(null))); System.out.println("Chaine = " +
	 * parser.parse(chaine)); Assert.assertEquals(
	 * "3SN0023400401DEC1502DEC15 23    FRDJU1538+0100FRAET1627+0100 FSN005209",
	 * parser.parse(chaine)); }
	 */

	@Test
	public void testParsingGenerique() {

		String chaine = "3 SN002340�04G01DEC1502DEC15 23     FRDJU15381538+0100  FRAET16271627+0100  TGAA B                                     OO                  87 CFSN005209                 03  T21BAHAVP                        02895902";

		IParser parser = new ParserFixedLength(new FilterEncodage(
				new FilterSSIMTypeEnr(null)), APP_enumParserSSIM.getBegins(),
				APP_enumParserSSIM.getEnds(), APP_enumParserSSIM.getNames());
		parser.parse(chaine);
		Map<String, String> map = parser.getParsedResult();
		Assert.assertTrue(map.get(
				APP_enumParserSSIM.POSITION_COMPAGNIE_TRAIN.name())
				.equals("SN"));
	}
}
