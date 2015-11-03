package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class TestComandoPrendi {
	private static final String MARTELLO = "martello";
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.comando = new ComandoPrendi();
		this.stanza = this.partita.getStanzaCorrente();
		Attrezzo martello = new Attrezzo(MARTELLO, 2);
		this.stanza.addAttrezzo(martello);
	}

	@Test
	public void testPrendiAttrezzoEsistente_VerificaInBorsa() {
		this.comando.setParametro(MARTELLO);
		this.comando.esegui(partita);
		System.out.println();
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(MARTELLO));
	}

	@Test
	public void testPrendiAttrezzoEsistente_VerificaInStanza() {
		this.comando.setParametro(MARTELLO);
		this.comando.esegui(partita);
		System.out.println();
		assertFalse(this.stanza.hasAttrezzo(MARTELLO));
	}

	@Test
	public void testPrendiAttrezzoNonPresente() {
		System.out.println("Deve stampare: \"Non posso raccogliere questo oggetto ...\"");
		this.comando.setParametro("cuscino");
		this.comando.esegui(partita);
		System.out.println();
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("cuscino"));
	}

	@Test
	public void testPrendiNulla() {
		System.out.println("Deve stampare: \"Cosa vuoi raccogliere? Devi indicare un parametro\"");
		this.comando.esegui(partita);
		System.out.println();
	}
}
