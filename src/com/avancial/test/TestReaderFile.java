package com.avancial.test;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import com.avancial.reader.IReader;
import com.avancial.reader.ReaderSSIM;


public class TestReaderFile {

	@SuppressWarnings("deprecation")
	@Test 
	public void testFileEquals() {
		IReader file = new ReaderSSIM() ;
		
		IReader file2 = new ReaderSSIM() ; 
	}
	
	@Test 
	public void testNotNull() {
		IReader file = new ReaderSSIM() ;
	}
	
}
