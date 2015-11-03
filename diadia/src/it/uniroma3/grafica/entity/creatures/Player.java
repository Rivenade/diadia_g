package it.uniroma3.grafica.entity.creatures;


import it.uniroma3.grafica.Assets;
import it.uniroma3.grafica.Handler;
import it.uniroma3.grafica.Id;

import java.awt.Graphics;


public class Player extends Creature {
	//1 front, 0 left, 2 right, 3 back 
	private int frame = 0;
	private int frameDelay = 0;
	
	private boolean animate = false;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT, true, Id.player);	
		
		//TEST
		bounds.x = 16;
		bounds.y = 16;
		bounds.width = 32;
		bounds.height = 32;
	}

	public void render(Graphics g) {
		if (facing == 0) {
			g.drawImage(Assets.player[frame].getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		} else if (facing == 1) {
			g.drawImage(Assets.player[frame+3].getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		} else if (facing == 2) {
			g.drawImage(Assets.player[frame+6].getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		} else if (facing == 3) {
			g.drawImage(Assets.player[frame+9].getBufferedImage(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		}
		
		
		//VISUALIZZO I BOUNDS
//		g.setColor (Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getCamera().getyOffset()),
//				bounds.width, bounds.height);
	}

	@Override
	public void tick() {
		getInput();
		move();
		handler.getCamera().centerOnEntity(this);
		
		//ANIMATION
		
		if(handler.getKeyInput().up || handler.getKeyInput().down ||
				handler.getKeyInput().left || handler.getKeyInput().right) animate = true;
		else animate = false;
		
		if (animate) {
			frameDelay++;
			if (frameDelay >= 7) {
				frame++;
				if (frame >= 2) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}
		if (!animate) {
			frame = 1;
		}
		
	}
	
	private void getInput () {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyInput().up){
			yMove = -speed;
			facing = 0;
		}
		if(handler.getKeyInput().down){
			yMove = speed;
			facing = 1;
		}
		if(handler.getKeyInput().left){
			xMove = -speed;
			facing = 3;
		}
		if(handler.getKeyInput().right){
			xMove = speed;
			facing = 2;
		}		

	}
	

}