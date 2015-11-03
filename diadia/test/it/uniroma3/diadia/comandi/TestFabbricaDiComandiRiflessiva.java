package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFabbricaDiComandiRiflessiva {
	private FabbricaDiComandi fabbrica;

	@Before
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testCostruisciComando() {
		assertSame(it.uniroma3.diadia.comandi.ComandoVai.class, this.fabbrica.costruisciComando("vai sud").getClass());
	}
	
	/* eventualmente testare anche la costruzione dei comandi con parametro */
	
	@Test (expected = RuntimeException.class)
	public void testCostruisciComandoDiClasseSenzaCostruttoreNoArgs() {
		this.fabbrica.costruisciComando("fake").getClass();
	}
	
	@Test
	public void testCostruisciComando_ComandoNonValido() {
		assertSame(it.uniroma3.diadia.comandi.ComandoNonValido.class, this.fabbrica.costruisciComando("corri sud").getClass());
	}
}
