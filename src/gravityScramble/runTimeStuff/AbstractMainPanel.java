package gravityScramble.runTimeStuff;

import gravityScramble.clickables.HelpBubble;
import gravityScramble.clickables.HelpBubbleDisplay;
import gravityScramble.clickables.HiddenToggleButton;
import gravityScramble.clickables.MenuButton;
import gravityScramble.clickables.ToggleButton;
import gravityScramble.decorations.Decoration;
import gravityScramble.touchables.Checkpoint;
import gravityScramble.touchables.ExitPortal;
import gravityScramble.touchables.Player;
import gravityScramble.touchables.Switch;
import gravityScramble.touchables.Touchable;
import gravityScramble.touchables.enemies.Boss;
import gravityScramble.touchables.enemies.Enemy;
import gravityScramble.touchables.enemies.GiantCat;
import gravityScramble.touchables.enemies.Group;
import gravityScramble.touchables.obstacles.FireCube;
import gravityScramble.touchables.obstacles.GravityChamber;
import gravityScramble.touchables.obstacles.Spikes;
import gravityScramble.touchables.powerups.Fireball;
import gravityScramble.touchables.powerups.Powerup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public abstract class AbstractMainPanel extends JPanel {

	/**
	 * DEFAULT
	 */
	private static final long serialVersionUID = 1L;

	protected Player player;
	protected HelpBubbleDisplay helpDisplay;
	protected Fireball fireball;
	protected ArrayList<HelpBubble> helpBubbles;
	protected ArrayList<Decoration> decorations;
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Boss> bosses;
	protected ArrayList<GravityChamber> gravityChambers;
	protected ArrayList<Spikes> spikes;
	protected ArrayList<FireCube> fireCubes;
	protected ArrayList<Switch> switches;
	protected ArrayList<Powerup> powerups;
	protected ArrayList<Checkpoint> checkpoints;
	protected ArrayList<MenuButton> menuButtons;
	protected ArrayList<ToggleButton> toggleButtons;
	protected ArrayList<HiddenToggleButton> hiddenToggleButtons;
	protected ArrayList<TextInput> textInputs;
	protected ExitPortal exitPortal;
	protected Color sky;
	protected Color ground;
	protected boolean isMenu;
	protected String menu = "none";
	private Options options;
	// private final int imageWidth = 1250;
	// private final int imageHeight = 750;
	private int levelFinished = 0;
	private int difficulty = 1;
	private boolean damageTaken = false;

	public void makeVariables(boolean menu, Color sk, Color grnd) {
		isMenu = menu;
		sky = sk;
		ground = grnd;
	}

	public void makeButtons() {
		if (!isMenu) {
			menuButtons.add(new MenuButton(1100, 10, 200, "Pause", "none", "Pause Menu"));
			menuButtons.add(new MenuButton(500, 300, 200, "Continue", "Pause Menu", "none"));
			menuButtons.add(new MenuButton(500, 350, 200, "Options", "Pause Menu", "Options"));
			menuButtons.add(new MenuButton(500, 400, 200, "Exit", "Pause Menu", "none"));
			menuButtons.add(new MenuButton(1100, 10, 200, "Save", "Options", "Pause Menu"));
		}
		toggleButtons.add(new ToggleButton(500, 50, 100, "On", "Off", options.getDrawOutline(), "Options", "Draw Outlines"));
		toggleButtons.add(new ToggleButton(500, 150, 100, "On", "Off", options.getReviveEnemies(), "Options", "Revive Enemies"));
		hiddenToggleButtons.add(new HiddenToggleButton(500, 350, 150, new String[] {"Normal", "Fast", "Faster"}, options.getSpeed(), "Options", "Speed"));

		hiddenToggleButtons.add(new HiddenToggleButton(500, 450, 150, new String[] {"Slow", "Normal", "Fast"}, options.getMaxSpeed(), "Options", "MaxSpeed"));

		menuButtons.add(new MenuButton(10, 550, 250, "Fix Window Size", "Options", "Options"));
		menuButtons.add(new MenuButton(500, 550, 250, "Set Preferred Size", "Options", "Options"));
		menuButtons.add(new MenuButton(10, 650, 250, "Use Default Size", "Options", "Options"));
		menuButtons.add(new MenuButton(500, 650, 250, "Use Preferred Size", "Options", "Options"));
	}

	public void paintComponent(Graphics gr) {
		BufferedImage image = new BufferedImage(1250, 750, 1);
		Graphics g = image.getGraphics();
		g.setColor(sky);
		g.fillRect(0, 0, 1250, 750);
		g.setColor(ground);
		int altitude = (player.getAltitude() >= 300) ? player.getAltitude() - 300 : 0;
		
		g.fillRect(0, 640 + altitude, 1250, 110);
		int position = player.getPosition();
		
		for (int i = 0; i < decorations.size(); i++) {
			decorations.get(i).paintComponent(g, position, altitude);
		}
		for (int i = 0; i < checkpoints.size(); i++) {
			checkpoints.get(i).paintComponent(g, position, altitude);
			drawOutlines(checkpoints.get(i), g, position, altitude);
		}
		for (int i = 0; i < switches.size(); i++) {
			switches.get(i).paintComponent(g, position, altitude);
			if (!switches.get(i).getActivatable()) {
				continue;
			}
			drawOutlines(switches.get(i), g, position, altitude);
		}

		exitPortal.paintComponent(g, position, altitude);
		player.paintComponent(g, position, altitude);
		drawOutlines(player, g, position, altitude);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).paintComponent(g, position, altitude);
			if (enemies.get(i) instanceof Group) {
				for (int j = 0; j < ((Group) enemies.get(i)).enemies.size(); j++) {
					drawOutlines(((Group) enemies.get(i)).enemies.get(j), g, position, altitude);
				}
			} else {
				drawOutlines(enemies.get(i), g, position, altitude);
				if (!(enemies.get(i) instanceof GiantCat))
					continue;
				drawOutlines(((GiantCat) enemies.get(i)).summonedCat1, g, position, altitude);
				drawOutlines(((GiantCat) enemies.get(i)).summonedCat2, g, position, altitude);
				drawOutlines(((GiantCat) enemies.get(i)).summonedBunny1, g, position, altitude);
				drawOutlines(((GiantCat) enemies.get(i)).summonedBunny2, g, position, altitude);
				drawOutlines(((GiantCat) enemies.get(i)).summonedFollower, g, position, altitude);
			}
		}

		for (int i = 0; i < fireCubes.size(); i++) {
			fireCubes.get(i).paintComponent(g, position, altitude);
		}
		for (int i = 0; i < gravityChambers.size(); i++) {
			gravityChambers.get(i).paintComponent(g, position, altitude);
			if (!(gravityChambers.get(i) instanceof Spikes))
				continue;
			drawOutlines(gravityChambers.get(i), g, position, altitude);
		}
		for (int i = 0; i < powerups.size(); i++) {
			powerups.get(i).paintComponent(g, position, altitude);
		}
		
		fireball.paintComponent(g, position, altitude);
		
		if (fireball.getActive()) {
			drawOutlines(fireball, g, position, altitude);
		}
		
		for (int i = 0; i < helpBubbles.size(); i++) {
			helpBubbles.get(i).paintComponent(g, position, altitude);
		}
		
		boolean b = false;
		Boss boss = null;
		
		for (int i = 0; i < bosses.size(); i++) {
			if (!bosses.get(i).getBattle())
				continue;
			b = true;
			boss = bosses.get(i);
		}

		if (b) {
			Color fog = new Color(230, 230, 230, 70);
			g.setColor(fog);
			g.fillRect(0, 0, 1300, 750);
			g.setColor(Color.black);
			for (int i = 1; i <= boss.getHealth(); i++) {
				g.fillOval(1230 - i * 30, 60, 25, 25);
			}
		}
		
		helpDisplay.paintComponent(g);
		g.setColor(Color.pink);
		
		for (int i = 1; i <= player.getHealth(); i++) {
			g.fillOval(30 * i - 20, 10, 25, 25);
		}
		
		if (levelFinished > 0) {
			if (levelFinished <= 255) {
				g.setColor(new Color(exitPortal.getBrightness(), 0, exitPortal.getBrightness(), levelFinished));
			} else {
				g.setColor(new Color(exitPortal.getBrightness(), 0, exitPortal.getBrightness(), 255));
			}
			g.fillRect(0, 0, 1250, 750);
			if (levelFinished >= 270) {
				g.setFont(new Font("Serif", 0, 40));
				g.setColor(Color.white);
				g.drawString("Congratulations! Try this cheat code:", 300, 100);
				String s1 = "";
				String s2 = "";
				if (difficulty == 1) {
					s1 = "evil cat";
					s2 = "Also try to win in hard mode!";
				} else if (damageTaken) {
					s1 = "ynnub";
					s2 = "Try to beat hard mode without taking damage!";
				} else {
					s1 = "invincible mode";
				}

				g.drawString(s1, 450, 200);
				g.drawString(s2, 300, 350);
			}
		}
		if (!menu.equals("none")) {
			g.setColor(new Color(100, 100, 100, 170));
			g.fillRect(0, 0, 1250, 750);
		}
		if (menu.equals("Terms of Agreement")) {
			helpDisplay.paintComponent(g);
		}
		for (int i = 0; i < menuButtons.size(); i++) {
			if (!menu.equals(menuButtons.get(i).getMenu()))
				continue;
			menuButtons.get(i).paintComponent(g, position, altitude);
		}

		for (int i = 0; i < toggleButtons.size(); i++) {
			if (!menu.equals(toggleButtons.get(i).getMenu()))
				continue;
			toggleButtons.get(i).paintComponent(g, position, altitude);
		}

		for (int i = 0; i < hiddenToggleButtons.size(); i++) {
			if (!menu.equals(hiddenToggleButtons.get(i).getMenu()))
				continue;
			hiddenToggleButtons.get(i).paintComponent(g, position, altitude);
		}

		for (int i = 0; i < textInputs.size(); i++) {
			if (!menu.equals(textInputs.get(i).getMenu()))
				continue;
			textInputs.get(i).paintComponent(g);
		}

		if (menu.equals("Options")) {
			g.setColor(Color.black);
			g.setFont(new Font("Serif", 0, 40));
			g.drawString("Draw Outlines", 10, 90);
			g.drawString("Revive Enemies", 10, 190);
			g.drawString("Music", 10, 290);
			g.drawString("Coming Soon (or not)", 500, 290);
			g.drawString("Speed", 10, 390);
			g.drawString("Max. Speed", 10, 490);
		}
		if (menu.equals("Custom Levels")) {
			g.setColor(Color.black);
			g.setFont(new Font("Serif", 0, 80));
			g.drawString("Coming Soon", 300, 400);
		}
		if (isMenu) {
			g.setColor(Color.black);
			g.setFont(new Font("Serif", 0, 20));
			g.drawString("v1.0", 5, 730);
		}
		int[] imageSize = getImageSize();
		gr.drawImage(image, 0, 0, imageSize[0], imageSize[1], null);
	}

	public int[] getImageSize() {
		if ((getWidth() == 1250) && (getHeight() == 750)) {
			return new int[] {1250, 750};
		}
		double w = getWidth() / 1250.0D;
		double h = getHeight() / 750.0D;
		int actualWidth = 1250;
		int actualHeight = 750;
		if (w > h) {
			actualWidth = (int) (1250.0D * h);
			actualHeight = getHeight();
		}
		if (w < h) {
			actualHeight = (int) (750.0D * w);
			actualWidth = getWidth();
		}
		return new int[] {actualWidth, actualHeight};
	}

	public boolean touchingUpChamber() {
		boolean b = false;
		for (int i = 0; i < gravityChambers.size(); i++) {
			if (!player.isTouching(gravityChambers.get(i)) || !gravityChambers.get(i).getDirection().equals("up")) {
				continue;
			}
			b = true;
		}

		for (int i = 0; i < enemies.size(); i++) {
			if (!canJumpOnEnemy(enemies.get(i)))
				continue;
			b = true;
		}

		return b;
	}

	public boolean canJumpOnEnemy(Enemy enemy) {
		if (isMenu) {
			return false;
		}

		if (!(enemy instanceof Group)) {
			return player.isPositionSimilar(enemy) && player.getAltitude() >= enemy.getAltitude() && player.getAltitude() <= enemy.getAltitude() + enemy.getHeight() && (!enemy.getDefeated() || enemy.getBounceCounter() != 5);
		}

		boolean b = false;
		for (int i = 0; i < ((Group) enemy).enemies.size(); i++) {
			b = b || canJumpOnEnemy(((Group) enemy).enemies.get(i));
		}
		return b;
	}

	public Color getSky() {
		return sky;
	}

	public Color getGround() {
		return ground;
	}

	public void drawOutlines(Touchable t, Graphics g, int position, int altitude) {
		if (options.getDrawOutline() && (!(t instanceof Enemy) || !((Enemy) t).getDefeated())) {
			g.setColor(Color.white);
			g.drawRect(t.drawingPosition(position), t.drawingAltitude(altitude - t.getHeight()), t.getWidth(), t.getHeight());
		}
	}

	public void setOptions(Options o) {
		options = o;
	}

	public Options getOptions() {
		return options;
	}

	public void resetLevelFinished() {
		levelFinished = 0;
		difficulty = 1;
		damageTaken = false;
	}

	public void incrementLevelFinished() {
		levelFinished += 1;
	}

	public void finishLevel() {
		levelFinished = 1;
	}

	public int getLevelFinished() {
		return levelFinished;
	}

	public void setDifficulty(int x) {
		difficulty = x;
	}

	public void takeDamage() {
		damageTaken = true;
	}

	public boolean getDamageTaken() {
		return damageTaken;
	}
}