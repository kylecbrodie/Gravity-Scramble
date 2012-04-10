package gravityScramble.runTimeStuff;

public class Level extends AbstractMainPanel {

	/**
	 * DEFAULT
	 */
	private static final long serialVersionUID = 1L;

	public String panel;

	public Level(Options o) {
		setOptions(o);
	}

	public void changeVariables(AbstractMainPanel a) {
		panel = a.getClass().getName();
		player = a.player;
		helpDisplay = a.helpDisplay;
		fireball = a.fireball;
		helpBubbles = a.helpBubbles;
		decorations = a.decorations;
		enemies = a.enemies;
		bosses = a.bosses;
		gravityChambers = a.gravityChambers;
		spikes = a.spikes;
		fireCubes = a.fireCubes;
		switches = a.switches;
		powerups = a.powerups;
		checkpoints = a.checkpoints;
		menuButtons = a.menuButtons;
		toggleButtons = a.toggleButtons;
		hiddenToggleButtons = a.hiddenToggleButtons;
		textInputs = a.textInputs;
		exitPortal = a.exitPortal;
		makeVariables(a.isMenu, a.sky, a.ground);
		menu = "none";
		resetLevelFinished();
		makeButtons();
	}
}