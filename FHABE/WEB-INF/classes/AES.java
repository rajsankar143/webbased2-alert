package com;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class AES {
public static void main(String a[])throws Exception{
	String msg = a[0];
	String enckey = "abcdefghabcdefgh";
	FileInputStream fin = new FileInputStream("Sunset.png");
	byte b[] = new byte[fin.available()];
	fin.read(b,0,b.length);
	fin.close();
	byte enc[] = encrypt(b);
	byte dec[] = decrypt(enc);
	FileOutputStream out = new FileOutputStream("enc.png");
	out.write(enc,0,enc.length);
	out.close();
	FileOutputStream out1 = new FileOutputStream("test.png");
	out1.write(dec,0,dec.length);
	out1.close();
}
private static Key generateKey(byte keydata[]) throws Exception { 
	Key key = new SecretKeySpec(keydata,"AES");
	return key;
}
public static byte[] decrypt(byte enc[]) throws Exception {
	Key key = generateKey("abcdefghabcdefgh".getBytes());
	Cipher c = Cipher.getInstance("AES");
	c.init(Cipher.DECRYPT_MODE, key);
	byte[] decValue = c.doFinal(enc);
	return decValue;
}
public static byte[] encrypt(byte plain[]) throws Exception {
	Key key = generateKey("abcdefghabcdefgh".getBytes());
	Cipher c = Cipher.getInstance("AES"); 
	c.init(Cipher.ENCRYPT_MODE, key); 
	byte[] encValue = c.doFinal(plain);
	return encValue;
}
}
