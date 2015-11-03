package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Classe che si occupa di stabilire quale comando invocare.
 * 
 * @author Paolo Merialdo
 * @version 0.3
 *
 */
public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	private String nome;
	private String parametro = null;
	private AbstractComando comando;

	/**
	 * Estrae il comando corretto da un elenco di possibili comandi
	 * 
	 * @param istruzione comando che si vuole eseguire
	 * @return comando
	 */
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		if (istruzione != null) {
			Scanner scannerDiParole = new Scanner(istruzione);
			if (scannerDiParole.hasNext())
				this.nome = scannerDiParole.next();
			if (scannerDiParole.hasNext())
				this.parametro = scannerDiParole.next();
			scannerDiParole.close();
		}

		this.comando = new ComandoNonValido();
		if (this.nome != null)
			if (this.nome.equals("fine"))
				this.comando = new ComandoFine();
			else if (this.nome.equals("vai"))
				this.comando = new ComandoVai();
			else if (this.nome.equals("aiuto"))
				this.comando = new ComandoAiuto();
			else if (this.nome.equals("prendi"))
				this.comando = new ComandoPrendi();
			else if (this.nome.equals("posa"))
				this.comando = new ComandoPosa();
			else if (this.nome.equals("guarda"))
				this.comando = new ComandoGuarda();
			else if (this.nome.equals("saluta"))
				this.comando = new ComandoSaluta();
			else if (this.nome.equals("interagisci"))
				this.comando = new ComandoInteragisci();
			else if (this.nome.equals("regala"))
				this.comando = new ComandoRegala();
		this.comando.setParametro(parametro);
		return this.comando;
	}



	//METODI GET, SET E PREDEFINITI JAVA

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
}