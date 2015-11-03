package it.uniroma3.grafica.entity.creatures;

import java.awt.Graphics;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;

public class Merlino extends NPC{

	public Merlino(Handler handler, float x, float y,
			AbstractPersonaggio personaggio) {
		super(handler, x, y, personaggio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int manage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.merlino, (int)(getX()- handler.getCamera().getxOffset()), (int)(getY() - handler.getCamera().getyOffset()), (int)(getWidth()*2.5), (int)(getHeight()*2.5), null);
		
	}

}
