package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa interfaccia modella i comandi. Un comando consiste al piu' di due
 * parole: il nome del comando ed un parametro su cui si applica il comando. (Ad
 * es. alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author Paolo Merialdo (da un'idea di Michael Kolling and David J. Barnes)
 * @version 0.3
 * 
 */
public interface Comando {

	/**
	 * Esegue il comando per far evolvere la partita
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	public String esegui(Partita partita);
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA
	
	public String getParametro();
	
	public void setParametro(String parametro);

	@Override
	public String toString();
}
