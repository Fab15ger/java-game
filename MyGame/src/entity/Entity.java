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
	public BufferedImage image;
	public int worldX;
	public int worldY;
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
	
	public void update() {}
	
	public void draw(Graphics2D g2) {}
}
