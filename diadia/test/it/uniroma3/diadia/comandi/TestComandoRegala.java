package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Borsa;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

import org.junit.Test;

public class TestComandoRegala {

	@Test
	public void test() {
		Partita partita = new Partita();
		AbstractPersonaggio personaggio = new FakePersonaggio();
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		Borsa borsa = partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(new Attrezzo("martello", 1));
		AbstractComando comando = new ComandoRegala();
		comando.setParametro("scalpello");
		assertEquals("Non puoi regalare l'oggetto perché non lo possiedi", comando.esegui(partita));
		comando.setParametro("martello");
		assertEquals("ricevuto", comando.esegui(partita));
		assertFalse(borsa.hasAttrezzo("martello"));
	}
}
