package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CaricatoreLabirinto {

	/* Esempio di un possibile file di specifica di un labirinto (vedi POO-16-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11
	 * Inizio: N10
	 * Vincente: N11
	 * Attrezzi: martello 10 biblioteca, pinza 2 N10
	 * Uscite: biblioteca nord N10, biblioteca sud N11
	 */

	private static final String STANZE_MARKER = "Stanze: ";   
	private static final String ENTRATA_MARKER = "Entrata: ";
	private static final String USCITA_MARKER = "Uscita: ";
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	private static final String ADIACENTI_MARKER = "Adiacenti: ";
	private static final String PERSONAGGI_MARKER = "Personaggi: ";

	private LineNumberReader reader;
	private Map<String, Stanza> nome2stanza;
	private Stanza entrata;
	private Stanza uscita;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreLabirinto(StringReader stringReader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(stringReader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiEntrataUscita();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaAdiacenti();
			this.leggiECollocaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * legge e colloca i personaggi nelle stanze
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specificheTuttiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for (String specificaSingoloPersonaggio: separaStringheAlleVirgole(specificheTuttiPersonaggi)){
			
			String attrezzoPersonaggio = null;
			String desiderio = null;
			int pesoAttrezzo = 0;
			Scanner scannerDiLinea = new Scanner(specificaSingoloPersonaggio);
			check(scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere il tipo di personaggio");
			String tipoPersonaggio = scannerDiLinea.next();
			check (scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere il nome del personaggio");
			String nomePersonaggio = scannerDiLinea.next();
			check (scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere la stanza di collocamento");
			String stanzaPersonaggio = scannerDiLinea.next();

			//TERRIBILE
			if (scannerDiLinea.hasNextInt())
			{
				attrezzoPersonaggio = stanzaPersonaggio;
				pesoAttrezzo = scannerDiLinea.nextInt();
				if (scannerDiLinea.hasNext()){
					stanzaPersonaggio = scannerDiLinea.next();
				}

			} else {
				if (scannerDiLinea.hasNext()) {
					desiderio = stanzaPersonaggio;
					attrezzoPersonaggio = scannerDiLinea.next();
					if (scannerDiLinea.hasNextInt()) {
						pesoAttrezzo = scannerDiLinea.nextInt();
						if (scannerDiLinea.hasNext()){
							stanzaPersonaggio = scannerDiLinea.next();
						}
					}
				}
			}
			
			AbstractPersonaggio personaggio = null;
			try {
				Class<?> classePersonaggio = Class.forName(costruisciNomeCompletamenteQualificato("Personaggio", tipoPersonaggio));
				personaggio = (AbstractPersonaggio) classePersonaggio.newInstance();
				personaggio.setNome(nomePersonaggio);
				if (attrezzoPersonaggio != null) {
					{

						try {
							//						Class[] args = new Class[1];
							//						args[0] = String.class;
							try {

								personaggio.getClass().getMethod("setAttrezzo", Attrezzo.class).invoke(personaggio, new Attrezzo (attrezzoPersonaggio, pesoAttrezzo));
								if(desiderio != null) {
									personaggio.getClass().getMethod("setDesiderio",  String.class).invoke(personaggio, desiderio);
								}

							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}

						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}
				nome2stanza.get(stanzaPersonaggio).setPersonaggio(personaggio);
				scannerDiLinea.close();
				
			} catch (ClassNotFoundException e) {
				new FakePersonaggio();
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Costruisce il nome completo della classe Stanza o Personaggio, a seconda del parametro "tipo"
	 * @param classe il nome della classe da costruire
	 * @param tipo il tipo particolare di quella classe 
	 * @return nome completo della classe
	 * @throws FormatoFileNonValidoException
	 */
	private String costruisciNomeCompletamenteQualificato(String classe, String tipo) throws FormatoFileNonValidoException{
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.");
		if(classe.equals("Stanza"))
			nomeClasse.append("ambienti.Stanza" + tipo);
		else if (classe.equals("Personaggio"))
			nomeClasse.append("personaggi." + tipo);
		else throw new FormatoFileNonValidoException("Errore nella costruzione di stanze sepeciali o personaggi");
		return nomeClasse.toString();
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check (riga.startsWith(marker),"era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(nomiStanze)) {
			System.out.println(specificaStanza);
			Scanner scannerDiLinea = new Scanner(specificaStanza);
			check (scannerDiLinea.hasNext(), "Terminazione precoce del file, prima di leggere il nome della stanza");
			String nomeStanza = scannerDiLinea.next();
			String stanzaSpeciale = "";
			Stanza stanza = null;
			if (scannerDiLinea.hasNext()){
				stanzaSpeciale = scannerDiLinea.next();
				
				if (stanzaSpeciale.equals("Buia")){
					if (scannerDiLinea.hasNext())
						stanza = new StanzaBuia(nomeStanza, scannerDiLinea.next());
					else
						stanza = new StanzaBuia(nomeStanza);
				}
				
				if (stanzaSpeciale.equals("Bloccata")){
					check(scannerDiLinea.hasNext(), "La stanza bloccata non ha una direzione da bloccare specificata");
					stanza = new StanzaBloccata(nomeStanza, "Chiave", scannerDiLinea.next());
				}
				
				if (stanzaSpeciale.equals("Magica")){
					if (scannerDiLinea.hasNext())
						stanza = new StanzaMagica(nomeStanza, scannerDiLinea.next().charAt(0));
					else
						stanza = new StanzaMagica(nomeStanza);
				}
			}
			else {
				stanza = new Stanza (nomeStanza);
			}
			scannerDiLinea.close();
			this.nome2stanza.put(nomeStanza, stanza);
			
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		while (scanner.hasNext())
			result.add(scanner.next());
		scanner.close();
		return result;
	}

	private void leggiEntrataUscita() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(ENTRATA_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(USCITA_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.entrata = this.nome2stanza.get(nomeStanzaIniziale);
		this.uscita = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), "Terminazione precoce del file prima di leggere il nome di un attrezzo.");
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), "Terminazione precoce del file prima di leggere il peso dell'attrezzo " + nomeAttrezzo + ".");
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), "Terminazione precoce del file prima di leggere il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + ".");
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaAdiacenti() throws FormatoFileNonValidoException {
		String specificheAdiacence = this.leggiRigaCheCominciaPer(ADIACENTI_MARKER);
		for(String specificaStanza: this.separaStringheAlleVirgole(specificheAdiacence)){
			try (Scanner scannerDiLinea = new Scanner(specificaStanza)) {			
				check(scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere le uscite di una stanza.");
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere la direzione di una uscita della stanza " + stanzaPartenza);
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), "Terminazione precoce del file prima di leggere la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir);
				String stanzaDestinazione = scannerDiLinea.next();
				impostaAdiacente(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private void impostaAdiacente(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta " + stanzaDa);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta " + nomeA);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check (boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.entrata;
	}

	public Stanza getStanzaVincente() {
		return this.uscita;
	}
}
