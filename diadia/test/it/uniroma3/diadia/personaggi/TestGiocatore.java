package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.personaggi.Giocatore;

import org.junit.Before;
import org.junit.Test;

public class TestGiocatore {
	private Giocatore alunno;

	@Before
	public void setUp() {
		alunno = new Giocatore();
		alunno.setCfu(60);
	}

	@Test
	public void testDecrementaCfu() {
		this.alunno.decrementaCfu();
		assertEquals(59, this.alunno.getCfu());
	}

	@Test
	public void testDecrementaCfuMultipli() {
		this.alunno.decrementaCfu(20);
		assertEquals(40, this.alunno.getCfu());
	}

	@Test
	public void testGetCFU_FALSE() {
		assertNotEquals(30, alunno.getCfu());
	}
}
