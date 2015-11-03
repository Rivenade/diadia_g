package it.uniroma3.grafica.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.grafica.Bag;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.entity.Items;
import it.uniroma3.grafica.entity.creatures.Player;
import it.uniroma3.grafica.rooms.Room;
import it.uniroma3.grafica.utils.MyFont;


public class GameState extends State {

	
	private Player player;
	private Room room;
	private Bag bag;
	private int xPointer, yPointer;
	
	
	public GameState (Handler handler) {
		super (handler);
		room = new Room(handler, this.pathConstructor());
		handler.setRoom(room);
		bag = new Bag(handler);
		player = new Player(handler, room.getSpawnX(), room.getSpawnY());
	}
	
	/**
	 * 
	 * @return - il percorso dell'immagine per la stanza corrente
	 */
	private String pathConstructor () {
		StringBuilder path = new StringBuilder("/rooms/");
		path.append(handler.getGioco().partita.getStanzaCorrente().getNome()+".png");
		return path.toString();
	}
	
	/**
	 * aggiorna la stanza
	 */
	private void refreshRoom () {
		room = new Room(handler, this.pathConstructor());
		handler.setRoom(room);
	}
	
	private String parseDirection (float x, float y) {
		String s = null;
		if (y < 60) {
			s = "nord";
		}
		if (y > room.getHeight()*64 - 60) {
			s = "sud";
		}
		if (x < 60) {
			s = "ovest";
		}
		if (x > room.getWidth()*64 - 60) {
			s = "est";
		}
		return s;
	}
	
	@Override
	public void tick() {
		String dir = null;
		if (player.getCenterY() > room.getHeight()*64 - 60 ||
				player.getCenterY() < 60 ||
				player.getCenterX() > room.getWidth()*64 -60 ||
				player.getCenterX() < 60) {
			dir = parseDirection(player.getCenterX(), player.getCenterY());
			Stanza prossimaStanza = handler.getGioco().partita.getStanzaCorrente().getStanzaAdiacente(dir);
			if (prossimaStanza != null) {
				handler.getGioco().partita.setStanzaCorrente(prossimaStanza);
				handler.getGioco().partita.getGiocatore().decrementaCfu();
				
				refreshRoom();
				if (dir.equals("nord")) {
					player.setY(room.getHeight()*64 -100);
				}
				if (dir.equals("sud")) {
					player.setY(50);
				}
				if (dir.equals("ovest")) {
					player.setX(room.getWidth()*64 - 100);
				}
				if (dir.equals("est")) {
					player.setX(50);
				}
			}
		}
		
		//per ora lo tengo fuori per poter cambiare stanza anche da riga di comando
		if (this.room.getStanza().getNome() != handler.getGioco().partita.getStanzaCorrente().getNome()) {
			refreshRoom();
		}
		room.tick();
		player.tick();
		bag.tick();

		xPointer = handler.getMouseInput().xMov + (int) handler.getCamera().getxOffset();
		yPointer = handler.getMouseInput().yMov + (int) handler.getCamera().getyOffset();
	}

	@Override
	public void render(Graphics g) {
		room.render(g);
		player.render(g);
		
		try {
			renderInterface(g);
		} catch (FontFormatException | IOException e) {e.printStackTrace();}
	}
	
	/**
	 * Disegna sull'oggetto grafico, l'interfaccia (numero di CFU rimasti, borsa,
	 * etichetta degli attrezzi)
	 * @param g - oggetto grafico su cui "disegnare" l'interfaccia
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	private void renderInterface (Graphics g) throws FontFormatException, IOException {
		
		//CFU RIMASTI E NOME STANZA
		
		Font timesR;
		timesR = MyFont.timesR.deriveFont(40f);
		g.setFont(timesR);
		g.setColor(Color.WHITE);
		
		//modo un po' barbaro di rendere visibile la scritta su sfondi scuri 
		g.drawString(this.room.getStanza().getNome(), handler.getWidth()/2, 50 - 2);
		g.drawString(this.room.getStanza().getNome(), handler.getWidth()/2, 50 + 2);
		g.drawString(this.room.getStanza().getNome(), handler.getWidth()/2 + 2, 50);
		g.drawString(this.room.getStanza().getNome(), handler.getWidth()/2 - 2, 50);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("CFU: "+handler.getGioco().partita.getGiocatore().getCfu(), 10, 10+40);
		g.drawString(this.room.getStanza().getNome(), handler.getWidth()/2, 10+40);


		//BORSA
		if (bag.isOpen()) {
			bag.render(g);
		}
		
		List<Items> tmp = new ArrayList<>(this.room.getItem().values());
		for (Items it : this.bag.getItem().values()) {
			tmp.add(it);
		}
		
		//ETICHETTA DEGLI ATTREZZI
		for (Items it : tmp) {
			if (handler.getGameState().getXPointer()>(it.getX())&&
					handler.getGameState().getXPointer()<(it.getX() + it.getWidth()) &&
					handler.getGameState().getYPointer()>(it.getY()) &&
					handler.getGameState().getYPointer()<(it.getY() + it.getHeight())) {

				String str = " "+it.getAttrezzo().getNome();
				g.setColor(Color.DARK_GRAY);
				int strWidth = g.getFontMetrics().stringWidth(str);
				g.fillRect(handler.getMouseInput().xMov, handler.getGioco().getMouseInput().yMov, strWidth + 10, 40);

				g.setColor(Color.CYAN);
				g.drawString(str, handler.getMouseInput().xMov, handler.getGioco().getMouseInput().yMov + 28);

			}
		}
		
		//BARRA DELLA "SALUTE"
		
		
		
	}
	
	public Player getPlayer () {
		return this.player;
	}
	
	public int getXPointer () {
		return this.xPointer;
	}
	
	public int getYPointer () {
		return this.yPointer;
	}
	
	public Bag getBag ()  {
		return this.bag;
	}
	
	public Room getRoom () {
		return this.room;
	}

}
