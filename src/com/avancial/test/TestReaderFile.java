package com.avancial.test;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;
import com.avancial.reader.ReaderXmlFile;

public class TestReaderFile {

	@SuppressWarnings("deprecation")
	@Test 
	public void testFileEquals() {
		IReader file = new ReaderSSIM() ;
		file = new ReaderXmlFile() ;
		IReader file2 = new ReaderSSIM() ; 
	}
	
	@Test 
	public void testNotNull() {
		IReader file = new ReaderSSIM() ;
	}
	
}
