package controls;

import main.GamePanel;

public class ButtonsManager {
	
	GamePanel gp;
	
	ButtonZoomMaxMiniMap bMax;
	ButtonZoomMinMiniMap bMin;
	ButtonScreenMoveLeftMiniMap bLeft;
	ButtonScreenMoveRightMiniMap bRight;
	ButtonScreenMoveUpMiniMap bUp;
	ButtonScreenMoveDownMiniMap bDown;
	
	int y;
	int x;
	
	public ButtonsManager(GamePanel gp) {
		this.gp = gp;
		x = gp.screenWidth-210;
		y = 220;
		bMax = new ButtonZoomMaxMiniMap(gp, x, y, 16, 16, "+");x+=20;
		bMin = new ButtonZoomMinMiniMap(gp, x, y,16,16, "-");x+=20;
		bLeft = new ButtonScreenMoveLeftMiniMap(gp, x, y,16,16, "<");x+=20;
		bRight = new ButtonScreenMoveRightMiniMap(gp, x, y,16,16, ">");x+=20;
		bUp = new ButtonScreenMoveUpMiniMap(gp, x, y,16,16, "A");x+=20;
		bDown = new ButtonScreenMoveDownMiniMap(gp, x, y,10,16, "V");x+=20;
	}
	

	public void setter() {
		
		gp.buttonsList.add(bMax);
		gp.buttonsList.add(bMin);
		gp.buttonsList.add(bLeft);
		gp.buttonsList.add(bRight);
		gp.buttonsList.add(bUp);
		gp.buttonsList.add(bDown);
		
	}
	
	

}
