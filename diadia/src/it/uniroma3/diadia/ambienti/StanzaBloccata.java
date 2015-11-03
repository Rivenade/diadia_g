package it.uniroma3.diadia.ambienti;

/**
 * Classe che modella una stanza con un comportamento speciale: una delle sue
 * direzioni non è accessibile a meno che non sia presente in essa un oggetto
 * speciale
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 *
 */
public class StanzaBloccata extends Stanza {
	private String chiave;
	private String direzioneBloccata;

	/**
	 * Costruttore della stanza bloccata
	 * 
	 * @param nome nome della stanza
	 * @param chiave nome dell'oggetto che apre le porte
	 * @param direzioneBloccata direzione inaccessibile senza chiave
	 */
	public StanzaBloccata(String nome, String chiave, String direzioneBloccata) {
		super(nome);
		this.chiave = chiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	/**
	 * Permette di ottenere la stanza adiacente nella direzione passata per
	 * parametro. Se la direzione è bloccata ritorna sé stessa
	 * 
	 * @param direzione direzione della stanza adiacente
	 * @return la stanza adiacente nella direzione corrispondente se questa non
	 *         è bloccata, sé stessa altrimenti
	 */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza s = this;
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.chiave))
			System.out.println("Non posso proseguire. Questa direzione è bloccata");
		else s = super.getStanzaAdiacente(direzione);
		return s;
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(super.toString());
		if (!this.hasAttrezzo(this.chiave))
			s.append("\nAttenzione: la direzione " + this.direzioneBloccata
					+ " è bloccata.\nHai bisogno di un attrezzo speciale per passare");
		return s.toString();
	}
}