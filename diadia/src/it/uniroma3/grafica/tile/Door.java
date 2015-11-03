package it.uniroma3.grafica.tile;

import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Id;

public class Door extends Tile {

	private boolean open = true;
	
	public Door(Id id, int allignement) {
		super(Assets.closedDoor[allignement].getBufferedImage(), id);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public boolean isSolid () {
		return !open;
	}
	
}
