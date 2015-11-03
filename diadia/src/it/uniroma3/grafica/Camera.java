package it.uniroma3.grafica;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.grafica.entity.Entity;


public class Camera {
	
	private float xOffset, yOffset;
	private DiaDia gioco;
	
	public Camera(DiaDia gioco, float xOffset, float yOffset) {
		this.gioco = gioco;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void centerOnEntity (Entity e) {
		xOffset = e.getX() - gioco.getWidth() / 2 + e.getWidth()/2;
		yOffset = e.getY() - gioco.getHeight() / 2 + e.getHeight()/2;
	}
	
	public void move (float xAmt, float yAmt)  {
		xOffset += xAmt;
		yOffset += yAmt;
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
}
