package it.uniroma3.grafica.states;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartingMenu extends State {
	
	private BufferedImage sfondo;
	JButton start = new JButton("Start");

	
	public StartingMenu (Handler handler) {
		super(handler);
	}
	
	@Override
	public void render (Graphics g) {
		JPanel p = new JPanel();
		p.add(start);
		handler.getFinestra().getFrame().add(p);
		sfondo = Assets.background;
		g.drawImage(sfondo, 0, 0, DiaDia.WIDTH*DiaDia.SCALE, DiaDia.HEIGHT*DiaDia.SCALE, null);

		
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
