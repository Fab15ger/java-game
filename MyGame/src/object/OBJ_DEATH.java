package object;

import java.awt.Graphics2D;
import java.util.Random;



import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_DEATH extends Projectile{

	GamePanel gp;
	
	public OBJ_DEATH(GamePanel gp,String direction, boolean alive, Entity user,double dx,double dy,Entity target, int dmg, int dmgM, int atk_type) {
		super(gp, direction, alive, user, dx, dy, target, dmg, dmgM, atk_type);
		this.gp = gp;
		
		this.worldX = user.worldX;
		this.worldY = user.worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		this.dx = dx;
		this.dy = dy;
		this.target = target;
		this.dmg = dmg(user);
		speed = 50;
		maxLife = 180;
		life = maxLife;
		attack_type = atk_type;

		alive = false;
		getImage();
	}
	
	public int dmg(Entity user) {
		
		double maxDamage, minDamage;
		maxDamage = (user.level * 2.5) + (user.magic * 6.5);
		minDamage = (user.level * 2) + (user.magic * 4.5);

		Random number = new Random();		
		return number.nextInt((int)maxDamage + 1 - (int)minDamage) + (int)minDamage;
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
