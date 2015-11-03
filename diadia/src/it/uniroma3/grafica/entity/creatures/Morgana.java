package it.uniroma3.grafica.entity.creatures;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;

import java.awt.Graphics;

public class Morgana extends NPC {
	
	private float startingY;

	public Morgana(Handler handler, float x, float y,
			AbstractPersonaggio personaggio) {
		super(handler, x, y, personaggio);
		startingY = y;
	}
	
	@Override
	public void tick() {
		
		//rozzo ma per ora funziona
		delay++;
		
		yMove = manage();
		if (delay > 3) {
			move();	
			delay = 0;
		}

	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.morgana, (int) (this.x - handler.getCamera().getxOffset()), (int) (this.y - handler.getCamera().getyOffset()), (int)(width*2), (int)(height*2), null);
	}
	
	 @Override
	 public int manage () {
			
			if (startingY - y >= 10) {
				set = 1;
			}

			if (startingY - y == 0) {
				set = -1;
			}

		return set;
	}

}
