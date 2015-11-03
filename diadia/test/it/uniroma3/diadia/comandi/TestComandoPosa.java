package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Borsa;

import org.junit.Before;
import org.junit.Test;

public class TestComandoPosa {
	private final static String MARTELLO = "martello";
	private ComandoPosa comando;
	private Partita partita;
	private Borsa borsa;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.comando = new ComandoPosa();
		this.borsa = this.partita.getGiocatore().getBorsa();
		Attrezzo martello = new Attrezzo(MARTELLO, 2);
		this.borsa.addAttrezzo(martello);
	}

	@Test
	public void testPosaAttrezzoCheNonHai() {
		System.out
				.println("Deve stampare: \"Non posso posare questo oggetto ...\"");
		this.comando.setParametro("cuscino");
		this.comando.esegui(this.partita);
		System.out.println();
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("cuscino"));
	}

	@Test
	public void testPosaAttrezzoEsistente_VerificaInBorsa() {
		System.out.println("Deve stampare: \"Hai lasciato: martello\"");
		this.comando.setParametro(MARTELLO);
		this.comando.esegui(this.partita);
		System.out.println();
		assertFalse(this.borsa.hasAttrezzo(MARTELLO));
	}

	@Test
	public void testPosaAttrezzoEsistente_VerificaInStanza() {
		System.out.println("Deve stampare: \"Hai lasciato: martello\"");
		this.comando.setParametro(MARTELLO);
		this.comando.esegui(this.partita);
		System.out.println();
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(MARTELLO));
	}

	@Test
	public void testPosaNulla() {
		System.out
				.println("Deve stampare: \"Non posso posare questo oggetto ...\"");
		this.comando.esegui(this.partita);
		System.out.println();
	}
}
