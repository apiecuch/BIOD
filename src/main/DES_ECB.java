package main;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DES_ECB {

	public void szyfruj(String fileName) {
		StringBuilder tekst = ReadFile.readTextFromFile(fileName);

		Security.addProvider(new BouncyCastleProvider());
		byte[] input = "www.java2s.com".getBytes();
		byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89,
				(byte) 0xab, (byte) 0xcd, (byte) 0xef };

		SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
		System.out.println("input : " + new String(input));
		
		   // encryption pass
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
	    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
	    ctLength += cipher.doFinal(cipherText, ctLength);
	    System.out.println("cipher: " + new String(cipherText) + " bytes: " + ctLength);

	}

	public void odszyfruj(String fileName) {
		StringBuilder tekst = ReadFile.readTextFromFileDecrypt(fileName);
		
		
		Security.addProvider(new BouncyCastleProvider());
		byte[] input = "www.java2s.com".getBytes();
		byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89,
				(byte) 0xab, (byte) 0xcd, (byte) 0xef };

		SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
		System.out.println("input : " + new String(input));
	
		
		 // decryption pass

	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
	    int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
	    ptLength += cipher.doFinal(plainText, ptLength);
	    System.out.println("plain : " + new String(plainText) + " bytes: " + ptLength);
	}

}
