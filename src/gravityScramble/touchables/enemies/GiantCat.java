package gravityScramble.touchables.enemies;

import gravityScramble.switchActions.SwitchAction2;
import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public class GiantCat extends Boss
  implements Serializable
{
  private String direction;
  private int savePosition;
  private int saveAltitude;
  private int attack;
  private int moveCounter;
  private int pauseCounter;
  public EvilCat summonedCat1;
  public EvilCat summonedCat2;
  public Bunny summonedBunny1;
  public Bunny summonedBunny2;
  public Follower summonedFollower;
  private boolean normalColor;
  private int savePlayerPosition;
  private boolean smiling;
  private boolean weakened;
  private boolean recovering;
  private int waitCounter;
  private int distance;
  private boolean trick;

  public GiantCat(int pos, int alt, Player pl, EvilCat e1, EvilCat e2, Bunny b1, Bunny b2, Follower fol, Touchable left, Touchable right, SwitchAction2 defAction)
  {
    super(160, 200, pos, alt, 255, 3, pl, left, right, defAction, "giantCat");
    direction = "none";
    savePosition = pos;
    saveAltitude = alt;
    attack = 1;
    moveCounter = 0;
    pauseCounter = 0;
    summonedCat1 = e1;
    summonedCat2 = e2;
    summonedBunny1 = b1;
    summonedBunny2 = b2;
    summonedFollower = fol;
    summonedCat1.becomeDefeated();
    summonedCat2.becomeDefeated();
    summonedBunny1.becomeDefeated();
    summonedBunny2.becomeDefeated();
    summonedFollower.becomeDefeated();
    summonedCat1.setInitialCoordinates(savePosition - 180, saveAltitude);
    summonedCat2.setInitialCoordinates(savePosition + 260, saveAltitude);
    summonedBunny1.setInitialCoordinates(savePosition - 140, saveAltitude);
    summonedBunny2.setInitialCoordinates(savePosition + 260, saveAltitude);
    summonedFollower.setInitialCoordinates(savePosition + 20, saveAltitude + 300);
    normalColor = true;
    savePlayerPosition = player.getPosition();
    smiling = true;
    weakened = false;
    recovering = false;
    waitCounter = 0;
    distance = 0;
    trick = false;
  }

  public void drawing(Graphics g, int x, int y)
  {
    Color cat = new Color(225, 130, 60, getOpacity());
    g.setColor(cat);
    g.fillRect(drawingPosition(x), 480 + y - altitude, 160, 120);
    g.fillRect(drawingPosition(x) + 10, 600 + y - altitude, 40, 40);
    g.fillRect(drawingPosition(x) + 110, 600 + y - altitude, 40, 40);
    g.fillRect(drawingPosition(x) + 40, 440 + y - altitude, 80, 80);
    int[] xPoints = { drawingPosition(x) + 50, drawingPosition(x) + 60, drawingPosition(x) + 70 };
    int[] yPoints = { 440 + y - altitude, 420 + y - altitude, 440 + y - altitude };
    g.fillPolygon(xPoints, yPoints, 3);
    int[] xPoints2 = { drawingPosition(x) + 90, drawingPosition(x) + 100, drawingPosition(x) + 110 };
    g.fillPolygon(xPoints2, yPoints, 3);
    Color differentBlack = new Color(0, 0, 0, getOpacity());
    Color differentRed = new Color(255, 0, 0, getOpacity());
    if (normalColor)
    {
      g.setColor(differentBlack);
    }
    else
    {
      g.setColor(differentRed);
    }
    g.drawRect(drawingPosition(x) + 40, 440 + y - altitude, 80, 80);
    g.fillRect(drawingPosition(x) + 60, 470 + y - altitude, 10, 10);
    g.fillRect(drawingPosition(x) + 90, 470 + y - altitude, 10, 10);
    Color differentWhite = new Color(255, 255, 255, getOpacity());
    if (normalColor)
    {
      g.setColor(differentWhite);
    }
    else
    {
      g.setColor(differentBlack);
    }
    g.fillRect(drawingPosition(x) + 55, 490 + y - altitude, 10, 20);
    g.fillRect(drawingPosition(x) + 95, 490 + y - altitude, 10, 20);
    if (smiling)
    {
      g.fillRect(drawingPosition(x) + 65, 500 + y - altitude, 30, 10);
    }
    else
    {
      g.fillRect(drawingPosition(x) + 65, 490 + y - altitude, 30, 10);
    }

    g.setColor(cat);
    g.fillRect(drawingPosition(x) + 10, 460 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 5, 475 + y - altitude, 5, 10);
    g.fillRect(drawingPosition(x) + 30, 475 + y - altitude, 5, 10);
    g.fillRect(drawingPosition(x) + 14, 457 + y - altitude, 3, 3);
    g.fillRect(drawingPosition(x) + 23, 457 + y - altitude, 3, 3);
    if (normalColor)
    {
      g.setColor(differentBlack);
    }
    else
    {
      g.setColor(differentRed);
    }
    g.drawRect(drawingPosition(x) + 10, 460 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 15, 468 + y - altitude, 2, 2);
    g.fillRect(drawingPosition(x) + 23, 468 + y - altitude, 2, 2);
    g.drawRect(drawingPosition(x) + 5, 475 + y - altitude, 5, 10);
    g.drawRect(drawingPosition(x) + 30, 475 + y - altitude, 5, 10);
    g.setColor(cat);
    g.fillRect(drawingPosition(x) + 130, 460 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 125, 475 + y - altitude, 5, 10);
    g.fillRect(drawingPosition(x) + 150, 475 + y - altitude, 5, 10);
    g.fillRect(drawingPosition(x) + 134, 457 + y - altitude, 3, 3);
    g.fillRect(drawingPosition(x) + 143, 457 + y - altitude, 3, 3);
    if (normalColor)
    {
      g.setColor(differentBlack);
    }
    else
    {
      g.setColor(differentRed);
    }
    g.drawRect(drawingPosition(x) + 130, 460 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 135, 468 + y - altitude, 2, 2);
    g.fillRect(drawingPosition(x) + 143, 468 + y - altitude, 2, 2);
    g.drawRect(drawingPosition(x) + 125, 475 + y - altitude, 5, 10);
    g.drawRect(drawingPosition(x) + 150, 475 + y - altitude, 5, 10);
    summonedCat1.paintComponent(g, x, y);
    summonedCat2.paintComponent(g, x, y);
    summonedBunny1.paintComponent(g, x, y);
    summonedBunny2.paintComponent(g, x, y);
    summonedFollower.paintComponent(g, x, y);
  }

  public void becomeDefeated()
  {
    if (attack == 7)
    {
      opacity = 170;
      moveCounter = 0;
      weakened = false;
      attack += 1;
      recovering = true;
    }
  }

  public void changePosition()
  {
    if ((!defeated) && (player.toTheRightOf(leftBoundary)) && (player.toTheLeftOf(rightBoundary)))
    {
      battle = true;
    }
    else
    {
      battle = false;
    }
    for (int number = 0; number < difficulty; number++)
    {
      summonedCat1changePosition();
      summonedCat2changePosition();
      summonedBunny1changePosition();
      summonedBunny2changePosition();
      summonedFollowerchangePosition();
    }
    if (battle)
    {
      if (attack == 1)
      {
        if (pauseCounter < 0)
        {
          pauseCounter += 1;
        }
        else
        {
          attack1();
        }
      }
      if (attack == 2)
      {
        if (pauseCounter < 50)
        {
          pauseCounter += 1;
        }
        else
        {
          attack2();
        }
      }
      if (attack == 3)
      {
        if (pauseCounter < 100)
        {
          pauseCounter += 1;
        }
        else
        {
          attack3();
        }
      }
      if (attack == 4)
      {
        if (pauseCounter < 150)
        {
          pauseCounter += 1;
        }
        else
        {
          attack4();
        }
      }
      if ((attack == 5) && ((health == 1) || (difficulty == 2)))
      {
        if (pauseCounter < 200)
        {
          pauseCounter += 1;
        }
        else
        {
          attack5();
        }
      }
      if ((attack == 5) && (health != 1) && (difficulty == 1))
      {
        attack += 1;
      }
      if (attack > 5)
      {
        smiling = false;
      }
      else
      {
        smiling = true;
      }
      if (attack == 6)
      {
        waitCounter += 1;
        if (waitCounter == (500 + 250 * (health + 1)) / difficulty)
        {
          moveCounter = 0;
          pauseCounter = -50;
          waitCounter = 0;
          attack = 1;
          smiling = true;
          normalColor = true;
        }
      }
      if ((attack == 6) && (weakened))
      {
        if (moveCounter < 50)
        {
          altitude -= 2;
          super.setCoordinates(position, altitude);
        }
        moveCounter += 1;
        if (moveCounter == 50)
        {
          weakened = false;
          moveCounter = 0;
          attack += 1;
        }
      }
      if ((attack == 8) && (recovering))
      {
        if (moveCounter == 0)
        {
          health -= 1;
        }
        if (health == 0)
        {
          if (moveCounter % 2 == 0)
          {
            if (moveCounter % 4 == 0)
            {
              position -= 5;
            }
            else
            {
              position += 5;
            }
            opacity -= 1;
            if (moveCounter < 200)
            {
              altitude += 1;
            }
          }
          super.setCoordinates(position, altitude);
          moveCounter += 1;
          if (opacity <= 0)
          {
            defeated = true;
            defeatAction.action();
          }
        }
        else
        {
          if (moveCounter < 100)
          {
            altitude += 1;
            super.setCoordinates(position, altitude);
          }
          moveCounter += 1;
          if (moveCounter == 100)
          {
            recovering = false;
            normalColor = true;
            moveCounter = 0;
            attack = 1;
            opacity = 255;
            pauseCounter = -50;
          }
        }
      }
    }
    else
    {
      attack = 1;
      moveCounter = 0;
      pauseCounter = 0;
      normalColor = true;
      smiling = true;
      health = 3;
      position = savePosition;
      altitude = saveAltitude;
      super.setCoordinates(position, altitude);
      summonedCat1.becomeDefeated();
      summonedCat2.becomeDefeated();
      summonedBunny2.becomeDefeated();
      summonedBunny2.becomeDefeated();
      summonedFollower.becomeDefeated();
    }
  }

  public void attack1()
  {
    for (int number = 0; number < difficulty; number++)
    {
      if (moveCounter == 0)
      {
        if (randomBoolean())
        {
          direction = "left";
        }
        else
        {
          direction = "right";
        }
      }
      int n = 0;
      if (direction == "right")
      {
        n = 1;
      }
      else
      {
        n = -1;
      }
      int m = 0;
      if (moveCounter < 100)
      {
        m = n * 2;
      }
      if ((moveCounter >= 100) && (moveCounter < 200))
      {
        m = n * 4;
      }
      if ((moveCounter >= 200) && (moveCounter < 300))
      {
        m = n * 6;
      }
      if (moveCounter >= 300)
      {
        m = n * 8;
      }
      position += m;
      super.setCoordinates(position, altitude);
      moveCounter += 1;
      if (moveCounter % 100 == 0)
      {
        changeDirection();
      }
      if (moveCounter != 450)
        continue;
      moveCounter = 0;
      direction = "none";
      attack += 1;
    }
  }

  public void attack2()
  {
    if (moveCounter == 0)
    {
      trick = false;
      if (difficulty == 2)
      {
        trick = randomBoolean();
      }
    }
    if (moveCounter < 70)
    {
      altitude += 3;
    }
    if (moveCounter == 70)
    {
      direction = "right";
    }
    if ((moveCounter >= 78) && (moveCounter < 94) && ((moveCounter + 2) % 4 == 0))
    {
      changeDirection();
    }
    if (moveCounter == 94)
    {
      direction = "none";
    }
    if ((moveCounter >= 94) && (moveCounter < 1000))
    {
      altitude -= 10;
    }
    if ((trick) && (moveCounter == 104))
    {
      moveCounter = 1000;
      altitude = 108;
      trick = false;
    }
    if (moveCounter == 1050)
    {
      moveCounter = 35;
    }
    int n = 0;
    if (direction == "left")
    {
      n = -2;
    }
    if (direction == "right")
    {
      n = 2;
    }
    if ((moveCounter >= 78) && (moveCounter < 94))
    {
      position += n;
    }
    super.setCoordinates(position, altitude);
    moveCounter += 1;
    if (moveCounter == 115)
    {
      if (player.getAltitude() == 0)
      {
        player.loseHealth();
      }
      moveCounter = 0;
      attack += 1;
    }
  }

  public void attack3()
  {
    if (moveCounter == 0)
    {
      normalColor = false;
    }
    if (moveCounter == 50)
    {
      normalColor = true;
      switch (health)
      {
      case 3:
        summonedCat1.becomeSummoned();
        summonedCat2.becomeSummoned();
        break;
      case 2:
        summonedBunny1.becomeSummoned();
        summonedBunny2.becomeSummoned();
        break;
      case 1:
        summonedFollower.becomeSummoned();
        break;
      }

    }

    moveCounter += 1;
    if (moveCounter == 200)
    {
      attack += 1;
      moveCounter = 0;
    }
  }

  public void attack4()
  {
    if (moveCounter == 0)
    {
      normalColor = false;
    }
    if (moveCounter == 50)
    {
      normalColor = true;
      if (toTheLeftOf(player))
      {
        distance = (positionCenter() - (player.getPosition() + 15));
      }
      else
      {
        distance = (positionCenter() - (player.getPosition() + player.getWidth() - 15));
      }
    }
    if ((moveCounter > 50) && (moveCounter <= 100))
    {
      position = (savePosition - distance * (moveCounter - 50) / 50);
    }
    if ((moveCounter > 100) && (moveCounter <= 150))
    {
      position = (savePosition - distance * (150 - moveCounter) / 50);
    }
    super.setCoordinates(position, altitude);
    moveCounter += 1;
    if (moveCounter > 150)
    {
      moveCounter = 0;
      attack += 1;
    }
  }

  public void attack5()
  {
    if (moveCounter < 70)
    {
      altitude += 3;
    }
    if ((moveCounter >= 70) && (moveCounter < 240))
    {
      setDirection(this);
      if (direction == "left")
      {
        position -= 3;
      }
      else
      {
        position += 3;
      }
    }
    if ((moveCounter >= 290) && (moveCounter < 306) && (moveCounter % 4 == 0))
    {
      changeDirection();
    }
    if (moveCounter == 306)
    {
      direction = "none";
    }
    if ((moveCounter >= 306) && (moveCounter < 327))
    {
      altitude -= 10;
    }
    int n = 0;
    if (direction == "left")
    {
      n = -2;
    }
    if (direction == "right")
    {
      n = 2;
    }
    if ((moveCounter >= 290) && (moveCounter < 306))
    {
      position += n;
    }
    super.setCoordinates(position, altitude);
    moveCounter += 1;
    if (moveCounter == 327)
    {
      if ((!player.getRecovering()) && (player.getAltitude() == 0))
      {
        player.loseHealth();
      }
    }
    if (moveCounter >= 327)
    {
      if (position == savePosition)
      {
        direction = "none";
        moveCounter = 0;
        attack += 1;
      }
      else
      {
        if (position < savePosition)
        {
          position += 3;
        }
        else
        {
          position -= 3;
        }
        super.setCoordinates(position, altitude);
      }
    }
  }

  public void becomeWeakened()
  {
    if (attack == 6)
    {
      weakened = true;
      normalColor = false;
    }
  }

  public boolean randomBoolean()
  {
    int rand;
    int rand;
    if (toTheLeftOf(player))
    {
      rand = 0;
    }
    else
    {
      rand = 1;
    }
    Random generator = new Random();
    int r = generator.nextInt();
    int randNum = player.getPosition() + player.getAltitude() + rand + r;

    return randNum % 2 == 0;
  }

  public void changeDirection()
  {
    if (direction == "left")
    {
      direction = "right";
    }
    else if (direction == "right")
    {
      direction = "left";
    }
  }

  public void setDirection(Enemy e)
  {
    if (e.toTheRightOf(player))
    {
      e.setDirection("left");
    }
    else
    {
      e.setDirection("right");
    }
  }

  public void setDirection(String dir)
  {
    direction = dir;
  }

  public void summonedCat1changePosition()
  {
    if (summonedCat1.getDefeated())
    {
      if (summonedCat1.getOpacity() == 0)
      {
        summonedCat1.setCoordinates(savePosition - 180, saveAltitude);
      }
      else
      {
        summonedCat1.update();
      }
    }
    else
    {
      summonedCat1.update();
      if (summonedCat1.getOpacity() != summonedCat1.getSaveOpacity())
      {
        setDirection(summonedCat1);
      }
    }
  }

  public void summonedCat2changePosition()
  {
    if (summonedCat2.getDefeated())
    {
      if (summonedCat2.getOpacity() == 0)
      {
        summonedCat2.setCoordinates(savePosition + 260, saveAltitude);
      }
      else
      {
        summonedCat2.update();
      }
    }
    else
    {
      summonedCat2.update();
      if (summonedCat2.getOpacity() != summonedCat2.getSaveOpacity())
      {
        setDirection(summonedCat2);
      }
    }
  }

  public void summonedBunny1changePosition()
  {
    if (summonedBunny1.getDefeated())
    {
      if (summonedBunny1.getOpacity() == 0)
      {
        summonedBunny1.setCoordinates(savePosition - 140, saveAltitude);
        summonedBunny1.resetCounter();
      }
      else
      {
        summonedBunny1.update();
      }
    }
    else
    {
      summonedBunny1.update();
      if (summonedBunny1.getOpacity() != summonedBunny1.getSaveOpacity())
      {
        setDirection(summonedBunny1);
      }
    }
  }

  public void summonedBunny2changePosition()
  {
    if (summonedBunny2.getDefeated())
    {
      if (summonedBunny2.getOpacity() == 0)
      {
        summonedBunny2.setCoordinates(savePosition + 260, saveAltitude);
        summonedBunny2.resetCounter();
      }
      else
      {
        summonedBunny2.update();
      }
    }
    else
    {
      summonedBunny2.update();
      if (summonedBunny2.getOpacity() != summonedBunny2.getSaveOpacity())
      {
        setDirection(summonedBunny2);
      }
    }
  }

  public void summonedFollowerchangePosition()
  {
    if (summonedFollower.getDefeated())
    {
      if (summonedFollower.getOpacity() == 0)
      {
        summonedFollower.setCoordinates(savePosition + 20, saveAltitude + 300);
        summonedFollower.resetSaves();
      }
      else
      {
        summonedFollower.update();
      }
    }
    else
    {
      summonedFollower.update();
    }
  }

  public boolean getWeakened()
  {
    return weakened;
  }

  public int getAttack()
  {
    return attack;
  }

  public boolean getRecovering()
  {
    return recovering;
  }

  public void becomeSummoned()
  {
    super.becomeSummoned();
    health = 3;
  }

  public void changePowerUp(String p)
  {
  }
}