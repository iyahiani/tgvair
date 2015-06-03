package com.avancial.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;

/**
 * 
 * @author ismael.yahiani
 * classe des tests unitaires sur les objet Reader
 */
public class TestReaderFile {

	@Test 
	public void testFileEquals() throws IOException {
		IReader file = new ReaderSSIM("D:/Users/ismael.yahiani/Documents/SN5209.txt") ;
		String line;
		while ((line=file.readLine())!=null) {
			System.out.println(line);
			}
		org.junit.Assert.assertNotNull(file.readLine()) ;
		
	}
	
	
}
