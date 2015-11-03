package it.uniroma3.grafica.entity.creatures;

import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;
import it.uniroma3.grafica.entity.Entity;
import it.uniroma3.grafica.tile.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final int DEFAULT_WIDTH = 64,
							DEFAULT_HEIGHT = 64;
	public static final float DEFAULT_SPEED = 12.0f; /*TODO sarebbe buono normalizzarlo in base a px/secondo,
							altrimenti varia in base alla macchina o all'efficienza temporanea del sistema */
	protected int health; 
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height, boolean solid, Id id) {
		super(handler, x, y, width, height, solid, id);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	//Muovo separatamente x e y (vedi collision detection)
	public void move () {
		moveX();
		moveY();
	}
	
	public void moveX () {
		if (xMove > 0) { //mi muovo a destra
			
			int temp = (int) (x + xMove + bounds.x + bounds.width) / Tile.DEFAULT_WIDTH; //in coordinate "di tile" invece che "di pixel"
			if (!collisionWithTile(temp, (int) (y + bounds.y) / Tile.DEFAULT_HEIGHT) &&
					!collisionWithTile(temp, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_HEIGHT)) {
				x += xMove;
			}
			
		} else if (xMove < 0) { //mi muovo a sinistra
			int temp = (int) (x + xMove + bounds.x) / Tile.DEFAULT_WIDTH; //in coordinate "di tile" invece che "di pixel"
			if (!collisionWithTile(temp, (int) (y + bounds.y) / Tile.DEFAULT_HEIGHT) &&
					!collisionWithTile(temp, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_HEIGHT)) {
				x += xMove;
			}
		}
	}
	
	public void moveY ()  {
		if (yMove < 0) { // Up
			int temp = (int) (y + yMove + bounds.y ) / Tile.DEFAULT_HEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x)/Tile.DEFAULT_WIDTH, temp) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width)/Tile.DEFAULT_WIDTH, temp)) {
				y += yMove;
			}
		} else if (yMove > 0 ) { //Down
			int temp = (int) (y + yMove + bounds.y + bounds.height) / Tile.DEFAULT_HEIGHT;

			if (!collisionWithTile((int) (x + bounds.x)/Tile.DEFAULT_WIDTH, temp) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width)/Tile.DEFAULT_WIDTH, temp)	) {
				y += yMove;
			}
		}
	}
	
	protected boolean collisionWithTile (int x, int y) {
		return handler.getRoom().getTile(x, y).isSolid();
	}
	
	//GETTERS SETTERS
	
	public int getHealth() {
		return health;
	}

	public float getxMove() {
		return xMove;
	}



	public void setxMove(float xMove) {
		this.xMove = xMove;
	}



	public float getYmove() {
		return yMove;
	}



	public void setYmove(float yMove) {
		this.yMove = yMove;
	}



	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
