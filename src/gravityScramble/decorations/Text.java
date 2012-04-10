package gravityScramble.decorations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends Decoration {

	private String text;

	// private String menu;

	public Text(int pos, int alt, String t) {
		super(pos, alt);
		text = t;
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(Color.black);
		g.setFont(new Font("Serif", 0, 40));
		g.drawString(text, drawingPosition(x), drawingAltitude(y));
	}
}