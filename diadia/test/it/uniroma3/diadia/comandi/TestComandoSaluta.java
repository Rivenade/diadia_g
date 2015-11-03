package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

import org.junit.Before;
import org.junit.Test;

public class TestComandoSaluta {
	private AbstractComando comando;
	private Partita partita;
	private AbstractPersonaggio pers;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.comando = new ComandoSaluta();
		this.pers = new FakePersonaggio();
	}

	@Test
	public void testSaluta() {
		this.partita.getStanzaCorrente().setPersonaggio(this.pers);
		this.comando.esegui(this.partita);
		assertTrue(this.pers.haSalutato());
		System.out.println("\n");
	}

	@Test
	public void testSalutaNessuno() {
		System.out.println("Deve stampare: \"Nessno risponde\"");
		this.comando.esegui(this.partita);
		System.out.println("\n");
	}
}
