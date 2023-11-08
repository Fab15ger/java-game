	package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		
		int tileNum1, tileNum2, x, y;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];			
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;		
		case "nortwest":
			x = (entity.worldX+gp.tileSize) / gp.tileSize;
			y = (entity.worldY-gp.tileSize) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][x][y];		
			if (gp.tileM.tile[tileNum1].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		case "norteast":
			x = (entity.worldX-gp.tileSize) / gp.tileSize;
			y = (entity.worldY-gp.tileSize) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][x][y];		
			if (gp.tileM.tile[tileNum1].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		case "southwest":
			x = (entity.worldX+gp.tileSize) / gp.tileSize;
			y = (entity.worldY+gp.tileSize) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][x][y];		
			if (gp.tileM.tile[tileNum1].collision == true) {
				entity.collisionOn = true;
			}		
			break;
		case "southeast":
			x = (entity.worldX-gp.tileSize) / gp.tileSize;
			y = (entity.worldY+gp.tileSize) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][x][y];		
			if (gp.tileM.tile[tileNum1].collision == true) {
				entity.collisionOn = true;
			}		
			break;			
		}
	}
}
