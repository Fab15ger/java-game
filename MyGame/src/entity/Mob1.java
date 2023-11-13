package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;


public class Mob1 extends Entity {
	
	GamePanel gp;

	public Mob1(GamePanel gp) {
		super(gp);
		this.gp = gp;
		getImage();
		getImagesEfects();
		worldX = 12*gp.tileSize;
		worldY = 7*gp.tileSize;
		type = "Monster";
		maxLife = 100;
		life = maxLife;
		
	}
	
	public void getImage() {		
		down1 = setup("/player/orc1", gp.tileSize, gp.tileSize);
}	

	public void update() {
		
		if (life<=0) {
			alive = false;
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
		

		
		
		if (alive) {
			g2.setColor(Color.black);
			g2.fillRect(screenX, (screenY - gp.tileSize/2), gp.tileSize, 10);
			
			double oneScale = (double)gp.tileSize/maxLife;
			double hpBarValue = oneScale*life;
			
			double p = (double)(life / maxLife);
			g2.setColor(Color.green);
			g2.fillRect(screenX, (screenY - gp.tileSize/2), (int) (hpBarValue), 10);
		}
		
	}
}
