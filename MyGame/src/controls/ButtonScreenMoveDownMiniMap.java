package controls;

import main.GamePanel;

public class ButtonScreenMoveDownMiniMap extends Button_Class {
	
	GamePanel gp;
	
	public ButtonScreenMoveDownMiniMap(GamePanel gp, int x, int y, int width, int height, String name) {
		super(gp);
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}
	
	public void use() {
		gp.miniMap.posY += 4;
		gp.miniMap.updateValues();
		gp.miniMap.updateLocalMap();
	}
}
