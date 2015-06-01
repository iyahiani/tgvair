package com.avancial.test;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import com.avancial.socle.reader.IReader;
import com.avancial.socle.reader.ReaderTxtFile;
import com.avancial.socle.reader.ReaderXmlFile;

public class TestReaderFile {

	@SuppressWarnings("deprecation")
	@Test 
	public void testFileEquals() {
		IReader file = new ReaderTxtFile() ;
		file = new ReaderXmlFile() ;
			
		IReader file2 = new ReaderTxtFile() ; 
	}
	
	@Test 
	public void testNotNull() {
		IReader file = new ReaderTxtFile() ;
		
		
	}
	
}
