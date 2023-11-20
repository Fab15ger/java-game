package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, nortWest,southWest, nortEast, southEast, shootPressesd,healPressed;
	
	boolean debug = true;

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		playState(code);							
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_NUMPAD4) {leftPressed=true;}
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP || code == KeyEvent.VK_NUMPAD8) {upPressed=true;}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_NUMPAD6) {rightPressed=true;}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_NUMPAD2) {downPressed=true;}
		if (code == KeyEvent.VK_NUMPAD9) {nortWest=true;}
		if (code == KeyEvent.VK_NUMPAD7) {nortEast=true;}
		if (code == KeyEvent.VK_NUMPAD3) {southWest=true;}
		if (code == KeyEvent.VK_NUMPAD1) {southEast=true;}
		
		if (code == KeyEvent.VK_F5 || code == KeyEvent.VK_NUMPAD5) {shootPressesd = true;}
		if (code == KeyEvent.VK_F7 || code == KeyEvent.VK_NUMPAD0) {healPressed = true;}
		
		if (code == KeyEvent.VK_C) {debug=true;}
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_NUMPAD4) {leftPressed=false;}
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP || code == KeyEvent.VK_NUMPAD8) {upPressed=false;}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_NUMPAD6) {rightPressed=false;}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_NUMPAD2) {downPressed=false;}
		if (code == KeyEvent.VK_NUMPAD9) {nortWest=false;}
		if (code == KeyEvent.VK_NUMPAD7) {nortEast=false;}
		if (code == KeyEvent.VK_NUMPAD3) {southWest=false;}
		if (code == KeyEvent.VK_NUMPAD1) {southEast=false;}
	}
}
