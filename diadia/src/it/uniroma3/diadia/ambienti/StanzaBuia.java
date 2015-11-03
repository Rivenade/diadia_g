package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella una stanza con un comportamento speciale: a meno che non
 * sia presente un oggetto speciale, questa non permette la stampa della sua
 * descrizione
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 */
public class StanzaBuia extends Stanza {
	private String attrezzoIlluminante;
	private final String ATTREZZO_DEFAULT = "lanterna";

	/**
	 * Costruttore di una nuova stanza buia
	 * 
	 * @param nome nome della stanza
	 * @param attrezzoSpeciale nome dell'attrezzo che illumina la stanza
	 */
	public StanzaBuia(String nome, String attrezzoSpeciale) {
		super(nome);
		this.attrezzoIlluminante = attrezzoSpeciale;
	}

	public StanzaBuia (String nome){
		super(nome);
		this.attrezzoIlluminante = this.ATTREZZO_DEFAULT;
	}
	/**
	 * Permette di aggiungere un attrezzo passato per parametro alla stanza
	 * 
	 * @param attrezzo Oggetto attrezzo da aggiungere
	 * @return true se l'attrezzo è stato aggiunto, false altrimenti
	 */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean aggiunto = super.addAttrezzo(attrezzo);
		if (attrezzo.getNome() == this.attrezzoIlluminante)
			System.out.println(this.toString());
		return aggiunto;
	}
	
	public String getNomeAttrezzoIlluminante () {
		return this.attrezzoIlluminante;
	}
	
	//METODI GET, SET E PREDEFINITI JAVA

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (this.hasAttrezzo(attrezzoIlluminante))
			s.append(super.toString());
		else s.append("qui c'è un buio pesto");
		return s.toString();
	}
}