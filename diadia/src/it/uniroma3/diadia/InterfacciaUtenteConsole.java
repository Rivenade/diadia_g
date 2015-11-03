package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Implementa InterfacciaUtente usando lo stadard input (System.in) e lo
 * standard output (System.out) per interagire con l'utente
 * 
 * @author Lorenzo
 *
 */
public class InterfacciaUtenteConsole implements InterfacciaUtente {
	/* Secondo me qui si potrebbe mettere una variabile Partita da inizializzare col costruttore.
	 * Fatto questo potremmo assegnare a questa classe la responsabilità di "processaIstruzione" della classe DiaDia
	 */
	private static final String MESSAGGIO_BENVENUTO = "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando \"aiuto\".";
	private Scanner scanner;

	/**
	 * Inizializza la classe e stampa a schermo il messaggio
	 * di benvenuto
	 * 
	 */
	public InterfacciaUtenteConsole(){
		this.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Permette di stampare a schermo un messaggio
	 * 
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	/**
	 * Si occupa di leggere le linee passate come input
	 * 
	 */
	@Override
	public String prendiIstruzione() {
		return scanner.nextLine();
	}
}
