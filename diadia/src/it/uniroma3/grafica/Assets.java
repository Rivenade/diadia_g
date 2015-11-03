package it.uniroma3.grafica;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.grafica.sprite.Sprite;
import it.uniroma3.grafica.sprite.SpriteSheet;
 /**
  * tutta la roba da caricare, esiste per non crearla ogni volta che facciamo il render in DiaDia
  * 
  * @author Stefano
  *
  */

public class Assets {

	private static int DEFAULT_SW = 32, DEFAULT_SH = 32;
	public static /*per essere acceduto dappertutto*/ Sprite player[] = new Sprite[3*4];
	public static Sprite wall;
	public static Map<String, Sprite> attrezzi = new HashMap<>();
	public static BufferedImage background;
	public static Sprite ground;
	public static Sprite closedDoor[] = new Sprite[2];
	public static BufferedImage bag;
	public static BufferedImage morgana;
	public static BufferedImage merlino;
	public static BufferedImage oscurita;
	
	public static void init ()  {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
		SpriteSheet sheet2 = new SpriteSheet (ImageLoader.loadImage("/doubleleggy.png"));
		
		oscurita = ImageLoader.loadImage("/oscurita.png");
		//PLAYER
		for (int i = 0; i < 3; i++) {
			player[i] = new Sprite(sheet2, i+1, 4, DEFAULT_SW, DEFAULT_SH); //front
			player[i+9] = new Sprite(sheet2, i+1, 2, DEFAULT_SW, DEFAULT_SH); //left
			player[i+6] = new Sprite(sheet2, i+1, 3, DEFAULT_SW, DEFAULT_SH); //right
			player[i+3] = new Sprite(sheet2, i+1, 1, DEFAULT_SW, DEFAULT_SH); //back
		}
		
		//NPC
		
		morgana = ImageLoader.loadImage("/personaggi/Morgana2.png"); 
		merlino = new Sprite(sheet, 1, 3, DEFAULT_SW, DEFAULT_SH).getBufferedImage();
		
		//WALL & GROUND
		wall = new Sprite (sheet, 2, 1, DEFAULT_SW, DEFAULT_SH);


		ground = new Sprite (sheet, 1, 1, DEFAULT_SW, DEFAULT_SH);
		
		//DOOR
		for (int i = 0; i < closedDoor.length; i ++) {
			closedDoor[i] = new Sprite (sheet, i+1, 2, DEFAULT_SW, DEFAULT_SH);
		}
		
		//ITEM
		attrezzi.put("osso", new Sprite (sheet, 3, 1, DEFAULT_SW, DEFAULT_SH));
		attrezzi.put("lanterna", new Sprite (sheet, 6, 1, DEFAULT_SW, DEFAULT_SH));
		attrezzi.put("chiave", new Sprite (sheet, 5, 1, DEFAULT_SW, DEFAULT_SH));
//		for (String attrezzo : attrezzi.keySet()){
//			StringBuilder rev = new StringBuilder(attrezzo);
//			rev.rereverse();
//		}
//		
		//BACKGROUND
		background = ImageLoader.loadImage("/sfondo.jpg");
		
		//BORSA
		bag = ImageLoader.loadImage("/borsa.png");
		
	}
	
}
