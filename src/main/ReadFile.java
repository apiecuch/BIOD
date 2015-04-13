package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {

	public static StringBuilder readTextFromFile(String filename) {

		StringBuilder tekst = new StringBuilder();
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(filename);

			// Odczytywanie jednego bajtu z pliku.
			int b = fileInputStream.read();

			short i = 1;
			while (b != -1) {
				//System.out.println("b= "+b);
				b = Character.toUpperCase((char) b);
				if(b > 64 && b < 91){ //pozbywamy sie znakow innych niz litery
					tekst.append((char) b);// Rzutowanie na typ znakowy.
					
					if(i%5 == 0 ){
						tekst.append(' ');
						i = 0;
					}
					
					++i;
				}
						
				b = fileInputStream.read();
				
			}
			
			fileInputStream.close();
			System.out.println("Odczytano tekst do zaszyfrowania");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return tekst;

	}
	
	public static StringBuilder readTextFromFileDecrypt(String filename) {

		StringBuilder tekst = new StringBuilder();
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(filename);

			// Odczytywanie jednego bajtu z pliku.
			int b = fileInputStream.read();

			while (b != -1) {
				
				//if(((char) b) != 32)
				//	System.out.println("b != 32 ************************************** "+(char)b);
				
				//if(b != 32)
				{
					//System.out.println("b != spacja ************************************** "+b);
					tekst.append((char) b);// Rzutowanie na typ znakowy.
//					if(i%5 == 0 ){
//						tekst.append(' ');
//						i = 0;
//					}
//					++i;
				}
								
				b = fileInputStream.read();
			}
			
			fileInputStream.close();
			//System.out.println("Odczytano tekst do zaszyfrowania");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tekst;

	}

	public static HashMap<Integer, ArrayList<String>> readKeyMapFromFile(
			String keyFileName) {

		HashMap<Integer, ArrayList<String>> keyMap = new HashMap<Integer, ArrayList<String>>();

		FileReader fr = null;
		String linia = "";

		// OTWIERANIE PLIKU:
		try {
			fr = new FileReader(keyFileName);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR PRZY OTWIERANIU PLIKU!");
			System.exit(1);
		}

		BufferedReader bfr = new BufferedReader(fr);
		// ODCZYT KOLEJNYCH LINII Z PLIKU:
				try {		
					ArrayList<String> usedKeys = new ArrayList<String>();

					while ((linia = bfr.readLine()) != null) {
						System.out.println(linia);
						String[] line = linia.split(" ");

						char sign = line[0].charAt(0);

						ArrayList<String> keys = new ArrayList<String>();

						for (int i = 1; i < line.length; i++) {

							// sprawdzam, zeby nie powtorzyly sie homofony
							if (checkIfNotUsed(usedKeys, line[i])) {
								keys.add(line[i]);
							} else {
								System.out
										.println("Pominiêto szyfr dla znaku z powodu powtórzenia");
							}
						}

						keyMap.put((int) sign, keys);

					}
			
		} catch (IOException e) {
			System.out.println("ERROR ODCZYTU Z PLIKU!");
			System.exit(2);
		}

		// ZAMYKANIE PLIKU
		try {
			fr.close();
			System.out.println("Wczytano klucze do szyfru");
		} catch (IOException e) {
			System.out.println("ERROR PRZY ZAMYKANIU PLIKU!");
			System.exit(3);
		}

		return keyMap;
	}
	
	private static boolean checkIfNotUsed(ArrayList<String> usedKeys,
			String code) {

		for (String s : usedKeys) {
			if (s.equals(code)) {
				return false;
			}
		}

		return true;
	}

}
