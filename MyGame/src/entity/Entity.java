package entity;

import java.awt.Graphics2D;

import main.GamePanel;

public class Entity {
	
	GamePanel gp;
	
	int worldX;
	int worldY;
	String name;
	int tempo = 0;
	int tempoDelay;
	String direction = "down";
	int speed;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void update() {}
	
	public void draw(Graphics2D g2) {}
}
