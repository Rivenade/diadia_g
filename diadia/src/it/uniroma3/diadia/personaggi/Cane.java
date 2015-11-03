package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella un personaggio Cane Il cane è arrabbiato, se non posiamo
 * un oggetto nella stanza dove si trova e proviamo ad interagirci, ci morderà
 * facendoci perdere un cfu la prima volta, il doppio ogni volta che riporviamo.
 * Se lo posiamo ci ignorerà, felice del nostro regalo.
 * 
 * @author Lorenzo e Stefano
 * @version 0.3
 * @see AbstractPersonaggio
 *
 */
public class Cane extends AbstractPersonaggio {
	private final static String PRESENTAZIONE = "Bau! Bau! [Il cane desidera qualcosa con cui giocare]";
	private int contatoreRabbia;
	private Attrezzo attrezzo;
	private String desiderio;

	/**
	 * Costruttore con parametri di default: nome "Pluto", attrezzo
	 * "giornale" e desiderio "osso"
	 * 
	 */
	public Cane() {
		this("Pluto", PRESENTAZIONE, "osso", new Attrezzo("giornale", 1));
	}
	
	public Cane(String nome, String desiderio, Attrezzo attrezzo) {
		this(nome, PRESENTAZIONE, desiderio, attrezzo);
	}

	public Cane(String nome, String presentazione, String desiderio, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.desiderio = desiderio;
		this.attrezzo = attrezzo;
		this.contatoreRabbia = 1;
	}

	/**
	 * Gestisce l'interazione col cane. Il moltiplicatore di contatoreRabbia
	 * viene attuato ad ogni interazione, anche se si ha posato l'osso
	 * 
	 * @param partita
	 * @return messsaggio che spiega cosa è successo
	 */
	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder();
		if (partita.getStanzaCorrente().hasAttrezzo(this.desiderio)) 
			msg.append("Il cane scodinzola felice mentre gioca col suo " + this.desiderio 
			+ "\nNon si è accorto di te. Puoi proseguire");
		else {
			mordi(partita.getGiocatore());
			msg.append("Il cane è molto arrabbiato. Ti ha morso: " + partita.getGiocatore());
			this.contatoreRabbia *= 2;
		}
		return msg.toString();
	}

	private void mordi(Giocatore giocatore) {
		giocatore.decrementaCfu(this.contatoreRabbia);
	}

	/**
	 * Un cane riceve un regalo, se questo è il suo cibo preferito lo accetta, e
	 * butta a terra un attrezzo, altrimenti non lo accetta e lascia cadere il
	 * regalo nella stanza
	 * 
	 * @param attrezzo attrezzo che si desidera regalare al cane
	 * @param partita
	 * @return messaggio del cane
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder msg = new StringBuilder();
		if (attrezzo.getNome() == this.desiderio) {
			msg.append("Mazza che bono! ehm volevo dire bau bau!");
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = attrezzo;
			msg.append("Il cane lascia cadere qualcosa: " + attrezzo.getNome());
		} else {
			msg.append("Grrrrrr!");
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			msg.append("Il cane non apprezza il tuo dono");
		}
		return msg.toString();
	}
	
	
	
	//METODI GET, SET E PREDEFINITI JAVA

	/**
	 * Restituisce il saluto del cane come Stringa
	 * 
	 * @return saluto
	 */
	@Override
	public String saluta() {
		return "Bau bau bau. Bau bau!";
	}
	
	public void setDesiderio (String desiderio) {
		this.desiderio = desiderio;
	}
	
	public void setAttrezzo (Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	
}
