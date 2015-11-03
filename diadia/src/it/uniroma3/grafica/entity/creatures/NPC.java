package it.uniroma3.grafica.entity.creatures;

import java.awt.Graphics;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;

public abstract class NPC extends Creature {

//	private AbstractPersonaggio personaggio;
	

	
	public NPC(Handler handler, float x, float y, AbstractPersonaggio personaggio) {
		super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, true, Id.NPC);
//		this.personaggio = personaggio;

	}

	int delay = 0;
	
//	public AbstractPersonaggio getPersonaggio () {
//		return this.personaggio;
//	}
//	
//	public void setPersonaggio (AbstractPersonaggio personaggio){
//		this.personaggio = personaggio;
//	}
	
	@Override
	public abstract void tick();

	int set = -1;

	public abstract int manage ();

	@Override
	public abstract void render(Graphics g);

}
