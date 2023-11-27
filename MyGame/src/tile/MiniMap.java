package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;


public class MiniMap extends TileManager {
	
	// 1, 2, 4, 5, 10 
	
	public int zoom = 1;
	
	public int posX = 0;
	public int posY = 0;
	
	int numColuns = 10*zoom;
	int numRows = 10*zoom;
	int size = 20/zoom;
	
	public int width = 200;
	public int height = 200;
	int x = gp.screenWidth - width - 8;
	int y = 8;
	
	int iX;
	int iY;
	
	
	int[][] miniMapLocal = new int[numColuns][numRows]; 

	public MiniMap(GamePanel gp) {
		super(gp);
		this.gp = gp;
		updateLocalMap();
	}
	
	public void updateValues() {
		numColuns = 10*zoom;
		numRows = 10*zoom;
		size = 20/zoom;
		miniMapLocal = new int[numColuns][numRows]; 
	}
	
	public void updateLocalMap() {
		
		iX = (gp.player.worldX/gp.tileSize) - (numColuns/2);
		iY = (gp.player.worldY/gp.tileSize) - (numRows/2);
		
		for (int i = 0; i < numColuns; i++) {	
			for (int j = 0; j < numRows; j++) {
				try {
					miniMapLocal[i][j] = mapTileNum[gp.currentMap][(i+iX)+posX][(j+iY)+posY];
					
				} catch (Exception e) {
					
					miniMapLocal[i][j] = 0;
					
				}	
			}
		}
		try {
			miniMapLocal[(numColuns/2)+posX*-1][(numRows/2)+posY*-1] = 100;
		}catch (Exception e) {
		}
	}
	
	public void zoomMore() {

		updateValues();
		updateLocalMap();
	}
	
	
	public void drawMiniMap(Graphics2D g2) {
		
		//double scaleX = (double) (gp.worldWidth)/width;
		//double scaleY = (double) (gp.worldHeight)/height;
		
		for (int i = 0; i < numColuns; i++) {
			for (int j = 0; j < numRows; j++) {
				
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
				if(miniMapLocal[i][j] == 18 || miniMapLocal[i][j] == 19) {
						g2.setColor(new Color(20, 80, 200));
						g2.fillRect(screenX, screenY, size, size);		
				}
				if(miniMapLocal[i][j] == 26 || miniMapLocal[i][j] == 24
						|| miniMapLocal[i][j] == 21 || miniMapLocal[i][j] == 23
						|| miniMapLocal[i][j] == 28 || miniMapLocal[i][j] == 29
						|| miniMapLocal[i][j] == 30 || miniMapLocal[i][j] == 31) {
					
					g2.setColor(new Color(20, 150, 41));
					g2.fillRect(screenX, screenY, size, size);
				}
				// arvore
				if(miniMapLocal[i][j] == 16) {
					g2.setColor(new Color(20, 150, 41));
					g2.fillRect(screenX, screenY, size, size);
				}
				
				//player				
				if(miniMapLocal[i][j] == 100) {
					g2.setColor(new Color(255, 255, 0));
					g2.fillRect(screenX, screenY, size, size);
				}
		}}
	}
}
