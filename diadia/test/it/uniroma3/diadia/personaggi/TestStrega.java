package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertSame;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoSaluta;
import it.uniroma3.diadia.personaggi.Strega;

import org.junit.Before;
import org.junit.Test;

public class TestStrega {
	private Partita partita;
	private Strega strega;
	private Stanza nord;
	private Stanza sud;

	@Before
	public void setUp() {
		/*
		 * Avvia una partita in un piccolo labirinto di tre stanze: Una stanza
		 * corrente, una a sud, una a nord. A nord ci sono 2 attrezzi, nella
		 * corrente 1, a sud 0. Il personaggio strega è nella stanza corrente
		 */
		
		this.strega = new Strega();
		Labirinto labirinto = new Labirinto(99);
		this.partita = new Partita(labirinto);
		Stanza corrente = this.partita.getStanzaCorrente();
		this.sud = corrente.getStanzaAdiacente("sud");
		this.nord = corrente.getStanzaAdiacente("nord");
		corrente.setPersonaggio(this.strega);
	}

	@Test
	public void testStregaGenerosa() {
		new ComandoSaluta().esegui(this.partita);
		new ComandoInteragisci().esegui(this.partita);
		assertSame(this.nord, this.partita.getStanzaCorrente());
	}

	@Test
	public void testStregaPermalosa() {
		new ComandoInteragisci().esegui(this.partita);
		assertSame(this.sud, this.partita.getStanzaCorrente());
	}
}
