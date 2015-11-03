package it.uniroma3.grafica.tile;

import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Id;

public class Ground extends Tile {

	public Ground(Id id) {
		super(Assets.ground.getBufferedImage(), id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
