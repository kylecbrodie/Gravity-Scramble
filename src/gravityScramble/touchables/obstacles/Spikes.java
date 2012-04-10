package gravityScramble.touchables.obstacles;

import java.awt.Color;
import java.awt.Graphics;

public class Spikes extends GravityChamber {

	public Spikes(int pos, int alt, String dir) {
		super(pos, alt, dir);
		width = 100;
		whichClass = "spikes";
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(Color.red);
		int drawPosX = drawingPosition(x);

		int[] xPoints = {drawPosX, drawPosX, drawPosX + 50};
		int[] yPoints = {640 + y - altitude, 490 + y - altitude, 640 + y - altitude};
		g.fillPolygon(xPoints, yPoints, 3);

		int[] xPoints2 = {drawPosX, drawPosX + 50, drawPosX + 100};
		g.fillPolygon(xPoints2, yPoints, 3);

		int[] xPoints3 = {drawPosX + 50, drawPosX + 100, drawPosX + 100};
		g.fillPolygon(xPoints3, yPoints, 3);
	}
}