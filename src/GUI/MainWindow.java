package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.DES_ECB;
import main.SzyfrowanieHomofoniczne;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Choice typSzyfrowania;
	private SzyfrowanieHomofoniczne szyfr = new SzyfrowanieHomofoniczne();
	private DES_ECB szyfr1 = new DES_ECB();

	public MainWindow() {

		setTitle("SZYFROWANIE G³uchowska & Piecuch");
		getContentPane().setLayout(null);

		JLabel lblWybierzTypSzyfrowania = new JLabel("Wybierz typ szyfrowania");
		lblWybierzTypSzyfrowania.setBounds(39, 35, 266, 37);
		getContentPane().add(lblWybierzTypSzyfrowania);

		typSzyfrowania = new Choice();
		typSzyfrowania.setBounds(77, 78, 206, 37);
		getContentPane().add(typSzyfrowania);
		typSzyfrowania.add("Szyfrowanie homofoniczne");
		typSzyfrowania.add("Szyfrowanie DES-ECB");

		// -----------------------------------------------------------
		Label label = new Label("Pliki wej\u015Bciowe");
		label.setBounds(39, 130, 150, 22);
		getContentPane().add(label);

		final TextField tekstNaturalny = new TextField("tekst_naturalny.txt");
		tekstNaturalny.setBounds(77, 177, 206, 22);
		getContentPane().add(tekstNaturalny);

		final TextField tekstDoOdszyfrowania = new TextField(
				"tekst_do_odszyfrowania.txt");
		tekstDoOdszyfrowania.setBounds(77, 225, 206, 22);
		getContentPane().add(tekstDoOdszyfrowania);

		final TextField keyFile = new TextField("keys.txt");
		keyFile.setBounds(77, 265, 206, 30);
		getContentPane().add(keyFile);

		// ------------------------------------------------------------
		Button szyfruj = new Button("Zaszyfruj");
		szyfruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		szyfruj.setBounds(315, 177, 135, 22);
		getContentPane().add(szyfruj);

		szyfruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (MainWindow.this.typSzyfrowania.getSelectedIndex()) {
				case 0:
					szyfr.zaszyfruj(tekstNaturalny.getText(), keyFile.getText());
					break;
				case 1:
					break;
				default:
					break;
				}

			}
		});

		Button odszyfruj = new Button("Odszyfruj");
		odszyfruj.setBounds(315, 225, 135, 22);
		getContentPane().add(odszyfruj);

		odszyfruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (MainWindow.this.typSzyfrowania.getSelectedIndex()) {
				case 0:
					szyfr.odszyfruj(tekstDoOdszyfrowania.getText(),
							keyFile.getText());
					break;
				case 1:
					break;
				default:
					break;
				}

			}
		});

	}
}
