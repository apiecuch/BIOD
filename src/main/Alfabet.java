package main;

import java.util.ArrayList;

public class Alfabet {

	private ArrayList<Character> letters;

	public ArrayList<Character> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Character> letters) {
		this.letters = letters;
	}

	public Alfabet() {

		this.letters = new ArrayList<Character>();
		this.init();
	}

	private void init() {

		for (int i=0; i<26; i++){
			this.letters.add((char)('A'+i));
		}
	}

}
