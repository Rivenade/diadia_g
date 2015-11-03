package it.uniroma3.grafica.states;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Noises {

	
	public static void play ()  {
		File bussa;
		Clip clipBussa;
		bussa = new File("res/audio/bussa.wav");
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(bussa);
		} catch (UnsupportedAudioFileException e) {
			System.out.println("formato file audio non supportato");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("il file audio è stato danneggiato o rimosso");
			e.printStackTrace();
		}
		
		AudioFormat format = ais.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		try {
			clipBussa = (Clip)AudioSystem.getLine(info);
			clipBussa.open(ais);
			clipBussa.start();
		} catch (IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
