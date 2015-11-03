package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella un personaggio Mago: un mago possiede un attrezzo che può
 * decidere di donare al personaggio della partita a seconda che questo lo abbia
 * salutato o meno
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * @see AbstractPersonaggio
 * @see Attrezzo
 * 
 */
public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "\ncon una mia magica azione, troverai un nuovo oggetto per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String PRESENTAZIONE = "";
	private Attrezzo attrezzo;

	/**
	 * Costruttore di default: crea un mago di nome "Merlino" con
	 * messaggio di presentazione semplice
	 * 
	 * @param attrezzo
	 */
	public Mago(Attrezzo attrezzo) {
		this("Merlino", "Il mio nome è Merlino. Ho un attrezzo molto carino", attrezzo);
	}
	
	public Mago() {
		super("carlo", PRESENTAZIONE);
		this.attrezzo = null;
	}
	
	public Mago(String nome, Attrezzo attrezzo){
		this(nome, PRESENTAZIONE, attrezzo);
	}

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	/**
	 * Metodo che permette l'interazione con un personaggio mago. Si occupa di
	 * stabilire il messaggio da restituire e l'azione da compiere sulla partita
	 * corrente
	 * 
	 * @param partita partita corrente
	 */
	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_SCUSE;
		if (attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		return msg;
	}

	/**
	 * Un mago riceve un regalo, gli dimezza il peso e lo lascia cadere nella
	 * stanza
	 * 
	 * @param attrezzo attrezzo ceduto al personaggio
	 * @param partita
	 * @return messaggio del personaggio
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo != null){
			Attrezzo nuovoAttrezzo = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
			partita.getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
		}
		return "Bibidi-bobidi-bu!";
	}



	//METODI GET, SET E PREDEFINITI JAVA

	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}
}