package it.uniroma3.diadia.comandi;

/**
 * Questa interfaccia modella quelle classi che si occupano di selezionare il
 * comando giusto in base all'istruzione ricevuta per parametro.
 *
 * @author Lorenzo e Stefano
 * @version 0.3
 * 
 */
public interface FabbricaDiComandi {

	/**
	 * Costruisce il comando appropriato per essere eseguito
	 * 
	 * @param istruzione comando in forma di stringa
	 * @return il comando che può eseguire l'istruzione
	 */
	public AbstractComando costruisciComando(String istruzione);
}
