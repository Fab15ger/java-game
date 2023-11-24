package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Energy_Dmg;

public class Entity {
	
	GamePanel gp;
	
	public BufferedImage image, up1, up2, up3, down1, down2, down3, left1,left2, left3, right1, right2, right3;
	
	public BufferedImage image_efect_dmg, image_effect_heal, anim1, anim2, anim3, anim4, anim5,anim6, anim7, anim8,
	animBlood1, animBlood2, animBlood3, animBlood4, animBlood5;
	public BufferedImage heal_01, heal_02, heal_03, heal_04, heal_05, heal_06;

	public ArrayList<Entity> entityProj = new ArrayList<>();

	public Rectangle solidArea = new Rectangle(48,48,48,48);
	
	
	// STATES
	public boolean collisionOn = false;
	boolean attacking = false;
	public boolean alive = true;
	public int worldX;
	public int worldY;
	public double dx, dy;
	public int mx, my;
	public String direction = "down";
	public int col;
	public int row;
	protected boolean explosion = false;
	boolean efectDmgReceive = false;
	boolean healing_animation = false;
	int healing_spr = 1;
	
	// ATTRIBUTES
	public Entity target;
	public Projectile projectile;
	public Projectile dmg_energy;
	String name;
	public int level;
	public int maxLife;
	public int life;
	public int maxMana;
	public int mana;
	public int magic;
	public int speed;
	public int delaySpeed;
	
	// DELAYS
	int spriteCounter = 0;
	int spriteCounterBlood = 0;
	public int spriteNum = 1;
	public int spriteNumBlood = 1;
	int standCounter;
	public int actionLockCounter;
	int healAnimationDelay = 0;
	public int delay_hit = 120;
	public int counterHit = 0;
	int delay_heal = 120;
	int ticks_heal = 120;
	int tempo = 0;
	int tempoDelay;
	
	//  TYPES
	public String type;
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
		} catch (IOException e) {}
		return image;
	}
	
	public void update() {
		
		if (ticks_heal < delay_heal) {
			ticks_heal ++;
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
		
		if (efectDmgReceive) {
			spriteCounterBlood++;
			if (spriteCounterBlood > 6) {
				if (spriteNumBlood == 1) {spriteNumBlood++;}
				else if (spriteNumBlood == 2) {spriteNumBlood++;}
				else if (spriteNumBlood == 3) {spriteNumBlood++;}
				else if (spriteNumBlood == 4) {spriteNumBlood++;}
				else if (spriteNumBlood == 5) {spriteNumBlood=1;efectDmgReceive = false;}
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
		
		heal_01 = setup("/efects/heal_anim_01", gp.tileSize, gp.tileSize);
		heal_02 = setup("/efects/heal_anim_02", gp.tileSize, gp.tileSize);
		heal_03 = setup("/efects/heal_anim_03", gp.tileSize, gp.tileSize);
		heal_04 = setup("/efects/heal_anim_04", gp.tileSize, gp.tileSize);
		heal_05 = setup("/efects/heal_anim_05", gp.tileSize, gp.tileSize);
		heal_06 = setup("/efects/heal_anim_06", gp.tileSize, gp.tileSize);
		
	}
	
	public void heal(int amount) {
			if (ticks_heal >= delay_heal) {
				healing_animation = true;
				life+= amount;		
				if (life>maxLife) {
					life=maxLife;
				}
				ticks_heal = 0;
			}
	}
	
	public void atk(Entity target) {
			counterHit ++;
			if (delay_hit <= counterHit && target != null) {
				dmg_energy = new OBJ_Energy_Dmg(gp, direction, true, this, dx, dy, target, 168, 57, attack_type);
				counterHit = 0;
			for (int i = 0; i < gp.projectile[1].length; i++) {
				if (gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = dmg_energy;
					break;
				}
			}
		}
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
		
		if (healing_animation) {
			if (healing_spr == 1) {image_effect_heal = heal_01;}
			if (healing_spr == 2) {image_effect_heal = heal_02;}
			if (healing_spr == 3) {image_effect_heal = heal_03;}
			if (healing_spr == 4) {image_effect_heal = heal_04;}
			if (healing_spr == 5) {image_effect_heal = heal_05;}
			if (healing_spr == 6) {image_effect_heal = heal_06;}
			g2.drawImage(image_effect_heal, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
		
		for (int i = 0; i < entityProj.size(); i++) {
			if (entityProj.get(i).explosion) {
				g2.drawImage(entityProj.get(i).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
