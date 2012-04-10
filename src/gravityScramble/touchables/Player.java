package gravityScramble.touchables;

import gravityScramble.touchables.enemies.Bunny;
import gravityScramble.touchables.enemies.Enemy;
import gravityScramble.touchables.enemies.EvilCat;
import gravityScramble.touchables.enemies.GiantCat;
import gravityScramble.touchables.enemies.Group;
import gravityScramble.touchables.obstacles.FireCube;
import gravityScramble.touchables.obstacles.Spikes;
import gravityScramble.touchables.powerups.Fireball;
import java.awt.Color;
import java.awt.Graphics;

public class Player extends Touchable {

	private int health;
	private Checkpoint checkpoint;
	private int jumpCounter;
	private boolean jumping;
	private int fallCounter;
	private boolean recovering;
	private int recoverCounter;
	private String powerUp;
	private int fireCounter;
	private boolean invincible;
	private String appearance;
	private Enemy appearanceObject;
	// private final int DRAWING_POSITION;
	public boolean reviveEnemies;

	public Player(int pos, int alt, Checkpoint ch) {
		super(30, 150, pos, alt, "player");
		health = 3;
		checkpoint = ch;
		jumpCounter = 0;
		jumping = false;
		fallCounter = 0;
		recovering = false;
		recoverCounter = 0;
		powerUp = "none";
		fireCounter = 0;
		invincible = false;
		appearance = "player";
		// DRAWING_POSITION = 610;
		reviveEnemies = false;
	}

	public void paintComponent(Graphics g, int x, int y) {
		if (appearance.equals("player")) {
			Color shirt = null;
			Color pants = null;
			Color shoes = null;
			Color skin = null;
			Color differentBlack = null;
			if (!recovering) {
				if (powerUp.equals("none")) {
					shirt = new Color(20, 20, 220);
				}
				if (powerUp.equals("fire")) {
					shirt = new Color(250, 10, 10);
				}
				pants = new Color(130, 200, 60);
				shoes = new Color(95, 90, 10);
				skin = new Color(255, 255, 128);
				differentBlack = Color.black;
			} else {
				if (powerUp.equals("none")) {
					shirt = new Color(20, 20, 220, 170);
				}
				if (powerUp.equals("fire")) {
					shirt = new Color(250, 10, 10, 170);
				}
				pants = new Color(130, 200, 60, 170);
				shoes = new Color(100, 100, 0, 170);
				skin = new Color(255, 255, 128, 170);
				differentBlack = new Color(0, 0, 0, 170);
			}
			g.setColor(shirt);
			g.fillRect(610, 520 + y - altitude, 30, 60);
			g.fillRect(600, 520 + y - altitude, 10, 40);
			g.fillRect(640, 520 + y - altitude, 10, 40);
			g.setColor(pants);
			g.fillRect(610, 580 + y - altitude, 30, 10);
			g.fillRect(610, 590 + y - altitude, 13, 40);
			g.fillRect(627, 590 + y - altitude, 13, 40);
			g.setColor(shoes);
			g.fillRect(610, 630 + y - altitude, 13, 10);
			g.fillRect(627, 630 + y - altitude, 13, 10);
			g.fillRect(612, 490 + y - altitude, 26, 5);
			g.setColor(skin);
			g.fillRect(612, 495 + y - altitude, 26, 25);
			g.fillRect(600, 560 + y - altitude, 10, 10);
			g.fillRect(640, 560 + y - altitude, 10, 10);
			g.setColor(differentBlack);
			g.fillRect(617, 500 + y - altitude, 5, 5);
			g.fillRect(628, 500 + y - altitude, 5, 5);
		} else {
			if (recovering) {
				appearanceObject.setOpacity(170);
			} else {
				appearanceObject.setOpacity(255);
			}
			appearanceObject.changePowerUp(powerUp);
			appearanceObject.setCoordinates(position, altitude);
			appearanceObject.paintComponent(g, x, y);
		}
	}

	public int getHealth() {
		return health;
	}

	public void move(boolean direction) {
		if (!direction) {
			position -= 2;
			super.setCoordinates(position, altitude);
		} else {
			position += 2;
			super.setCoordinates(position, altitude);
		}
	}

	public boolean falling() {
		return altitude > 0 && !jumping;
	}

	public void changeAltitude(int y) {
		altitude += y;
		super.setCoordinates(position, altitude);
	}

	public void changePosition(int x) {
		position += x;
		super.setCoordinates(position, altitude);
	}

	public void jump() {
		jumping = true;
		if (jumpCounter < 50) {
			altitude += 3;
			super.setCoordinates(position, altitude);
		}
		jumpCounter++;
		if (jumpCounter == 60) {
			jumpCounter = 0;
			super.setCoordinates(position, altitude);
			jumping = false;
		}
	}

	public boolean getJumping() {
		return jumping;
	}

	public int getJumpCounter() {
		return jumpCounter;
	}

	public void fall() {
		if (falling()) {
			if (fallCounter < 50) {
				changeAltitude(-1);
			} else {
				changeAltitude(-1);
			}
		}
	}

	public void changeFallCounter(boolean falling) {
		if (falling) {
			fallCounter++;
		} else {
			fallCounter = 0;
		}
	}

	public boolean defeatingEnemy(Enemy e) {
		if (!e.whatClass().equals("group")) {
			if ((position >= e.getPosition() && position <= e.getPosition() + e.getWidth()) || (position + width >= e.getPosition() && position + width <= e.getPosition() + e.getWidth())) {
				if (altitude >= e.getAltitude() + e.getHeight() - 2 && altitude <= e.getAltitude() + e.getHeight() + 5) {
					if (!e.getPowerUp().equals("phantom")) {
						if (e.getOpacity() == e.getSaveOpacity()) {
							if (e.whatClass().equals("giantCat")) {
								return ((GiantCat) e).getAttack() == 7;
							}
							return true;
						} // return false;
					} // return false;
				} // return false;
			}
			return false;
		}

		for (int i = 0; i < ((Group) e).enemies.size(); i++) {
			checkEnemy(((Group) e).enemies.get(i));
		}
		return true;
	}

	public void checkEnemy(Enemy e) {
		if (defeatingEnemy(e)) {
			e.becomeDefeated();
		} else if (isTouching(e) && !e.getDefeated() && e.getOpacity() == e.getSaveOpacity()) {
			loseHealth();
		}

		if (e.whatClass().equals("giantCat")) {
			checkEnemy(((GiantCat) e).summonedCat1);
			checkEnemy(((GiantCat) e).summonedCat2);
			checkEnemy(((GiantCat) e).summonedBunny1);
			checkEnemy(((GiantCat) e).summonedBunny2);
			checkEnemy(((GiantCat) e).summonedFollower);
		}
	}

	public void checkSpikes(Spikes s) {
		if (isTouching(s)) {
			loseHealth();
		}
	}

	public void checkFireCube(FireCube f) {
		if (isTouching(f) && f.getActivated()) {
			loseHealth();
		}
	}

	public void changeRecoverCounter() {
		if (recovering) {
			recoverCounter++;
			if (recoverCounter == 100) {
				recovering = false;
				recoverCounter = 0;
			}
		}
	}

	public int getFallCounter() {
		return fallCounter;
	}

	public void changeCheckpoint(Checkpoint c) {
		if (isTouching(c) && c != checkpoint) {
			checkpoint.changeActivated();
			c.changeActivated();
			checkpoint = c;
		}
	}

	public void setAltitude(int x) {
		altitude = x;
	}

	public void setPosition(int x) {
		position = x;
	}

	public String getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(String p) {
		powerUp = p;
	}

	public void resetJumpCounter() {
		jumpCounter = 0;
	}

	public void throwFireBall(Fireball f, String s, boolean b) {
		if (powerUp.equals("fire")) {
			if (fireCounter == 0 && b) {
				f.setDirection(s);
				f.activate(true);
			}
		}
		if (f.getActive()) {
			fireCounter++;
		}
		if (fireCounter == 100) {
			f.setDirection("none");
			f.activate(false);
			fireCounter = 0;
		}
	}

	public boolean getRecovering() {
		return recovering;
	}

	public void loseHealth() {
		if (!recovering && !invincible) {
			health--;
			if (health == 0) {
				position = checkpoint.getPosition();
				altitude = checkpoint.getAltitude();
				super.setCoordinates(position, altitude);
				health = 3;
				powerUp = "none";
				reviveEnemies = true;
			}
			recovering = true;
		}
	}

	public void setInvincible(boolean i) {
		invincible = i;
	}

	public void setAppearance(String a) {
		appearance = a;
		if (appearance.equals("evilCat")) {
			appearanceObject = new EvilCat(position, altitude, null, null);
			setDimensions(80, 100);
		}
		if (appearance.equals("bunny")) {
			appearanceObject = new Bunny(position, altitude, null, null);
			setDimensions(40, 95);
		}
	}

	public void setDimensions(int w, int h) {
		width = w;
		height = h;
		super.setDimensions(w, h);
	}
}