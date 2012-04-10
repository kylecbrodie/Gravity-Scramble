package gravityScramble.runTimeStuff;

import gravityScramble.clickables.*;
import gravityScramble.decorations.*;
import gravityScramble.touchables.*;
import gravityScramble.touchables.enemies.*;
import gravityScramble.touchables.obstacles.*;
import gravityScramble.touchables.powerups.*;
import java.awt.Color;
import java.util.ArrayList;

public class Menu extends AbstractMainPanel {

	/**
	 * DEFAULT
	 */
	private static final long serialVersionUID = 1L;

	public Menu() {
		refresh();
	}

	public void refresh() {
		exitPortal = new ExitPortal(0, 1000);
		helpBubbles = new ArrayList<HelpBubble>();
		decorations = new ArrayList<Decoration>();
		enemies = new ArrayList<Enemy>();
		bosses = new ArrayList<Boss>();
		gravityChambers = new ArrayList<GravityChamber>();
		spikes = new ArrayList<Spikes>();
		fireCubes = new ArrayList<FireCube>();
		switches = new ArrayList<Switch>();
		powerups = new ArrayList<Powerup>();
		checkpoints = new ArrayList<Checkpoint>();
		menuButtons = new ArrayList<MenuButton>();
		toggleButtons = new ArrayList<ToggleButton>();
		hiddenToggleButtons = new ArrayList<HiddenToggleButton>();
		textInputs = new ArrayList<TextInput>();
		checkpoints.add(new Checkpoint(0, 0, true));
		player = new Player(0, 0, (Checkpoint) checkpoints.get(0));
		fireball = new Fireball(0, 0, player);
		helpDisplay = new HelpBubbleDisplay();
		gravityChambers.add(new GravityChamber(300, 0, "up"));
		gravityChambers.add(new GravityChamber(-500, 0, "right"));
		enemies.add(new EvilCat(-400, 0, (Touchable) gravityChambers.get(1), (Touchable) gravityChambers.get(0)));
		enemies.add(new Bunny(-100, 0, (Touchable) gravityChambers.get(1), (Touchable) gravityChambers.get(0)));
		enemies.add(new Follower(-200, 500, player));
		helpBubbles.add(new HelpBubble(0, 1000, 'c', "Terms of Agreement/n/nYou may redistribute this version (1.0) of the game to others for them/nto enjoy. However, you may not make money from this game or any/nelements of it in any way./nYou may decompile and modify this version of the game, but you may/nnot distribute any modified version or part of a modified version of this/ngame, nor can you modifythe terms of agreement or the version number./n-Nathaniel Minsung Kim/n/n/n/n/n"));

		MenuButton play = new MenuButton(50, 200, 200, "Start Game", "none", "Level Loader");
		MenuButton levelCreator = new MenuButton(50, 300, 200, "Create Levels", "none", "Custom Levels");
		MenuButton options = new MenuButton(50, 400, 200, "Options", "none", "Options");
		MenuButton termsOfAgreement = new MenuButton(900, 640, 300, "Terms of Agreement", "none", "Terms of Agreement");
		menuButtons.add(play);
		menuButtons.add(levelCreator);
		menuButtons.add(options);
		menuButtons.add(termsOfAgreement);
		menuButtons.add(new MenuButton(1100, 10, 200, "Cancel", "Level Loader", "none"));
		menuButtons.add(new MenuButton(500, 500, 200, "Play", "Level Loader", "none"));
		menuButtons.add(new MenuButton(1100, 10, 200, "Save", "Options", "none"));
		menuButtons.add(new MenuButton(1100, 10, 200, "Back", "Custom Levels", "none"));
		menuButtons.add(new MenuButton(1100, 10, 200, "Back", "Terms of Agreement", "none"));
		toggleButtons.add(new ToggleButton(500, 100, 100, "Easy", "Hard", true, "Level Loader", ""));
		textInputs.add(new TextInput("Enter Cheat Code", 400, 300, 400, "Level Loader"));
		makeVariables(true, new Color(25, 150, 255), new Color(0, 190, 0));
	}
}