package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import org.junit.Test;

public class TestComandoNonValido {
	private Partita partita;
	private AbstractComando comando;

	@Test
	public void test() {
		System.out.println("Deve stampare: \"Il comando inserito non è valido\"");
		this.partita = new Partita();
		this.comando = new ComandoNonValido();
		this.comando.esegui(this.partita);
		System.out.println();
	}
}