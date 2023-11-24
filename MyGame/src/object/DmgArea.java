package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Mob1;
import main.GamePanel;
import main.UtilityTool;

public class DmgArea {
	
	GamePanel gp;
	int attack_type;
	
	//  TYPES
	public String type;

	public int ATK_PHYSIC = 0;
	public int ATK_FIRE = 1;
	public int ATK_ICE = 2;
	public int ATK_EARTH = 3;
	public int ATK_DEATH = 4;
	public int ATK_HOLLY = 5;
	public int ATK_ENERGY = 6;
	
	int spriteNum = 1;
	
	int worldX, worldY;
	
	boolean explosion = false;
	
	int spriteCounter = 0;
	
	int poder = 350;
	
	int time;
	
	public BufferedImage anim1, anim2, anim3, anim4, anim5, anim6, anim7, anim8, image;
	
	Entity user;

	public DmgArea(GamePanel gp, int attack_type, int worldX, int worldY, Entity user) {
		this.gp = gp;
		this.worldX = worldX;
		this.worldY = worldY;
		this.attack_type = attack_type;
		this.user = user;
		getImage();
	}
	
	boolean isCollision(Entity en) {
		
		Rectangle e = new Rectangle(en.worldX, en.worldY, en.solidArea.width, en.solidArea.height);
		Rectangle iM =  new Rectangle(worldX, worldY, gp.tileSize, gp.tileSize);
		
		if (e.intersects(iM)) {
			return true;
		}
		
		return false;
	}
	
	public void getImage() {
		
		if (attack_type == ATK_ENERGY) {
			anim1 = setup("/efects/efect_energy_explosion_1", gp.tileSize, gp.tileSize);
			anim2 = setup("/efects/efect_energy_explosion_2", gp.tileSize, gp.tileSize);
			anim3 = setup("/efects/efect_energy_explosion_3", gp.tileSize, gp.tileSize);
			anim4 = anim3;
			anim5 = setup("/efects/efect_energy_explosion_4", gp.tileSize, gp.tileSize);
			anim6 = setup("/efects/efect_energy_explosion_4", gp.tileSize, gp.tileSize);
			anim7 = setup("/efects/efect_energy_explosion_5", gp.tileSize, gp.tileSize);
			anim8 = anim6;
		}
		if (attack_type == ATK_DEATH) {
			anim1 = setup("/efects/death_bomb1", gp.tileSize, gp.tileSize);
			anim2 = setup("/efects/death_bomb2", gp.tileSize, gp.tileSize);
			anim3 = setup("/efects/death_bomb3", gp.tileSize, gp.tileSize);
			anim4 = setup("/efects/death_bomb4", gp.tileSize, gp.tileSize);
			anim5 = setup("/efects/death_bomb5", gp.tileSize, gp.tileSize);
		}
		if (attack_type == ATK_FIRE) {
			
			anim1 = setup("/efects/efect_fire_explosion_01", gp.tileSize, gp.tileSize);
			anim2 = setup("/efects/efect_fire_explosion_02", gp.tileSize, gp.tileSize);
			anim3 = setup("/efects/efect_fire_explosion_03", gp.tileSize, gp.tileSize);
			anim4 = setup("/efects/efect_fire_explosion_04", gp.tileSize, gp.tileSize);
			anim5 = setup("/efects/efect_fire_explosion_05", gp.tileSize, gp.tileSize);
			anim6 = setup("/efects/efect_fire_explosion_06", gp.tileSize, gp.tileSize);
			anim7 = setup("/efects/efect_fire_explosion_07", gp.tileSize, gp.tileSize);
			anim8 = setup("/efects/efect_fire_explosion_08", gp.tileSize, gp.tileSize);
		}
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
		
		time++;
		
		for (int i = 0; i < gp.entityList.size(); i++) {
			if (gp.entityList.get(i) !=null) {
				Entity atual = gp.entityList.get(i);
				if (atual instanceof Mob1) {
					if(isCollision(atual)) {
						if (time<2d) {
							atual.life-=poder;
						}
						
					}
				}
			}
		}

		spriteCounter ++;
			if (spriteCounter > 3) {
				if (spriteNum == 1) {
					spriteNum++;
				}
				else if (spriteNum == 2) {
					spriteNum++;
				}else if (spriteNum == 3) {
					spriteNum++;
				}
				else if (spriteNum == 4) {
					spriteNum++;
				}
				else if (spriteNum == 5) {
						spriteNum++;
				}
				else if (spriteNum == 6) {
					spriteNum++;
				}
				else if (spriteNum == 7) {
					spriteNum++;
				}
				else if (spriteNum == 8) {
					gp.dmgAreaList.remove(this);
					//gp.entityList.remove(this);
				}
				spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (spriteNum == 1) {image = anim1;}
		if (spriteNum == 2) {image = anim2;}
		if (spriteNum == 3) {image = anim3;}
		if (spriteNum == 4) {image = anim4;}
		if (spriteNum == 5) {image = anim5;}
		if (spriteNum == 6) {image = anim6;}
		if (spriteNum == 7) {image = anim7;}
		if (spriteNum == 8) {image = anim8;}
		
		//if (!explosion) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		//}
	}
}
