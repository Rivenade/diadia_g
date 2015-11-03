package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Cerca di lasicare un oggetto tenuto in borsa nella stanza corrente
 * 
 * @author Lorenzo e Stefano
 * @see Attrezzo
 * @see Stanza
 * 
 */
public class ComandoPosa extends AbstractComando{

	/**
	 * Cerca di lasicare un oggetto tenuto in borsa nella stanza corrente. Se
	 * l'oggetto è presente il borsa lo posa, altrimenti stampa un messaggio di
	 * errore
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (this.getParametro() != null)
			if (stanzaCorrente.hasSpazio())
				if (partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
					stanzaCorrente.addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro()));
					msg = "Hai lasciato: " + this.getParametro();
				} else msg = "Non posso posare questo oggetto perche' non è presnete nella borsa";
			else msg = "Non posso posare questo oggetto perche' la stanza non può contentere altri oggetti";
		else msg = "Cosa vuoi posare? Devi specificare un parametro";
		return msg;
	}
}