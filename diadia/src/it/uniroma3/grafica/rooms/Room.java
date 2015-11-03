package it.uniroma3.grafica.rooms;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;
import it.uniroma3.grafica.entity.Items;
import it.uniroma3.grafica.entity.creatures.NPC;
import it.uniroma3.grafica.tile.Tile;
import it.uniroma3.grafica.utils.Utils;

public class Room {


	private Handler handler;
	private int width, height;
	private Id[][] tiles;
	private int spawnX, spawnY;
	private Stanza stanza;
	private Map<String, Items> item;
	private NPC npc;
	
	public Room (Handler handler, String path) {
		this.handler = handler;
		this.stanza = handler.getGioco().partita.getStanzaCorrente();
	
		loadRoom(path);

	}
	/**
	 * Carica la stanza usando un file <i>immagine</i> salvato in memoria e 
	 * utilizzando i metodi di suppoprto della classe it.uniroma3.grafica.utils.Utils:
	 * i pixel dell'immagine vengono analizzati in base al colore, che corrispode ad un valore
	 * dell'enum Id nel progetto, esplicativo (speriamo) della sua natura, e vengono memorizzati
	 * in un array bidimensionale.
	 * 
	 * @param path - il percorso del file nel progetto
	 * @see Utils
	 */
	private void loadRoom (String path){
		
/*
 * Nel caso di una loadRoom da file di testo, possiamo usare il seguente codice
 * 		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int [width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles [x][y] = Utils.parseInt(tokens[(x+y*width)]);
			}
		}
 */
		
		Color[][] file = Utils.loadFileAsImage(path);
		width = Utils.width;
		height = Utils.height;
		
		tiles = new Id [Utils.width][Utils.height];
		int count = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				//conto e posiziono gli attrezzi (non avendo un colore per ogni
				//attrezzo, li aggiungo nell'ordine dato
				if (Utils.parseId(file[x][y])==Id.attrezzo && 
						!stanza.getAttrezzi().isEmpty() &&
						stanza.getAttrezzi().size()!=count &&
						!stanza.getAttrezzi().get(count).isBeenMoved()) {
					stanza.getAttrezzi().get(count).setXY(x*64, y*64);
					count ++;
				} else tiles [x][y] = Utils.parseId(file[x][y]);
				
				//imposto lo spawn del player nella casella blu
				if (Utils.parseId(file[x][y])==Id.player){
					spawnX = x*64;
					spawnY = y*64;
				}
				
				//colloco personaggio, se presente
				if ((Utils.parseId(file[x][y]) == Id.NPC)) {

					try {
						
						//cos� verr� instanziato l' NPC giusto
						Class<?> classeNPC = Class.forName("it.uniroma3.grafica.entity.creatures."+this.stanza.getPersonaggio().getNome());

						Class<?>[] formalArgs = {Handler.class, float.class, float.class, AbstractPersonaggio.class};
						Object[] initargs = {handler, x*64, y*64, this.stanza.getPersonaggio()};
						npc = (NPC) classeNPC.getConstructor(formalArgs).newInstance(initargs);

					} catch (InstantiationException | IllegalAccessException
							| IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException | ClassNotFoundException e) {
						e.printStackTrace();
					}

				}

			}
		}
		
	}
	
	public void tick () {
		item = new HashMap<String, Items>();
		for (Attrezzo a : stanza.getAttrezzi()) {
			Items it = new Items (handler, a.getX(), a.getY(), a);
			item.put(a.getNome(), it);
			it.getInputInStanza();
		}
		
		
		if (npc != null) {
			npc.tick();
		}
	}
	
	public void render (Graphics g)  {

		//in questo modo, renderizzo solo ci� che � visibile;
		//col classico for, renderizzerei tutte le tile del livello
		
		int xStart = (int)Math.max(0, handler.getCamera().getxOffset() / Tile.DEFAULT_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset()+ handler.getWidth())/Tile.DEFAULT_WIDTH + 1);
		int yStart = (int)Math.max(0, handler.getCamera().getyOffset() / Tile.DEFAULT_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset()+ handler.getHeight())/Tile.DEFAULT_HEIGHT + 1);
		
		//se la stanza � buia, renderizzo solo il personaggio
		boolean isBuia = false;
		try {
			if (this.stanza.getClass().getName().endsWith("Buia") &&
					!this.stanza.hasAttrezzo((String) this.stanza.getClass().getMethod("getNomeAttrezzoIlluminante").invoke(this.stanza))){
						isBuia = true;
			}


			for (int y = yStart; y < yEnd; y++) {
				for (int x = xStart; x < xEnd; x++){

					if (getTile(x, y).getId() == Id.hDoor ||
							getTile(x, y).getId() == Id.vDoor) {
						Tile.ground.render(g, (int) (x * Tile.DEFAULT_WIDTH - handler.getCamera().getxOffset()), //ho mattonelle anche sotto le porte!
								(int)(y * Tile.DEFAULT_HEIGHT - handler.getCamera().getyOffset()));


					}
					getTile(x, y).render(g, (int) (x * Tile.DEFAULT_WIDTH - handler.getCamera().getxOffset()),
							(int) (y * Tile.DEFAULT_HEIGHT - handler.getCamera().getyOffset()));

					if (isBuia) {
						g.drawImage(Assets.oscurita, (int) (x * Tile.DEFAULT_WIDTH - handler.getCamera().getxOffset()), 
								(int)(y * Tile.DEFAULT_HEIGHT - handler.getCamera().getyOffset()), 64, 64, null);
					}
				}
			}
			


		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//temporaneamente renderizzo anche tutti gli attrezzi, da migliorare,
		//credo si possa fare qualcosa di simile al codice pi� sopra per le tile
		/* se la stanza � buia, non renderizzo gli oggetti*/
		if (!isBuia){
			for (Items it : item.values()) {
				it.render(g);
			}
		}
		
		if (this.npc != null) {
			npc.render(g);
		}
		
	}
	
	public Tile getTile (int x, int y) {
		if (x < 0 || y < 0 || x > width || y > height)
			return Tile.ground;
		
		Tile t = Tile.tiles.get(tiles[x][y]);
		if (t == null) {
			return Tile.ground;
		}
		return t;
	}
	
	//GETTERS
	
	public int getSpawnX () {
		return this.spawnX;
	}
	
	public int getSpawnY () {
		return this.spawnY;
	}
	
	public Stanza getStanza() {
		return this.stanza;
	}
	
	public Map<String, Items> getItem() {
		return item;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
	
	
}
