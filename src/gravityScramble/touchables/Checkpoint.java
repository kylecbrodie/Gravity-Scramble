package gravityScramble.touchables;

import java.awt.Color;
import java.awt.Graphics;

public class Checkpoint extends Touchable {

	private boolean activated;
	private int flagHeight;

	public Checkpoint(int pos, int alt, boolean act) {
		super(75, 150, pos, alt, "checkpoint");
		activated = act;
		if (activated) {
			flagHeight = 490;
		} else {
			flagHeight = 590;
		}
	}

	public void paintComponent(Graphics g, int x, int y) {
		int xx = drawingPosition(x);
		int[] xPoints = {xx, xx + 75, xx};
		g.setColor(Color.white);
		if (activated && flagHeight > 490) {
			flagHeight -= 4;
		}
		if (!activated && flagHeight < 590) {
			flagHeight += 4;
		}
		int[] yPoints = {flagHeight + y - altitude, flagHeight + 25 + y - altitude, flagHeight + 50 + y - altitude};
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(Color.black);
		g.drawLine(xx, 640 + y - altitude, xx, 490 + y - altitude);
	}

	public void changeActivated() {
		activated = !activated;
	}
}