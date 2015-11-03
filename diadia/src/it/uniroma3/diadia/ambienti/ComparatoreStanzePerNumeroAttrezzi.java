package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

/**
 * Classe per l'ordinamento esterno di oggetti stanza
 * 
 * @author Lorenzo e Stefano
 * @version 0.4
 * @see Stanza
 *
 */
public class ComparatoreStanzePerNumeroAttrezzi implements Comparator<Stanza> {

	/**
	 * Metodo che si occupa di ordinare due stanze passate come parametro per
	 * numero di attrezzi contenuti. A parità di attrezzi ordina per nome
	 * 
	 * @param s1 Prima stanza
	 * @param s2 Seconda stanza
	 * @return intero negativo se l'oggetto s1 ha meno attrezzi, intero
	 *         positivo altrimenti
	 */
	@Override
	public int compare(Stanza s1, Stanza s2) {
		int ordine = s1.getAttrezzi().size() - s2.getAttrezzi().size();
		if (ordine == 0)
			s1.getNome().compareTo(s2.getNome());
		return ordine;
	}
}
