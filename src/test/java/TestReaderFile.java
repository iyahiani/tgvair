package test.java;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;

import parsingFileTest.IReader;
import parsingFileTest.ReaderFile;

public class TestReaderFile {

	@SuppressWarnings("deprecation")

	@Test 
	public void testFileEquals() {
		IReader file = new ReaderFile() ; 
		IReader file2 = new ReaderFile() ; 
		Map<String, String> val1 = file.readCSVFile("d:/Users/ismael.yahiani/Desktop/SN5209_bis.csv");
		Map<String, String> val2 = file2.readTxtFile("d:/Users/ismael.yahiani/Desktop/SN5209.txt");
		//org.junit.Assert.assertNotNull("insatiate success", file);
		org.junit.Assert.assertEquals(val1, val2);
	}
	
	@Test 
	public void testNotNull() {
		IReader file = new ReaderFile() ;
		Map<String, String> val2 = file.readTxtFile("d:/Users/ismael.yahiani/Desktop/SN5209.txt");
		org.junit.Assert.assertNotNull(val2);
	}
	
}
