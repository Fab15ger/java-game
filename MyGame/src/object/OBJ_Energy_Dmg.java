package object;

import java.awt.Graphics2D;
import java.util.Random;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Energy_Dmg extends Projectile{

	GamePanel gp;
	
	public OBJ_Energy_Dmg(GamePanel gp,String direction, boolean alive, Entity user,double dx,double dy,Entity target, int dmgMax, int dmgMin, int atk_type) {
		super(gp, direction, alive, user, dx, dy, target, dmgMax, dmgMin, atk_type);
		this.gp = gp;
		
		this.target = target;
		
		this.user = user;
		
		this.worldX = user.worldX;
		this.worldY = user.worldY;
		
		this.direction = direction;
		this.alive = alive;
		
		this.life = this.maxLife;
		this.dx = dx;
		this.dy = dy;
			
		this.dmg = dmg(dmgMax, dmgMin);
					
		speed = 40;
		maxLife = 180;
		life = maxLife;
		attack_type = atk_type;

		alive = false;
		getImage();
	}
	
	public int dmg(int dmgMax, int dmgMin) {

		Random number = new Random();		
		return number.nextInt((int)dmgMax + 1 - (int)dmgMin) + (int)dmgMin;
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
		
		if (!explosion) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
							
	}
}
