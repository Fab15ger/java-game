package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ELements.Fields;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Energy_Dmg;
import object.OBJ_DEATH;

public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int mx, my;
	Fields f1;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.gp = gp;
		getPlayerImage();
		this.keyH = keyH;
		image = down1;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		delaySpeed = 0;
		
		setDefaultValues();
		delay_hit = 120;
		shotAvalableCounter = 30;
		level = 125;
		magic = 42;
		
		int f = 3;
		
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < f; j++) {
				addField(11+i, 9+j);
			}
			
		}

	}
	
	public void addField(int x, int y) {
		for (int i = 0; i < gp.fields[1].length; i++) {
			if (gp.fields[gp.currentMap][i] == null) {
				gp.fields[gp.currentMap][i] = f1 = new Fields(gp, x, y);
				break;
			}
		}
	}
	
	public void getPlayerImage() {		
		down1 = setup("/player/player_female_down1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/player_female_down2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/player_female_down3", gp.tileSize, gp.tileSize);
		
		left1 = setup("/player/player_female_left1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/player_female_left2", gp.tileSize, gp.tileSize);
		left3 = left2;
		
		right1 = setup("/player/player_female_right1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/player_female_right2", gp.tileSize, gp.tileSize);
		right3 = right2;
		
		up1 = setup("/player/player_female_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/player_female_up2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/player_female_up3", gp.tileSize, gp.tileSize);
}
	
	public void setDefaultValues() {
		
		solidArea.x = 1;
		solidArea.y = 1;
		solidArea.height = 46;
		solidArea.width = 46;

		worldX = 11*gp.tileSize;
		worldY = 7*gp.tileSize;
		
		speed = 12;
		
		target = gp.m1;
		attack_type = ATK_PHYSIC;
	}
	
	public void update() {
		
		col = (worldX)/gp.tileSize;
		row = (worldY)/gp.tileSize;

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
						case "up":worldY -= gp.tileSize; image = up1; break;
						case "down":worldY += gp.tileSize; image = down1; break;
						case "right":worldX += gp.tileSize; image = right1; break;
						case "left":worldX -= gp.tileSize; image = left1; break;
						case "nortwest": worldY -= gp.tileSize; worldX += gp.tileSize; image = right1; break;
						case "norteast": worldY -= gp.tileSize; worldX -= gp.tileSize; image = left1; break;
						case "southwest": worldY += gp.tileSize; worldX += gp.tileSize; image = right1; break;
						case "southeast": worldY += gp.tileSize; worldX -= gp.tileSize; image = left1; break;	
						
					}
				}
				delaySpeed=0;
			}
			
			spriteCounter++;
			if (spriteCounter>=12) {
				if (spriteNum==1) {spriteNum = 2;}
				else if (spriteNum==2) {spriteNum = 3;}
				else if (spriteNum==3) {spriteNum =1;}
				spriteCounter=0;
			}
			
		}else {			
			standCounter++;
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		

		
		if (target != null) {
			
			counterHit ++;
			
			if (delay_hit <= counterHit) {

				dmg_energy = new OBJ_Energy_Dmg(gp, worldX, worldY, direction, true, this, dx, dy, target, 80, ATK_ENERGY);
				counterHit = 0;
			
			
			for (int i = 0; i < gp.projectile[1].length; i++) {
				if (gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = dmg_energy;
					break;
				}
			}
		}
		}
		
		if (gp.keyH.shootPressesd && target != null) {
			gp.keyH.shootPressesd = false;
			//projectile.subtractResource(this);
			
			projectile = new OBJ_DEATH(gp, worldX, worldY, direction, true, this, dx, dy, target, 18, ATK_DEATH);

			// CHECK VACANCY
			for (int i = 0; i < gp.projectile[1].length; i++) {
				//gp.projectile[gp.currentMap][i] = null;
				if (gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}	
			}		
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
				if (spriteNum == 3) {image = up3;}
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}
				if (spriteNum == 3) {image = down3;}
				
			}				
			break;
		case "nortwest":
			if (attacking  == false) {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
				if (spriteNum == 3) {image = right3;}
			}
			break;
		case "southwest":
			if (attacking  == false) {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
				if (spriteNum == 3) {image = right3;}
			}
			break;			
		case "right":
			if (attacking  == false) {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
				if (spriteNum == 3) {image = right3;}
			}
			break;			
		case "left":
			
			if (attacking == false) {
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
				if (spriteNum == 3) {image = left3;}
			}
			break;
		case "norteast":
			
			if (attacking == false) {
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
				if (spriteNum == 3) {image = left3;}
			}
			break;
		case "southeast":
			
			if (attacking == false) {
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
				if (spriteNum == 3) {image = left3;}
			}
			break;			
	}
			
		g2.drawImage(image, screenX, screenY, null);
	}


}
