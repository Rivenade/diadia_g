package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe attraverso la quale il giocatore pu� regalare un attrezzo al
 * personaggio presente nella stanza Il parametro del comando regala deve essere
 * il nome di uno degli attrezzi presenti nella borsa perch� possa essere
 * effettivamente regalato
 * 
 * @author Lorenzo e Stefano
 * @version 0.4
 * 
 */
public class ComandoRegala extends AbstractComando {

	/**
	 * Metodo che preleva un oggetto dalla borsa e lo regala al personaggio
	 * presente nella stanza Se l'oggetto non � presente in borsa stampa un
	 * messaggio di avvertimento
	 * 
	 * @param partita
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		Attrezzo dono = partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
		if (dono != null)
			msg = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(dono, partita);
		else msg = "Non puoi regalare l'oggetto perch� non lo possiedi";
		return msg;
	}
}
