package gravityScramble.decorations;

import java.awt.Graphics;

public abstract class Decoration {

	protected int position;
	protected int altitude;

	public Decoration(int pos, int alt) {
		position = pos;
		altitude = alt;
	}

	public int drawingPosition(int x) {
		return 610 + (position - x);
	}

	public int drawingAltitude(int y) {
		return 640 + y - altitude;
	}

	public abstract void paintComponent(Graphics g, int x, int y);
}