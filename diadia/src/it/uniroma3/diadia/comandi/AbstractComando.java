package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che modella un generico comando
 * 
 * @author Lorenzo
 *
 */
public abstract class AbstractComando {
	private String parametro;

	/**
	 * Esegue il comando per far evolvere la partita
	 * 
	 * @param partita partita su cui si vuole eseguire il comando
	 */
	abstract public String esegui(Partita partita);
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	public String getParametro() {
		return parametro;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
