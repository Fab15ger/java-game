package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ui {
	
	GamePanel gp;
	
	public Ui(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		
		int x; int y;
		x = gp.screenWidth-200;
		y = 270;
		
		int percHp = (gp.player.life*100)/gp.player.maxLife;
		
		double oneScale = (double)gp.tileSize/gp.player.maxLife;
		double hpBarValue = oneScale*gp.player.life;
		g2.setColor(Color.black);	
		g2.fillRect(x, (y - gp.tileSize/2), gp.tileSize, 5);
		g2.setColor(Color.red);		
		g2.fillRect(x, (y - gp.tileSize/2), (int) (hpBarValue), 5);
	}

}
