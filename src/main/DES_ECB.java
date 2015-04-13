package main;

public class DES_ECB {

	
	public void szyfruj(String fileName){
		StringBuilder tekst = ReadFile.readTextFromFile(fileName);
	}
	
	public void odszyfruj(String fileName){
		StringBuilder tekst = ReadFile.readTextFromFileDecrypt(fileName);
	}
	
	
}
