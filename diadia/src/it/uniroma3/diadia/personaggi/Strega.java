package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.Collections;

/**
 * Classe che modella un personaggio Strega con dei poteri speciali: Se
 * interagiamo con una strega questa ci trasferisce in una stanza tra quelle
 * adiacenti. Siccome è permalosa: - se non l’abbiamo ancora salutata, ci
 * «trasferisce» nella stanza adiacente che contiene meno attrezzi - altrimenti
 * in quella che contiene più attrezzi
 *
 * @author Lorenzo e Stefano
 * @version 0.3
 * @see AbstractPersonaggio
 * @see Stanza
 *
 */
public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_GENEROSA = "Sei un bravo studente. Ti meriti una bella magia!";
	private static final String MESSAGGIO_PERMALOSA = "Come osi disturbarmi?! Ti colpirò con la mia magia nera!";
	private static final String PRESENTAZIONE = "Le streghe ballano\nI buchi neri sono sempre dietro l'angolo";
	private static final ComparatoreStanzePerNumeroAttrezzi cmp = new ComparatoreStanzePerNumeroAttrezzi();
	private boolean haAgito;

	/**
	 * Costruttore di default: Strega di nome "Morgana" con
	 * messaggio di presentazione semplice
	 * 
	 */
	public Strega() {
		this("Morgana", PRESENTAZIONE);
	}
	
	public Strega(String nome){
		this(nome, PRESENTAZIONE);
	}

	/**
	 * 
	 * @param nome
	 * @param presentazione
	 */
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
		this.haAgito = false;
	}

	/**
	 * Metodo per gestire l'interazione con la strega. Sceglie il messaggio da
	 * restituire e l'azione da compiere sulla partita passata per parametro
	 * 
	 * @param partita
	 * @return Stringa di risposta della strega
	 */
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (!this.haAgito) {
			if (super.haSalutato())
				msg = this.spostaGenerosa(partita);
			else msg = this.spostaPermalosa(partita);
			this.haAgito = true;
		} else msg = "Di nuovo tu? Ho esaurito i poteri";
		return msg;
	}

	/**
	 * Una strega riceve un regalo, che trattiene scoppiando a ridere
	 * 
	 * @param attrezzo attrezzo ceduto al personaggio
	 * @param partita
	 * @return messaggio di risata
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder msg = new StringBuilder("Muahahahaha! Sciocco!");
		if(attrezzo.getNome()=="chiave")
			msg.append(" E ora come uscirai di qui!?");
		return msg.toString();
	}

	/*
	 * Metodo che effettua la scelta della stanza con più attrezzi adiacente
	 * alla stanza corrente nella partita. Una volta trovata, vi sposta il
	 * giocatore e restituisce un messaggio
	 */
	private String spostaGenerosa(Partita partita) {
		Stanza nuova = Collections.max(partita.getStanzaCorrente().getStanzeAdiacenti(), cmp);
		partita.setStanzaCorrente(nuova);
		return MESSAGGIO_GENEROSA + ("\nSei stato spostato in " + nuova.getNome());
	}

	/*
	 * Metodo che effettua la scelta della stanza con meno attrezzi adiacente
	 * alla stanza corrente nella partita. Una volta trovata, vi sposta il
	 * giocatore e restituisce un messaggio
	 */
	private String spostaPermalosa(Partita partita) {
		Stanza nuova = Collections.min(partita.getStanzaCorrente().getStanzeAdiacenti(), cmp);
		partita.setStanzaCorrente(nuova);
		return MESSAGGIO_PERMALOSA + ("\nSei stato spostato in " + nuova.getNome());
	}
}
