package com.avancial.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParsingTest {
	
	  private final Path fFilePath;
	  private final static Charset ENCODING = StandardCharsets.UTF_8;    
	  static int i = 0 ; 
	  List<String> fields = new ArrayList<String>(); 
	 
	 public ParsingTest(String aFileName){
		    fFilePath = Paths.get(aFileName);
		    }
		  
		  /** Traitement du fichier ligne par ligne  */
 
	 public final void processLineByLine() throws IOException {
	  		
		    try (Scanner scanner =  new Scanner(fFilePath) ){ 
		    	
		    	while (scanner.hasNextLine()){	
		            /*
		    		if (scanner.next().trim().length()==0) { 
		    			continue ;
		    		}*/
		    			i++;
		    			processLine(scanner.nextLine());
		    		} 
		    	
		    } catch (Exception e){e.printStackTrace(); }
		    finally {
		    	
		    }
		  }  
	public static  List<String> processLine(String aLine) {
			
		 Scanner scanner = new Scanner(aLine);
		 	 
		    List<String> field = new ArrayList<String>() ;	    
		    while (scanner.hasNext() ){   	  
		    field.add(scanner.next());
		    	//field.add(scanner.next());		    
		    }
		    log(i+""+field) ;
		    return field ;
		}
	   
		public static void processByString(String chaine) {
			
		}
	
		private static void log(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }

	 	public static void main(String[] args)throws IOException {
		ParsingTest test = new ParsingTest("d:/Users/ismael.yahiani/Desktop/SN5209.txt") ;
		test.processLineByLine();
		log("done");
		
		
		
	}

}
