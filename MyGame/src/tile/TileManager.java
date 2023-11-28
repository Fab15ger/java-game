package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	public Tile[] tile;
	GamePanel gp;
	public int mapTileNum[][][];
	boolean drawPath = true;
	public int miniMapScreenSize = 50;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[50];
		
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		
		loadMap("/map/map_01.txt", 0);
	}
	
	public void getTileImage() {
		
		// FLOOR / ROAD
		setup(0, "000", false);// NULL
		setup(1, "001", false);// FLOOR GRASS
		setup(2, "002", false);
		setup(3, "003", false);// FLOOR ROAD
		setup(4, "004", false);// FLOOR ROAD
		setup(5, "005", false);// FLOOR ROAD
		setup(6, "006", false);// FLOOR ROAD
		setup(7, "007", false);// FLOOR ROAD
		setup(8, "008", false);// FLOOR ROAD
		setup(9, "009", false);// FLOOR ROAD
		setup(10, "010", false);// FLOOR ROAD
		setup(11, "011", false);// FLOOR ROAD
		setup(12, "012", false);// FLOOR ROAD
		setup(13, "013", false);// FLOOR ROAD
		setup(14, "014", false);// FLOOR ROAD
		setup(15, "015", false);// FLOOR ROAD
		setup(16, "016", true);//TREE 
		
		// EARTH
		setup(17, "017", false);// FLOOR EARTH
		
		// WATER
		setup(18, "018", false);
		setup(19, "019", false);
		setup(20, "020", true);//water // true
		setup(21, "021", true);//water // true
		setup(22, "022", true);//water // true
		setup(23, "023", true);//water // true
		setup(24, "024", true);//water // true
		setup(25, "025", true);//water // true
		setup(26, "026", true);//water // true
		setup(27, "027", true);//water // true
		setup(28, "028", true);
		setup(29, "029", true);
		setup(30, "030", true);
		setup(31, "031", true);
		setup(32, "032", true);// WALL
		setup(34, "034", false);// WOOD FLOOR - PISO MADEIRA
		setup(35, "035", true);// TABLE
		
		setup(40, "032", true);// WALL
		setup(41, "016", false);//TREE
		setup(33, "033", false);// HOUSE	
		setup(43, "034", false);// WOOD FLOOR - PISO MADEIRA
		setup(44, "035", true);// TABLE
		setup(36, "036", false);// LADDER - TOP
		setup(37, "037", false);// LADDER - BOTTOM
		
		setup(46, "060", false);// WOOD FLOOR - PISO cinza claro
		setup(47, "061", false);// WOOD FLOOR - PISO branco
		
		///setup(38, "050", true);// TERRAIN
		///setup(39, "051", true);// TERRAIN
		//setup(40, "052", true);// TERRAIN
		//setup(41, "053", true);// TERRAIN
		///setup(42, "054", true);// TERRAIN
		//setup(43, "055", true);// TERRAIN
		//setup(44, "056", true);// TERRAIN
		//setup(45, "057", true);// TERRAIN
		

	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
		
		}
	}
	
	public void loadMap(String path, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
			
		} catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX && 
					worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY  - gp.tileSize < gp.player.worldY + gp.player.screenY
					) {
				try {
				g2.drawImage(tile[tileNum].image, screenX,screenY,null);
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol=0;
				worldRow++;
			}
		}							
	}
}
