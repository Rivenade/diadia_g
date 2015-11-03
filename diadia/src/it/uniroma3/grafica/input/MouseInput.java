package it.uniroma3.grafica.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{


	public int xMov, yMov,
			xPre, yPre;
	public boolean pressed = false;

	
	public void tick () {
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		xMov = e.getX();
		yMov = e.getY();	
	}

	@Override
	public void mousePressed(MouseEvent e) {

		xPre = e.getX();
		yPre = e.getY();
		pressed = true;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		pressed = false;

	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
