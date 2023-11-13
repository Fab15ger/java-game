package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class Projectile extends Entity{
	
	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
		
	}
	
	public void set(int wordX, int worldY, String direction, boolean alive, Entity user, double dx, double dy, Entity alvo) {
		
		this.worldX = wordX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		this.dx = dx;
		this.dy = dy;
		this.target = alvo;
	}
	
	boolean isCollision(Entity en) {
		
		Rectangle e = new Rectangle(en.worldX, en.worldY, en.solidArea.width, en.solidArea.height);
		Rectangle iM =  new Rectangle(worldX, worldY, solidArea.width, solidArea.height);
		
		if (e.intersects(iM)) {
			return true;
		}
		
		return false;
	}
	
	public void update() {

		col = (worldX)/gp.tileSize;
		row = (worldY)/gp.tileSize;		
		
		if (!isCollision(target)) {
			worldX += speed * mira(target)[0];
			worldY+= speed * mira(target)[1] ;
		} else {
			this.worldX = target.worldX;
			this.worldY = target.worldY;
			target.damageReceive(user);
			if (!explosion) {
				target.life -= 30;
			}
			explosion = true;
		}
		
		life -- ;
		if (life <= 0) {
			alive = false;
		}
		
		if (explosion) {
			spriteCounter ++;
			if (spriteCounter > 6) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum++;
				}else if (spriteNum == 3) {
					spriteNum++;
				}
				else if (spriteNum == 4) {
					spriteNum++;
				}else if (spriteNum == 5) {
					spriteNum=1;
					explosion = false;
					alive = false;
				}
				spriteCounter = 0;
			}
		}
	}
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	public void subtractResource(Entity user) {}
		
}
