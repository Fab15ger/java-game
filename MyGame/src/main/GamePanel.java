	package main;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import entity.Entity;
import entity.Mob1;
import entity.Player;
import tile.TileManager;



public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 16 x 16
	public final int maxScreenCol = 20;
	public final int maxSreenRow = 12;
	public final int screenWidth = maxScreenCol * tileSize; // 960 pixels
	public final int screenHeight = maxSreenRow * tileSize; // 576 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 250;
	public final int maxWorldRow = 250;
	public final int maxMap = 10;
	public int currentMap = 0;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow  * tileSize;
	
	// FOR FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	//FPS
	private int FPS = 60;
	
	// SYSTEM
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	
	//public Rune_SD sd;
	public Mob1 m1 = new Mob1(this);
	public Player player = new Player(this, keyH);
	public Entity projectile[][] = new Entity[maxMap][20];
	public ArrayList<Entity> entityList = new ArrayList<>();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
	}
	
	// SETUP GAME
	public void setupGame() {
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();

	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {

		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime = 0;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread!=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				//repaint();
				drawToTempScreen(); // draw everything to the buffered image
				drawToScreen(); // draw the buffered image to the screen
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);

				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update() {		
		m1.update();
		player.update();
		
		for (int i = 0; i < projectile[1].length; i++) {
			if (projectile[currentMap][i] != null) {
				if (projectile[currentMap][i].alive) {
					projectile[currentMap][i].update();
				}
				if (!projectile[currentMap][i].alive) {
					projectile[currentMap][i] = null;
				}
				
			}
		}
		
	}
	public void drawToTempScreen() {
		
		//g2.setColor(Color.black);
		//g2.fillRect(0, 0, screenWidth, screenHeight);
		
		tileM.draw(g2);
		
		
		entityList.add(player);
		entityList.add(m1);
		
		for (int i = 0; i < projectile[1].length; i++) {
			if (projectile[currentMap][i] != null) {
				entityList.add(projectile[currentMap][i]);
			}
		}
		
		// SORT
		Collections.sort(entityList, new Comparator<Entity>() {
			
			@Override
			public int compare(Entity e1, Entity e2) {
				int result = Integer.compare(e1.worldY, e2.worldY);
				return result;
			}
		});
		
		// DRAW ENTITIES
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(g2);
		}
		// EMPTY ENTITY LIST
		entityList.clear();
		// DEBUG
		if (keyH.debug==true) {
			//long drawEnd = System.nanoTime();
			//long passed = drawEnd - drawStart;
												
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
			g2.drawString("WorldY: " + player.worldY, x, y); y += lineHeight;
			g2.drawString("Col: " + player.col, x, y); y += lineHeight;
			g2.drawString("Row: " + player.row,x, y); y += lineHeight;
			g2.drawString("Sd Col: " + player.projectile.col, x, y); y += lineHeight;
			g2.drawString("sd Row: " + player.projectile.row,x, y); y += lineHeight;
			
			g2.drawString("enemy Col: " + player.target.col, x, y); y += lineHeight;
			g2.drawString("enemy Row: " + player.target.row,x, y); y += lineHeight;
			//g2.drawString("Draw time: " + passed, 10, 380);
			//System.out.println("Draw time: " + passed);
		}
		
	}
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}

}