package it.uniroma3.diadia.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Finestra;
import it.uniroma3.grafica.ImageLoader;
import it.uniroma3.grafica.sprite.Sprite;
import it.uniroma3.grafica.sprite.SpriteSheet;

public class Rendering extends JPanel implements ActionListener {
	
	private static Finestra f;
	
	public Rendering () {
		JButton b1 = new JButton("puppa");
		b1.addActionListener(this);
		b1.setToolTipText("puppa tanto");
		add(b1);
	}
	
	public static void main (String[] args) throws MalformedURLException {
		f = new Finestra("JUST TESTING", 400, 400);
		Graphics g = f.getCanvas().getGraphics();
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sprite.png"));
		Assets.init();
		BufferedImage morg = Assets.morgana;
		BufferedImage merl = new Sprite(sheet, 1, 3, 32, 32).getBufferedImage();
		g.drawImage(morg, 20, 20, (int) (morg.getWidth()*2), (int)(morg.getHeight()*2.2) , null);
		g.drawImage(merl, Finestra.getWidth()-100, 20, (int) (merl.getWidth()*1.5), (int)(merl.getHeight()*1.5), null);
		Font ft = new Font("Helvetica", Font.PLAIN, 20);
		g.setFont(ft);
		
		Rendering r = new Rendering();
		r.setOpaque(true);
		JFrame frame = new JFrame("ButtonDemo");
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(r);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println ("ahia");
	}

}