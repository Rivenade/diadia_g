package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che si occupa di raccogliere un comando dalla stanza corrente per
 * riporlo nella borsa.
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * @see Attrezzo
 * @see Stanza
 * 
 */
public class ComandoPrendi extends AbstractComando{

	/**
	 * Cerca di raccogliere un oggetto nella stanza corrente per metterlo nella
	 * borsa. Se l'oggetto non esiste o la borsa è piena stampa un messaggio
	 * d'errore
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (this.getParametro() != null)
			if (stanzaCorrente.hasAttrezzo(this.getParametro())) {
				Attrezzo attrezzo = stanzaCorrente.getAttrezzo(this.getParametro());
				if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
					stanzaCorrente.removeAttrezzo(attrezzo);
					msg = "Hai raccolto: " + attrezzo.getNome();
				} else msg = "Non posso raccogliere questo oggetto perche' la borsa è piena";
			} else msg = "Non posso raccogliere questo oggetto perche' non esiste";
		else msg = "Cosa vuoi raccogliere? Devi indicare un parametro";
		return msg;
	}
}