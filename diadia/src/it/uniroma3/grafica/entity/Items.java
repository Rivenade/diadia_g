package it.uniroma3.grafica.entity;

import java.awt.Graphics;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;

public class Items extends Entity {
	
	public static final int DEFAULT_WIDTH = 64,
						DEFAULT_HEIGHT = 64;
	private Attrezzo attrezzo;


	public Items(Handler handler, float x, float y,  Attrezzo attrezzo) {
		super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, true, Id.attrezzo);
		this.attrezzo = attrezzo;
	}



	@Override
	public void tick() {

	}



	public void getInputInStanza() {
		if(handler.getMouseInput().pressed){
			/*se il player è vicino...*/
			if (this.isNear(handler.getGameState().getPlayer()) ){
				/*...e se il cursore è vicino */
				if(Math.sqrt(Math.pow(handler.getGameState().getXPointer() -(this.x+this.width/2), 2)+
						Math.pow(handler.getGameState().getYPointer() -(this.y+this.height/2), 2))<=64) {
					if(handler.getGioco().partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo)){
						handler.getGioco().partita.getStanzaCorrente().removeAttrezzo(this.attrezzo);
						this.attrezzo.setMoved();
					}
					
					handler.getMouseInput().pressed = false; //così aspetta il prossimo click

				}
			}
		}

	}
	
	public void getInputInBorsa () {
		if (handler.getMouseInput().pressed) {
			if(Math.sqrt(Math.pow(handler.getGameState().getXPointer() -(this.x+this.width/2), 2)+
					Math.pow(handler.getGameState().getYPointer() -(this.y+this.height/2), 2))<=32) {
				String tmp = attrezzo.getNome();
				handler.getGioco().partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				handler.getGioco().partita.getGiocatore().getBorsa().removeAttrezzo(tmp);
				this.attrezzo.setXY(handler.getGameState().getPlayer().getX(),
						handler.getGameState().getPlayer().getY());
				handler.getMouseInput().pressed = false; //così aspetta il prossimo click
			}			
		}
	}



	@Override
	public void render(Graphics g) {

		if (Assets.attrezzi.containsKey(attrezzo.getNome()))
			g.drawImage(Assets.attrezzi.get(attrezzo.getNome()).getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), 
					(int)(y - handler.getCamera().getyOffset()),64, 64, null);
		else{
			StringBuilder tmp = new StringBuilder(attrezzo.getNome());
			tmp.reverse();
			g.drawImage(Assets.attrezzi.get(tmp.toString()).getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), 
					(int)(y - handler.getCamera().getyOffset()),64, 64, null);
		}
	}



	public Attrezzo getAttrezzo() {
		return attrezzo;
	}
	
}
