package com.avancial.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParsingFileTests {

public static void main(String[] args) {
		
	IReader file1 = new ReaderSSIM() ; 
	IReader file2 = new ReaderSSIM() ; 
	file1.readLine("d:/Users/ismael.yahiani/Desktop/SN5209.txt") ; 
	file1.readLine("d:/Users/ismael.yahiani/Desktop/SN5209_bis.csv") ;
	
 }
}


/*  String snFile = "d:/Users/ismael.yahiani/Desktop/SN5209_bis.csv";
	BufferedReader br = null;
	String line = ""; 
	String snSplitBy = " "; 
	List< String> periode = new ArrayList<String>();
	List<String> field = new ArrayList<String>() ; 
	Set<String> periodesSet = new HashSet<String>() ;
	Map<String,String> periodesMap = new HashMap<String, String>();
	 
	try {
		br = new BufferedReader(new FileReader(snFile));
		
		while ((line = br.readLine()) != null) {
		        // use Blanc as separator
			
			field = Arrays.asList(line.split(",")); 
			String s = field.get(0).replaceAll("[^a-zA-Z 0-9]", "");
			
			if (!s.isEmpty()) {
				periodesSet.add((String)s.subSequence(13,27)) ;
				periode.add( (String)s.subSequence(13,27)+(String)s.subSequence(27,34)+"\t"+(String)s.subSequence(140,152)); 
				periodesMap.put((String)s.subSequence(13,27),(String)s.subSequence(27,34)+"\t"+(String)s.subSequence(140,152));
				
			}
		}  
	
	for (Map.Entry<String, String> entry : res1.entrySet())
	{
		System.out.println(entry.getKey() + "\t" + entry.getValue());
	}	
	for (Map.Entry<String, String> entry : res2.entrySet())
	{
		System.out.println(entry.getKey() + "\t" + entry.getValue());
	}	
		
		System.out.println("--------------periodesMap------------------"); 
		
		for (Map.Entry<String, String> entry : periodesMap.entrySet())
		{
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}		
		
		
		System.out.println("------------periodeList--------------------");
		for (String string : periode) {
			System.out.println(string);
		}
		
		
		System.out.println("-------------periodesSet--------------------");
		System.out.println(periodesSet);
		
			 
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
*/