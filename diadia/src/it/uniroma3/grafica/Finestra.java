package it.uniroma3.grafica;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Finestra  {

	private JFrame frame;
	private Canvas canvas;
	
	private String titolo;
	private static int width;
	private static int height;
	
	public Finestra (String titolo, int width, int height) {
		this.titolo = titolo;
		this.setWidth(width);
		this.setHeight(height);
		
		creaFinestra();
	}
	
	private void creaFinestra () {
		frame = new JFrame (titolo);
		frame.setSize(getWidth(), getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(getWidth(), getHeight()));
		canvas.setMaximumSize(new Dimension(getWidth(), getHeight()));
		canvas.setMinimumSize(new Dimension(getWidth(), getHeight()));
		canvas.setFocusable(false);
		canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));;
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas () {
		return this.canvas;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Finestra.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		Finestra.height = height;
	}
	
	
	public JFrame getFrame () {
		return this.frame;
	}
	
}
