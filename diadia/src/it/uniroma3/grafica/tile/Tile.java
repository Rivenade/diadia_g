package it.uniroma3.grafica.tile;

import it.uniroma3.grafica.Id;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

	public static Map<Id, Tile> tiles = new HashMap<>();
	public static Tile wall = new Wall(Id.wall);
	public static Tile ground = new Ground(Id.ground);
	public static Tile hDoor = new Door(Id.hDoor, 0);
	public static Tile vDoor = new Door(Id.vDoor, 1);
	
	public static final int DEFAULT_WIDTH = 64,
							DEFAULT_HEIGHT = 64;
	
	protected BufferedImage texture;
	private Id id;

	public Tile (BufferedImage texture, Id id) {
		this.texture = texture;
		this.id = id;
		
		tiles.put(id, this);
	}
	
	public void render (Graphics g, int x, int y){
		g.drawImage(texture, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
	}
	public abstract void tick ();
	
	
	//GETTER E SETTER
	
	public boolean isSolid () {
		return false;
	}
	
	public Id getId () {
		return id;
	}
}
