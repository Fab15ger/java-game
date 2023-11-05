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
		delaySpeed = 0;
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void getPlayerImage() {
		
		image = setup("/player/player_image", gp.tileSize, gp.tileSize);

}
	
	public void setDefaultValues() {
		solidArea.x = 1;
		solidArea.y = 1;
		solidArea.height = 46;
		solidArea.width = 46;
		
		worldX = 11*gp.tileSize;
		worldY = 7*gp.tileSize;
		speed = 8;
	}
	
	public void update() {

		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed
				|| keyH.nortWest || keyH.southWest || keyH.nortEast || keyH.southEast) {

			if (keyH.upPressed) {direction = "up";} 
			if (keyH.downPressed) {direction = "down";}
			if (keyH.leftPressed) {direction = "left";}
			if (keyH.rightPressed) {direction = "right";}
			if (keyH.nortWest) {direction = "nortwest";}
			if (keyH.southWest) {direction = "southwest";}
			if (keyH.nortEast) {direction = "norteast";}
			if (keyH.southEast) {direction = "southeast";}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			delaySpeed++;
			if (delaySpeed >= speed) {
				if (collisionOn == false) {
					switch(direction) {
						case "up":worldY -= gp.tileSize; break;
						case "down":worldY += gp.tileSize; break;
						case "right":worldX += gp.tileSize; break;
						case "left":worldX -= gp.tileSize; break;
						case "nortwest": worldY -= gp.tileSize; worldX += gp.tileSize; break;
						case "norteast": worldY -= gp.tileSize; worldX -= gp.tileSize; break;
						case "southwest": worldY += gp.tileSize; worldX += gp.tileSize; break;
						case "southeast": worldY += gp.tileSize; worldX -= gp.tileSize; break;											
					}
				}
				delaySpeed=0;
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, screenX, screenY, null);
		//g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
	}

}
