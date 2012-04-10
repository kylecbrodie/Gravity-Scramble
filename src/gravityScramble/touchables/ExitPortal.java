package gravityScramble.touchables;

import java.awt.Color;
import java.awt.Graphics;

public class ExitPortal extends Touchable {

	private int brightness;
	private boolean brightening;

	public ExitPortal(int pos, int alt) {
		super(100, 100, pos, alt, "exitPortal");
		brightness = 20;
		brightening = true;
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(new Color(brightness, 0, brightness));
		g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
		if (brightening) {
			brightness++;
			if (brightness == 70) {
				brightening = false;
			}
		} else {
			brightness--;
			if (brightness == 20) {
				brightening = true;
			}
		}
	}

	public int getBrightness() {
		return brightness;
	}

	public void grow() {
		position--;
		altitude--;
		width += 2;
		height += 2;
	}
}