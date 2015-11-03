package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;

import org.junit.Before;
import org.junit.Test;

public class TestMago {
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Mago mago;
	private Attrezzo bacchetta;
	private Partita partita;

	@Before
	public void setUp() {
		this.bacchetta = new Attrezzo("bacchetta", 2);
		this.mago = new Mago(bacchetta);
		this.partita = new Partita();
	}

	@Test
	public void testAgisci() {
		this.mago.agisci(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}

	@Test
	public void testAgisci_SenzaAttrezzo() {
		this.mago.agisci(partita);
		assertTrue(this.mago.getAttrezzo() == null);
		assertEquals(MESSAGGIO_SCUSE, this.mago.agisci(partita));
	}
	
	@Test
	public void testRiceviRegalo(){
		this.mago.riceviRegalo(bacchetta, partita);
		assertTrue(this.partita.getStanzaCorrente().getAttrezzo("bacchetta").getPeso()==1);
	}
}
