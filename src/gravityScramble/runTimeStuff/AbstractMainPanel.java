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

public abstract class AbstractMainPanel extends JPanel
{
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
  private final int imageWidth = 1250;
  private final int imageHeight = 750;
  private int levelFinished = 0;
  private int difficulty = 1;
  private boolean damageTaken = false;

  public void makeVariables(boolean menu, Color sk, Color grnd) {
    isMenu = menu;
    sky = sk;
    ground = grnd;
  }

  public void makeButtons()
  {
    if (!isMenu)
    {
      menuButtons.add(new MenuButton(1100, 10, 200, "Pause", "none", "Pause Menu"));
      menuButtons.add(new MenuButton(500, 300, 200, "Continue", "Pause Menu", "none"));
      menuButtons.add(new MenuButton(500, 350, 200, "Options", "Pause Menu", "Options"));
      menuButtons.add(new MenuButton(500, 400, 200, "Exit", "Pause Menu", "none"));
      menuButtons.add(new MenuButton(1100, 10, 200, "Save", "Options", "Pause Menu"));
    }
    toggleButtons.add(new ToggleButton(500, 50, 100, "On", "Off", options.getDrawOutline(), "Options", "Draw Outlines"));
    toggleButtons.add(new ToggleButton(500, 150, 100, "On", "Off", options.getReviveEnemies(), "Options", "Revive Enemies"));
    hiddenToggleButtons.add(new HiddenToggleButton(500, 350, 150, new String[] { "Normal", "Fast", "Faster" }, options.getSpeed(), "Options", "Speed"));

    hiddenToggleButtons.add(new HiddenToggleButton(500, 450, 150, new String[] { "Slow", "Normal", "Fast" }, options.getMaxSpeed(), "Options", "MaxSpeed"));

    menuButtons.add(new MenuButton(10, 550, 250, "Fix Window Size", "Options", "Options"));
    menuButtons.add(new MenuButton(500, 550, 250, "Set Preferred Size", "Options", "Options"));
    menuButtons.add(new MenuButton(10, 650, 250, "Use Default Size", "Options", "Options"));
    menuButtons.add(new MenuButton(500, 650, 250, "Use Preferred Size", "Options", "Options"));
  }

  public void paintComponent(Graphics gr)
  {
    BufferedImage image = new BufferedImage(1250, 750, 1);
    Graphics g = image.getGraphics();
    g.setColor(sky);
    g.fillRect(0, 0, 1250, 750);
    g.setColor(ground);
    int altitude = 0;
    if (player.getAltitude() >= 300)
    {
      altitude = player.getAltitude() - 300;
    }
    g.fillRect(0, 640 + altitude, 1250, 110);
    int position = player.getPosition();
    for (int x = 0; x < decorations.size(); x++)
    {
      ((Decoration)decorations.get(x)).paintComponent(g, position, altitude);
    }
    for (int x = 0; x < checkpoints.size(); x++)
    {
      ((Checkpoint)checkpoints.get(x)).paintComponent(g, position, altitude);
      drawOutlines((Touchable)checkpoints.get(x), g, position, altitude);
    }
    for (int x = 0; x < switches.size(); x++)
    {
      ((Switch)switches.get(x)).paintComponent(g, position, altitude);
      if (!((Switch)switches.get(x)).getActivatable())
        continue;
      drawOutlines((Touchable)switches.get(x), g, position, altitude);
    }

    exitPortal.paintComponent(g, position, altitude);
    player.paintComponent(g, position, altitude);
    drawOutlines(player, g, position, altitude);
    for (int x = 0; x < enemies.size(); x++)
    {
      ((Enemy)enemies.get(x)).paintComponent(g, position, altitude);
      if ((enemies.get(x) instanceof Group))
      {
        for (int y = 0; y < ((Group)(Group)enemies.get(x)).enemies.size(); y++)
        {
          drawOutlines((Touchable)((Group)(Group)enemies.get(x)).enemies.get(y), g, position, altitude);
        }
      }
      else
      {
        drawOutlines((Touchable)enemies.get(x), g, position, altitude);
        if (!(enemies.get(x) instanceof GiantCat))
          continue;
        drawOutlines(((GiantCat)(GiantCat)enemies.get(x)).summonedCat1, g, position, altitude);
        drawOutlines(((GiantCat)(GiantCat)enemies.get(x)).summonedCat2, g, position, altitude);
        drawOutlines(((GiantCat)(GiantCat)enemies.get(x)).summonedBunny1, g, position, altitude);
        drawOutlines(((GiantCat)(GiantCat)enemies.get(x)).summonedBunny2, g, position, altitude);
        drawOutlines(((GiantCat)(GiantCat)enemies.get(x)).summonedFollower, g, position, altitude);
      }
    }

    for (int x = 0; x < fireCubes.size(); x++)
    {
      ((FireCube)fireCubes.get(x)).paintComponent(g, position, altitude);
    }
    for (int x = 0; x < gravityChambers.size(); x++)
    {
      ((GravityChamber)gravityChambers.get(x)).paintComponent(g, position, altitude);
      if (!(gravityChambers.get(x) instanceof Spikes))
        continue;
      drawOutlines((Touchable)gravityChambers.get(x), g, position, altitude);
    }

    for (int x = 0; x < powerups.size(); x++)
    {
      ((Powerup)powerups.get(x)).paintComponent(g, position, altitude);
    }
    fireball.paintComponent(g, position, altitude);
    if (fireball.getActive())
    {
      drawOutlines(fireball, g, position, altitude);
    }
    for (int x = 0; x < helpBubbles.size(); x++)
    {
      ((HelpBubble)helpBubbles.get(x)).paintComponent(g, position, altitude);
    }
    boolean b = false;
    Boss boss = null;
    for (int x = 0; x < bosses.size(); x++)
    {
      if (!((Boss)bosses.get(x)).getBattle())
        continue;
      b = true;
      boss = (Boss)bosses.get(x);
    }

    if (b)
    {
      Color fog = new Color(230, 230, 230, 70);
      g.setColor(fog);
      g.fillRect(0, 0, 1300, 750);
      g.setColor(Color.black);
      for (int x = 1; x <= boss.getHealth(); x++)
      {
        g.fillOval(1230 - x * 30, 60, 25, 25);
      }
    }
    helpDisplay.paintComponent(g);
    g.setColor(Color.pink);
    for (int x = 1; x <= player.getHealth(); x++)
    {
      g.fillOval(30 * x - 20, 10, 25, 25);
    }
    if (levelFinished > 0)
    {
      if (levelFinished <= 255)
      {
        g.setColor(new Color(exitPortal.getBrightness(), 0, exitPortal.getBrightness(), levelFinished));
      }
      else
      {
        g.setColor(new Color(exitPortal.getBrightness(), 0, exitPortal.getBrightness(), 255));
      }
      g.fillRect(0, 0, 1250, 750);
      if (levelFinished >= 270)
      {
        g.setFont(new Font("Serif", 0, 40));
        g.setColor(Color.white);
        g.drawString("Congratulations! Try this cheat code:", 300, 100);
        String s1 = "";
        String s2 = "";
        if (difficulty == 1)
        {
          s1 = "evil cat";
          s2 = "Also try to win in hard mode!";
        }
        else if (damageTaken)
        {
          s1 = "ynnub";
          s2 = "Try to beat hard mode without taking damage!";
        }
        else
        {
          s1 = "invincible mode";
        }

        g.drawString(s1, 450, 200);
        g.drawString(s2, 300, 350);
      }
    }
    if (!menu.equals("none"))
    {
      g.setColor(new Color(100, 100, 100, 170));
      g.fillRect(0, 0, 1250, 750);
    }
    if (menu.equals("Terms of Agreement"))
    {
      helpDisplay.paintComponent(g);
    }
    for (int x = 0; x < menuButtons.size(); x++)
    {
      if (!menu.equals(((MenuButton)menuButtons.get(x)).getMenu()))
        continue;
      ((MenuButton)menuButtons.get(x)).paintComponent(g, position, altitude);
    }

    for (int x = 0; x < toggleButtons.size(); x++)
    {
      if (!menu.equals(((ToggleButton)toggleButtons.get(x)).getMenu()))
        continue;
      ((ToggleButton)toggleButtons.get(x)).paintComponent(g, position, altitude);
    }

    for (int x = 0; x < hiddenToggleButtons.size(); x++)
    {
      if (!menu.equals(((HiddenToggleButton)hiddenToggleButtons.get(x)).getMenu()))
        continue;
      ((HiddenToggleButton)hiddenToggleButtons.get(x)).paintComponent(g, position, altitude);
    }

    for (int x = 0; x < textInputs.size(); x++)
    {
      if (!menu.equals(((TextInput)textInputs.get(x)).getMenu()))
        continue;
      ((TextInput)textInputs.get(x)).paintComponent(g);
    }

    if (menu.equals("Options"))
    {
      g.setColor(Color.black);
      g.setFont(new Font("Serif", 0, 40));
      g.drawString("Draw Outlines", 10, 90);
      g.drawString("Revive Enemies", 10, 190);
      g.drawString("Music", 10, 290);
      g.drawString("Coming Soon (or not)", 500, 290);
      g.drawString("Speed", 10, 390);
      g.drawString("Max. Speed", 10, 490);
    }
    if (menu.equals("Custom Levels"))
    {
      g.setColor(Color.black);
      g.setFont(new Font("Serif", 0, 80));
      g.drawString("Coming Soon", 300, 400);
    }
    if (isMenu)
    {
      g.setColor(Color.black);
      g.setFont(new Font("Serif", 0, 20));
      g.drawString("v1.0", 5, 730);
    }
    int[] imageSize = getImageSize();
    gr.drawImage(image, 0, 0, imageSize[0], imageSize[1], null);
  }

  public int[] getImageSize()
  {
    if ((getWidth() == 1250) && (getHeight() == 750))
    {
      return new int[] { 1250, 750 };
    }
    double w = getWidth() / 1250.0D;
    double h = getHeight() / 750.0D;
    int actualWidth = 1250;
    int actualHeight = 750;
    if (w > h)
    {
      actualWidth = (int)(1250.0D * h);
      actualHeight = getHeight();
    }
    if (w < h)
    {
      actualHeight = (int)(750.0D * w);
      actualWidth = getWidth();
    }
    return new int[] { actualWidth, actualHeight };
  }

  public boolean touchingUpChamber()
  {
    boolean b = false;
    for (int x = 0; x < gravityChambers.size(); x++)
    {
      if ((!player.isTouching((Touchable)gravityChambers.get(x))) || (!((GravityChamber)gravityChambers.get(x)).getDirection().equals("up")))
        continue;
      b = true;
    }

    for (int x = 0; x < enemies.size(); x++)
    {
      if (!canJumpOnEnemy((Enemy)enemies.get(x)))
        continue;
      b = true;
    }

    return b;
  }

  public boolean canJumpOnEnemy(Enemy enemy)
  {
    if (isMenu)
    {
      return false;
    }

    if (!(enemy instanceof Group))
    {
      return (player.isPositionSimilar(enemy)) && (player.getAltitude() >= enemy.getAltitude()) && (player.getAltitude() <= enemy.getAltitude() + enemy.getHeight()) && ((!enemy.getDefeated()) || (enemy.getBounceCounter() != 5));
    }

    boolean b = false;
    for (int x = 0; x < ((Group)enemy).enemies.size(); x++)
    {
      b = (b) || (canJumpOnEnemy((Enemy)((Group)enemy).enemies.get(x)));
    }
    return b;
  }

  public Color getSky()
  {
    return sky;
  }

  public Color getGround() {
    return ground;
  }

  public void drawOutlines(Touchable t, Graphics g, int position, int altitude)
  {
    if ((options.getDrawOutline()) && ((!(t instanceof Enemy)) || (!((Enemy)t).getDefeated())))
    {
      g.setColor(Color.white);
      g.drawRect(t.drawingPosition(position), t.drawingAltitude(altitude - t.getHeight()), t.getWidth(), t.getHeight());
    }
  }

  public void setOptions(Options o)
  {
    options = o;
  }

  public Options getOptions() {
    return options;
  }

  public void resetLevelFinished()
  {
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