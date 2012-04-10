package gravityScramble.runTimeStuff;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import gravityScramble.clickables.*;

/**
 * Write a description of class UniversalLevelPlayer here.
 * 
 * @author Nathan Kim
 * @version 1.0
 */
public class UniversalLevelPlayer {

	private javax.swing.Timer timer;
	private int speed = 25;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private boolean isUpPressed;
	private boolean isZPressed;
	private boolean isXPressed;
	// private boolean isSPressed;
	// private boolean isPPressed;
	private boolean isBubblePressed = false;
	private boolean isButtonPressed = false;
	private String cheatCode = "";
	private int count = 0;
	private int difficulty = 1;
	Level world;
	private String changeLevel = "none";
	private Options options;
	private final String terms = "Terms of Agreement/n/nYou may redistribute this version (1.0) of the " + "game to others for them to enjoy. However, you may not make money from this game or any elements o" + "f it in any way./nYou may decompile and modify this version of the game, but you may not distribut" + "e any modified version or part of a modified version of this game, nor can you modify the terms of" + " agreement or the version number./n-Nathaniel Minsung Kim";
	private final String help = "If the keyboard does not work, minimize the window then reopen it. Thi" + "s should make it work again./nTo see the instructions, click on all of the question mark bubbles s" + "cattered throughout the game.";
	private final String howToUseOptions = "Draw Outlines - Turn this on to see the hitbox for most obj" + "ects./nRevive Enemies - Turn this on to revive all enemies every time you die. The boss will also " + "regain its health./nSpeed - Toggle this to adjust the speed of the game. Higher speeds may result " + "in lower-quality animations./nMax. Speed - Toggle this to adjust the highest speed of the game. Hi" + "gher speeds might be more resource-intensive on your computer./nFix Window Size - Click on this to" + " remove any gray areas from your window./nSet Preferred Size - Click on this to use the current wi" + "ndow size as your preferred size./nUse Default Size - Click on this to use the same window size as" + "the first time you played./nUse Preferred Size - Click on this to change your window size to your " + "preferred size.";

	public UniversalLevelPlayer() {

	}

	public void startGame() throws IOException {
		final File f = new File("options.dat");
		if (f.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				options = (Options) (in.readObject());
				in.close();
			} catch (IOException e) {
				options = new Options();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(options);
				out.close();
			} catch (ClassCastException e) {
				options = new Options();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(options);
				out.close();
			} catch (ClassNotFoundException e) {
				options = new Options();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(options);
				out.close();
			}
		} else {
			options = new Options();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(options);
			out.close();
		}
		world = new Level(options);
		world.changeVariables(new Menu());
		final JFrame frame = new JFrame("Gravity Scramble");
		frame.setSize(options.getPreferredWidth(), options.getPreferredHeight());
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(world);
		speed = 10 * (1 - options.getMaxSpeed()) + 25;
		timer = new javax.swing.Timer(speed, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.setSize(frame.getWidth() - 8, frame.getHeight() - 24);
				if (!changeLevel.equals("none")) {
					isUpPressed = false;
					if (changeLevel.equals("main menu")) {
						cheatCode = "";
						difficulty = 1;
						world.changeVariables(new Menu());
						changeLevel = "none";
						world.setDifficulty(1);
					}
					if (changeLevel.equals("level 1")) {
						cheatCode = world.textInputs.get(0).getInput();
						if (world.toggleButtons.get(0).getSelected()) {
							difficulty = 1;
						} else {
							difficulty = 2;
						}
						world.changeVariables(new MainPanel1());
						changeLevel = "none";
						world.setDifficulty(difficulty);
					}
					if (difficulty == 2) {
						for (int x = 0; x < world.bosses.size(); x++) {
							world.bosses.get(x).setDifficulty();
						}
					}
					if (cheatCode.equals("invincible mode")) {
						world.player.setInvincible(true);
					}
					if (cheatCode.equals("evil cat")) {
						world.player.setAppearance("evilCat");
					}
					if (cheatCode.equals("ynnub")) {
						world.player.setAppearance("bunny");
					}
					count = 0;
				}
				// play
				if (world.menu.equals("none")) {
					if (!world.getDamageTaken() && world.player.getHealth() == 2) {
						world.takeDamage();
					}
					if (options.getReviveEnemies() && world.player.reviveEnemies) {
						for (int x = 0; x < world.enemies.size(); x++) {
							world.enemies.get(x).becomeSummoned();
						}
						world.player.reviveEnemies = false;
					}
					if (world.isMenu) {
						count++;
						if ((options.getSpeed() == 0 && count >= 600) || (options.getSpeed() == 1 && count >= 400) || (options.getSpeed() == 2 && count >= 300)) {
							world.changeVariables(new Menu());
							count = 0;
						}
					}
					// play
					for (int i = 0; i < 2 + options.getSpeed(); i++) {
						boolean b = false;
						for (int j = 0; j < world.powerups.size(); j++) {
							b = b || world.powerups.get(j).getActivating();
						}
						if (!b) {
							if (world.isMenu) {
								isUpPressed = true;
							}
							/*
							 * if (false && isSPressed) { //DEAD CODE if
							 * (world.panel
							 * .equals("gravityScramble.runTimeStuff.MainPanel1"
							 * )) { world.player.setCoordinates(3500, 0); } }
							 */
							if (isLeftPressed) {
								world.player.move(false);
							}
							if (isRightPressed) {
								world.player.move(true);
							}
							if ((isUpPressed && (!world.player.falling() || world.touchingUpChamber()) && !world.player.getJumping()) || world.player.getJumpCounter() > 0) {
								for (int j = 0; j < world.spikes.size(); j++) {
									world.player.checkSpikes(world.spikes.get(j));
								}
								world.player.jump();
							} else {
								world.player.resetJumpCounter();
							}
						}
						world.player.changeRecoverCounter();
						// activating switches
						for (int j = 0; j < world.switches.size(); j++) {
							world.switches.get(j).activate(world.player);
							world.switches.get(j).activate(world.fireball);
						}
						// enemy movement
						for (int number = 0; number < difficulty; number++) {
							for (int j = 0; j < world.enemies.size(); j++) {
								if (!world.enemies.get(j).whatClass().equals("giantCat") || number == 0) {
									world.enemies.get(j).update();
								}
								// enemies damaging player
								world.player.checkEnemy(world.enemies.get(j));
							}
						}
						// gravity chambers move and change opacity
						for (int j = 0; j < world.gravityChambers.size(); j++) {
							world.gravityChambers.get(j).move();
							world.gravityChambers.get(j).changeOpacity();
						}
						// throw fireball
						String st = "none";
						if (isZPressed) {
							st = "left";
						}
						if (isXPressed) {
							st = "right";
						}
						b = false;
						if (isZPressed || isXPressed) {
							b = true;
						}
						world.player.throwFireBall(world.fireball, st, b);
						world.fireball.move();
						// gravity chambers and spikes pushing player
						for (int j = 0; j < world.gravityChambers.size(); j++) {
							world.gravityChambers.get(j).pushPlayer(world.player);
						}
						// firecubes changing
						for (int j = 0; j < world.fireCubes.size(); j++) {
							for (int number = 0; number < difficulty; number++) {
								if (number == 0 || world.fireCubes.get(j).difficulty) {
									world.fireCubes.get(j).changeTime();
								}
							}
						}
						// player getting damaged (not by enemies)
						for (int x = 0; x < world.spikes.size(); x++) {
							world.player.checkSpikes(world.spikes.get(x));
						}
						for (int x = 0; x < world.fireCubes.size(); x++) {
							world.player.checkFireCube(world.fireCubes.get(x));
						}
						for (int x = 0; x < world.enemies.size(); x++) {
							world.fireball.attack(world.enemies.get(x));
						}
						// fall
						if (world.player.falling() && !world.touchingUpChamber()) {
							if (world.player.getFallCounter() < 50) {
								for (int k = 0; k < 3; k++) {
									if (world.player.falling() && !world.touchingUpChamber()) {
										world.player.fall();
									}
								}
								world.player.changeFallCounter(true);
							} else {
								for (int k = 0; k < 6; k++) {
									if (world.player.falling() && !world.touchingUpChamber()) {
										world.player.fall();
									}
								}
								world.player.changeFallCounter(true);
							}
						} else {
							world.player.changeFallCounter(false);
						}
						for (int x = 0; x < world.powerups.size(); x++) {
							world.powerups.get(x).activate(world.player);
						}
						// changes checkpoint
						for (int x = 0; x < world.checkpoints.size(); x++) {
							world.player.changeCheckpoint(world.checkpoints.get(x));
						}
						if (world.player.isTouching(world.exitPortal) && world.getLevelFinished() < 1) {
							world.finishLevel();
						}
						if (world.getLevelFinished() > 0 && i % 2 == 0) {
							if (world.getLevelFinished() <= 255 && world.menu.equals("none")) {
								world.incrementLevelFinished();
								world.exitPortal.grow();
							}
							if (world.getLevelFinished() == 269 && world.menu.equals("none")) {
								for (int x = 0; x < world.menuButtons.size(); x++) {
									world.menuButtons.remove(0);
								}
								world.menuButtons.add(new MenuButton(450, 500, 150, "Continue", "none", "none"));
								world.incrementLevelFinished();
							}
							if (world.getLevelFinished() >= 256 && world.getLevelFinished() < 270) {
								world.incrementLevelFinished();
							}
						}
					}
				}
				frame.repaint();
			}
		});
		KeyListener keyListener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (!world.isMenu) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							isLeftPressed = true;
							break;
						case KeyEvent.VK_RIGHT:
							isRightPressed = true;
							break;
						case KeyEvent.VK_UP:
							isUpPressed = true;
							break;
						case KeyEvent.VK_Z:
							isZPressed = true;
							break;
						case KeyEvent.VK_X:
							isXPressed = true;
							break;
						case KeyEvent.VK_S:
							// isSPressed = true;
							break;
						default:
							break;
					}
				}
				for (int x = 0; x < world.textInputs.size(); x++) {
					if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
						world.textInputs.get(x).setShift(true);
					}
					if (world.textInputs.get(x).getMenu().equals(world.menu)) {
						world.textInputs.get(x).addLetter(e.getKeyCode());
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				if (!world.isMenu) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							isLeftPressed = false;
							break;
						case KeyEvent.VK_RIGHT:
							isRightPressed = false;
							break;
						case KeyEvent.VK_UP:
							isUpPressed = false;
							break;
						case KeyEvent.VK_Z:
							isZPressed = false;
							break;
						case KeyEvent.VK_X:
							isXPressed = false;
							break;
						case KeyEvent.VK_S:
							// isSPressed = false;
							break;
						default:
							break;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					for (int x = 0; x < world.textInputs.size(); x++) {
						world.textInputs.get(x).setShift(false);
					}
				}
			}

			public void keyTyped(KeyEvent e) {

			}
		};
		MouseListener mouseListener = new MouseListener() {

			public void mousePressed(MouseEvent e) {
				for (int x = 0; x < world.menuButtons.size(); x++) {
					if (world.menuButtons.get(x).getMenu().equals(world.menu) && world.menuButtons.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
						isButtonPressed = true;
					}
				}
				if (!isButtonPressed) {
					for (int x = 0; x < world.helpBubbles.size(); x++) {
						if (world.helpBubbles.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
							isBubblePressed = true;
						}
					}
				}
				for (int x = 0; x < world.toggleButtons.size(); x++) {
					if (world.toggleButtons.get(x).getMenu().equals(world.menu) && world.toggleButtons.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
						isButtonPressed = true;
					}
				}
				for (int x = 0; x < world.hiddenToggleButtons.size(); x++) {
					if (world.hiddenToggleButtons.get(x).getMenu().equals(world.menu) && world.hiddenToggleButtons.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
						isButtonPressed = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (isButtonPressed) {
					for (int x = 0; x < world.hiddenToggleButtons.size(); x++) {
						if (world.hiddenToggleButtons.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false)) && world.hiddenToggleButtons.get(x).getMenu().equals(world.menu)) {
							world.hiddenToggleButtons.get(x).toggle();
						}
					}
					for (int x = 0; x < world.menuButtons.size(); x++) {
						if (world.menuButtons.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false)) && world.menuButtons.get(x).getMenu().equals(world.menu)) {
							if (world.menuButtons.get(x).getName().equals("Exit")) {
								changeLevel = "main menu";
							}
							if (world.menuButtons.get(x).getName().equals("Play")) {
								changeLevel = "level 1";
							}
							if (world.menuButtons.get(x).getName().equals("Terms of Agreement") || world.menuButtons.get(x).getMenu().equals("Terms of Agreement")) {
								world.helpDisplay.changeBubble(world.helpBubbles.get(0));
							}
							if (world.menuButtons.get(x).getName().equals("Continue") && world.menuButtons.get(x).getMenu().equals("none")) {
								changeLevel = "main menu";
							}
							if (world.menuButtons.get(x).getName().equals("Fix Window Size")) {
								int[] temp = world.getImageSize();
								frame.setSize(temp[0] + 8, temp[1] + 24);
							}
							if (world.menuButtons.get(x).getName().equals("Set Preferred Size")) {
								options.setPreferredSize(frame.getWidth(), frame.getHeight());
							}
							if (world.menuButtons.get(x).getName().equals("Use Default Size")) {
								frame.setSize(options.getDefaultWidth(), options.getDefaultHeight());
							}
							if (world.menuButtons.get(x).getName().equals("Use Preferred Size")) {
								frame.setSize(options.getPreferredWidth(), options.getPreferredHeight());
							}
							if (world.menuButtons.get(x).getName().equals("Save")) {
								for (int y = 0; y < world.toggleButtons.size(); y++) {
									if (world.toggleButtons.get(y).getTag().equals("Draw Outlines")) {
										options.setDrawOutline(world.toggleButtons.get(y).getSelected());
									}
									if (world.toggleButtons.get(y).getTag().equals("Revive Enemies")) {
										options.setReviveEnemies(world.toggleButtons.get(y).getSelected());
									}
								}
								for (int y = 0; y < world.hiddenToggleButtons.size(); y++) {
									if (world.hiddenToggleButtons.get(y).getTag().equals("Speed")) {
										options.setSpeed(world.hiddenToggleButtons.get(y).getOption());
									}
									if (world.hiddenToggleButtons.get(y).getTag().equals("MaxSpeed")) {
										options.setMaxSpeed(world.hiddenToggleButtons.get(y).getOption());
										speed = 10 * (1 - options.getMaxSpeed()) + 25;
										timer.setDelay(speed);
									}
								}
								world.setOptions(options);
								try {
									ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
									out.writeObject(options);
									out.close();
								} catch (FileNotFoundException exception) {
									try {
										ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
										out.writeObject(options);
										out.close();
									} catch (IOException exception2) {
										System.err.println("Options could not be saved.");
									}
								} catch (IOException exception) {
									try {
										f.createNewFile();
										ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
										out.writeObject(options);
										out.close();
									} catch (IOException exception2) {
										System.err.println("Options could not be saved.");
									}
								}
							}
							if (changeLevel.equals("none")) {
								for (int y = 0; y < world.toggleButtons.size(); y++) {
									if (world.toggleButtons.get(y).getTag().equals("")) {
										world.toggleButtons.get(y).reset();
									}
								}
								for (int y = 0; y < world.textInputs.size(); y++) {
									world.textInputs.get(y).reset();
								}
							}
							world.menu = world.menuButtons.get(x).getSend();
						}
					}
				}
				if (!isButtonPressed && isBubblePressed) {
					for (int x = 0; x < world.helpBubbles.size(); x++) {
						if (world.helpBubbles.get(x).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
							world.helpDisplay.changeBubble(world.helpBubbles.get(x));
						}
					}
				}
				isButtonPressed = false;
				isBubblePressed = false;
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
		};
		frame.addKeyListener(keyListener);
		world.addMouseListener(mouseListener);
		timer.setInitialDelay(speed);
		timer.start();
	}

	public int getScaledClick(int x, boolean b) {
		if (b) {
			return (int) (x * 1250 / (double) (world.getImageSize()[0]));
		} else {
			return (int) (x * 750 / (double) (world.getImageSize()[1]));
		}
	}

	public String getTerms() {
		return terms;
	}

	public String getHelp() {
		return help;
	}

	public String getHowToUseOptions() {
		return howToUseOptions;
	}
}
