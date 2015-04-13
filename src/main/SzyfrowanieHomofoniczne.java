package main;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;

public class SzyfrowanieHomofoniczne {

	private Alfabet alfabet;
	private HashMap<Integer, ArrayList<String>> mapaKluczy;

	public Alfabet getAlfabet() {
		return alfabet;
	}

	public void setAlfabet(Alfabet alfabet) {
		this.alfabet = alfabet;
	}

	public HashMap<Integer, ArrayList<String>> getMapaKluczy() {
		return mapaKluczy;
	}

	public void setMapaKluczy(HashMap<Integer, ArrayList<String>> mapaKluczy) {
		this.mapaKluczy = mapaKluczy;
	}

	// ------------------------------------------------------
	public SzyfrowanieHomofoniczne() {
		this.alfabet = new Alfabet();
		this.mapaKluczy = new HashMap<Integer, ArrayList<String>>();

	}

	// -----------------------------------------------------------
	public void zaszyfruj(String fileName, String keyFileName) {

		StringBuilder tekst = ReadFile.readTextFromFile(fileName); // wczytujemy
																	// teskt
																	// naturalny
		this.mapaKluczy = ReadFile.readKeyMapFromFile(keyFileName); // pobieramy
																	// mape
																	// kodow do
																	// szyfrowania

		StringBuilder encryptedTxt = new StringBuilder(); // tworzymy pusty
															// teskt ktory
															// bedzie
															// zaszyfrowany

		Random rand = new Random();
		System.out.println(tekst);
		for (int i = 0; i < tekst.length(); i++) { // iterujemy po tekscie

			char letter = tekst.charAt(i); // pobieramy kolejne znaki z tekstu
											// naturalnego
			ArrayList<String> keys = this.mapaKluczy.get((int) letter); // pobieramy
																		// zestaw
																		// kodow
																		// ktorymi
																		// mozna
																		// zaszyfrowac
																		// dany
																		// znak

			if (keys != null) { // jesli mamy kod to losujemy z tablicy dowolony
								// ktory jest w mapie dla tej litey i dolaczamy
								// do pliku
				encryptedTxt.append(keys.get(rand.nextInt(keys.size())) + ' ');
			} else if (letter == 32) {
				// nic nie robimy dla spacji
			} else {
				encryptedTxt.append('@'); // blad, nie powinno wystapic jezeli
											// klucz jest poprawny
			}

		}
		// System.out.println(encryptedTxt.toString());
		SaveFile.saveTextToFile(encryptedTxt, "tekst_do_odszyfrowania.txt");

	}

	// -----------------------------------------------------------------------
	public void odszyfruj(String fileName, String keyFileName) {

		StringBuilder tekst = ReadFile.readTextFromFileDecrypt(fileName); // wczytujemy
		// sobie
		// tekst
		// zaszyfrowany
		this.mapaKluczy = ReadFile.readKeyMapFromFile(keyFileName); // odczytujemy
																	// klucze z
																	// pliku

		StringBuilder decryptedTxt = new StringBuilder(); // inicjalizujemy
															// tekst ktory
															// bedziemy budowac
															// jakos
															// odszyfrowany
		System.out.println("tekst: " + tekst);
		// System.out.println(fileName+" | "+keyFileName);
		// System.out.println("lafdjsafjdsfiwdjedslo");
		short k = 1;
		for (int i = 0; i < tekst.length(); i++) { // iterujemy po tekscie do
													// odszyfrowania

			char letter = tekst.charAt(i); // pobieramy znak
			// System.out.println("letter "+letter);
			// System.out.println("Character.getNumericValue(letter) "+Character.getNumericValue(letter));
			int numVal = Character.getNumericValue(letter);
			// System.out.println("ZNAK: "+numVal);
			// System.out.println("(int)letter: "+(int)letter);
			// || (numVal > 26)
			if (letter == ' ') {

			} else if ((numVal < 0)) { // jesli nie jest
										// litera od a
										// do z to po
										// prostu
										// wpisuejmy
										// System.out.println("((int) letter < 1) || ((int) letter > 26): "
				// + ((Character.getNumericValue(letter) < 1) ||
				// (Character.getNumericValue(letter) > 26))
				// +" | "+Character.getNumericValue(letter)+" | "+letter);

				System.out.println("NIELITERA: " + numVal);

				// decryptedTxt.append(letter);
			} else {
				if ((i + 1) < tekst.length()) { //dwuznakowe kody
					i++; // jesli tak to iterujemy

					String code = String.valueOf(letter)
							+ String.valueOf(tekst.charAt(i));

					System.out.println("CODE: " + code);

					for (Integer literaKlucz : this.mapaKluczy.keySet()) { //it po keys

						ArrayList<String> codes = this.mapaKluczy
								.get(literaKlucz);

						for (String c : codes) {
							if (c.equals(code)) { // jezeli okaze sie ze cod z
													// mapy jest rowny
													// kolejenemu z tekstu to
													// mamy juz wartosc ascii
													// litery musimy ja znalezc
													// w alfabecie

								for (Character ch : this.alfabet.getLetters()) {
									if ((int) ch == literaKlucz) { 
										decryptedTxt.append(Character
												.toChars(literaKlucz));
										break;
									}
								}
								break;
							}
						}
					}

				} else
					break; //koniec tekstu
			}
			if (k % 10 == 0) {
				decryptedTxt.append(' ');
				k = 0;
			}
			++k;
			SaveFile.saveTextToFile(decryptedTxt, "tekstOdszyfrowany.txt");

		}

	}

}
