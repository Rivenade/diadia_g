package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class TestStanzaMagica {
	private static final String COMPUTER_REVERSED = "retupmoC";
	private static final int SOGLIA_MAGICA = 2;
	private static final String COMPUTER = "Computer";
	private Stanza stanzaMagicaVuota;
	private Attrezzo computer;

	@Before
	public void setUp() {
		this.computer = new Attrezzo(COMPUTER, 8);
		this.stanzaMagicaVuota = new StanzaMagica("stanza magica", SOGLIA_MAGICA);
	}

	@Test
	public void testComportamentoMagicoNome() {
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		this.stanzaMagicaVuota.removeAttrezzo(this.computer);
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		assertTrue(this.stanzaMagicaVuota.hasAttrezzo(COMPUTER_REVERSED));
	}

	@Test
	public void testComportamentoMagicoPeso() {
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		this.stanzaMagicaVuota.removeAttrezzo(this.computer);
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		assertTrue(this.stanzaMagicaVuota.getAttrezzo(COMPUTER_REVERSED).getPeso() == 16);
	}

	@Test
	public void testComportamentoNormaleNome() {
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		assertEquals(COMPUTER, this.stanzaMagicaVuota.getAttrezzo(COMPUTER).getNome());
	}

	@Test
	public void testComportamentoNormalePeso() {
		this.stanzaMagicaVuota.addAttrezzo(this.computer);
		assertEquals(this.computer.getPeso(), this.stanzaMagicaVuota.getAttrezzo(COMPUTER).getPeso());
	}

	@Test
	public void testGetStanzaAdiacenteStanzaMagicaConStanzaAppenaCreataNull() {
		assertNull(this.stanzaMagicaVuota.getStanzaAdiacente("nord"));
	}
}
