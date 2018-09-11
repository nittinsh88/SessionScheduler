package com.scope.scheduler.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nittinsharma
 * 
 * Read the file name and take the file path from user
 *
 */
public class InputReader {
	
	
	public static List<String> read(){
		List<String>  rawSessions=new ArrayList<String>();
		System.out.println("Please enter file location");
		Scanner input=new Scanner(System.in);
		File file = new File("D:\\Hiddentasks\\input.txt");
		file=new File(input.next());
		input.close();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		
		while(scanner.hasNextLine()){
		     rawSessions.add(scanner.nextLine());		   
		}
		} catch (FileNotFoundException e) {
			
			System.out.println("error reading file "+e.getMessage());
		}finally{
		
		scanner.close();}
		return rawSessions;
		
		
	}

}
