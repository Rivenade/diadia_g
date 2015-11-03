package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertSame;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class TestStanzaBloccata {
	private Stanza stanzaBloccata;
	private Stanza stanzaNord;
	private Stanza stanzaSud;

	@Before
	public void setUp() {
		stanzaNord = new Stanza("stanza a nord");
		stanzaSud = new Stanza("stanza a sud");
		this.stanzaBloccata = new StanzaBloccata("stanza bloccata", "chiave", "nord");
		this.stanzaBloccata.impostaStanzaAdiacente("nord", stanzaNord);
		this.stanzaBloccata.impostaStanzaAdiacente("sud", stanzaSud);
	}

	@Test
	public void testVaiDirezioneBloccata() {
		System.out.println("Deve stampare: \"Non posso proseguire. Questa direzione è bloccata\"");
		assertSame(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente("nord"));
		System.out.println();
	}

	@Test
	public void testVaiDirezioneBloccataConChiave() {
		this.stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 2));
		assertSame(this.stanzaNord, this.stanzaBloccata.getStanzaAdiacente("nord"));
	}

	@Test
	public void testVaiDirezioneLibera() {
		assertSame(this.stanzaSud, this.stanzaBloccata.getStanzaAdiacente("sud"));
	}
}
