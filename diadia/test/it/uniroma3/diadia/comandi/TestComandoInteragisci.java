package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

import org.junit.Test;

public class TestComandoInteragisci {

	@Test
	public void testEsegui() {
		AbstractPersonaggio pers = new FakePersonaggio();
		AbstractComando comando = new ComandoInteragisci();
		Partita partita = new Partita();
		partita.getStanzaCorrente().setPersonaggio(pers);
		comando.esegui(partita);
		assertEquals("done", pers.agisci(partita));
	}
}
