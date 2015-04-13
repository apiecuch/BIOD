package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveFile {
	
	
	public static void saveTextToFile(StringBuilder txt, String fileName){
		
		File output = new File(fileName);
		  try {
		   
		   PrintWriter printer = new PrintWriter(output);
		   
		   printer.write(txt.toString());

		   printer.close();
		  // System.out.println("Zapisano zaszyfrowany tekst do pliku");
		
		  }
		  catch (FileNotFoundException e) {
		   e.printStackTrace();
		  }
	}

}
