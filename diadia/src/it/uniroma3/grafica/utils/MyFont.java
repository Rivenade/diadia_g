package it.uniroma3.grafica.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyFont {
	
	public static Font timesR;
	
	public static void timesRoman () {
		
		InputStream myStream;
		try {
			myStream = new BufferedInputStream(new FileInputStream("res/Time Roman.ttf"));
			timesR = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException | IOException e) {
			System.out.println("font non caricato");
			e.printStackTrace();
		}
	}
}
