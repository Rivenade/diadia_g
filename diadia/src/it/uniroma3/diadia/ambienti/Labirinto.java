package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

/**
 * Classe che modella un labirinto
 * 
 * @author Paolo Merialdo
 * @see Stanza
 * @version 0.3
 * 
 */
public class Labirinto {
	private Stanza entrata;
	private Stanza uscita;

	/**
	 * Carica un labirinto già presente come file .txt tra le risorse del gioco.
	 * Il labirinto può essere scelto in base alla difficoltà. Questa deve essere
	 * passata come parametro
	 * 
	 * @param livello
	 */
	public Labirinto(int livello) {
		String nomeFile = "Labirinto" + livello + ".txt";
		CaricatoreLabirinto c = null;
		try {
			c = new CaricatoreLabirinto(nomeFile);
			c.carica();
		} catch (FileNotFoundException e) {
			System.out.println("Problema nella classe Labirinto: il file non è stato trovato\n");
		} catch (FormatoFileNonValidoException e) {
			System.out.println("Problema nella classe Labirinto: il file non è in un formato valido\n");
		}
		this.entrata = c.getStanzaIniziale();
		this.uscita = c.getStanzaVincente();
	}

	/**
	 * Crea un labirnto di default di livello 3
	 * 
	 */
	public Labirinto(){
		this(3);
	}

	
	
	//METODI GET, SET E PREDEFINITI JAVA
	
	public Stanza getEntrata() {
		return this.entrata;
	}

	public Stanza getUscita() {
		return this.uscita;
	}
}




///**
// * Crea tutte le stanze e le porte di collegamento
// * 
// */
//public void creaLabirinto() {
//
//	/* crea gli attrezzi */
//	Attrezzo lanterna = new Attrezzo("lanterna", 3);
//	Attrezzo osso = new Attrezzo("osso", 1);
//	Attrezzo chiave = new Attrezzo("evaihc", 2);
//	Attrezzo fiori = new Attrezzo("fiori", 2);
//
//	/* creo i personaggi */
//	Mago mago = new Mago("Merlino", "Wingardium Leviosa", fiori);
//	Strega strega = new Strega();
//	Cane cane = new Cane();
//
//	/* crea stanze del labirinto */
//	Stanza atrio = new Stanza("Atrio");
//	Stanza aulaN11 = new Stanza("Aula N11");
//	Stanza aulaN10 = new Stanza("Aula N10");
//	Stanza laboratorio = new Stanza("Laboratorio Campus");
//	Stanza biblioteca = new Stanza("Biblioteca");
//	Stanza mensa = new StanzaBloccata("Mensa", "chiave", "nord");
//	Stanza aulaN1 = new StanzaBuia("Aula N1", "lanterna");
//	Stanza sotterraneo = new StanzaMagica("Sotterraneo");
//	Stanza cortile = new Stanza("Cortile");
//	Stanza sotterraneo2 = new Stanza("Sotterraneo");
//	Stanza aulaDS3 = new Stanza("Aula DS3");
//
//	/* collega le stanze */
//	mensa.impostaStanzaAdiacente("sud", atrio);
//	mensa.impostaStanzaAdiacente("est", cortile);
//	mensa.impostaStanzaAdiacente("nord", biblioteca);
//	mensa.impostaStanzaAdiacente("ovest", aulaDS3);
//	aulaDS3.impostaStanzaAdiacente("est", mensa);
//	aulaDS3.impostaStanzaAdiacente("sud", laboratorio);
//	cortile.impostaStanzaAdiacente("sud", aulaN11);
//	cortile.impostaStanzaAdiacente("ovest", mensa);
//	atrio.impostaStanzaAdiacente("nord", mensa);
//	atrio.impostaStanzaAdiacente("est", aulaN11);
//	atrio.impostaStanzaAdiacente("sud", aulaN10);
//	atrio.impostaStanzaAdiacente("ovest", laboratorio);
//	aulaN11.impostaStanzaAdiacente("est", laboratorio);
//	aulaN11.impostaStanzaAdiacente("ovest", atrio);
//	aulaN11.impostaStanzaAdiacente("nord", cortile);
//	aulaN10.impostaStanzaAdiacente("nord", atrio);
//	aulaN10.impostaStanzaAdiacente("est", aulaN11);
//	aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
//	aulaN10.impostaStanzaAdiacente("sud", aulaN1);
//	aulaN1.impostaStanzaAdiacente("nord", aulaN10);
//	aulaN1.impostaStanzaAdiacente("sotto", sotterraneo);
//	laboratorio.impostaStanzaAdiacente("est", atrio);
//	laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
//	laboratorio.impostaStanzaAdiacente("nord", aulaDS3);
//	sotterraneo.impostaStanzaAdiacente("sopra", aulaN1);
//	sotterraneo.impostaStanzaAdiacente("ovest", sotterraneo2);
//	sotterraneo2.impostaStanzaAdiacente("est", sotterraneo);
//	biblioteca.impostaStanzaAdiacente("sud", mensa);
//
//	/* pone gli attrezzi nelle stanze */
//	aulaN10.addAttrezzo(lanterna);
//	atrio.addAttrezzo(osso);
//	aulaN1.addAttrezzo(chiave);
//
//	/* pone i personaggi nelle stanze */
//	cortile.setPersonaggio(cane);
//	sotterraneo2.setPersonaggio(mago);
//	aulaDS3.setPersonaggio(strega);
//
//	// il gioco comincia nell'atrio
//	this.entrata = atrio;
//	this.uscita = biblioteca;
//}




///**
// * Metodo che crea un labirinto molto semplice
// * 
// */
//public void creaLabirintoPiccolo() {
//	// entrata ->(nord)-> corridodio ->(nord)-> uscita
//	Stanza corridoio = new Stanza("corridoio");
//	this.uscita = new Stanza("uscita");
//	this.entrata = new Stanza("entrata");
//	this.entrata.impostaStanzaAdiacente("nord", corridoio);
//	corridoio.impostaStanzaAdiacente("sud", this.entrata);
//	corridoio.impostaStanzaAdiacente("nord", this.uscita);
//	this.uscita.impostaStanzaAdiacente("sud", corridoio);
//}