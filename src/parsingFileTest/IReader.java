package parsingFileTest;

import java.nio.file.Path;
import java.util.Map;

public interface IReader {

	public Map<String,String> readTxtFile(String fileName) ; 
	public Map<String,String> readCSVFile(String fileName) ;
	public Map<String,String> readXMLFile(String fileName) ; 
}
