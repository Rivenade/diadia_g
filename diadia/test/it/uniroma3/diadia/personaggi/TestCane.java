package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;

import org.junit.Before;
import org.junit.Test;

public class TestCane {
	private static final String DESIDERIO = "osso di gomma";
	private final static String MESSAGGIO_MANSUETO = "Il cane scodinzola felice mentre gioca col suo"
			+ " osso di gomma\nNon si è accorto di te. Puoi proseguire";
	private Cane cane;
	private Partita partita;

	@Before
	public void setUp() {
		this.cane = new Cane("Pluto", "Bel cane", DESIDERIO, new Attrezzo("regalo", 2));
		this.partita = new Partita();
		this.partita.getGiocatore().setCfu(10);
	}

	@Test
	public void testAgisciArrabbiato() {
		this.cane.agisci(partita);
		assertEquals(9, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testAgisciMansueto() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo(DESIDERIO, 1));
		assertEquals(MESSAGGIO_MANSUETO, this.cane.agisci(partita));
	}

	@Test
	public void testMordiDueVolte() {
		this.cane.agisci(partita);
		this.cane.agisci(partita);
		assertEquals(7, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testSaluta() {
		assertEquals("Bau bau bau. Bau bau!", this.cane.saluta());
	}
	
	@Test
	public void testRiceviRegalo(){
//		this.cane.riceviRegalo(new Attrezzo("Brutto Regalo", 2), partita);
//		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Brutto Regalo"));
		this.cane.riceviRegalo(new Attrezzo(DESIDERIO, 2), partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("regalo"));
	}
}
