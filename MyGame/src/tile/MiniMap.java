package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;


public class MiniMap extends TileManager {

	int a1 = 3458;
	int b1 = 4567;
	int at = a1;
	
	int[][] miniMapLocal = new int[20][12]; 

	public MiniMap(GamePanel gp) {
		super(gp);
		this.gp = gp;
		updateLocalMap();
	}
	
	public void updateLocalMap() {
		
		int iX = (gp.player.worldX/gp.tileSize) - (20/2);
		int iY = (gp.player.worldY/gp.tileSize) - (12/2);
		
		for (int i = 0; i < 20; i++) {	
			for (int j = 0; j < 12; j++) {
					miniMapLocal[i][j] = mapTileNum[gp.currentMap][i+iX][j+iY];
			}
		}
	}
	
	public void drawMiniMap(Graphics2D g2) {
		
		int width = 200;
		int height = 120;
		int x = gp.screenWidth - width - 8;
		int y = 8;
		int size = 10;
		
		//double scaleX = (double) (gp.tileSize * gp.maxWorldCol)/width;
		//double scaleY = (double) (gp.tileSize * gp.maxWorldCol)/height;
		
		int playerScreenX = (x+width/2);
		int playerSscreenY = (y+height/2);
		
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 12; j++) {
				
				int screenX = (x+(i*size));
				int screenY = (y+(j*size));

				// null
				if(miniMapLocal[i][j] == 0) {
					g2.setColor(Color.black);
					g2.fillRect(screenX, screenY, size, size);
				}				
				// agua
				if(miniMapLocal[i][j] == 01 || miniMapLocal[i][j] == 02) {
					g2.setColor(Color.green);
					g2.fillRect(screenX, screenY, size, size);
				}
				
				//mato
				if(miniMapLocal[i][j] == 18 || miniMapLocal[i][j] == 19
						|| miniMapLocal[i][j] == 26 || miniMapLocal[i][j] == 24
						|| miniMapLocal[i][j] == 21 || miniMapLocal[i][j] == 23
						|| miniMapLocal[i][j] == 28 || miniMapLocal[i][j] == 29
						|| miniMapLocal[i][j] == 30 || miniMapLocal[i][j] == 31) {
					g2.setColor(new Color(20, 80, 200));
					g2.fillRect(screenX, screenY, size, size);
				}
				// arvore
				if(miniMapLocal[i][j] == 16) {
					g2.setColor(new Color(20, 150, 41));
					g2.fillRect(screenX, screenY, size, size);
				}
				if(miniMapLocal[i][j] == 100) {
					g2.setColor(Color.yellow);
					g2.fillRect(screenX, screenY, size, size);
				}
				
				// player
				g2.setColor(new Color(255, 255, 0));
				g2.fillRect(playerScreenX, playerSscreenY, size, size);
		}}
	}
}
