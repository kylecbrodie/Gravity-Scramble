package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;

public abstract class MovingEnemy extends Enemy {

	protected Touchable leftBoundary;
	protected Touchable rightBoundary;
	protected String direction;

	public MovingEnemy(int w, int h, int pos, int alt, boolean def, int opa, String power, String dir, Touchable l, Touchable r, String cl) {
		super(w, h, pos, alt, def, opa, power, cl);
		direction = dir;
		leftBoundary = l;
		rightBoundary = r;
	}

	public void setDirection(String dir) {
		direction = dir;
	}

	public Touchable getLeftBoundary() {
		return leftBoundary;
	}

	public Touchable getRightBoundary() {
		return rightBoundary;
	}

	public void becomeSummoned() {
		direction = "right";
		super.becomeSummoned();
	}
}