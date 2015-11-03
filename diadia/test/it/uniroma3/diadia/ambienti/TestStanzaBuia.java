package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class TestStanzaBuia {
	private Stanza stanzaBuiaSemplice;

	@Before
	public void setUp() {
		this.stanzaBuiaSemplice = new StanzaBuia("stanza buia", "chiave");
	}

	@Test
	public void testGetStanzaAdiacenteStanzaMagicaConStanzaAppenaCreataNull() {
		assertNull(this.stanzaBuiaSemplice.getStanzaAdiacente("nord"));
	}

	@Test
	public void testToStringStanzaBuia() {
		System.out.println("\nDeve stampare: \"qui c'è un buio pesto\"");
		System.out.println(this.stanzaBuiaSemplice);
	}

	@Test
	public void testToStringStanzaIlluminata() {
		System.out.println("\nDeve stampare la descrizione della stanza");
		this.stanzaBuiaSemplice.addAttrezzo(new Attrezzo("chiave", 2));
	}
}