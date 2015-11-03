package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che si occpua di gestire il tipo astratto Personaggio Un personaggio
 * partecipa alla partita con due azioni: - Saluta: da una breve descrizione di
 * sé stesso - Interagisci: compie un'azione. Cambia a seconda del personaggio
 * 
 * @author Lorenzo e Stefano
 * @version 0.4
 *
 */
public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	/**
	 * Si occupa di generare il saluto dei vari personaggi che estendono la
	 * classe
	 * 
	 * @return Stringa di saluto
	 */
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono " + this.getNome() + ".");
		if (!this.haSalutato)
			risposta.append("\n" + this.presentazione);
		else risposta.append("\nCi siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}

	/**
	 * Metodo che si occupa di eseguire l'azione del personaggio e di restituire
	 * un messaggio stringa che la spieghi
	 * 
	 * @param partita
	 * @return Stringa che spiega l'azione del personaggio
	 */
	abstract public String agisci(Partita partita);
	
	/**
	 * Metodo che si occupa di accettare il regalo e di compiere un'azione
	 * (diversa a seconda del pesonaggio)
	 * 
	 * @param attrezzo attrezzo in dono
	 * @param partita
	 * @return messaggio del personaggio che ha ricevuto il dono
	 */
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	
	
	//METODI GET, SET E PREDEFINITI JAVA
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
