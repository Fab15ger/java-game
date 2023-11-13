package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{

	GamePanel gp;
	
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		

		speed = 40;
		maxLife = 180;
		life = maxLife;

		alive = false;
		getImage();
	}
	public void getImage() {
		anim1 = setup("/efects/death_bomb1", gp.tileSize, gp.tileSize);
		anim2 = setup("/efects/death_bomb2", gp.tileSize, gp.tileSize);
		anim3 = setup("/efects/death_bomb3", gp.tileSize, gp.tileSize);
		anim4 = setup("/efects/death_bomb4", gp.tileSize, gp.tileSize);
		anim5 = setup("/efects/death_bomb5", gp.tileSize, gp.tileSize);
	}
	public void draw(Graphics2D g2) {
		super.draw(g2);
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		//BufferedImage image = null;
		if (spriteNum == 1) {image = anim1;}
		if (spriteNum == 2) {image = anim2;}
		if (spriteNum == 3) {image = anim3;}
		if (spriteNum == 4) {image = anim4;}
		if (spriteNum == 5) {image = anim5;}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		//g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
					
	}
	public Color getParticleColor() {
		Color color = new Color(240,50,0);
		return color;
	}
	public int getParticleSize() {
		int size = 10; // 6 pixels
		return size;
	}	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
