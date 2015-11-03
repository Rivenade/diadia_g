package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella una classe con un comportamento speciale: aggiungento
 * attrezzi si incrementa un contatore fino a una soglia prestabvilita. Quando
 * questa soglia viene superata il comportamento cambia e gli strumenti
 * depositati vengono automaticamente trasformati
 * 
 * @author Lorenzo e Stefano
 * @see Attrezzo
 * @version 0.3
 * 
 */
public class StanzaMagica extends Stanza {
	private final static int SOGLIA_MAGICA_DEFAULT = 1;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	/**
	 * Crea una stanza magica con una soglia minima (1)
	 * 
	 * @param nome
	 */
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/**
	 * 
	 * @param nome
	 * @param soglia
	 */
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	/**
	 * Aggiunge un attrezzo nella stanza. Se il contatore ha raggiunto la soglia
	 * l'attrezzo prima di essere posato viene trasformato
	 * 
	 * @param attrezzo attrezzo da depositare
	 * @return true se l'attrezzo è stato lasciato, false altrimenti
	 */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati >= this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	/**
	 * Modifica un attrezzo modificandone i parametri nome e peso. Il nome viene
	 * inverito, il peso raddoppiato
	 * 
	 * @param attrezzo attrezzo da trasformare
	 * @return nuovo attrezzo modificato
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		attrezzo.setPeso(attrezzo.getPeso()*2);
		attrezzo.setNome(nomeInvertito.reverse().toString());
		return attrezzo;
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(super.toString());
		s.append("\nLa stanza ha uno strano comportamento: sembra trasformare gli oggetti");
		return s.toString();
	}
}
