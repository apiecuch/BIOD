package main;

import GUI.MainWindow;

public class Main {
	
	public static void main(String[] args) {

			Main m = new Main();
			m.openMainWindow();
		}
	
	public void openMainWindow() {
			MainWindow mwin = new MainWindow();
			mwin.setVisible(true);
		}
}
