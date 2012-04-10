package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;

public class EvilCat extends MovingEnemy {

	public EvilCat(int pos, int alt, Touchable l, Touchable r) {
		super(80, 100, pos, alt, false, 255, "none", "right", l, r, "evilCat");
	}

	public EvilCat(int pos, int alt, Touchable l, Touchable r, String power) {
		super(80, 100, pos, alt, false, 255, power, "right", l, r, "evilCat");
		if (power.equals("phantom")) {
			super.differentOpacity(210);
		}
	}

	public void drawing(Graphics g, int x, int y) {
		defeated = super.getDefeated();
		Color cat;
		if (powerUp.equals("phantom")) {
			cat = new Color(60, 30, 50, getOpacity());
		} else {
			cat = new Color(225, 130, 60, getOpacity());
		}
		g.setColor(cat);
		int drawPosX = drawingPosition(x);
		g.fillRect(drawPosX, 560 + y - altitude, 80, 60);
		g.fillRect(drawPosX + 5, 620 + y - altitude, 20, 20);
		g.fillRect(drawPosX + 55, 620 + y - altitude, 20, 20);
		g.fillRect(drawPosX + 20, 540 + y - altitude, 40, 40);
		int[] xPoints = {drawPosX + 25, drawPosX + 30, drawPosX + 35};
		int[] yPoints = {540 + y - altitude, 530 + y - altitude, 540 + y - altitude};
		g.fillPolygon(xPoints, yPoints, 3);
		int[] xPoints2 = {drawPosX + 45, drawPosX + 50, drawPosX + 55};
		g.fillPolygon(xPoints2, yPoints, 3);
		Color differentBlack = new Color(0, 0, 0, getOpacity());
		if (powerUp.equals("fire")) {
			differentBlack = new Color(255, 0, 0, getOpacity());
		}
		g.setColor(differentBlack);
		g.drawRect(drawPosX + 20, 540 + y - altitude, 40, 40);
		if (powerUp.equals("phantom")) {
			g.drawLine(drawPosX + 30, 570 + y - altitude, drawPosX + 50, 570 + y - altitude);
			Color differentWhite = new Color(255, 255, 255, getOpacity());
			g.setColor(differentWhite);
		}
		g.fillRect(drawPosX + 30, 555 + y - altitude, 5, 5);
		g.fillRect(drawPosX + 45, 555 + y - altitude, 5, 5);
	}

	public void changePosition() {
		if (direction.equals("right")) {
			position++;
			super.setCoordinates(position, altitude);
			if (isPositionSimilar(rightBoundary)) {
				direction = "left";
				super.setDirection("left");
			}
		} else {
			position--;
			super.setCoordinates(position, altitude);
			if (isPositionSimilar(leftBoundary)) {
				direction = "right";
				super.setDirection("right");
			}
		}
	}

	public void changePowerUp(String p) {
		powerUp = p;
	}

	public void becomeDefeated() {
		defeated = true;
	}
}