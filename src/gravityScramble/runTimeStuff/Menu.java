package gravityScramble.runTimeStuff;

import gravityScramble.clickables.HelpBubble;
import gravityScramble.clickables.HelpBubbleDisplay;
import gravityScramble.clickables.MenuButton;
import gravityScramble.clickables.ToggleButton;
import gravityScramble.touchables.Checkpoint;
import gravityScramble.touchables.ExitPortal;
import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import gravityScramble.touchables.enemies.Bunny;
import gravityScramble.touchables.enemies.EvilCat;
import gravityScramble.touchables.enemies.Follower;
import gravityScramble.touchables.obstacles.GravityChamber;
import gravityScramble.touchables.powerups.Fireball;
import java.awt.Color;
import java.util.ArrayList;

public class Menu extends AbstractMainPanel
{
  public Menu()
  {
    refresh();
  }

  public void refresh()
  {
    exitPortal = new ExitPortal(0, 1000);
    helpBubbles = new ArrayList();
    decorations = new ArrayList();
    enemies = new ArrayList();
    bosses = new ArrayList();
    gravityChambers = new ArrayList();
    spikes = new ArrayList();
    fireCubes = new ArrayList();
    switches = new ArrayList();
    powerups = new ArrayList();
    checkpoints = new ArrayList();
    menuButtons = new ArrayList();
    toggleButtons = new ArrayList();
    hiddenToggleButtons = new ArrayList();
    textInputs = new ArrayList();
    checkpoints.add(new Checkpoint(0, 0, true));
    player = new Player(0, 0, (Checkpoint)checkpoints.get(0));
    fireball = new Fireball(0, 0, player);
    helpDisplay = new HelpBubbleDisplay();
    gravityChambers.add(new GravityChamber(300, 0, "up"));
    gravityChambers.add(new GravityChamber(-500, 0, "right"));
    enemies.add(new EvilCat(-400, 0, (Touchable)gravityChambers.get(1), (Touchable)gravityChambers.get(0)));
    enemies.add(new Bunny(-100, 0, (Touchable)gravityChambers.get(1), (Touchable)gravityChambers.get(0)));
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