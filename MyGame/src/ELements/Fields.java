package ELements;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class Fields {
	
	GamePanel gp;
	BufferedImage img, img1, img2;
	
	int delayDmg = 120;
	int ticksDamage = delayDmg;

	
	public int worldX;
	public int worldY;
	public int width;
	public int height;
	
	int counter;
	int spr = 1;
	
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
	
	
	
	public Fields(GamePanel gp, int worldX, int worldY) {
		
		this.gp = gp;
		this.width = gp.tileSize;
		this.height = gp.tileSize;
		getImage();
		
		attack_type = ATK_FIRE;
		
		img = img1;
		
		this.worldX = worldX*gp.tileSize;
		this.worldY = worldY*gp.tileSize;
		
	
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
	
	public void getImage() {
		img1 = setup("/efects/fire_field_1", gp.tileSize, gp.tileSize);
		img2 = setup("/efects/fire_field_2", gp.tileSize, gp.tileSize);
	}
	
	public void damage(Entity target) {
			
			if (ticksDamage>= delayDmg) {
				ticksDamage = 0;
				target.life-=20;
				target.efectFireField = true;
			}
	}
	
	public boolean isCollision(Entity target) {
		
		Rectangle en = new Rectangle(target.worldX, target.worldY, target.solidArea.width, target.solidArea.height);
		Rectangle iM = new Rectangle(worldX, worldY, width, height);
		
		if (en.intersects(iM)) {
			return true;
		}
		return false;
	}
	
	public void update() {
		
		if (ticksDamage < delayDmg) {
			ticksDamage++;
		}
		
		for (int i = 0; i < gp.entityList.size(); i++) {
			if (gp.entityList.get(i) != null) {
				Entity atual = gp.entityList.get(i);
				if (atual instanceof Player) {
					if (this.isCollision(atual)) {
						
						damage(atual);
						
						
					}
				}
			}
		}
		
		
		
		counter++;
		
		if (counter >15) {
			if (spr == 1) {
				spr ++;
			} else if (spr == 2) {
				spr = 1;
			}
			counter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		switch (spr) {
		case 1: img = img1; break;
		case 2: img = img2; break;
		}
		
		g2.drawImage(img, screenX, screenY, width, height, null);
		//g2.drawImage(img, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
		
}
