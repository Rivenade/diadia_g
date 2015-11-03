package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFake extends AbstractComando{
	
	public ComandoFake(String fake){	/* Creo un costruttore con parametri per evitare che Java ne crei uno di default no-args */
	}

	@Override
	public String esegui(Partita partita) {
		throw new UnsupportedOperationException();	/* Eccezione uncheched creata per non implementare le classi dei test come questa (tipo) */
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	@Override
	public String getParametro() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setParametro(String parametro) {
		throw new UnsupportedOperationException();
	}
}
