package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class Projectile extends Entity{
	
	public Entity user;
	public int dmg;
	
	public Projectile(GamePanel gp,int worldX,int worldY,String direction, boolean alive, Entity user,double dx,double dy,Entity target, int dmg, int atk_type) {
		super(gp);
		
	}
	
	boolean isCollision(Entity en) {
		
		Rectangle e = new Rectangle(en.worldX, en.worldY, en.solidArea.width, en.solidArea.height);
		Rectangle iM =  new Rectangle(worldX, worldY, solidArea.width, solidArea.height);
		
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
			anim4 = setup("/efects/efect_energy_explosion_4", gp.tileSize, gp.tileSize);
			anim5 = setup("/efects/efect_energy_explosion_5", gp.tileSize, gp.tileSize);
		}
		if (attack_type == ATK_DEATH) {
			anim1 = setup("/efects/death_bomb1", gp.tileSize, gp.tileSize);
			anim2 = setup("/efects/death_bomb2", gp.tileSize, gp.tileSize);
			anim3 = setup("/efects/death_bomb3", gp.tileSize, gp.tileSize);
			anim4 = setup("/efects/death_bomb4", gp.tileSize, gp.tileSize);
			anim5 = setup("/efects/death_bomb5", gp.tileSize, gp.tileSize);
		}
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
			
			if (!explosion) {
				target.damageReceive(user);
				target.life -= dmg;
				System.out.println(dmg);
				gp.player.atkNumber += 1;
				//System.out.println(gp.player.atkNumber);
			}
			explosion = true;
		}
		
		life -- ;
		if (life <= 0) {
			alive = false;
		}
		
		if (explosion) {
			spriteCounter ++;
			if (spriteCounter > 3) {
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
					//spriteNum=1;
					explosion = false;
					alive = false;
					if (target.life <= 0) {
						user.target = null;
						
					}
					gp.entityList.remove(this);
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
