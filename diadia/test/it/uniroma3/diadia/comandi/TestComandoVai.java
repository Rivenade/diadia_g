package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertSame;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class TestComandoVai {
	private Partita partita;
	private AbstractComando comando;
	private Stanza adiacente;
	private Stanza corrente;

	@Before
	public void setUp() {
		this.partita = new Partita();
		corrente = new Stanza("Inizio");
		adiacente = new Stanza("Stanza Adiacente");
		this.partita.setStanzaCorrente(corrente);
		corrente.impostaStanzaAdiacente("est", adiacente);
		this.comando = new ComandoVai();
	}

	@Test
	public void testVaiDirezioneNulla() {
		System.out.println("Deve stampare: \"Dove vuoi andare? Devi...\"");
		this.comando.esegui(this.partita);
		System.out.println();
		assertSame(this.corrente, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiStanzaEsistente() {
		System.out.println("Deve stampare le informazioni sulla nuova stanza corrente");
		comando.setParametro("est");
		comando.esegui(partita);
		System.out.println();
		assertSame(this.adiacente, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiStanzaInesistente() {
		System.out.println("Deve stampare: \"Direzione inesistente\"");
		comando.setParametro("ovest");
		comando.esegui(partita);
		System.out.println();
		assertSame(this.corrente, this.partita.getStanzaCorrente());
	}
}
