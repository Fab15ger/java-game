	package main;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;

import javax.swing.JPanel;



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
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
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
	
	int playerX = 100;
	int playerY = 100;
	
	KeyHandler keyH = new KeyHandler(this);
		
	
	int tempo;
	int tempoDaley = 6;
	
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

	public void setFullScreen() {
		
		// GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		// GET FULLSCREEN WIDTH AND HEGHT
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
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
		//int drawCount = 0;
		
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
				//drawCount++;
			}
			if (timer >= 1000000000) {
				//System.out.println("FPS: " + drawCount);
				//drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		
		tempo ++;
		
		if (keyH.upPressed) {
			if (tempo>=tempoDaley) {
				playerY -= tileSize;
				tempo=0;
			}
			
		}
		if (keyH.downPressed) {
			if (tempo>=tempoDaley) {
				playerY += tileSize;
				tempo=0;
			}
		}
		if (keyH.leftPressed) {
			if (tempo>=tempoDaley) {
				playerX -= tileSize;
				tempo=0;
			}
			
		}
		if (keyH.rightPressed) {
			if (tempo>=tempoDaley) {
				playerX += tileSize;
				tempo=0;
			}			
		}
		
	}
	public void drawToTempScreen() {
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, screenWidth, screenHeight);
		
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, 50, 50);
	}
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}

}