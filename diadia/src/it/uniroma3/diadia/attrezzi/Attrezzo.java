package it.uniroma3.diadia.attrezzi;



/**
 * Una semplice classe che modella un attrezzo. Gli attrezzi possono trovarsi
 * all'interno delle stanze del labirinto. Ogni attrezzo ha un nome ed un peso.
 *
 * @author Paolo Merialdo
 * @version 0.3
 * 
 */
public class Attrezzo implements Comparable<Attrezzo> {
	private String nome;
	private int peso;
	private float x,  y;
	private boolean moved = false;
	
	public void setXY (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX () {
		return this.x;
	}
	
	public float getY () {
		return this.y;
	}


	/**
	 * Crea un attrezzo
	 * 
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}
	
	//METODI GET, SET E PREDEFINITI JAVA E RENDER
	
	@Override
	public int compareTo(Attrezzo that) {
		// prima per peso poi per nome
		int ordine = this.getPeso() - that.getPeso();
		if (ordine == 0)
			ordine = this.getNome().compareTo(that.getNome());
		return ordine;
	}

	@Override
	public int hashCode() {
		return this.peso + this.getNome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		Attrezzo attrezzo = (Attrezzo) o;
		return (this.nome == attrezzo.getNome() && this.peso == attrezzo.getPeso());
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso (int peso) {
		this.peso = peso;
	}
	
	public void setNome (String nome){
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}

	public boolean isBeenMoved() {
		return moved ;
	}

	public void setMoved() {
		moved = true;
		
	}
}