package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe identifica un comando non valido e notifica a schermo un
 * messaggio di errore
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * 
 */
public class ComandoNonValido extends AbstractComando{

	/**
	 * Avverte con una stampa su schermo che il comando invocato non è un
	 * comando valido
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		return "Il comando inserito non è valido";
	}
}
