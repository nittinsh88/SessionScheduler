package com.test.scheduler.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
	
	
	public static List<String> read(){
		List<String>  rawSessions=new ArrayList<String>();
		//System.out.println("Please enter file location");
		//Scanner scanner=new Scanner(System.in);
		File file = new File("D:\\Hiddentasks\\input.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		
		while(scanner.hasNextLine()){
		     rawSessions.add(scanner.nextLine());		   
		}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
		
		scanner.close();}
		return rawSessions;
		
		
	}

}
