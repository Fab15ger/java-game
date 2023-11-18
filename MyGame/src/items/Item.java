package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Item {
	
	GamePanel gp;
	BufferedImage img, img1, img2, img3;
	
	public int worldX;
	public int worldY;
	
	int counter;
	int spr = 1;
	
	public Item(GamePanel gp) {
		
		this.gp = gp;
		
		getImage();
		
		img = img1;
		
		worldX = 15*gp.tileSize;
		worldY = 13*gp.tileSize;
		
	
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
		img1 = setup("/efects/wand_fire_1", gp.tileSize, gp.tileSize);
		img2 = setup("/efects/wand_fire_2", gp.tileSize, gp.tileSize);
		img3 = setup("/efects/wand_fire_3", gp.tileSize, gp.tileSize);
	}
	
	public void update() {
		
		counter++;
		
		if (counter >15) {
			if (spr == 1) {
				spr ++;
			} else if (spr == 2) {
				spr ++;
			} else if (spr == 3) {
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
		case 3: img = img3; break;
		}
		
		g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
		//g2.drawImage(img, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
		
}
