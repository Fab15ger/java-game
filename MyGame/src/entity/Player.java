package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		
		image = setup("/tile/004", gp.tileSize, gp.tileSize);
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		
	}
	
	public void setDefaultValues() {
		worldX = 10*gp.tileSize;
		worldY = 8*gp.tileSize;
		speed = 3;
	}
	
	public void update() {

		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

			if (keyH.upPressed) {direction = "up";} 
			if (keyH.downPressed) {direction = "down";}
			if (keyH.leftPressed) {direction = "left";}
			if (keyH.rightPressed) {direction = "right";}
					
			switch(direction) {
				case "up":worldY -= speed; break;
				case "down":worldY += speed; break;
				case "right":worldX += speed; break;
				case "left":worldX -= speed; break;			
					
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
	}

}
