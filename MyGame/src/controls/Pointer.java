package controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class Pointer {
	int worldX = 0;
	int worldY = 0;
	int width = 2;
	int height = 2;
	int col; int row;
	GamePanel gp;
	//int[][] tela = new int[gp.maxScreenCol][gp.maxSreenRow];
	
	
	public Pointer(GamePanel gp) {
		this.gp = gp;
	}
	
	public void click_monster(Entity en) {
		for (int i = 0; i < gp.entityList.size(); i++) {
			Rectangle mouse = new Rectangle(worldX, worldY, gp.tileSize, gp.tileSize);
			
			if (gp.entityList.get(i) != null) {
				
				Entity atual = gp.entityList.get(i);
				
				System.out.println(atual.name);
			}
		}
	}
	
	public boolean isCollisionEntity(Entity target) {
		int screenX = target.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = target.worldY - gp.player.worldY + gp.player.screenY;
		Rectangle en = new Rectangle(screenX, screenY, target.solidArea.width, target.solidArea.height);
		Rectangle iM = new Rectangle(worldX, worldY, width, height);
		if (en.intersects(iM)) {
			return true;
		}
		return false;
	}
	
	public boolean isCollisionButtonZoomMinMiniMap(Button_Class target) {
		//int screenX = target.x - gp.player.worldX + gp.player.screenX;
		//int screenY = target.y - gp.player.worldY + gp.player.screenY;
		Rectangle en = new Rectangle(target.x, target.y, target.width, target.height);
		Rectangle iM = new Rectangle(worldX, worldY, width, height);
		if (en.intersects(iM)) {
			return true;
		}
		return false;
	}
	
	public void update() {
		
		//if (isCollision(gp.m1)) {
		//	System.out.println(gp.m1.name);
		//}
		
		//for (int i = 0; i < gp.entityList.size(); i++) {
			//if (gp.entityList.get(i) != null) {
				//Entity atual = gp.entityList.get(i);
				//if (atual instanceof Player) {
				//	if (this.isCollision(atual)) {
				//		System.out.println(atual.name);			
				//	}
			//	}
			//}
		//}
	}
	
	public void clicked() {
		if (isCollisionEntity(gp.m1)) {
			System.out.println(gp.m1.name);
			gp.player.target = gp.m1;
		}
		
		for (int i = 0; i < gp.buttonsList.size(); i++) {
			if (gp.buttonsList.get(i)!=null) {
				Button_Class atual = gp.buttonsList.get(i);
				
				if (isCollisionButtonZoomMinMiniMap(atual)) {
					atual.use();
				}
			}
		}
		
	}
	
	
	public void setPointer(int x, int y) {
		worldX = x;
		worldY = y;
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		g2.setColor(Color.white);
		g2.fillRect(worldX, worldY, width, height);
	}
}
