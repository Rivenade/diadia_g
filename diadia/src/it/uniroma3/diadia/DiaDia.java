package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Camera;
import it.uniroma3.grafica.Finestra;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.input.KeyInput;
import it.uniroma3.grafica.input.MouseInput;
import it.uniroma3.grafica.sprite.SpriteSheet;
import it.uniroma3.grafica.states.BackTrack;
import it.uniroma3.grafica.states.GameState;
import it.uniroma3.grafica.states.StartingMenu;
import it.uniroma3.grafica.states.State;
import it.uniroma3.grafica.utils.MyFont;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca Questa
 * e' la classe principale crea e istanzia tutte le altre
 *
 * @author Paolo Merialdo (da un'idea di Michael Kolling and David J. Barnes)
 * @version 0.3
 * 
 */
public class DiaDia implements Runnable {
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*9;
	public static final int SCALE = 4; 
	
	public Partita partita;
	public InterfacciaUtente interfaccia;
	private Finestra finestra;
	private Thread thread;

	
	private int width, height;
	public String titolo;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	
	private boolean running = false;
	
	private Camera cam;
	private MouseInput mouseInput;
	private KeyInput keyInput;
	
	public static SpriteSheet sheet;
	private State gameState;
	private State menu;
	
	public DiaDia(String titolo, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.titolo = titolo;
		keyInput = new KeyInput();
		mouseInput = new MouseInput();
		
		this.partita = new Partita();
		this.interfaccia = new InterfacciaUtenteConsole();

	}

	/**
	 * Permette l'evoluzione del gioco
	 * 
	 */
	public void gioca() {
		this.interfaccia.mostraMessaggio("Il gioco � iniziato!\nTi trovi in: " + this.partita.getStanzaCorrente().toString());
		do this.processaIstruzione(this.interfaccia.prendiIstruzione());
		while (!this.partita.isFinita());
		this.checkPartita();
	}

	private void processaIstruzione(String istruzione) {
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		AbstractComando comandoDaEseguire = factory.costruisciComando(istruzione);
		this.interfaccia.mostraMessaggio(comandoDaEseguire.esegui(this.partita));
	}
	
	/**
	 * Si occupa di controllare come finisce la partita
	 * e di stampare a schermo un messaggio corrispondente
	 * 
	 */
	private void checkPartita(){
		if (this.partita.isVinta())
			this.interfaccia.mostraMessaggio("Hai vinto!");
		else if (!this.partita.getGiocatore().isVivo())
			this.interfaccia.mostraMessaggio("Hai esaurito i CFU...");
	}

	
	//METODI PER IMPLEMENTAZIONE GRAFICA
	private void init() {

		finestra = new Finestra(titolo, width, height);
		finestra.getFrame().addKeyListener(keyInput);
		
		finestra.getCanvas().addMouseListener(mouseInput);				
		finestra.getCanvas().addMouseMotionListener(mouseInput);		
		Assets.init();
		MyFont.timesRoman();
		
		cam = new Camera(this, 0, 0);
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menu = new StartingMenu(handler);
		State.setState(gameState);
	}
	
	
	
	@Override
	public void run() {
		init();
		
		/*porto gli fps ad una frequenza normalizzata (60 in questo caso) */
		int fps = 60;
		double timePerTick = 1000000000 / fps; //nanosecondi in un secondo / fps
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while (running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick; 
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) { //delta vale 1 se se � passato il "tempo di un frame"
				tick();
				render();
				delta--;
			}
			if (timer >= 1000000000) {
//				System.out.println(handler.getGameState().getPlayer().getCenterX() + " " +handler.getGameState().getPlayer().getCenterY());
//				System.out.println(getGameState().getRoom().getHeight()*64 - 60);
				timer = 0;
			}
		}
		stop();
	}
	
	
	//START E STOP
	/**
	 * avvia il thread
	 */
	public synchronized void start () {
		if (running) return;
		running = true;
		thread = new Thread (this, "diadia_daemon");
		thread.start();
	}
	
	/**
	 * ferma il thread
	 */
	public synchronized void stop () {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//TICK E RENDER
	/**
	 * aggiorna le variabili del gioco prima del rendering
	 */
	private void tick() {
		keyInput.tick();
		mouseInput.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	/**
	 * renderizza l'oggetto grafico g (richiama il metodo <i>void render(Graphics g)</i> dello <i>State</i> attuale)
	 * @see State
	 * @see Graphics
	 */
	public void render () {
		bs = finestra.getCanvas().getBufferStrategy();
		if (bs == null) {
			finestra.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		//Draw here
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Draw
		
		if (State.getState().getClass()== StartingMenu.class) menu.render(g);
		
		g.dispose();
		bs.show();
	}
	
	//GETTERS
	public KeyInput getKeyInput () {
		return this.keyInput;
	}
	
	public MouseInput getMouseInput () {
		return this.mouseInput;
	}
	
	public Camera getCamera () {
		return this.cam;
	}

	public int getWidth () {
		return this.width;
	}
	
	public int getHeight () {
		return this.height;
	}
	
	public GameState getGameState () {
		return (GameState) this.gameState;
	}
	
	public Finestra getFinestra () {
		return this.finestra;
	}
	
	
	public static void main (String [] args) {
		DiaDia gioco = new DiaDia("DiaDia PlUs!", WIDTH*SCALE, HEIGHT*SCALE);
		gioco.start();
		BackTrack.sound();
		gioco.gioca();
	}
}