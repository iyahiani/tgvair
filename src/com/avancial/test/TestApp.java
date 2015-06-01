package com.avancial.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.avancial.socle.reader.ReaderTxtFile;




@RunWith (value = Suite.class) 
//@SuiteClasses(value = { TestTrain.class})
@SuiteClasses(value = { ReaderTxtFile.class})
public class TestApp {
	
	public TestApp() {
		
	}
}
