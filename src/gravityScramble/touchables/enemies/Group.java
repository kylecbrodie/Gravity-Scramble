package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Graphics;
import java.util.ArrayList;

public class Group extends MovingEnemy {

	public ArrayList<MovingEnemy> enemies;
	private ArrayList<MovingEnemy> initialEnemies;
	private int space;
	private int counter;

	public Group(int pos, int alt, ArrayList<MovingEnemy> e, int sp, Touchable l, Touchable r) {
		super(0, 0, pos, alt, false, 255, "none", "right", l, r, "group");
		enemies = e;
		space = sp;
		for (int i = 0; i < enemies.size(); i++) {
			width += enemies.get(i).getWidth();
			width += space;
			if (i == 0) {
				enemies.get(i).setCoordinates(pos, alt);
			} else {
				enemies.get(i).setCoordinates(enemies.get(i - 1).getPosition() + enemies.get(i - 1).getWidth() + space, alt);
			}

			enemies.get(i).setInitialCoordinates(enemies.get(i).getPosition(), enemies.get(i).getAltitude());
		}

		width -= space;
		for (int x = 0; x < enemies.size(); x++) {
			updateBoundaries(x);
		}
		initialEnemies = new ArrayList<MovingEnemy>();
		initialEnemies.addAll(enemies);
		counter = 0;
	}

	public void drawing(Graphics g, int x, int y) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).paintComponent(g, x, y);
		}
	}

	public void updateBoundaries(int i) {
		if (i == 0) {
			enemies.get(i).leftBoundary = leftBoundary;
		} else {
			enemies.get(i).leftBoundary = enemies.get(i - 1);
		}
		if (i == enemies.size() - 1) {
			enemies.get(i).rightBoundary = rightBoundary;
		} else {
			enemies.get(i).rightBoundary = enemies.get(i + 1);
		}
	}

	public void changePosition() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).opacity != 0 || !enemies.get(i).getDefeated()) {
				continue;
			}
			enemies.remove(i);
			if (enemies.size() > 0) {
				if (i > 0) {
					updateBoundaries(i - 1);
				}
				if (i < enemies.size()) {
					updateBoundaries(i);
				}
			}
			width = 0;
			for (int j = 0; j < enemies.size(); j++) {
				width += enemies.get(j).getWidth();
				width += space;
			}
			width -= space;
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		if (counter > 0) {
			if (counter == 1) {
				enemies = new ArrayList<MovingEnemy>();
				enemies.addAll(initialEnemies);
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).becomeDefeated();
					enemies.get(i).setOpacity(0);
					enemies.get(i).becomeSummoned();
					updateBoundaries(i);
				}
			}
			counter++;
			if (counter >= 52) {
				counter = 0;
			}
		}
	}

	public void changePowerUp(String p) {}

	public void becomeDefeated() {}

	public void becomeSummoned() {
		counter = 1;
	}
}