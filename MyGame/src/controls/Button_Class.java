package controls;

import java.awt.Graphics2D;

import main.GamePanel;

public class Button_Class {
	GamePanel gp;
	int x;
	int y;
	int width;
	int height;
	public String name = "+";
	
	public Button_Class(GamePanel gp) {
		this.gp=gp;
	}
	
	public void use() {}
	
	public void draw(Graphics2D g2) {
		g2.drawString("\n"+name, x, y+height);	
	}

}
