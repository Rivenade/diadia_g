package it.uniroma3.grafica.entity;

import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y; //pixel interi, ma utile per i conti
	protected int width, height;
	public boolean solid;
	public int facing = 0; //0 front, 1 left, 2 right, 3 back 
	protected Rectangle bounds;
	
	public Id id;
	
	
	public Entity(Handler handler, float x, float y, int width, int height, boolean solid, Id id) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		
		bounds = new Rectangle(0, 0, width, height);	//bounding box come lo sprite, per default
	}
	

	
	public boolean isNear (Entity en) {
		boolean near = false;
		double centersDistance = Math.sqrt(Math.pow(this.getCenterX()-en.getCenterX(), 2) +
				  Math.pow(this.getCenterY()-en.getCenterY(), 2));
		if (centersDistance <= 72.0) near = true;
		return near;	
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//GETTERS SETTERS
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setSolid (boolean solid) {
		this.solid = solid;
	}
	
	public Id getId () {
		return id;
	}
	
	public float getCenterX() {
		return  (this.x + this.width/2);
	}
	
	public float getCenterY () {
		return (this.y + this.height/2);
	}
}
