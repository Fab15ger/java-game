package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Energy_Dmg extends Projectile{

	GamePanel gp;
	
	public OBJ_Energy_Dmg(GamePanel gp,int worldX,int worldY,String direction, boolean alive, Entity user,double dx,double dy,Entity target, int dmg, int atk_type) {
		super(gp, worldX, worldY, direction, alive, user, dx, dy, target, dmg, atk_type);
		this.gp = gp;
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		this.dx = dx;
		this.dy = dy;
		this.target = target;	
		this.dmg = dmg;
		speed = 40;
		maxLife = 180;
		life = maxLife;
		attack_type = atk_type;

		alive = false;
		getImage();
	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (spriteNum == 1) {image = anim1;}
		if (spriteNum == 2) {image = anim2;}
		if (spriteNum == 3) {image = anim3;}
		if (spriteNum == 4) {image = anim4;}
		if (spriteNum == 5) {image = anim5;}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);					
	}
}
