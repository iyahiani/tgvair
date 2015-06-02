package com.avancial.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParserT {

	String snFile = "d:/Users/ismael.yahiani/Desktop/SN5209.txt";
	BufferedReader br = null;
	String line = ""; 
	String snSplitBy = " "; 
	List< String> periode = new ArrayList<String>();
	List<String> field = new ArrayList<String>() ; 
	Set<String> periodesSet = new HashSet<String>() ;
	Map<String,String> periodesMap = new HashMap<String, String>();
 public ParserT() {	
	try {
		br = new BufferedReader(new FileReader(snFile));
		
		while ((line = br.readLine()) != null) {
		        // use Blanc as separator
			
			field = Arrays.asList(line.split(",")); 
			String s = field.get(0);
			
			if (!s.isEmpty()) {
				periodesSet.add((String)s.subSequence(13,27)) ;
				periode.add( (String)s.subSequence(2,27)); 
				periodesMap.put((String)s.subSequence(13,27),(String)s.subSequence(28,34));
			}
		}  
		System.out.println("--------------periodesMap------------------"); 
		System.out.println(periodesMap); 
		
		System.out.println("------------periodeList--------------------");
		System.out.println(periode);
		/*
		System.out.println("-------------periodesSet--------------------");
		System.out.println(periodesSet);
		*/
			 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	System.out.println("Done"); 
	}
}
