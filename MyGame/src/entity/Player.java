package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		
	}
	
	public void setDefaultValues() {
		worldX = 0;
		worldY = 0;
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

		g2.setColor(Color.white);
		g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);
		
	}

}
