package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J.
 *         Barnes)
 * @see Attrezzo
 * @version 0.3
 * 
 */
public class Stanza {
	protected/* private */static final int NUMERO_MASSIMO_ATTREZZI = 10;
	private static final int NUMERO_MASSIMO_DIREZIONI = 4;
	private String nome;
	protected/* private */final Map<String, Attrezzo> attrezzi;
	private final Map<String, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * 
	 * @param nome
	 * @param personaggio
	 */
	public Stanza(String nome, AbstractPersonaggio personaggio) {
		this.stanzeAdiacenti = new HashMap<>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<>(NUMERO_MASSIMO_ATTREZZI);
		this.nome = nome;
		this.personaggio = personaggio;
	}
	
	public Stanza(String nome) {
		this(nome, null);
	}
	

	/**
	 * Mette un attrezzo nella stanza se il posto è occupato prova a metterlo accanto
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.hasSpazio()) {	
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
		}
		return this.hasSpazio();
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da cercare
	 * @return l'attrezzo presente nella stanza, null se l'attrezzo non e'
	 *         presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione dove si vuole andare
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione per raggiungere la stanza adiacente.
	 * @param stanzaAdiacente stanza nella direzione indicata.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
		this.stanzeAdiacenti.put(direzione, stanzaAdiacente);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param attrezzo nome dell'attrezzod a rimuovere
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}

	/**
	 * Genera la collezione di stanze adiacenti alla stanza su cui viene
	 * invocato
	 * 
	 * @return collezione stanze adiacenti
	 */
	public Collection<Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti.values();
	}

	/**
	 * Si occupa di verificare se nella stanza c'è abbastanza spazio per
	 * aggiungere uno strumento
	 * 
	 * @return true se c'è uno spazio per aggiungere un attrezzo, false
	 *         altrimenti
	 */
	public boolean hasSpazio() {
		return this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI;
	}

	/**
	 * Verifica se nella stanza è presente un personaggio
	 * 
	 * @return true se il personaggio è presente, false altrimenti
	 */
	public boolean isPopolata() {
		return this.getPersonaggio() != null;
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	/**
	 * Ritorna la collezione contenente le possibili direzioni raggiungibili
	 * dalla stanza su cui viene invocato
	 * 
	 * @return collezione di direzioni raggiungibili
	 */
	public Collection<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	public List<Attrezzo> getAttrezzi() {
		return new ArrayList<Attrezzo>(this.attrezzi.values());
	}
	
	public String getNome() {
		return this.nome;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	@Override
	public boolean equals(Object obj) {
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, contenente la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getNome() + "\n" + "Uscite: " + this.getDirezioni().toString());
		s.append("\nAttrezzi nella stanza: " + this.getAttrezzi().toString() + " ");
		if (isPopolata())
			s.append("\nHai incontrato " + this.getPersonaggio().toString());
		return s.toString();
	}
}