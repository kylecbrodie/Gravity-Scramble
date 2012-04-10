package gravityScramble.touchables.obstacles;

import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;

public class GravityChamber extends Touchable {

	private String direction;
	private String moveDirection;
	private int counter;
	private int startPosition;
	private boolean switchStarted;
	private int distanceToStart;
	private boolean active;
	private int opacity;
	private boolean canMove;
	private boolean adjust;
	private int counterLimit;

	public GravityChamber(int pos, int alt, String dir) {
		super(0, 0, pos, alt, "gravityChamber");
		if (dir.equals("up") || dir.equals("down")) {
			width = 150;
			height = 150;
		} else {
			width = 75;
			height = 225;
		}
		direction = dir;
		moveDirection = "none";
		counter = 0;
		startPosition = pos;
		switchStarted = false;
		distanceToStart = 0;
		active = true;
		opacity = 170;
		canMove = false;
		adjust = false;
		counterLimit = 0;
	}

	public GravityChamber(int pos, int alt, String dir, String movdir, boolean switchOperated, boolean adj, int move) {
		super(0, 0, pos, alt, "gravityChamber");
		if (dir.equals("up")) {
			width = 150;
			height = 150;
		} else {
			width = 75;
			height = 225;
		}
		position = pos;
		altitude = alt;
		direction = dir;
		moveDirection = movdir;
		counter = 0;
		startPosition = pos;
		switchStarted = false;
		distanceToStart = 0;
		active = true;
		opacity = 170;
		canMove = switchOperated;
		adjust = adj;
		counterLimit = move;
		whichClass = "gravityChamber";
	}

	public void paintComponent(Graphics g, int x, int y) {
		int drawPosX = drawingPosition(x);
		int drawAltY = drawingAltitude(y);
		
		if (direction.equals("up")) {
			Color transparentWhite = new Color(255, 255, 255, opacity);
			g.setColor(transparentWhite);
			g.fillRect(drawPosX, drawAltY - height, width, height);
			
			transparentWhite = new Color(255, 255, 255, 170);
			g.setColor(transparentWhite);
			g.drawRect(drawPosX, drawAltY - height, width, height);
		}
		if (direction.equals("left")) {
			Color transparentPurple = new Color(220, 0, 220, opacity);
			g.setColor(transparentPurple);
			g.fillRect(drawPosX, drawAltY - height, width, height);
			
			transparentPurple = new Color(220, 0, 220, 170);
			g.setColor(transparentPurple);
			g.drawRect(drawPosX, drawAltY - height, width, height);
		}
		if (direction.equals("right")) {
			Color transparentGreen = new Color(0, 255, 0, opacity);
			g.setColor(transparentGreen);
			g.fillRect(drawPosX, drawAltY - height, width, height);
			
			transparentGreen = new Color(0, 255, 0, 170);
			g.setColor(transparentGreen);
			g.drawRect(drawPosX, drawAltY - height, width, height);
		}
		if (direction.equals("down")) {
			Color transparentYellow = new Color(255, 255, 0, opacity);
			g.setColor(transparentYellow);
			g.fillRect(drawPosX, drawAltY - height, width, height);
			
			transparentYellow = new Color(255, 255, 0, 170);
			g.setColor(transparentYellow);
			g.drawRect(drawPosX, drawAltY - height, width, height);
		}
	}

	public void pushPlayer(Player player) {
		if (adjust) {
			altitude = player.getAltitude();
		}
		if (isTouching(player) && player.getAltitude() != altitude + height && active) {
			if (direction.equals("up")) {
				player.changeAltitude(3);
			}
			if (direction.equals("left")) {
				player.changePosition(-2);
			}
			if (direction.equals("right")) {
				player.changePosition(2);
			}
			if (direction.equals("down")) {
				player.changeAltitude(-3);
			}
		}
	}

	public void move() {
		if (canMove) {
			if (moveDirection.equals("left")) {
				position--;
			} else if (moveDirection.equals("right")) {
				position++;
			}

			if (moveDirection.equals("up")) {
				altitude++;
			} else if (moveDirection.equals("down")) {
				altitude--;
			}

			counter++;
			if (counter == counterLimit) {
				counter = 0;
				if (moveDirection.equals("left")) {
					moveDirection = "right";
				} else if (moveDirection.equals("right")) {
					moveDirection = "left";
				}

				if (moveDirection.equals("up")) {
					moveDirection = "down";
				} else if (moveDirection.equals("down")) {
					moveDirection = "up";
				}
			}
		}
	}

	public void goBack() {
		if (!switchStarted) {
			counter = 0;
			switchStarted = true;
			distanceToStart = position - startPosition;
			canMove = false;
		}
		counter++;
		position = startPosition + distanceToStart * (100 - counter) / 100;
		super.setCoordinates(position, altitude);
		if (counter == 100) {
			counter = 0;
			switchStarted = false;
			moveDirection = "right";
			canMove = true;
		}
	}

	public void setActive() {
		active = false;
	}

	public void setClass(String cl) {
		whichClass = cl;
		super.setClass(cl);
	}

	public String getDirection() {
		return direction;
	}

	public GravityChamber setSize(int x, int y) {
		width = x;
		height = y;
		return this;
	}

	public void changeOpacity() {
		if (!active && opacity != 0) {
			opacity -= 5;
		}
	}
}