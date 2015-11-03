package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Borsa;

import org.junit.Before;
import org.junit.Test;

public class TestBorsa {
	private static final String MARTELLO = "Martello";

	private Borsa borsaConAttrezzo;
	private Borsa borsaVariAttrezzi;
	private Borsa borsaVuota;

	private Attrezzo martello;
	private Attrezzo lavagna;
	private Attrezzo macigno;
	private Attrezzo spazzolino;

	@Before
	public void setUp() {
		this.borsaVuota = new Borsa();
		this.borsaConAttrezzo = new Borsa(15);
		this.borsaVariAttrezzi = new Borsa(13);

		this.martello = new Attrezzo(MARTELLO, 3);
		this.macigno = new Attrezzo("Macigno", 14);
		this.lavagna = new Attrezzo("Lavagna", 5);
		this.spazzolino = new Attrezzo("Spazzolino", 2);

		this.borsaConAttrezzo.addAttrezzo(martello);
		this.borsaVariAttrezzi.addAttrezzo(martello);
		this.borsaVariAttrezzi.addAttrezzo(lavagna);
		this.borsaVariAttrezzi.addAttrezzo(spazzolino);
	}

	@Test
	public void testAddAttrezzoABorsaPiena() {
		this.borsaConAttrezzo.addAttrezzo(macigno);
		assertFalse(this.borsaConAttrezzo.hasAttrezzo("Macigno"));
	}

	@Test
	public void testAddAttrezzoGiaPresente() {
		this.borsaConAttrezzo.addAttrezzo(martello);
		assertEquals(3, this.borsaConAttrezzo.getPeso());
	}

	@Test
	public void testGetAttrezzo() {
		assertEquals(this.martello, this.borsaConAttrezzo.getAttrezzo(MARTELLO));
	}

	@Test
	public void testGetContenuto_InMappa_PerPeso() {
		String msg = "{2=[Spazzolino (2kg)], 3=[Martello (3kg)], 5=[Lavagna (5kg)]}";
		assertEquals(msg, this.borsaVariAttrezzi
				.getContenutoRaggruppatoPerPeso().toString());
	}

	@Test
	public void testGetContenuto_PerNome() {
		String msg = "[Lavagna (5kg), Martello (3kg), Spazzolino (2kg)]";
		assertEquals(msg, this.borsaVariAttrezzi.getContenutoOrdinatoPerNome()
				.toString());
	}

	@Test
	public void testGetContenuto_PerPeso() {
		String msg = "[Spazzolino (2kg), Martello (3kg), Lavagna (5kg)]";
		assertEquals(msg, this.borsaVariAttrezzi.getContenutoOrdinatoPerPeso()
				.toString());
	}

	@Test
	public void testGetContenuto_RaggruppatoPerPeso_ConDoppione() {
		this.borsaVariAttrezzi.addAttrezzo(new Attrezzo("Zampone", 3));
		String msg = "{2=[Spazzolino (2kg)], 3=[Martello (3kg), Zampone (3kg)], 5=[Lavagna (5kg)]}";
		assertEquals(msg, this.borsaVariAttrezzi
				.getContenutoRaggruppatoPerPeso().toString());
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(15, this.borsaConAttrezzo.getPesoMax());
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(this.borsaConAttrezzo.hasAttrezzo(MARTELLO));
		assertFalse(this.borsaVuota.hasAttrezzo(MARTELLO));
	}

	@Test
	public void testHasSpazio() {
		assertTrue(this.borsaVuota.hasSpazio(martello));
		assertFalse(this.borsaConAttrezzo.hasSpazio(macigno));
	}

	@Test
	public void testPeso() {
		assertEquals(3, this.borsaConAttrezzo.getPeso());
		assertEquals(0, this.borsaVuota.getPeso());
	}

	@Test
	public void testRimuoviAttrezzoDaBorsaConAttrezzo() {
		this.borsaConAttrezzo.removeAttrezzo(MARTELLO);
		assertFalse(this.borsaConAttrezzo.hasAttrezzo(MARTELLO));
	}

	@Test
	public void testRimuoviAttrezzoDaBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("lanciarazzi"));
	}

	@Test
	public void testToStringBorsaVuota() {
		assertEquals("Borsa vuota", this.borsaVuota.toString());
	}

	@Test
	public void testToStringConAttrezzo() {
		assertEquals("Contenuto borsa (3kg/15kg): [Martello (3kg)]",
				this.borsaConAttrezzo.toString());
	}
}
