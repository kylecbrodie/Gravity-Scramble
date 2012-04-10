package gravityScramble.runTimeStuff;

import gravityScramble.clickables.*;
import gravityScramble.decorations.*;
import gravityScramble.switchActions.*;
import gravityScramble.touchables.*;
import gravityScramble.touchables.enemies.*;
import gravityScramble.touchables.obstacles.*;
import gravityScramble.touchables.powerups.*;
import java.awt.Color;
import java.util.ArrayList;

public class MainPanel1 extends AbstractMainPanel {

	/**
	 * DEFAULT
	 */
	private static final long serialVersionUID = 1L;

	Checkpoint checkPoint1 = new Checkpoint(0, 0, true);
	Text text1 = new Text(-235, 505, "click here ==>");
	HelpBubble help1 = new HelpBubble(0, 500, '?', "Welcome to Gravity Scramble! Help bubbles are labeled with a \"?\"./nClick on them to receive instructions. Bubbles with a \"!\" contain/nspecial tips or advice. Do not click on them unless the game is too/nhard for you. Hold the arrow keys to move. Press the up arrow key/nto jump. Gravity chambers push you in certain directions. White ones/npush you up, green ones push you to the right, and purple ones push/nyou to the left. Click on the bubble again to exit these instructions.");

	HelpBubble help2 = new HelpBubble(250, 500, '?', "If you touch an enemy, you take damage. If you lose all three health,/nyou are returned to your active checkpoint, which looks like a flag./nEvil cat move side to side. Jump on them to defeat them.");

	GravityChamber gravRight1 = new GravityChamber(-225, 0, "right");
	GravityChamber gravUp1 = new GravityChamber(250, 0, "up");
	HelpBubble help3 = new HelpBubble(1000, 500, '?', "If you touch a spike, it damages you. Spikes push you in the direction/nthey are pointing, similar to how gravity chambers push you.");

	GravityChamber gravUp2 = new GravityChamber(1000, 0, "up");
	EvilCat evilCat1 = new EvilCat(500, 0, gravUp1, gravUp2);
	Spikes spikes1 = new Spikes(1150, 0, "up");
	GravityChamber gravUp3A = new GravityChamber(1250, 0, "up");
	HelpBubble tip1 = new HelpBubble(1400, 500, '!', "If you hold up as you jump on an enemy, you can jump off of it.");

	GravityChamber gravUp3B = new GravityChamber(1400, 0, "up");
	GravityChamber gravUp3C = new GravityChamber(1550, 0, "up");
	GravityChamber gravUp3D = new GravityChamber(1700, 0, "up");
	HelpBubble help4 = new HelpBubble(1850, 500, '?', "Bunnies can jump up and down. Their ears cannot damage you,/nnor can you damage their ears. Jump on their head to defeat them.");

	GravityChamber gravUp3E = new GravityChamber(1850, 0, "up");
	EvilCat evilCat2 = new EvilCat(1500, 150, gravUp3A, gravUp3E);
	HelpBubble tip2 = new HelpBubble(2000, 500, '!', "To jump on bunnies, you need careful timing. Remember how long/nit takes you to jump, and remember how long it takes them to jump./nDo not panic. Predict when you need to jump in relation to their/naltitude.");

	HelpBubble help5 = new HelpBubble(2750, 500, '?', "Touch a switch to activate it. Different switches can make different/nthings happen./nSome gravity chambers can move.");

	GravityChamber gravUp4 = new GravityChamber(2750, 0, "up");
	Bunny bunny1 = new Bunny(2000, 0, gravUp3E, gravUp4);
	GravityChamber gravUpMoving1 = new GravityChamber(2900, 301, "up", "right", true, false, 1500);
	Switch switch1 = new Switch(2750, 350, true, new SwitchAction1(gravUpMoving1));
	HelpBubble help6 = new HelpBubble(2900, 500, '?', "Not only can followers fly, but they can also follow you./nJump on them to defeat them.");

	HelpBubble tip3 = new HelpBubble(3000, 500, '!', "Followers always move a certain distance before they change the/ndirection of their flight. To defeat them, get them to move downwards,/nthen jump on them when they are low enough.");
	Follower follower1;
	Group group1;
	EvilCat evilCat3 = new EvilCat(3500, 0, null, null);
	GravityChamber gravLeft1 = new GravityChamber(4500, 0, "left");
	Bunny bunny2 = new Bunny(3600, 0, evilCat3, gravLeft1);
	HelpBubble tip4 = new HelpBubble(4500, 500, '!', "To get past this purple gravity chamber, jump past it from the/nmoving gravity chamber.");

	Switch switch2 = new Switch(4600, 75, false, new SwitchAction2(new GravityChamber[] {gravLeft1}));
	Checkpoint checkPoint2 = new Checkpoint(4800, 0, false);
	HelpBubble help7 = new HelpBubble(4800, 500, '?', "Touch a checkpoint to activate it./nFirecubes turn on and off. When they are on, they can damage you,/nand when they are off, they look transparent. Good timing is required/nto get past them.");

	FireCube fireCube1 = new FireCube(5200, 0, 300, 200, 0, true);
	FireCube fireCube2A = new FireCube(5500, 0, 300, 200, 0, false);
	FireCube fireCube2B = new FireCube(5650, 0, 300, 200, 100, false);
	FireCube fireCube2C = new FireCube(5800, 0, 300, 200, 200, false);
	FireCube fireCube2D = new FireCube(5950, 0, 300, 200, 300, false);
	FireCube fireCube3A = new FireCube(6250, 0, 500, 100, 0, false);
	FireCube fireCube3B = new FireCube(6400, 0, 500, 100, 0, false);
	FireCube fireCube3C = new FireCube(6550, 0, 500, 100, 0, false);
	FireCube fireCube3D = new FireCube(6700, 0, 500, 100, 0, false);
	GravityChamber gravUpMoving2 = new GravityChamber(7000, 0, "up", "up", true, false, 600);
	Text text2 = new Text(7150, 600, "Warning: Fire cubes up here!");
	HelpBubble help8 = new HelpBubble(7150, 500, '?', "Some gravity chambers can move vertically./nSome fire cubes never turn off until a certain switch is activated.");

	GravityChamber gravUp5A = new GravityChamber(7150, 600, "up");
	GravityChamber gravUp5B = new GravityChamber(7300, 600, "up");
	GravityChamber gravUp5C = new GravityChamber(7450, 600, "up");
	GravityChamber gravUp5D = new GravityChamber(7600, 600, "up");
	FireCube fireCube4A = new FireCube(7150, 750, true);
	FireCube fireCube4B = new FireCube(7300, 750, true);
	FireCube fireCube4C = new FireCube(7450, 750, true);
	FireCube fireCube4D = new FireCube(7600, 750, true);
	FireCube[] fireCubes1 = {fireCube4A, fireCube4B, fireCube4C, fireCube4D};
	Switch switch3 = new Switch(7650, 75, false, new SwitchAction3(new FireCube[] {fireCube4A, fireCube4B, fireCube4C, fireCube4D}));

	GravityChamber gravLeft2A = new GravityChamber(7750, 0, "left");
	GravityChamber gravLeft2B = new GravityChamber(7750, 225, "left");
	Bunny bunny3 = new Bunny(7150, 0, gravUpMoving2, gravLeft2A);
	EvilCat evilCat4 = new EvilCat(7150, 750, gravUpMoving2, gravLeft2B);
	Powerup firePowerUp1 = new Powerup(8100, 0, "fire");
	HelpBubble help9 = new HelpBubble(8100, 500, '?', "The fireball powerup lets you throw fireballs. Press Z to throw a/nfireball to the left, and X to throw one to the right. You can only/nthrow one fireball at a time. Fireballs can defeat enemies and/nactivate switches./nYou cannot defeat phantom cats by jumping on them.");

	GravityChamber gravLeft3 = new GravityChamber(9000, 0, "left");
	EvilCat phantomCat1 = new EvilCat(8200, 0, firePowerUp1, gravLeft3, "phantom");
	Switch switch4 = new Switch(9200, 75, false, new SwitchAction2(new GravityChamber[] {gravLeft3}));
	Checkpoint checkPoint3 = new Checkpoint(9400, 0, false);
	Powerup firePowerUp2 = new Powerup(9550, 0, "fire");
	HelpBubble help10 = new HelpBubble(9400, 500, '?', "There are rumors of a giant cat that lives around here. Survivors/nhave said that it's so heavy that if it falls onto the ground, it creates/nan earthquake that can damage you if you don't jump.");

	HelpBubble tip5 = new HelpBubble(9550, 500, '!', "To defeat the boss, you must first dodge all of its attacks. Afterwards,/nit will frown. Throw a fireball at it when this happens. It will then/nsink into the ground. Jump on top of it, without touching its sides./nThe health meter on the top right will decrease. Repeat until it is/ndefeated.");

	HelpBubble tip6 = new HelpBubble(9825, 500, '!', "Here is how to dodge the giant cat's attacks. First, it will move side/nto side. Just stay at the edge of the battlefield to dodge this. Next,/nit should rise into the air. As it falls back down, jump to avoid the/nearthquake it creates. Afterwards, it will change colors then summon/nsome enemies. In order to dodge the next attack, you must defeat/nthese quickly, then go close to it. When it changes color again, start/nrunning away. It will start charging towards you, but it should miss./nNext, it might rise into the air again. Keep on moving, and when it/nstops moving, move closer to the edge of the battlefield. It will fall,/nbut if you move soon enough and you jump, you should avoid any/ndamage. Finally, it will frown. Now is your chance!");

	GravityChamber gravRight2 = new GravityChamber(9825, 0, "right", "none", false, true, 0);
	GravityChamber gravLeft4 = new GravityChamber(11100, 0, "left", "none", false, true, 0);
	GravityChamber gravRight3 = new GravityChamber(9825, 0, "right", "none", false, true, 0);
	GravityChamber gravLeft5 = new GravityChamber(11100, 0, "left", "none", false, true, 0);
	EvilCat summonedCat1 = new EvilCat(10420, 0, gravRight2, gravLeft4, "phantom");
	EvilCat summonedCat2 = new EvilCat(10420, 0, gravRight2, gravLeft4, "phantom");
	Bunny summonedBunny1 = new Bunny(10420, 0, gravRight2, gravLeft4);
	Bunny summonedBunny2 = new Bunny(10420, 0, gravRight2, gravLeft4);
	Follower summonedFollower;
	GiantCat giantCat;
	GravityChamber gravLeft6 = new GravityChamber(11500, 0, "left", "none", false, true, 0);

	public MainPanel1() {
		helpDisplay = new HelpBubbleDisplay();
		helpBubbles = new ArrayList<HelpBubble>();
		enemies = new ArrayList<Enemy>();
		bosses = new ArrayList<Boss>();
		gravityChambers = new ArrayList<GravityChamber>();
		spikes = new ArrayList<Spikes>();
		fireCubes = new ArrayList<FireCube>();
		switches = new ArrayList<Switch>();
		powerups = new ArrayList<Powerup>();
		checkpoints = new ArrayList<Checkpoint>();
		decorations = new ArrayList<Decoration>();
		menuButtons = new ArrayList<MenuButton>();
		toggleButtons = new ArrayList<ToggleButton>();
		hiddenToggleButtons = new ArrayList<HiddenToggleButton>();
		textInputs = new ArrayList<TextInput>();
		player = new Player(0, 0, checkPoint1);
		fireball = new Fireball(0, 0, player);
		exitPortal = new ExitPortal(11250, 20);
		helpBubbles.add(help1);
		helpBubbles.add(help2);
		helpBubbles.add(help3);
		helpBubbles.add(tip1);
		helpBubbles.add(help4);
		helpBubbles.add(tip2);
		helpBubbles.add(help5);
		helpBubbles.add(help6);
		helpBubbles.add(tip3);
		helpBubbles.add(tip4);
		helpBubbles.add(help7);
		helpBubbles.add(help8);
		helpBubbles.add(help9);
		helpBubbles.add(help10);
		helpBubbles.add(tip5);
		helpBubbles.add(tip6);
		decorations.add(text1);
		decorations.add(text2);
		ArrayList<MovingEnemy> list = new ArrayList<MovingEnemy>();
		list.add(evilCat3);
		list.add(bunny2);
		group1 = new Group(3500, 0, list, 20, gravUp4, gravLeft1);
		follower1 = new Follower(3700, 600, player);
		summonedFollower = new Follower(10420, 0, player);
		giantCat = new GiantCat(10420, 0, player, summonedCat1, summonedCat2, summonedBunny1, summonedBunny2, summonedFollower, gravRight2, gravLeft4, new SwitchAction2(new GravityChamber[] {gravRight2, gravRight3, gravLeft4, gravLeft5}));

		enemies.add(evilCat1);
		enemies.add(evilCat2);
		enemies.add(bunny1);
		enemies.add(group1);
		enemies.add(follower1);
		enemies.add(bunny3);
		enemies.add(evilCat4);
		enemies.add(phantomCat1);
		enemies.add(giantCat);
		bosses.add(giantCat);
		gravityChambers.add(gravRight1);
		gravityChambers.add(gravUp1);
		gravityChambers.add(gravUp2);
		gravityChambers.add(spikes1);
		gravityChambers.add(gravUp3A);
		gravityChambers.add(gravUp3B);
		gravityChambers.add(gravUp3C);
		gravityChambers.add(gravUp3D);
		gravityChambers.add(gravUp3E);
		gravityChambers.add(gravUp4);
		gravityChambers.add(gravUpMoving1);
		gravityChambers.add(gravLeft1);
		gravityChambers.add(gravUpMoving2);
		gravityChambers.add(gravUp5A);
		gravityChambers.add(gravUp5B);
		gravityChambers.add(gravUp5C);
		gravityChambers.add(gravUp5D);
		gravityChambers.add(gravLeft2A);
		gravityChambers.add(gravLeft2B);
		gravityChambers.add(gravLeft3);
		gravityChambers.add(gravRight2);
		gravityChambers.add(gravRight3);
		gravityChambers.add(gravLeft4);
		gravityChambers.add(gravLeft5);
		gravityChambers.add(gravLeft6);
		spikes.add(spikes1);
		fireCubes.add(fireCube1);
		fireCubes.add(fireCube2A);
		fireCubes.add(fireCube2B);
		fireCubes.add(fireCube2C);
		fireCubes.add(fireCube2D);
		fireCubes.add(fireCube3A);
		fireCubes.add(fireCube3B);
		fireCubes.add(fireCube3C);
		fireCubes.add(fireCube3D);
		fireCubes.add(fireCube4A);
		fireCubes.add(fireCube4B);
		fireCubes.add(fireCube4C);
		fireCubes.add(fireCube4D);
		switches.add(switch1);
		switches.add(switch2);
		switches.add(switch3);
		switches.add(switch4);
		powerups.add(firePowerUp1);
		powerups.add(firePowerUp2);
		checkpoints.add(checkPoint1);
		checkpoints.add(checkPoint2);
		checkpoints.add(checkPoint3);
		super.makeVariables(false, new Color(25, 150, 255), new Color(0, 190, 0));
	}
}