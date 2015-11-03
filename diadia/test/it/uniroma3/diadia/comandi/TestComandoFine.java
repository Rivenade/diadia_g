package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.Partita;

import org.junit.Test;

public class TestComandoFine {

	@Test
	public void test() {
		Partita partita = new Partita();
		AbstractComando comando = new ComandoFine();
		System.out.println("Deve stampare: \"Grazie di aver giocato!\"");
		comando.esegui(partita);
		System.out.println();
		assertTrue(partita.isFinita());
	}
}