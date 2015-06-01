package test.java;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import parsingFileTest.ReaderFile;


@RunWith (value = Suite.class) 
//@SuiteClasses(value = { TestTrain.class})
@SuiteClasses(value = { ReaderFile.class})
public class TestApp {
	
	public TestApp() {
		
	}
}
