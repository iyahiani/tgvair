package parsingFileTest;

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

public class ReaderFile implements IReader {

	
	public ReaderFile() {
	}
	/**
	 * lecture d'un fichier Text 
	 * @return Map<String,String>
	 * @param 
	 */
	@Override
	public Map<String,String> readTxtFile(String fileName) {
		BufferedReader br = null;
		String line = ""; 
		String snSplitBy = " "; 
		List<String> field = new ArrayList<String>() ; 
		List< String> periode = new ArrayList<String>();
		Set<String> periodesSet = new HashSet<String>() ;
  		Map<String,String> periodesMap = new HashMap<String, String>();
		 
		try {
			br = new BufferedReader(new FileReader(fileName));
			
			while ((line = br.readLine()) != null) {
			        // use Blanc as separator
				
				field = Arrays.asList(line.split(",")); 
				String s = field.get(0).replaceAll("[^a-zA-Z 0-9]", "");
				
				if (!s.isEmpty()) {
					periodesSet.add((String)s.subSequence(13,27)) ;
					periode.add( (String)s.subSequence(2,27)); 
					periodesMap.put((String)s.subSequence(13,27),(String)s.subSequence(27,34)+""+(String)s.subSequence(140,152));
					
				}
			}  
				 
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
		return periodesMap; 
	}
	
	/**
	 * lecture d'un fichier CSV
	 * @param  
	 * @return Map<String,String> 
	 */

	@Override
	public Map<String,String> readCSVFile(String fileName) {
		
		BufferedReader br = null;
		String line = ""; 
		String snSplitBy = " "; 
		List<String> field = new ArrayList<String>() ; 
		List< String> periode = new ArrayList<String>();
		Set<String> periodesSet = new HashSet<String>() ;
  		Map<String,String> periodesMap = new HashMap<String, String>();
		 
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
			        // use Blanc as separator
				
				field = Arrays.asList(line.split(",")); 
				String s = field.get(0).replaceAll("[^a-zA-Z 0-9]", "");
				
				if (!s.isEmpty()) {
					periodesSet.add((String)s.subSequence(13,27)) ;
					periode.add( (String)s.subSequence(2,27)); 
					periodesMap.put((String)s.subSequence(13,27),(String)s.subSequence(27,34)+""+(String)s.subSequence(140,152));
				}
			} 
			
			/*
			System.out.println("--------------periodesMap------------------"); 
			System.out.println(periodesMap);
			
			
			System.out.println("------------periodeList--------------------");
			System.out.println(periode);
			
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
		return periodesMap; 
	}

	@Override
	public Map<String,String> readXMLFile(String fileName) {
		return null;
		// TODO Auto-generated method stub
		
	} 
	
	
 }
