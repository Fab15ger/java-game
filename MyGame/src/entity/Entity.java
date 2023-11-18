package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public boolean collisionOn = false;
	public BufferedImage image, up1, up2, up3, down1, down2, down3, left1,left2, left3, right1, right2, right3;
	public BufferedImage image_efect_dmg, anim1, anim2, anim3, anim4, anim5, animBlood1, animBlood2, animBlood3, animBlood4, animBlood5;
	public int worldX;
	public int worldY;
	boolean attacking = false;
	int spriteCounter = 0;
	int spriteCounterBlood = 0;
	public int spriteNum = 1;
	public int spriteNumBlood = 1;
	int standCounter;
	public boolean alive = true;
	public int maxLife;
	public int life;
	public int shotAvalableCounter = 0;
	public double dx, dy;
	public int mx, my;
	public Entity target;
	public Projectile projectile;
	public Projectile dmg_energy;
	String name;
	int tempo = 0;
	int tempoDelay;
	public String direction = "down";
	public int delaySpeed;
	public int speed;
	public int col;
	public int row;
	public String type;
	public Rectangle solidArea = new Rectangle(48,48,48,48);
	boolean explosion = false;
	boolean efectDmgReceive = false;
	public int delay_hit;
	public int counterHit = 0;
	public int level;
	public int magic;
	
	public int actionLockCounter;
	
	public int atkNumber = 0;
	
	public int attack_type;
	public int ATK_PHYSIC = 0;
	public int ATK_FIRE = 1;
	public int ATK_ICE = 2;
	public int ATK_EARTH = 3;
	public int ATK_DEATH = 4;
	public int ATK_HOLLY = 5;
	public int ATK_ENERGY = 6;

	public Entity(GamePanel gp) {
		this.gp = gp;
		
	}

	public double[] mira(Entity alvo) {
		
		double[] coordinates = new double[2];
		
        double angle = 0;
        int px = 0;
        int py = 0;	        
        mx = (alvo.worldX) + (gp.tileSize/2);
        my = alvo.worldY + (gp.tileSize/2);
        angle = Math.atan2(my - (worldY+ (gp.tileSize/2)), mx - (worldX+ (gp.tileSize/2)));                   
        double dx = Math.cos(angle);
        double dy = Math.sin(angle);
        
        coordinates[0] = dx;
        coordinates[1] = dy;
        
        return coordinates;
	}
	
	public BufferedImage setup(String imageName, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return image;
	}
	
	public void update() {
		
		spriteCounter++;
		if (spriteCounter>=24) {
			if (spriteNum==1) {
				spriteNum++;
			}else if (spriteNum==2) {
				spriteNum =1;
			}
			spriteCounter=0;
		}
		
		if (efectDmgReceive) {
			
			spriteCounterBlood++;
			if (spriteCounterBlood > 6) {
				if (spriteNumBlood == 1) {
					spriteNumBlood = 2;
				}
				else if (spriteNumBlood == 2) {
					spriteNumBlood++;
				}else if (spriteNumBlood == 3) {
					spriteNumBlood++;
				}
				else if (spriteNumBlood == 4) {
					spriteNumBlood++;
				}else if (spriteNumBlood == 5) {
					spriteNumBlood=1;
					efectDmgReceive = false;
				}
				spriteCounterBlood = 0;
			}
		}
	}
	
	public void getImagesEfects() {
		animBlood1 = setup("/efects/receive_dmg1", gp.tileSize, gp.tileSize);
		animBlood2 = setup("/efects/receive_dmg2", gp.tileSize, gp.tileSize);
		animBlood3 = setup("/efects/receive_dmg3", gp.tileSize, gp.tileSize);
		animBlood4 = setup("/efects/receive_dmg4", gp.tileSize, gp.tileSize);
		animBlood5 = setup("/efects/receive_dmg5", gp.tileSize, gp.tileSize);
	}
	
	public void damageReceive(Entity enemy) {
		efectDmgReceive = true;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX && 
				worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY  - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			switch (direction) {
			case "up":
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
				break;
			case "down":
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}
				break;
			case "right":
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
				break;
			case "left":
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
				break;
			
				case "nortwest":
					if (spriteNum == 1) {image = up1;}
					if (spriteNum == 2) {image = up2;}
					break;
				case "norteast":
					if (spriteNum == 1) {image = down1;}
					if (spriteNum == 2) {image = down2;}
					break;
				case "southwest":
					if (spriteNum == 1) {image = right1;}
					if (spriteNum == 2) {image = right2;}
					break;
				case "southeast":
					if (spriteNum == 1) {image = left1;}
					if (spriteNum == 2) {image = left2;}
					break;
				}

			if (alive) {
				g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		}
		
		if (efectDmgReceive) {
			if (spriteNumBlood == 1) {image_efect_dmg = animBlood1;}
			if (spriteNumBlood == 2) {image_efect_dmg = animBlood2;}
			if (spriteNumBlood == 3) {image_efect_dmg = animBlood3;}
			if (spriteNumBlood == 4) {image_efect_dmg = animBlood4;}
			if (spriteNumBlood == 5) {image_efect_dmg = animBlood5;}
			g2.drawImage(image_efect_dmg, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
