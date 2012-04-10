package gravityScramble.clickables;

import java.awt.Graphics;

public abstract class Clickable {

	protected int position;
	protected int altitude;

	public Clickable(int pos, int alt) {
		position = pos;
		altitude = alt;
	}

	public abstract void paintComponent(Graphics g, int x, int y);

	public abstract boolean isClickedBy(int x, int y);

	public int drawingPosition(int x) {
		return 610 + (position - x);
	}

	public int drawingAltitude(int y) {
		return 640 + y - altitude;
	}

	public int getPosition() {
		return position;
	}

	public int getAltitude() {
		return altitude;
	}
}