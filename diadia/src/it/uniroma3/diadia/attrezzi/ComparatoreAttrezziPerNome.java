package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

/**
 * Classe per l'ordinamento esterno di oggetti attrezzo
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * @see Attrezzo
 *
 */
public class ComparatoreAttrezziPerNome implements Comparator<Attrezzo> {

	/**
	 * Metodo che si occupa di ordinare due attrezzi passati come parametro
	 * prima per per nome, poi per peso
	 * 
	 * @param a1 Primo attrezzo
	 * @param a2 Secondo attrezzo
	 * @return intero negativo se l'oggetto a1 viene prima dell'oggetto a2, 0 se
	 *         sono nella stessa posizione, intero positivo altrimenti
	 */
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int ordine = a1.getNome().compareTo(a2.getNome());
		if (ordine == 0)
			ordine = a1.getPeso() - a2.getPeso();
		return ordine;
	}
}