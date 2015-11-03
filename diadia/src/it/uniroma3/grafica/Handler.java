package it.uniroma3.grafica;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.grafica.input.KeyInput;
import it.uniroma3.grafica.input.MouseInput;
import it.uniroma3.grafica.rooms.Room;
import it.uniroma3.grafica.states.GameState;

public class Handler {

	private DiaDia gioco;
	private Room room;
	
	public Handler (DiaDia gioco) {
		this.gioco = gioco;
	}

	
	
	//GETTERS SETTERS
	
	public int getWidth () {
		return gioco.getWidth();
	}
	
	public int getHeight () {
		return gioco.getHeight();
	}
	
	public KeyInput getKeyInput () {
		return gioco.getKeyInput();
	}
	
	public MouseInput getMouseInput ()  {
		return gioco.getMouseInput();
	}
	
	public Camera getCamera () {
		return gioco.getCamera();
	}
	
	public DiaDia getGioco() {
		return gioco;
	}

	public void setGioco(DiaDia gioco) {
		this.gioco = gioco;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public GameState getGameState () {
		return gioco.getGameState();
	}
	
	public Finestra getFinestra () {
		return gioco.getFinestra();
	}
	
	
}
