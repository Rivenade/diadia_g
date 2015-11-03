package it.uniroma3.grafica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Borsa;
import it.uniroma3.grafica.entity.Items;
import it.uniroma3.grafica.utils.MyFont;

public class Bag {

	private Borsa borsa;
	private Handler handler;
	private boolean open = false;
	private int x, y;
	boolean alreadyPressed = false;
	private Map<String, Items> item;
	
	public Bag (Handler handler) {
		this.handler = handler;
		borsa = handler.getGioco().partita.getGiocatore().getBorsa();
	}
	
	public boolean isOpen () {
		return open;
	}
	
	public void tick () {
		if (!alreadyPressed)
			getInput();
		if (!handler.getKeyInput().bag)
			alreadyPressed = false;
		
		item = new HashMap<String, Items>();
		for (int i = 0; i < borsa.getContenutoOrdinatoPerNome().size(); i++) {
			Attrezzo att = borsa.getContenutoOrdinatoPerNome().get(i);
			att.setXY(i*64 + handler.getCamera().getxOffset()+x, handler.getCamera().getyOffset()+y);
			Items it = new Items(handler, att.getX(), att.getY(), att);
			item.put(att.getNome(), it);
			it.getInputInBorsa();
		}
	}
	
	public void getInput () {
		if(handler.getKeyInput().bag){
			open = !open;
			alreadyPressed = true;
		} 
	}

	public void render(Graphics g) {
		
			this.x = handler.getWidth() - Assets.bag.getWidth()*2 - 10;
			this.y = handler.getHeight() - Assets.bag.getHeight()*2 - 10;
			
			
			g.drawImage(Assets.bag, x, y, Assets.bag.getWidth()*2, Assets.bag.getHeight()*2, null);	
			g.setColor(Color.BLACK);
			g.fillRect(x, y- 10 - 22, Assets.bag.getWidth()*2, 25);
			g.setColor(Color.CYAN);

			g.setFont(MyFont.timesR.deriveFont(20f));
			g.drawString(costruisciInfoBorsa(), x, y - 10);
			g.setFont(MyFont.timesR.deriveFont(40f));
			for (Items it : item.values()) {
				it.render(g);
			}

	}
	
	/**
	 * Costruisce la stringa di descrizione della borsa (nome: peso/capienza kg)
	 * @return - la stringa descrittiva
	 */
	private String costruisciInfoBorsa () {
		StringBuilder info = new StringBuilder ("La bella borsa di Bella: ");
		info.append(handler.getGioco().partita.getGiocatore().getBorsa().getPeso()+"/"+
					handler.getGioco().partita.getGiocatore().getBorsa().getPesoMax()+" kg");
		return info.toString();
	}	
	
	public int getX() {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}

	public Map<String, Items> getItem() {
		return item;
	}
	
	
}
