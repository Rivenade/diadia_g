package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/* Classe creata per il testing */
public class FakePersonaggio extends AbstractPersonaggio {

	public FakePersonaggio() {
		super("Personaggio", "Mi presento");
	}

	@Override
	public String agisci(Partita partita) {
		return "done";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "ricevuto";
	}
}