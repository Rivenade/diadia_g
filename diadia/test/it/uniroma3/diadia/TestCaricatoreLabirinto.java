package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;


public class TestCaricatoreLabirinto {
	
	private final static String FORMATO_SBAGLIATO = "Stanza: Entrata, Corridoio, Uscita/n" +
													"Entrata: Entrata/n" +
													"Uscita: Uscita/n" +
													"Attrezzi:/n" +
													"Adiacenti: Entrata nord Corridoio, Corridoio sud Entrata, Corriodio nord Uscita, Uscita sud Corridoio";
	
	private final static String FORMATO_CORRETTO = "Stanze: Entrata, Corridoio, Uscita\n" +
													"Entrata: Entrata\n" +
													"Uscita: Uscita\n" +
													"Attrezzi: \n" +
													"Adiacenti: Entrata nord Corridoio, Corridoio sud Entrata, Corridoio nord Uscita, Uscita sud Corridoio\n"+
													"Personaggi: Carlo\n";
	
	@Test (expected = FileNotFoundException.class)
	public void Test_file_inesistente () throws FileNotFoundException {
		new CaricatoreLabirinto("nonEsisto.txt");
	}

	@Test (expected = FormatoFileNonValidoException.class)
	public void Test_carica_nonValido () throws FileNotFoundException, FormatoFileNonValidoException, IOException {
		StringReader str = new StringReader(FORMATO_SBAGLIATO);
		CaricatoreLabirinto c = new CaricatoreLabirinto(str);
		c.carica();
	}
	
	
	@Test
	public void Test_corretto () {
		StringReader str = new StringReader(FORMATO_CORRETTO);
		CaricatoreLabirinto corretto;
		try {
			corretto = new CaricatoreLabirinto (str);
			corretto.carica();
			assertEquals("Entrata", corretto.getStanzaIniziale().getNome());
			assertEquals("Uscita", corretto.getStanzaVincente().getNome());
			
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}