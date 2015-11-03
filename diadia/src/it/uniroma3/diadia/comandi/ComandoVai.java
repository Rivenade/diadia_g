package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * AbstractComando che si occupa dello spostamento del giocatore nel labirinto
 * 
 * @see Stanza
 * @author Paolo Merialdo
 * @version 0.3
 * 
 */
public class ComandoVai extends AbstractComando{

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa
	 * il nome, altrimenti stampa un messaggio di errore
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		if (this.getParametro() != null) {
			Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.getParametro());
			if (prossimaStanza != null) {
				partita.setStanzaCorrente(prossimaStanza);
				partita.getGiocatore().decrementaCfu();
				msg = prossimaStanza + "\n" + partita.getGiocatore();
			} else msg = "Direzione inesistente";
		} else msg = "Dove vuoi andare? Devi specificare una direzione";
		return msg;
	}
}