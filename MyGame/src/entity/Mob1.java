package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;


public class Mob1 extends Entity {	
	
	GamePanel gp;
	int countHeal = 0;
	
	public Mob1(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		getImage();
		getImagesEfects();
		
		worldX = 12*gp.tileSize;
		worldY = 7*gp.tileSize;
		name = "Orc";
		type = "monster";
		maxLife = 520;
		life = maxLife;
				
		attack_type = ATK_FIRE;
		
	}	
	
	public void getImage() {		
		down1 = setup("/player/orc1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/dead_orc", gp.tileSize, gp.tileSize);
}	

	public void setAction() {
			actionLockCounter++;
			
			if (actionLockCounter >= 120) {
				Random randon = new Random();
				int i = randon.nextInt(100)+1;
				
				if (i <= 25 ) {
					//direction="up";
					worldX -= 10;
				}
				
				if (i > 25 && i <= 50) {
					//direction = "down";
					worldX += 10;
				}
				
				if (i > 50 && i <= 75) {
					//direction = "right";
					worldX += 10;
				}
				if (i > 75 && i <= 100) {
					//direction = "left";
					worldX -= 10;
				}
				actionLockCounter=0;
			}
		}

	public void dmgReceive(Entity user, int hitPoints) {
		life-=hitPoints;
		target = user;
	}
	
	public void update() {
		
		super.update();
		

		if (target == null) {
			for (int i = 0; i < gp.entityList.size(); i++) {
				Entity p1 = gp.entityList.get(i);
				if (p1 instanceof Player) {
					//target = p1;
				}
			}
		}
		
		if (alive) {
			atk(target);
		}
		
		
		if (ticks_heal < delay_heal) {
			ticks_heal ++;
		}
		
		if (life <= (int) (this.maxLife*0.8)) {
			
			Random r = new Random();
			heal(r.nextInt(100)+150);
		}
		
		if (life<=0) {
			alive = false;
			down1 = down2;
		};
		
		if (efectDmgReceive) {
			
			spriteCounterBlood++;
			if (spriteCounterBlood > 6) {
				if (spriteNumBlood == 1) {
					spriteNumBlood = 2;
				}
				else if (spriteNumBlood == 2) {
					spriteNumBlood++;
				}else if (spriteNumBlood == 3) {
					spriteNumBlood++;
				}
				else if (spriteNumBlood == 4) {
					spriteNumBlood++;
				}else if (spriteNumBlood == 5) {
					spriteNumBlood=1;
					efectDmgReceive = false;
				}
				spriteCounterBlood = 0;
			
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		super.draw(g2);
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		int percHp = (life*100)/maxLife;
		
		if (alive) {
			g2.setColor(Color.black);
			g2.fillRect(screenX-1, ((screenY - gp.tileSize/2)-1), gp.tileSize+2, 5+2);
			
			double oneScale = (double)gp.tileSize/maxLife;
			double hpBarValue = oneScale*life;
			
			if (percHp >= 90) {
				g2.setColor(new Color(40, 237, 40));
			}
			if (percHp < 90 && percHp >= 70) {
				g2.setColor(new Color(120, 237, 10));
			}
			if (percHp < 70 && percHp >= 50) {
				g2.setColor(new Color(255, 255, 0));
			}
			if (percHp < 50 && percHp >= 40) {
				g2.setColor(new Color(237, 170, 10));
			}
			if (percHp < 40 && percHp >= 10) {
				g2.setColor(new Color(255, 	0, 0));
			}
			if (percHp < 10) {
				g2.setColor(new Color(127, 0, 0));
			}
			g2.drawString(name, screenX, (screenY - gp.tileSize/2)-10);
			g2.fillRect(screenX, (screenY - gp.tileSize/2), (int) (hpBarValue), 5);
		}	
	}
}
