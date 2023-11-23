package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ELements.Fields;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_DEATH;

public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int mx, my;
	Fields f1;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		getImagesEfects();
		
		this.gp = gp;
		getPlayerImage();
		this.keyH = keyH;
		image = down1;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		delaySpeed = 0;

		setDefaultValues();
		delay_hit = 120;

		attack_type = ATK_ENERGY;

		//target = gp.m1;
		
//		int f = 3;
//		
//		for (int i = 0; i < f; i++) {
//			for (int j = 0; j < f; j++) {
//				addField(11+i, 9+j);
//			}
//			
//		}

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
		
		col = worldX/gp.tileSize;
		row = worldY/gp.tileSize;
		
		level = 18;
		magic = 12;
		maxLife = ((level-8) * 15) + 185;
		maxMana = ((level*5))+50;
		life = maxLife;
		mana = maxMana;
		life /= 2;
		
		speed = 12;
		
		target = gp.m1;
		attack_type = ATK_PHYSIC;
		
		delay_heal = 60;
	}
	
	public void update() {
		
		if (keyH.zoomMaxPressed || keyH.zoomMinPressed || keyH.rightScreenPressed || keyH.leftScreenPressed
				|| keyH.upScreenPressed || keyH.downScreenPressed) {
			
			if (keyH.zoomMaxPressed) {keyH.zoomMaxPressed=false;gp.miniMap.zoom = 2;}
			if (keyH.zoomMinPressed) {keyH.zoomMinPressed=false;gp.miniMap.zoom = 4;}
			
			if (keyH.leftScreenPressed) {keyH.leftScreenPressed=false;gp.miniMap.posX-=1;}
			if (keyH.rightScreenPressed) {keyH.rightScreenPressed=false;gp.miniMap.posX+=1;}
			
			if (keyH.upScreenPressed) {keyH.upScreenPressed=false;gp.miniMap.posY-=1;}
			if (keyH.downScreenPressed) {keyH.downScreenPressed=false; gp.miniMap.posY+=1;}
				
			gp.miniMap.updateValues();
			gp.miniMap.updateLocalMap();
		}
		
		/// delays
		if (ticks_heal < delay_heal) {
			ticks_heal ++;
		}
		
		if (target == null) {
			for (int i = 0; i < gp.entityList.size(); i++) {
				Entity p1 = gp.entityList.get(i);
				if (p1 instanceof Mob1) {
					target = p1;
				}
			}
		}
		
		if (target!=null && target.alive) {
			atk(target);
		}
		
		
		if (healing_animation) {
			healAnimationDelay ++;
			if (healAnimationDelay > 5) {
				if (healing_spr == 1) {healing_spr ++;} 
				if (healing_spr == 2) {healing_spr ++;}
				else if (healing_spr == 3) {healing_spr ++;}
				else if (healing_spr == 4) {healing_spr ++;}
				else if (healing_spr == 5) {healing_spr ++;}
				else if (healing_spr == 6) {healing_spr = 1;healing_animation = false;}	
				healAnimationDelay = 0;
			}
		}
		
		col = (worldX)/gp.tileSize;
		row = (worldY)/gp.tileSize;
		
		if (delaySpeed < speed) {
			delaySpeed++;
		}
		
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
			
			
			if (delaySpeed >= speed) {
				if (collisionOn == false) {
					switch(direction) {
						case "up":if(worldY>0)worldY -= gp.tileSize; image = up1; break;
						case "down":if(worldY<gp.worldHeight-gp.tileSize*2)worldY += gp.tileSize; image = down1; break;
						case "right":if(worldX<gp.worldWidth-gp.tileSize*2)worldX += gp.tileSize; image = right1; break;
						case "left":if(worldX>0)worldX -= gp.tileSize; image = left1; break;
						case "nortwest": worldY -= gp.tileSize; worldX += gp.tileSize; image = right1; break;
						case "norteast": worldY -= gp.tileSize; worldX -= gp.tileSize; image = left1; break;
						case "southwest": worldY += gp.tileSize; worldX += gp.tileSize; image = right1; break;
						case "southeast": worldY += gp.tileSize; worldX -= gp.tileSize; image = left1; break;	
						
					}
					gp.miniMap.updateLocalMap();
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
		
		
		if (efectDmgReceive) {
			spriteCounterBlood++;
			if (spriteCounterBlood > 3) {
				if (spriteNumBlood == 1) {spriteNumBlood++;}
				else if (spriteNumBlood == 2) {spriteNumBlood++;}
				else if (spriteNumBlood == 3) {spriteNumBlood++;}
				else if (spriteNumBlood == 4) {spriteNumBlood++;}
				else if (spriteNumBlood == 5) {spriteNumBlood=1;efectDmgReceive = false;}
				spriteCounterBlood = 0;
			}
		}	
		
		
		if (gp.keyH.healPressed) {
			heal(level*2);
			gp.keyH.healPressed = false;
		}
		
		if (gp.keyH.shootPressesd && target != null) {
			
			gp.keyH.shootPressesd = false;
			projectile = new OBJ_DEATH(gp, direction, true, this, dx, dy, target, 0, 0, ATK_DEATH);
			//gp.tileM.miniMapScreenSize -= 10;
			// CHECK VACANCY
			for (int i = 0; i < gp.projectile[1].length; i++) {
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
		
		if (alive) {
			g2.setColor(Color.black);
			g2.fillRect(screenX-1, ((screenY - gp.tileSize/2)-1), gp.tileSize+2, 10+2);
			
			double oneScale = (double)gp.tileSize/maxLife;
			double hpBarValue = oneScale*life;
			
			g2.setColor(Color.green);
			g2.fillRect(screenX, (screenY - gp.tileSize/2), (int) (hpBarValue), 10);
		}
		
		if (healing_animation) {
			if (healing_spr == 1) {image_effect_heal = heal_01;}
			if (healing_spr == 2) {image_effect_heal = heal_02;}
			if (healing_spr == 3) {image_effect_heal = heal_03;}
			if (healing_spr == 4) {image_effect_heal = heal_04;}
			if (healing_spr == 5) {image_effect_heal = heal_05;}
			if (healing_spr == 6) {image_effect_heal = heal_06;}
			g2.drawImage(image_effect_heal, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
		if (efectDmgReceive) {
			if (spriteNumBlood == 1) {image_efect_dmg = animBlood1;}
			if (spriteNumBlood == 2) {image_efect_dmg = animBlood2;}
			if (spriteNumBlood == 3) {image_efect_dmg = animBlood3;}
			if (spriteNumBlood == 4) {image_efect_dmg = animBlood4;}
			if (spriteNumBlood == 5) {image_efect_dmg = animBlood5;}
			g2.drawImage(image_efect_dmg, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
		for (int i = 0; i < entityProj.size(); i++) {
			if (entityProj.get(i).explosion) {
				g2.drawImage(entityProj.get(i).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
