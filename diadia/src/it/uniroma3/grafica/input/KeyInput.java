package it.uniroma3.grafica.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean take;
	public boolean bag;
	public boolean interfaccia;
	
	public KeyInput () {
		keys = new boolean[256];
	}
	
	public void tick () {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		take = keys[KeyEvent.VK_X];
		bag = keys[KeyEvent.VK_I];
		interfaccia = keys[KeyEvent.VK_F];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
