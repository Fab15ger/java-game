package controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class ButtonZoomMaxMiniMap extends Button_Class {
	
	GamePanel gp;
	
	public ButtonZoomMaxMiniMap(GamePanel gp, int x, int y, int width, int height, String name) {
		super(gp);
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}
	
	public void use() {
		switch (gp.miniMap.zoom) {
			case 10: gp.miniMap.zoom=5;break;
			case 5: gp.miniMap.zoom=4;break;
			case 4: gp.miniMap.zoom=2;break;
			case 2: gp.miniMap.zoom=1;break;
		}
		gp.miniMap.updateValues();
		gp.miniMap.updateLocalMap();
	}
}
