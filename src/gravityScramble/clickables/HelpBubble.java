package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HelpBubble extends Clickable {

	private char symbol;
	private String text;
	private int drawingPosition = 0;
	private int drawingAltitude = 0;
	private boolean isPressed;

	public HelpBubble(int pos, int alt, char c, String t) {
		super(pos, alt);
		symbol = c;
		text = t;
		isPressed = false;
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(Color.white);
		int drawPosX = drawingPosition(x);
		int drawAltY = drawingAltitude(y);
		
		g.fillOval(drawPosX, drawAltY - 40, 40, 40);
		if (isPressed) {
			g.setColor(Color.blue.darker());
		} else {
			g.setColor(Color.blue);
		}
		g.fillOval(drawPosX + 2, drawAltY - 38, 36, 36);
		if (isPressed) {
			g.setColor(Color.white.darker());
		} else {
			g.setColor(Color.white);
		}
		g.setFont(new Font("Serif", 1, 20));
		g.drawString("" + symbol, drawPosX + 16, drawAltY - 13);
		drawingPosition = drawPosX;
		drawingAltitude = drawAltY;
	}

	public String getText() {
		return text;
	}

	public boolean isClickedBy(int x, int y) {
		int xSqrd = (x - drawingPosition - 20) * (x - drawingPosition - 20);
		int ySqrd = (y - drawingAltitude + 20) * (y - drawingAltitude + 20);
		double distance = Math.sqrt(xSqrd + ySqrd);
		return distance <= 20.0;
	}

	public void press(boolean b) {
		isPressed = b;
	}
}