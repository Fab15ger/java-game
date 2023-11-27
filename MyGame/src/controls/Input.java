package controls;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.w3c.dom.events.MouseEvent;

import main.GamePanel;

public class Input implements MouseListener, MouseMotionListener{
	
	GamePanel gp;
		
	public Input(GamePanel gp) {
		this.gp = gp;
	}


	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		gp.p.clicked();
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());	
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		gp.p.setPointer(e.getX(), e.getY());
	}}
