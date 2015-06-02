package com.avancial.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParsingFileTests {

	public static void main(String[] args) {

		String snFile = "d:/Users/ismael.yahiani/Desktop/SN5209.txt";
		BufferedReader br = null;
		String line = ""; 
		String snSplitBy = " "; 
		List< String> periode = new ArrayList<String>();
		List<String> field = new ArrayList<String>() ; 
		int temp ; 
		try {
	 
			br = new BufferedReader(new FileReader(snFile));
			
			while ((line = br.readLine()) != null) {
			        // use Blanc as separator
				line.replaceAll("[^a-zA-Z 0-9]", "") ;
				field = Arrays.asList(line.trim()); 
				String s = field.get(0);
				if (!s.isEmpty()) {
					periode.add( (String)s.subSequence(142,152)); 
				}
			}  
			
			System.out.println(periode);
				 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
