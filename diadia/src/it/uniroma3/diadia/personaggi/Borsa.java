package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Una classe che modella una borsa di attrezzi. La borsa può ospitare un numero
 * finito di attrezzi che non possono eccedere un certo peso.
 *
 * @author Paolo Merialdo
 * @see Attrezzo
 * @version 0.3
 *
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;
	private final Map<String, Attrezzo> attrezzi;

	

	/**
	 * Crea una borsa di capienza default
	 * 
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Crea una borsa di peso massimo passato per parametro
	 * 
	 * @param pesoMax il peso che la borsa può sostenere
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>(10);
	}

	/**
	 * Aggiunge alla borsa l'attrezzo passato per parametro
	 * 
	 * @param attrezzo attrezzo da aggiungere
	 * @return true se l'attrezzo è stato aggiunto, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean aggiunto = false;
		if (this.hasSpazio(attrezzo)) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			aggiunto = true;
		}
		return aggiunto;
	}

	/**
	 * Restituisce l'oggetto attrezzo cercato per corrispondenza sul nome
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da cercare
	 * @return l'attrezzo cercato se questo è presente, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per nome
	 * 
	 * @return lista ordinata
	 */
	public List<Attrezzo> getContenutoOrdinatoPerNome() {
		List<Attrezzo> attrezziOrdinati = new ArrayList<Attrezzo>();
		attrezziOrdinati.addAll(this.attrezzi.values());
		Comparator<Attrezzo> comp = new ComparatoreAttrezziPerNome();
		Collections.sort(attrezziOrdinati, comp);
		return attrezziOrdinati;
	}

	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi per nome a parità di peso
	 * 
	 * @return lista ordinata
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> attrezziOrdinati = new ArrayList<Attrezzo>();
		attrezziOrdinati.addAll(this.attrezzi.values());
		Collections.sort(attrezziOrdinati);
		return attrezziOrdinati;
	}

	/**
	 * Restituisce una mappa che associa ad un intero con un insieme (comunque
	 * non vuoto) di attrezzi: tutti gli attrezzi nell'insieme hanno lo stesso
	 * peso, ed è pari all'intero che figura come chiave nella mappa
	 * 
	 * @return mappa con attrezzi raggruppati per peso
	 */
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		Comparator<Attrezzo> comp = new ComparatoreAttrezziPerNome();
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if (mappa.containsKey(attrezzo.getPeso()))
				mappa.get(attrezzo.getPeso()).add(attrezzo);
			else {
				Set<Attrezzo> nuovoSet = new TreeSet<>(comp);
				nuovoSet.add(attrezzo);
				mappa.put(attrezzo.getPeso(), nuovoSet);
			}
		}
		return mappa;
	}

	/**
	 * Restituisce valore del peso attualmente contenuto dalla borsa
	 * 
	 * @return il valore del peso attualmente contenuto dalla borsa
	 */
	public int getPeso() {
		int pesoTotale = 0;
		for (Attrezzo a: this.attrezzi.values())
			pesoTotale += a.getPeso();
		return pesoTotale;
	}

	/**
	 * Controlla nella borsa la presenza dell'attrezzo passato per parametro
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da cercare
	 * @return true se l'attrezzo è presente, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return (this.getAttrezzo(nomeAttrezzo) != null);
	}

	/**
	 * Metodo per determinare se nella borsa c'è abbastanza spazio per
	 * aggiungere un oggetto passato per parametro
	 * 
	 * @param attrezzoDaAggiungere oggetto che si vuole affiungere
	 * @return true se c'è spazio per aggiungere l'oggetto, false altrimenti
	 */
	public boolean hasSpazio(Attrezzo attrezzoDaAggiungere) {
		return (this.getPesoMax() - this.getPeso() >= attrezzoDaAggiungere.getPeso()) && this.attrezzi.size() < 10;
	}

	/**
	 * Rimuove un attrezzo dalla borsa
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo che si vuole rimuovere
	 * @return attrezzo estratto dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	
	
	//METODI GET, SET E PREDEFINITI JAVA

	public int getPesoMax() {
		return this.pesoMax;
	}
	
	/**
	 * Restituisce una rappresentazione stringa della borsa
	 * 
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (this.attrezzi.size() != 0) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(this.getContenutoOrdinatoPerPeso().toString());
		} else s.append("Borsa vuota");
		return s.toString();
	}
}