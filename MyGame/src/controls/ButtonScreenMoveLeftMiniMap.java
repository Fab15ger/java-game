package controls;

import main.GamePanel;

public class ButtonScreenMoveLeftMiniMap extends Button_Class {
	
	GamePanel gp;
	
	public ButtonScreenMoveLeftMiniMap(GamePanel gp, int x, int y, int width, int height, String name) {
		super(gp);
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}
	
	public void use() {
		gp.miniMap.posX -= 1;
		gp.miniMap.updateValues();
		gp.miniMap.updateLocalMap();
	}
}
