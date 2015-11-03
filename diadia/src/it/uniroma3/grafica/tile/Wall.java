package it.uniroma3.grafica.tile;

import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Id;

public class Wall extends Tile {

	public Wall(Id id) {
		super(Assets.wall.getBufferedImage(), id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isSolid ()  {
		return true;
	}
}
