package it.uniroma3.diadia.personaggi;

/**
 * Una semplice classe che modella un giocatore. I giocatori si possono muovere
 * nel labirinto di un numero predefinito di passi uguale al numero di cfu
 * accumulati. Ogni giocatore ha dei cfu.
 *
 * @author Studenti
 * @version 0.3
 * @see Borsa
 *
 */
public class Giocatore {
	private final static int CFU_INIZIALI_DEFAULT = 20;
	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI_DEFAULT;
	}

	/**
	 * Decrementa di 1 il numero di cfu rimanenti
	 * 
	 */
	public void decrementaCfu() {
		this.cfu--;
	}

	/**
	 * Decrementa di n il numero di cfu rimanenti senza mai andare oltre il
	 * valore 0 cfu
	 * 
	 * @param n numero di cfu da sottrarre
	 */
	public void decrementaCfu(int n) {
		if (n > this.getCfu())
			this.setCfu(0);
		else this.setCfu(getCfu() - n);
	}

	/**
	 * Controlla che il giocatore abbia ancora cfu disponibili
	 * 
	 * @return true se il giocatore ha cfu, false altrimenti
	 */
	public boolean isVivo() {
		return this.getCfu() > 0;
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	public int getCfu() {
		return this.cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	@Override
	public String toString() {
		return "Il giocatore ha ancora: " + this.getCfu() + " CFU";
	}
}