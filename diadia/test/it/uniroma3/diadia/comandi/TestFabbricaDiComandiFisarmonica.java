package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestFabbricaDiComandiFisarmonica {
	private FabbricaDiComandiFisarmonica fabbrica;

	@Before
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testAiuto() {
		assertEquals(ComandoAiuto.class, this.fabbrica.costruisciComando("aiuto").getClass());
	}
	
	@Test
	public void testAiutoNome() {
		assertEquals("ComandoAiuto", this.fabbrica.costruisciComando("aiuto").toString());
	}

	@Test
	public void testComandoNonValido() {
		assertEquals(ComandoNonValido.class, this.fabbrica.costruisciComando("").getClass());
	}

	@Test
	public void testComandoNullo() {
		assertEquals(ComandoNonValido.class, this.fabbrica.costruisciComando(null).getClass());
	}

	@Test
	public void testFine() {
		assertEquals(ComandoFine.class, this.fabbrica.costruisciComando("fine").getClass());
	}

	@Test
	public void testGuarda() {
		assertEquals(ComandoGuarda.class, this.fabbrica.costruisciComando("guarda").getClass());
	}

	@Test
	public void testInteragisci() {
		assertEquals(ComandoInteragisci.class, this.fabbrica.costruisciComando("interagisci").getClass());
	}

	@Test
	public void testPosa() {
		assertEquals(ComandoPosa.class, this.fabbrica.costruisciComando("posa").getClass());
	}

	@Test
	public void testPrendi() {
		assertEquals(ComandoPrendi.class, this.fabbrica.costruisciComando("prendi").getClass());
	}

	@Test
	public void testSaluta() {
		assertEquals(ComandoSaluta.class, this.fabbrica.costruisciComando("saluta").getClass());
	}

	@Test
	public void testVai() {
		assertEquals(ComandoVai.class, this.fabbrica.costruisciComando("vai").getClass());
	}
}
