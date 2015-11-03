package it.uniroma3.grafica.utils;

import it.uniroma3.grafica.Id;
import it.uniroma3.grafica.ImageLoader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Stefano
 *
 */
public class Utils {

	public static int width = 0;
	public static int height = 0;
	
	public static String loadFileAsString (String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line+ '\n');
			
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
		
	}
	
	public static Color[][] loadFileAsImage (String path) {
		BufferedImage room = ImageLoader.loadImage(path);
		width = room.getWidth();
		height = room.getHeight();
		Color[][] colours = new Color[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = room.getRGB(x, y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel >> 0) & 0xff;
				
				colours[x][y] = new Color(red, green, blue);
				
			}
		}
		return colours;
	}
	
	public static int parseInt (String number) {
		try {
			return Integer.parseInt(number);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Id parseId (Color c){
		Id id = Id.ground; //DEFAULT
		if (c.getRed()==127 && c.getGreen()==22 && c.getBlue() == 0) id = Id.vDoor;
		if (c.getRed()==255 && c.getGreen()==255 && c.getBlue() == 0) id = Id.hDoor;
		if (c.getRed()==0 && c.getGreen()==0 && c.getBlue() == 0) id = Id.wall;
		if (c.getRed()==0 && c.getGreen()==0 && c.getBlue() == 255) id = Id.player;
		if (c.getRed()==255 && c.getGreen()==255 && c.getBlue() == 255) id = Id.ground;
		if (c.getRed()==255 && c.getGreen()==0 && c.getBlue() == 0) id = Id.attrezzo;
		if (c.getRed()==0 && c.getGreen()==255 && c.getBlue() == 0) id = Id.NPC;
		return id;
	}
	
}
