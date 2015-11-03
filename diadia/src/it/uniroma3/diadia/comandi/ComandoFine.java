package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando che si occupa di terminare la partita
 * 
 * @author prof
 * @version 0.3
 */
public class ComandoFine extends AbstractComando {

	/**
	 * Stampa un messaggio e termina la partita.
	 * 
	 * @param partita partita da terminare
	 */
	@Override
	public String esegui(Partita partita) {
		partita.setFinita();
		return "Grazie di aver giocato!";
	}
}