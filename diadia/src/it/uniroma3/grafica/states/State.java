package it.uniroma3.grafica.states;

import it.uniroma3.grafica.Handler;

import java.awt.Graphics;
/**
 * Classe astratta che implementa lo stato generico della "schermata di gioco" (i.e. il menu, lo status del gioco ecc...)
 * @author Stefano
 * @see 
 *
 */
public abstract class State {

	private static State currentState = null;
	
	public static void setState (State state) {
		currentState = state;
	}
	
	public static State getState () {
		return currentState;
	}
	
	protected Handler handler;
	
	public State (Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick ();
	
	public abstract void render(Graphics g);
	
}
