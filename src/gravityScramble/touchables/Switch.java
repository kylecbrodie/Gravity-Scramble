package gravityScramble.touchables;

import gravityScramble.switchActions.SwitchAction;
import gravityScramble.touchables.powerups.Fireball;
import java.awt.Color;
import java.awt.Graphics;

public class Switch extends Touchable {

	private boolean activated;
	private boolean repeatable;
	private boolean activatable;
	private int counter;
	private int messageDiameter;
	private int messageOpacity;
	private SwitchAction switchAction;
	private int opacity;
	private boolean a;
	private boolean b;

	public Switch(int pos, int alt, boolean rep, SwitchAction s) {
		super(30, 30, pos, alt, "switch");
		activated = false;
		repeatable = rep;
		activatable = true;
		counter = 0;
		messageDiameter = 30;
		messageOpacity = 255;
		switchAction = s;
		opacity = 255;
		a = false;
		b = false;
	}

	public void paintComponent(Graphics g, int x, int y) {
		if (activatable && opacity != 0) {
			/*
			 * if (!activatable) { //Never runs, activatable is always true because of the &&
			 *     opacity -= 15;
			 *     messageOpacity = 0;
			 * }
			 */
			Color metal = new Color(120, 120, 120);
			Color message = new Color(255, 0, 0, 150);
			g.setColor(message);
			if (messageDiameter >= 90) {
				g.fillOval(drawingPosition(x) - 30, 580 + y - altitude, 90, 90);
				message = new Color(255, 0, 0, messageOpacity);
				g.drawOval(drawingPosition(x) - (messageDiameter - 30) / 2, 610 - (messageDiameter - 30) / 2 + y - altitude, messageDiameter, messageDiameter);
			} else {
				g.fillOval(drawingPosition(x) - (messageDiameter - 30) / 2, 610 - (messageDiameter - 30) / 2 + y - altitude, messageDiameter, messageDiameter);
			}
			g.setColor(metal);
			g.fillOval(drawingPosition(x), 610 + y - altitude, 30, 30);
		}
	}

	public void activate(Player player) {
		if ((isTouching(player) || activated) && activatable && !b) {
			activated = true;
			a = true;
			messageDiameter += 10;
			messageOpacity = (255 - 255 * counter / 100);
			counter++;
			switchAction.action();
			if (counter == 100) {
				activated = false;
				counter = 0;
				messageDiameter = 30;
				messageOpacity = 255;
				if (!repeatable) {
					activatable = false;
				}
				a = false;
			}
		}
	}

	public void activate(Fireball fireball) {
		if ((isTouching(fireball) || activated) && activatable && !a) {
			activated = true;
			b = true;
			messageDiameter += 10;
			messageOpacity = (255 - 255 * counter / 100);
			counter++;
			switchAction.action();
			if (counter == 100) {
				activated = false;
				counter = 0;
				messageDiameter = 30;
				messageOpacity = 255;
				if (!repeatable) {
					activatable = false;
				}
				b = false;
			}
		}
	}

	public boolean getActivated() {
		return activated;
	}

	public boolean getActivatable() {
		return activatable;
	}
}