package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author Paolo Merialdo, Valter Crescenzi (da un'idea di Michael Kolling and
 *         David J. Barnes)
 * @see Stanza
 * @see Giocatore
 * @see Labirinto
 * @version 0.3
 * 
 */
public class Partita {
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;

	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getEntrata();
		this.finita = false;
		this.giocatore = new Giocatore();
	}

	/**
	 * Permette l'avvio del gioco creando una nuova partita: inizializza un
	 * labirinto e un giocatore di default
	 * 
	 */
	public Partita() {
		this(new Labirinto());
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return (this.finita || this.isVinta() || !this.giocatore.isVivo());
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return true se partita vinta, false altrimenti
	 */
	public boolean isVinta() {
		return this.getStanzaCorrente() == this.labirinto.getUscita();
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	public void setStanzaCorrente(Stanza nuovaStanza) {
		this.stanzaCorrente = nuovaStanza;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getEntrata();
	}

	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
}
