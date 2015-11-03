package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;

import org.junit.Before;
import org.junit.Test;

public class TestStanza {
	private static final String MARTELLO = "martello";
	private Stanza senzaAdiacenti;
	private Stanza senzaAttrezzi;
	private Stanza maxAdiacenti;
	private Stanza maxAttrezzi;
	private Stanza conPersonaggio;

	private Cane personaggio;
	private Attrezzo martello;

	@Before
	public void setUp() {
		this.senzaAdiacenti = new Stanza("Giardino"); 	// 0 adiacenti, 4 attrezzi, 0 personaggi
		this.senzaAttrezzi = new Stanza("Balcone"); 	// 3 adiacenti, 0 attrezzi, 0 personaggi
		this.maxAdiacenti = new Stanza("Corridoio"); 	// 4 adiacenti, 2 attrezzi, 0 personaggi
		this.maxAttrezzi = new Stanza("Garage"); 		// 1 adiacente, 10 attrezzi, 0 personaggi
		this.conPersonaggio = new Stanza("Atrio");	 	// 0 adiacenti, 0 attrezzi, 1 personaggio

		this.martello = new Attrezzo(MARTELLO, 1);
		this.maxAttrezzi.addAttrezzo(martello);
		this.maxAttrezzi.addAttrezzo(new Attrezzo("chiave", 0));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("ciambella", 0));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("sedia", 1));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("estintore", 3));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("cattedra", 9));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("libro", 2));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("computer", 2));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("cellulare", 1));
		this.maxAttrezzi.addAttrezzo(new Attrezzo("spada", 4));
		this.maxAttrezzi.impostaStanzaAdiacente("ovest", new Stanza("StanzaOvest"));

		this.maxAdiacenti.impostaStanzaAdiacente("nord", this.senzaAttrezzi);
		this.maxAdiacenti.impostaStanzaAdiacente("est", new Stanza("CameraEst"));
		this.maxAdiacenti.impostaStanzaAdiacente("sud", new Stanza("CameraSud"));
		this.maxAdiacenti.impostaStanzaAdiacente("ovest", new Stanza("CameraOvest"));
		this.maxAdiacenti.addAttrezzo(new Attrezzo("collana", 1));
		this.maxAdiacenti.addAttrezzo(new Attrezzo("forno", 9));

		this.senzaAdiacenti.addAttrezzo(new Attrezzo("mouse", 1));
		this.senzaAdiacenti.addAttrezzo(new Attrezzo("gesso", 0));
		this.senzaAdiacenti.addAttrezzo(new Attrezzo("lavagna", 5));
		this.senzaAdiacenti.addAttrezzo(new Attrezzo("vaso", 1));

		this.senzaAttrezzi.impostaStanzaAdiacente("nord", new Stanza("StanzaNord"));
		this.senzaAttrezzi.impostaStanzaAdiacente("sud", this.maxAdiacenti);
		this.senzaAttrezzi.impostaStanzaAdiacente("est", new Stanza("StanzaEst"));

		this.personaggio = new Cane();
		this.conPersonaggio.setPersonaggio(this.personaggio);
	}

	@Test
	public void testGetAttrezzo() {
		assertSame(this.martello, this.maxAttrezzi.getAttrezzo(MARTELLO));
		assertNull(this.senzaAttrezzi.getAttrezzo(MARTELLO));
	}

	@Test
	public void testGetDirezioni_SenzaAdiacenti() {
		assertTrue(this.senzaAdiacenti.getDirezioni().isEmpty());
	}

	@Test
	public void testGetDirezioni_VerificaPerNumero() {
		assertEquals(4, this.maxAdiacenti.getDirezioni().size());
	}

	@Test
	public void testGetNome() {
		assertEquals("Balcone", this.senzaAttrezzi.getNome());
	}

	@Test
	public void testGetStanzaAdiacente() {
		assertSame(this.maxAdiacenti, this.senzaAttrezzi.getStanzaAdiacente("sud"));
		assertNull(this.senzaAdiacenti.getStanzaAdiacente("nord"));
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(this.maxAttrezzi.hasAttrezzo(MARTELLO));
		assertFalse(this.senzaAttrezzi.hasAttrezzo(MARTELLO));
	}

	@Test
	public void testHasSpazio() {
		assertTrue(this.senzaAttrezzi.hasSpazio());
		assertFalse(this.maxAttrezzi.hasSpazio());
	}

	@Test
	public void testIsPopolata() {
		assertTrue(this.conPersonaggio.isPopolata());
		assertFalse(this.senzaAttrezzi.isPopolata());
	}

	@Test
	public void testRemoveAttrezzo() {
		assertTrue(this.maxAttrezzi.removeAttrezzo(this.martello) && !this.maxAttrezzi.hasAttrezzo(MARTELLO));
		assertFalse(this.senzaAttrezzi.removeAttrezzo(this.martello));
	}
}