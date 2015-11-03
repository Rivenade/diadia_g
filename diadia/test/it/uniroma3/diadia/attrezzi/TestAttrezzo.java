package it.uniroma3.diadia.attrezzi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
//C'è un errore!!! Vedi giù
import java.util.Comparator;

public class TestAttrezzo {
	private Attrezzo piccoloArnese;
	private Attrezzo grandeArnese;

	@Before
	public void setUp() {
		this.piccoloArnese = new Attrezzo("martello", 1);
		this.grandeArnese = new Attrezzo("martello", 2);
	}

	@Test
	public void testComparatoreEsterno() {
		Comparator<Attrezzo> comp = new ComparatoreAttrezziPerNome();
		assertTrue(comp.compare(this.piccoloArnese, this.grandeArnese) < 0);
		assertTrue(comp.compare(this.piccoloArnese, new Attrezzo("cacciavite", 1)) > 0);
		assertTrue(comp.compare(this.piccoloArnese, new Attrezzo("sega", 4)) < 0);
	}

	@Test
	public void testComparazionePerPeso() {
		assertTrue(this.piccoloArnese.compareTo(this.grandeArnese) < 0);
		assertTrue(this.piccoloArnese.compareTo(new Attrezzo("cacciavite", 1)) > 0);
		assertTrue(this.piccoloArnese.compareTo(new Attrezzo("martello", 1)) == 0);
	}

	@Test
	public void testDescrittore() {
		assertEquals("martello (1kg)", this.piccoloArnese.toString());
	}

	@Test
	public void testGetNome() {
		assertEquals("martello", this.piccoloArnese.getNome());
	}

	@Test
	public void testGetPeso() {
		assertEquals(1, this.piccoloArnese.getPeso());
	}

	@Test
	public void testUguaglianze_HashCode() {
		assertFalse(this.piccoloArnese.hashCode() == this.grandeArnese.hashCode());
		assertTrue(this.piccoloArnese.hashCode() == (new Attrezzo("martello", 1)).hashCode());
	}
}
