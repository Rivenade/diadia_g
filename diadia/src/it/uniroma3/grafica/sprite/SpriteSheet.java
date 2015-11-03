package it.uniroma3.grafica.sprite;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet (BufferedImage bi) {

		sheet = bi;
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return sheet.getSubimage(x*width - width, y*height - height, width, height);
	}

}
