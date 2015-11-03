package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Modella il comando per eseguire l' interazione con un personaggio nella
 * stanza corrente.
 * 
 * @author Lorenzo e Stefano
 * @version 0.4
 * @see AbstractPersonaggio
 * 
 */
public class ComandoInteragisci extends AbstractComando {

	/**
	 * Controlla se c'è un personaggio nella stanza attuale e, se presente,
	 * interagisce con esso. Altrimenti avverte con una stampa a schermo che non
	 * è presente nessuno
	 * 
	 * @param partita partita sulla quale eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null)
			msg = personaggio.agisci(partita);
		else msg = "In questa stanza non c'è nessuno";
		return msg;
	}
}
