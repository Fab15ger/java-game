package entity;

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
	public int worldX;
	public int worldY;
	boolean attacking = false;
	int spriteCounter = 0;
	int spriteNum = 1;
	int standCounter;
	
	String name;
	int tempo = 0;
	int tempoDelay;
	public String direction = "down";
	public int delaySpeed;
	public int speed;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	
	public Entity(GamePanel gp) {
		this.gp = gp;
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
		
		if (spriteCounter>=24) {
			if (spriteNum==1) {
				spriteNum++;
			}else if (spriteNum==2) {
				spriteNum =1;
			}
			spriteCounter=0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
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
		}
	}
}
