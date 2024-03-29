package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Player;
import java.awt.Color;
import java.awt.Graphics;

public class Follower extends Enemy {

	private int savePosition;
	private int saveAltitude;
	private int counter;
	private double ratio;
	private double positionChange;
	private double altitudeChange;
	private String leftRightDirection;
	private String upDownDirection;
	private boolean moving;
	private int sightDistance;
	private Player player;

	public Follower(int pos, int alt, Player p) {
		super(120, 30, pos, alt, false, 255, "none", "follower");
		savePosition = pos;
		saveAltitude = alt;
		counter = 0;
		ratio = 0.0;
		positionChange = 0.0;
		altitudeChange = 0.0;
		leftRightDirection = "none";
		upDownDirection = "none";
		moving = false;
		sightDistance = 500;
		player = p;
	}

	public void drawing(Graphics g, int x, int y) {
		Color brown = new Color(95, 90, 10, getOpacity());
		g.setColor(brown);
		int drawPosX = drawingPosition(x);
		g.fillRect(drawPosX, 620 + y - altitude, 120, 10);
		g.fillRect(drawPosX + 45, 610 + y - altitude, 30, 30);
		g.fillRect(drawPosX, 630 + y - altitude, 7, 5);
		g.fillRect(drawPosX + 113, 630 + y - altitude, 7, 5);
		Color differentBlack = new Color(0, 0, 0, getOpacity());
		g.setColor(differentBlack);
		g.drawLine(drawPosX + 50, 615 + y - altitude, drawPosX + 55, 620 + y - altitude);
		g.drawLine(drawPosX + 65, 620 + y - altitude, drawPosX + 70, 615 + y - altitude);
		g.drawLine(drawPosX + 55, 625 + y - altitude, drawPosX + 60, 635 + y - altitude);
		g.drawLine(drawPosX + 60, 635 + y - altitude, drawPosX + 65, 625 + y - altitude);
	}

	public void changePosition() {
		if (counter == 0 && distanceTo(player) <= sightDistance) {
			savePosition = position;
			saveAltitude = altitude;
			ratio = distanceTo(player) / 150.0;
			positionChange = positionDifference(player) / ratio;
			altitudeChange = altitudeDifference(player) / ratio;
			if (toTheLeftOf(player)) {
				leftRightDirection = "right";
			} else if (toTheRightOf(player)) {
				leftRightDirection = "left";
			} else {
				leftRightDirection = "none";
			}

			if (above(player)) {
				upDownDirection = "down";
			} else if (below(player)) {
				upDownDirection = "up";
			} else {
				upDownDirection = "none";
			}

			moving = true;
		} else if (distanceTo(player) > sightDistance) {
			sightDistance = 500;
		}

		if (moving) {
			if (leftRightDirection.equals("left")) {
				position = savePosition - (int) (counter * positionChange / 150.0);
			}
			if (leftRightDirection.equals("right")) {
				position = savePosition + (int) (counter * positionChange / 150.0);
			}
			if (upDownDirection.equals("up")) {
				altitude = saveAltitude + (int) (counter * altitudeChange / 150.0);
			}
			if (upDownDirection.equals("down")) {
				altitude = saveAltitude - (int) (counter * altitudeChange / 150.0);
			}
			super.setCoordinates(position, altitude);
			counter++;
			if (counter == 150) {
				counter = 0;
				moving = false;
				sightDistance = 1000;
			}
		}
	}

	public void setCoordinates(int pos, int alt) {
		position = pos;
		altitude = alt;
		super.setCoordinates(pos, alt);
	}

	public void resetSaves() {
		savePosition = position;
		saveAltitude = altitude;
		counter = 0;
		moving = false;
		sightDistance = 1000;
	}

	public void becomeDefeated() {
		defeated = true;
	}

	public void becomeSummoned() {
		resetSaves();
		super.becomeSummoned();
	}

	public void setDirection(String dir) {}

	public void changePowerUp(String p) {}
}