package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che si occupa di stampare a video, la descrizione della stanza, il
 * contenuto della borsa, i cfu rimasti.
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * 
 */
public class ComandoGuarda extends AbstractComando{

	/**
	 * Stampa la descrizione della stanza, il contenuto della borsa, i cfu
	 * rimanenti a seconda del parametro dell' istanza
	 * 
	 * @param partita partita su cui eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		if (this.getParametro() != null) {
			if (this.getParametro().equals("borsa"))
				msg = partita.getGiocatore().getBorsa().toString();
			else if (this.getParametro().equals("stanza"))
				msg = partita.getStanzaCorrente().toString();
			else if (this.getParametro().equals("cfu"))
				msg = "Ti rimangono " + partita.getGiocatore().getCfu() + " cfu";
			else msg = "\"" + this.getParametro() + "\" non è un parametro valido";
		} else msg = "Cosa vuoi guardare? Specificare un parametro!";
		return msg;
	}
}