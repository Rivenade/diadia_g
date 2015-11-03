package it.uniroma3.grafica.states;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackTrack {

	public static void sound ()  {

		File file = new File("res/audio/Scumm Bar.wav");
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			System.out.println("formato file audio non supportato");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("il file audio è stato danneggiato o rimosso");
			e.printStackTrace();
		}
		
		AudioFormat format = ais.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		Clip clip = null;
		try {
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
		} catch (IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner ss = new Scanner(System.in);
		if (ss.nextLine().equals("s")) {
			clip.stop();
		}
	}

}
