package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Modella il comando per salutare un personaggio, se presente nella stanza
 * corrente
 * 
 * @author Lorenzo e Stefano
 * @see AbstractPersonaggio
 * @version 0.3
 * 
 */
public class ComandoSaluta extends AbstractComando{

	/**
	 * Controlla se c'è un personaggio nella stanza, se sì, lo saluta,
	 * altrimenti avverte che non c'è nessuno
	 * 
	 * @param partita partita sulla quale eseguire il comando
	 * 
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null)
			msg = personaggio.saluta();
		else msg = "Nessuno risponde";
		return msg;
	}
}