package gravityScramble.runTimeStuff;

import gravityScramble.clickables.HelpBubble;
import gravityScramble.clickables.HelpBubbleDisplay;
import gravityScramble.clickables.HiddenToggleButton;
import gravityScramble.clickables.MenuButton;
import gravityScramble.clickables.ToggleButton;
import gravityScramble.touchables.Checkpoint;
import gravityScramble.touchables.ExitPortal;
import gravityScramble.touchables.Player;
import gravityScramble.touchables.Switch;
import gravityScramble.touchables.enemies.Boss;
import gravityScramble.touchables.enemies.Enemy;
import gravityScramble.touchables.obstacles.FireCube;
import gravityScramble.touchables.obstacles.GravityChamber;
import gravityScramble.touchables.obstacles.Spikes;
import gravityScramble.touchables.powerups.Fireball;
import gravityScramble.touchables.powerups.Powerup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

public class UniversalLevelPlayer
{
  private Timer timer;
  private int speed = 25;
  private boolean isLeftPressed;
  private boolean isRightPressed;
  private boolean isUpPressed;
  private boolean isZPressed;
  private boolean isXPressed;
  private boolean isSPressed;
  private boolean isPPressed;
  private boolean isBubblePressed = false;
  private boolean isButtonPressed = false;
  private String cheatCode = "";
  private int count = 0;
  private int difficulty = 1;
  Level world;
  private String changeLevel = "none";
  private Options options;
  private final String terms = "Terms of Agreement/n/nYou may redistribute this version (1.0) of the game to others for them to enjoy. However, you may not make money from this game or any elements of it in any way./nYou may decompile and modify this version of the game, but you may not distribute any modified version or part of a modified version of this game, nor can you modify the terms of agreement or the version number./n-Nathaniel Minsung Kim";

  private final String help = "If the keyboard does not work, minimize the window then reopen it. This should make it work again./nTo see the instructions, click on all of the question mark bubbles scattered throughout the game.";

  private final String howToUseOptions = "Draw Outlines - Turn this on to see the hitbox for most objects./nRevive Enemies - Turn this on to revive all enemies every time you die. The boss will also regain its health./nSpeed - Toggle this to adjust the speed of the game. Higher speeds may result in lower-quality animations./nMax. Speed - Toggle this to adjust the highest speed of the game. Higher speeds might be more resource-intensive on your computer./nFix Window Size - Click on this to remove any gray areas from your window./nSet Preferred Size - Click on this to use the current window size as your preferred size./nUse Default Size - Click on this to use the same window size asthe first time you played./nUse Preferred Size - Click on this to change your window size to your preferred size.";

  public void startGame()
    throws IOException
  {
    File f = new File("options.dat");
    if (f.exists())
    {
      try
      {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        options = ((Options)(Options)in.readObject());
        in.close();
      }
      catch (IOException e)
      {
        options = new Options();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(options);
        out.close();
      }
      catch (ClassCastException e)
      {
        options = new Options();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(options);
        out.close();
      }
      catch (ClassNotFoundException e)
      {
        options = new Options();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(options);
        out.close();
      }
    }
    else
    {
      options = new Options();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
      out.writeObject(options);
      out.close();
    }
    world = new Level(options);
    world.changeVariables(new Menu());
    JFrame frame = new JFrame("Gravity Scramble");
    frame.setSize(options.getPreferredWidth(), options.getPreferredHeight());
    frame.setLocation(0, 0);
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);
    frame.setContentPane(world);
    speed = (10 * (1 - options.getMaxSpeed()) + 25);
    timer = new Timer(speed, new ActionListener(frame)
    {
      public void actionPerformed(ActionEvent e)
      {
        world.setSize(val$frame.getWidth() - 8, val$frame.getHeight() - 24);
        if (!changeLevel.equals("none"))
        {
          UniversalLevelPlayer.access$102(UniversalLevelPlayer.this, false);
          if (changeLevel.equals("main menu"))
          {
            UniversalLevelPlayer.access$202(UniversalLevelPlayer.this, "");
            UniversalLevelPlayer.access$302(UniversalLevelPlayer.this, 1);
            world.changeVariables(new Menu());
            UniversalLevelPlayer.access$002(UniversalLevelPlayer.this, "none");
            world.setDifficulty(1);
          }
          if (changeLevel.equals("level 1"))
          {
            UniversalLevelPlayer.access$202(UniversalLevelPlayer.this, ((TextInput)world.textInputs.get(0)).getInput());
            if (((ToggleButton)world.toggleButtons.get(0)).getSelected())
            {
              UniversalLevelPlayer.access$302(UniversalLevelPlayer.this, 1);
            }
            else
            {
              UniversalLevelPlayer.access$302(UniversalLevelPlayer.this, 2);
            }
            world.changeVariables(new MainPanel1());
            UniversalLevelPlayer.access$002(UniversalLevelPlayer.this, "none");
            world.setDifficulty(difficulty);
          }
          if (difficulty == 2)
          {
            for (int x = 0; x < world.bosses.size(); x++)
            {
              ((Boss)world.bosses.get(x)).setDifficulty();
            }
          }
          if (cheatCode.equals("invincible mode"))
          {
            world.player.setInvincible(true);
          }
          if (cheatCode.equals("evil cat"))
          {
            world.player.setAppearance("evilCat");
          }
          if (cheatCode.equals("ynnub"))
          {
            world.player.setAppearance("bunny");
          }
          UniversalLevelPlayer.access$402(UniversalLevelPlayer.this, 0);
        }

        if (world.menu.equals("none"))
        {
          if ((!world.getDamageTaken()) && (world.player.getHealth() == 2))
          {
            world.takeDamage();
          }
          if ((options.getReviveEnemies()) && (world.player.reviveEnemies))
          {
            for (int x = 0; x < world.enemies.size(); x++)
            {
              ((Enemy)world.enemies.get(x)).becomeSummoned();
            }
            world.player.reviveEnemies = false;
          }
          if (world.isMenu)
          {
            UniversalLevelPlayer.access$408(UniversalLevelPlayer.this);
            if (((options.getSpeed() == 0) && (count >= 600)) || ((options.getSpeed() == 1) && (count >= 400)) || ((options.getSpeed() == 2) && (count >= 300)))
            {
              world.changeVariables(new Menu());
              UniversalLevelPlayer.access$402(UniversalLevelPlayer.this, 0);
            }
          }

          for (int kk = 0; kk < 2 + options.getSpeed(); kk++)
          {
            boolean b = false;
            for (int x = 0; x < world.powerups.size(); x++)
            {
              b = (b) || (((Powerup)world.powerups.get(x)).getActivating());
            }
            if (!b)
            {
              if (world.isMenu)
              {
                UniversalLevelPlayer.access$102(UniversalLevelPlayer.this, true);
              }

              if (isLeftPressed)
              {
                world.player.move(false);
              }
              if (isRightPressed)
              {
                world.player.move(true);
              }
              if (((isUpPressed) && ((!world.player.falling()) || (world.touchingUpChamber())) && (!world.player.getJumping())) || (world.player.getJumpCounter() > 0))
              {
                for (int x = 0; x < world.spikes.size(); x++)
                {
                  world.player.checkSpikes((Spikes)world.spikes.get(x));
                }
                world.player.jump();
              }
              else
              {
                world.player.resetJumpCounter();
              }
            }
            world.player.changeRecoverCounter();

            for (int x = 0; x < world.switches.size(); x++)
            {
              ((Switch)world.switches.get(x)).activate(world.player);
              ((Switch)world.switches.get(x)).activate(world.fireball);
            }

            for (int number = 0; number < difficulty; number++)
            {
              for (int x = 0; x < world.enemies.size(); x++)
              {
                if ((!((Enemy)world.enemies.get(x)).whatClass().equals("giantCat")) || (number == 0))
                {
                  ((Enemy)world.enemies.get(x)).update();
                }

                world.player.checkEnemy((Enemy)world.enemies.get(x));
              }
            }

            for (int x = 0; x < world.gravityChambers.size(); x++)
            {
              ((GravityChamber)world.gravityChambers.get(x)).move();
              ((GravityChamber)world.gravityChambers.get(x)).changeOpacity();
            }

            String st = "none";
            if (isZPressed)
            {
              st = "left";
            }
            if (isXPressed)
            {
              st = "right";
            }
            b = false;
            if ((isZPressed) || (isXPressed))
            {
              b = true;
            }
            world.player.throwFireBall(world.fireball, st, b);
            world.fireball.move();

            for (int x = 0; x < world.gravityChambers.size(); x++)
            {
              ((GravityChamber)world.gravityChambers.get(x)).pushPlayer(world.player);
            }

            for (int x = 0; x < world.fireCubes.size(); x++)
            {
              for (int number = 0; number < difficulty; number++)
              {
                if ((number != 0) && (!((FireCube)world.fireCubes.get(x)).difficulty))
                  continue;
                ((FireCube)world.fireCubes.get(x)).changeTime();
              }

            }

            for (int x = 0; x < world.spikes.size(); x++)
            {
              world.player.checkSpikes((Spikes)world.spikes.get(x));
            }
            for (int x = 0; x < world.fireCubes.size(); x++)
            {
              world.player.checkFireCube((FireCube)world.fireCubes.get(x));
            }
            for (int x = 0; x < world.enemies.size(); x++)
            {
              world.fireball.attack((Enemy)world.enemies.get(x));
            }

            if ((world.player.falling()) && (!world.touchingUpChamber()))
            {
              if (world.player.getFallCounter() < 50)
              {
                for (int k = 0; k < 3; k++)
                {
                  if ((!world.player.falling()) || (world.touchingUpChamber()))
                    continue;
                  world.player.fall();
                }

                world.player.changeFallCounter(true);
              }
              else
              {
                for (int k = 0; k < 6; k++)
                {
                  if ((!world.player.falling()) || (world.touchingUpChamber()))
                    continue;
                  world.player.fall();
                }

                world.player.changeFallCounter(true);
              }
            }
            else
            {
              world.player.changeFallCounter(false);
            }
            for (int x = 0; x < world.powerups.size(); x++)
            {
              ((Powerup)world.powerups.get(x)).activate(world.player);
            }

            for (int x = 0; x < world.checkpoints.size(); x++)
            {
              world.player.changeCheckpoint((Checkpoint)world.checkpoints.get(x));
            }
            if ((world.player.isTouching(world.exitPortal)) && (world.getLevelFinished() < 1))
            {
              world.finishLevel();
            }
            if ((world.getLevelFinished() <= 0) || (kk % 2 != 0))
              continue;
            if ((world.getLevelFinished() <= 255) && (world.menu.equals("none")))
            {
              world.incrementLevelFinished();
              world.exitPortal.grow();
            }
            if ((world.getLevelFinished() == 269) && (world.menu.equals("none")))
            {
              for (int x = 0; x < world.menuButtons.size(); x++)
              {
                world.menuButtons.remove(0);
              }
              world.menuButtons.add(new MenuButton(450, 500, 150, "Continue", "none", "none"));
              world.incrementLevelFinished();
            }
            if ((world.getLevelFinished() < 256) || (world.getLevelFinished() >= 270))
              continue;
            world.incrementLevelFinished();
          }

        }

        val$frame.repaint();
      }
    });
    KeyListener keyListener = new KeyListener()
    {
      public void keyPressed(KeyEvent e)
      {
        if (!world.isMenu)
        {
          switch (e.getKeyCode())
          {
          case 37:
            UniversalLevelPlayer.access$602(UniversalLevelPlayer.this, true);
            break;
          case 39:
            UniversalLevelPlayer.access$702(UniversalLevelPlayer.this, true);
            break;
          case 38:
            UniversalLevelPlayer.access$102(UniversalLevelPlayer.this, true);
            break;
          case 90:
            UniversalLevelPlayer.access$802(UniversalLevelPlayer.this, true);
            break;
          case 88:
            UniversalLevelPlayer.access$902(UniversalLevelPlayer.this, true);
            break;
          case 83:
            UniversalLevelPlayer.access$1002(UniversalLevelPlayer.this, true);
            break;
          }

        }

        for (int x = 0; x < world.textInputs.size(); x++)
        {
          if (e.getKeyCode() == 16)
          {
            ((TextInput)world.textInputs.get(x)).setShift(true);
          }
          if (!((TextInput)world.textInputs.get(x)).getMenu().equals(world.menu))
            continue;
          ((TextInput)world.textInputs.get(x)).addLetter(e.getKeyCode());
        }
      }

      public void keyReleased(KeyEvent e)
      {
        if (!world.isMenu)
        {
          switch (e.getKeyCode())
          {
          case 37:
            UniversalLevelPlayer.access$602(UniversalLevelPlayer.this, false);
            break;
          case 39:
            UniversalLevelPlayer.access$702(UniversalLevelPlayer.this, false);
            break;
          case 38:
            UniversalLevelPlayer.access$102(UniversalLevelPlayer.this, false);
            break;
          case 90:
            UniversalLevelPlayer.access$802(UniversalLevelPlayer.this, false);
            break;
          case 88:
            UniversalLevelPlayer.access$902(UniversalLevelPlayer.this, false);
            break;
          case 83:
            UniversalLevelPlayer.access$1002(UniversalLevelPlayer.this, false);
            break;
          }

        }

        if (e.getKeyCode() == 16)
        {
          for (int x = 0; x < world.textInputs.size(); x++)
          {
            ((TextInput)world.textInputs.get(x)).setShift(false);
          }
        }
      }

      public void keyTyped(KeyEvent e)
      {
      }
    };
    MouseListener mouseListener = new MouseListener(frame, f)
    {
      public void mousePressed(MouseEvent e)
      {
        for (int x = 0; x < world.menuButtons.size(); x++)
        {
          if ((!((MenuButton)world.menuButtons.get(x)).getMenu().equals(world.menu)) || (!((MenuButton)world.menuButtons.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))))
          {
            continue;
          }
          UniversalLevelPlayer.access$1102(UniversalLevelPlayer.this, true);
        }

        if (!isButtonPressed)
        {
          for (int x = 0; x < world.helpBubbles.size(); x++)
          {
            if (!((HelpBubble)world.helpBubbles.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
              continue;
            }
            UniversalLevelPlayer.access$1202(UniversalLevelPlayer.this, true);
          }
        }

        for (int x = 0; x < world.toggleButtons.size(); x++)
        {
          if ((!((ToggleButton)world.toggleButtons.get(x)).getMenu().equals(world.menu)) || (!((ToggleButton)world.toggleButtons.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))))
          {
            continue;
          }
          UniversalLevelPlayer.access$1102(UniversalLevelPlayer.this, true);
        }

        for (int x = 0; x < world.hiddenToggleButtons.size(); x++)
        {
          if ((!((HiddenToggleButton)world.hiddenToggleButtons.get(x)).getMenu().equals(world.menu)) || (!((HiddenToggleButton)world.hiddenToggleButtons.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))))
          {
            continue;
          }
          UniversalLevelPlayer.access$1102(UniversalLevelPlayer.this, true);
        }
      }

      public void mouseReleased(MouseEvent e)
      {
        if (isButtonPressed)
        {
          for (int x = 0; x < world.hiddenToggleButtons.size(); x++)
          {
            if ((!((HiddenToggleButton)world.hiddenToggleButtons.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) || (!((HiddenToggleButton)world.hiddenToggleButtons.get(x)).getMenu().equals(world.menu)))
            {
              continue;
            }
            ((HiddenToggleButton)world.hiddenToggleButtons.get(x)).toggle();
          }

          for (int x = 0; x < world.menuButtons.size(); x++)
          {
            if ((!((MenuButton)world.menuButtons.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) || (!((MenuButton)world.menuButtons.get(x)).getMenu().equals(world.menu)))
            {
              continue;
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Exit"))
            {
              UniversalLevelPlayer.access$002(UniversalLevelPlayer.this, "main menu");
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Play"))
            {
              UniversalLevelPlayer.access$002(UniversalLevelPlayer.this, "level 1");
            }
            if ((((MenuButton)world.menuButtons.get(x)).getName().equals("Terms of Agreement")) || (((MenuButton)world.menuButtons.get(x)).getMenu().equals("Terms of Agreement")))
            {
              world.helpDisplay.changeBubble((HelpBubble)world.helpBubbles.get(0));
            }
            if ((((MenuButton)world.menuButtons.get(x)).getName().equals("Continue")) && (((MenuButton)world.menuButtons.get(x)).getMenu().equals("none")))
            {
              UniversalLevelPlayer.access$002(UniversalLevelPlayer.this, "main menu");
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Fix Window Size"))
            {
              int[] temp = world.getImageSize();
              val$frame.setSize(temp[0] + 8, temp[1] + 24);
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Set Preferred Size"))
            {
              options.setPreferredSize(val$frame.getWidth(), val$frame.getHeight());
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Use Default Size"))
            {
              val$frame.setSize(options.getDefaultWidth(), options.getDefaultHeight());
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Use Preferred Size"))
            {
              val$frame.setSize(options.getPreferredWidth(), options.getPreferredHeight());
            }
            if (((MenuButton)world.menuButtons.get(x)).getName().equals("Save"))
            {
              for (int y = 0; y < world.toggleButtons.size(); y++)
              {
                if (((ToggleButton)world.toggleButtons.get(y)).getTag().equals("Draw Outlines"))
                {
                  options.setDrawOutline(((ToggleButton)world.toggleButtons.get(y)).getSelected());
                }
                if (!((ToggleButton)world.toggleButtons.get(y)).getTag().equals("Revive Enemies"))
                  continue;
                options.setReviveEnemies(((ToggleButton)world.toggleButtons.get(y)).getSelected());
              }

              for (int y = 0; y < world.hiddenToggleButtons.size(); y++)
              {
                if (((HiddenToggleButton)world.hiddenToggleButtons.get(y)).getTag().equals("Speed"))
                {
                  options.setSpeed(((HiddenToggleButton)world.hiddenToggleButtons.get(y)).getOption());
                }
                if (!((HiddenToggleButton)world.hiddenToggleButtons.get(y)).getTag().equals("MaxSpeed"))
                  continue;
                options.setMaxSpeed(((HiddenToggleButton)world.hiddenToggleButtons.get(y)).getOption());
                UniversalLevelPlayer.access$1302(UniversalLevelPlayer.this, 10 * (1 - options.getMaxSpeed()) + 25);
                timer.setDelay(speed);
              }

              world.setOptions(options);
              try
              {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(val$f));
                out.writeObject(options);
                out.close();
              }
              catch (FileNotFoundException exception)
              {
                try
                {
                  ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(val$f));
                  out.writeObject(options);
                  out.close();
                }
                catch (IOException exception2)
                {
                  System.err.println("Options could not be saved.");
                }
              }
              catch (IOException exception)
              {
                try
                {
                  val$f.createNewFile();
                  ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(val$f));
                  out.writeObject(options);
                  out.close();
                }
                catch (IOException exception2)
                {
                  System.err.println("Options could not be saved.");
                }
              }
            }
            if (changeLevel.equals("none"))
            {
              for (int y = 0; y < world.toggleButtons.size(); y++)
              {
                if (!((ToggleButton)world.toggleButtons.get(y)).getTag().equals(""))
                  continue;
                ((ToggleButton)world.toggleButtons.get(y)).reset();
              }

              for (int y = 0; y < world.textInputs.size(); y++)
              {
                ((TextInput)world.textInputs.get(y)).reset();
              }
            }
            world.menu = ((MenuButton)world.menuButtons.get(x)).getSend();
          }
        }

        if ((!isButtonPressed) && (isBubblePressed))
        {
          for (int x = 0; x < world.helpBubbles.size(); x++)
          {
            if (!((HelpBubble)world.helpBubbles.get(x)).isClickedBy(getScaledClick(e.getX(), true), getScaledClick(e.getY(), false))) {
              continue;
            }
            world.helpDisplay.changeBubble((HelpBubble)world.helpBubbles.get(x));
          }
        }

        UniversalLevelPlayer.access$1102(UniversalLevelPlayer.this, false);
        UniversalLevelPlayer.access$1202(UniversalLevelPlayer.this, false);
      }

      public void mouseClicked(MouseEvent e)
      {
      }

      public void mouseEntered(MouseEvent e)
      {
      }

      public void mouseExited(MouseEvent e)
      {
      }
    };
    frame.addKeyListener(keyListener);
    world.addMouseListener(mouseListener);
    timer.setInitialDelay(speed);
    timer.start();
  }

  public int getScaledClick(int x, boolean b)
  {
    if (b)
    {
      return (int)(x * 1250 / world.getImageSize()[0]);
    }

    return (int)(x * 750 / world.getImageSize()[1]);
  }

  public String getTerms()
  {
    return "Terms of Agreement/n/nYou may redistribute this version (1.0) of the game to others for them to enjoy. However, you may not make money from this game or any elements of it in any way./nYou may decompile and modify this version of the game, but you may not distribute any modified version or part of a modified version of this game, nor can you modify the terms of agreement or the version number./n-Nathaniel Minsung Kim";
  }

  public String getHelp() {
    return "If the keyboard does not work, minimize the window then reopen it. This should make it work again./nTo see the instructions, click on all of the question mark bubbles scattered throughout the game.";
  }

  public String getHowToUseOptions() {
    return "Draw Outlines - Turn this on to see the hitbox for most objects./nRevive Enemies - Turn this on to revive all enemies every time you die. The boss will also regain its health./nSpeed - Toggle this to adjust the speed of the game. Higher speeds may result in lower-quality animations./nMax. Speed - Toggle this to adjust the highest speed of the game. Higher speeds might be more resource-intensive on your computer./nFix Window Size - Click on this to remove any gray areas from your window./nSet Preferred Size - Click on this to use the current window size as your preferred size./nUse Default Size - Click on this to use the same window size asthe first time you played./nUse Preferred Size - Click on this to change your window size to your preferred size.";
  }
}