package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Classe che si occupa di costruire il comando da eseguire in base
 * all'istruzione passata per parametro tramite un meccanismo
 * di riconoscimento basato sull'introspezione
 * 
 * @author Lorenzo
 *
 */
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	/**
	 * Costruisce il comando a partire dall'istruzione
	 * 
	 * @param istruzione stringa per il riconoscimento dele comando
	 * @return comando corrispondente
	 */
	public AbstractComando costruisciComando(String istruzione) {
		AbstractComando comando = null;
		Scanner scannerNomeEParametro = new Scanner(istruzione);
		String nome = null, parametro = null;
		if (scannerNomeEParametro.hasNext())
			nome = scannerNomeEParametro.next();
		if (scannerNomeEParametro.hasNext())
			parametro = scannerNomeEParametro.next();
		scannerNomeEParametro.close();
		String nomeCompleto = costruisciNomeCompletamenteQualificato(nome);
		try {
			Class<?> classeComando = Class.forName(nomeCompleto);
			comando = (AbstractComando) classeComando.newInstance();
			} catch (InstantiationException e) {
				System.out.println("Il comando richiesto non ammette un costruttore vuoto");
				throw new RuntimeException();
			} catch (IllegalAccessException e) {
				System.out.println("currently executing method does not have access to the definition of the specified class");
				throw new RuntimeException();
			} catch (ClassNotFoundException e) {
				return new ComandoNonValido();
			}
		if (parametro != null)
			comando.setParametro(parametro);
		return comando;
	}
	
	private String costruisciNomeCompletamenteQualificato(String nomeComando){
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		if (nomeComando == null) {
			nomeClasse.append("NonValido");
			return nomeClasse.toString();
		}
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1).toLowerCase());
		return nomeClasse.toString();
	}
}




//	COSì E' COME L'AVEVAMO FATTA IN CLASSE (più o meno)

//@Override
//	public AbstractComando costruisciComando(String istruzione) {
//		Scanner scanner = new Scanner(istruzione);
//		String nome = null, parametro = null, nomeCompleto = null;
//		AbstractComando comando = null;
//		if (scanner.hasNext())
//			nome = scanner.next();
//		if (scanner.hasNext())
//			parametro = scanner.next();
//		scanner.close();
//		nomeCompleto = this.costruisciNomeCompletamenteQualificato(nome);
//		try {
//			Class classeComando = Class.forName(nomeCompleto);
//			comando = (AbstractComando) classeComando.newInstance();
//		} catch (ClassNotFoundException e) {
//			System.out.println("Il comando non è stato riconosciuto");
//			return new ComandoNonValido();
//		} catch (InstantiationException | IllegalAccessException e) {
//			/* QUI VA CAMBIATO. COSI' FA UN PO' SCHIFO! */
//			System.out.println("Sono stati rilevati errori con la costruzione del comando");
//		}
//		if (comando != null && parametro != null)
//			comando.setParametro(parametro);
//		return comando;
//	}
//	

//	
//	/* Metodo creato per il testiing. Il nome "fa schifo" bisognerebbe cambiarlo */
//	public static String getMapIstruzioneToClasse(String nomeComando){
//		return new FabbricaDiComandiRiflessiva().costruisciNomeCompletamenteQualificato(nomeComando);
//	}
//}