package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe si occupa di stampare a schermo i possibili comandi da eseguire
 * durante il gioco o i parametri con cui questi possono essere combinati.
 * 
 * @author Paolo Merialdo
 * @version 0.3
 *
 */
public class ComandoAiuto extends AbstractComando{
	private final String[] elencoComandi = { "vai", "fine", "prendi", "posa",
			"guarda", "interagisci", "saluta", "regala",
			"\nUna combinazione di \"aiuto\" con i precedenti parametri" };
	private final String[] elencoComandiGuarda = { "borsa", "stanza", "cfu" };

	/**
	 * Stampa informazioni di aiuto decidendo in base al parametro dell'istanza,
	 * quale è più opportuna
	 * 
	 * @param partita
	 */
	@Override
	public String esegui(Partita partita) {
		String msg;
		if (this.getParametro() == null)
			msg = this.elenco2string(this.elencoComandi);
		else if (this.getParametro().equals("vai"))
			msg = partita.getStanzaCorrente().getDirezioni().toString();
		else if (this.getParametro().equals("prendi"))
			msg = "E' possibile prendere uno qualunque degli oggetti presenti nella stanza."
					+ "\nPer conoscerli esegui \"guarda stanza\"";
		else if (this.getParametro().equals("posa"))
			msg = "E' possibile posare uno qualunque degli oggetti presenti nella borsa."
					+ "\nPer conoscerli esegui \"guarda borsa\"";
		else if (this.getParametro().equals("guarda"))
			msg = this.elenco2string(this.elencoComandiGuarda);
		else msg = "Parametro non valido";
		return msg;
	}

	/**
	 * Genera la stringa contenente i possibili comandi a partire dall'array che
	 * li contiene
	 * 
	 * @param elencoComandi array di comandi da cui formare la stringa
	 * @return Stringa dei possibili comandi
	 */
	private String elenco2string(String[] elencoComandi) {
		StringBuilder parametroCascata = new StringBuilder();
		for (int i = 0; i < elencoComandi.length; i++)
			parametroCascata.append(elencoComandi[i] + " ");
		return parametroCascata.toString();
	}
}