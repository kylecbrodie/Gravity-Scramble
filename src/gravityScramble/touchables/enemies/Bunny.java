package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Bunny extends MovingEnemy
  implements Serializable
{
  private int counter;

  public Bunny(int pos, int alt, Touchable l, Touchable r)
  {
    super(40, 95, pos, alt, false, 255, "none", "right", l, r, "bunny");
    counter = 0;
  }

  public void drawing(Graphics g, int x, int y)
  {
    Color differentWhite = new Color(255, 255, 255, getOpacity());
    g.setColor(differentWhite);
    g.fillRect(drawingPosition(x), 575 + y - altitude, 40, 55);
    g.fillRect(drawingPosition(x) + 5, 630 + y - altitude, 10, 10);
    g.fillRect(drawingPosition(x) + 25, 630 + y - altitude, 10, 10);
    g.fillRect(drawingPosition(x), 635 + y - altitude, 5, 5);
    g.fillRect(drawingPosition(x) + 35, 635 + y - altitude, 5, 5);
    g.fillRect(drawingPosition(x) + 5, 545 + y - altitude, 30, 30);
    g.fillRect(drawingPosition(x) + 10, 525 + y - altitude, 5, 20);
    g.fillRect(drawingPosition(x) + 25, 525 + y - altitude, 5, 20);
    Color differentBlack = new Color(0, 0, 0, getOpacity());
    g.setColor(differentBlack);
    g.fillRect(drawingPosition(x) + 12, 550 + y - altitude, 5, 5);
    g.fillRect(drawingPosition(x) + 23, 550 + y - altitude, 5, 5);
    Color differentPink = new Color(255, 150, 255, getOpacity());
    if (powerUp == "fire")
    {
      differentPink = new Color(255, 0, 0, getOpacity());
    }
    g.setColor(differentPink);
    g.fillRect(drawingPosition(x) + 18, 560 + y - altitude, 4, 4);
  }

  public void changePosition()
  {
    if (direction == "right")
    {
      position += 1;
      if (isPositionSimilar(rightBoundary))
      {
        direction = "left";
      }
    }
    else
    {
      position -= 1;
      if (isPositionSimilar(leftBoundary))
      {
        direction = "right";
      }
    }
    if (counter < 50)
    {
      altitude += 3;
    }
    if ((counter >= 50) && (counter < 100))
    {
      altitude -= 3;
    }
    counter += 1;
    if (counter == 150)
    {
      counter = 0;
    }
    super.setCoordinates(position, altitude);
  }

  public void resetCounter()
  {
    counter = 0;
    direction = "right";
  }

  public void changePowerUp(String p)
  {
    powerUp = p;
  }

  public void becomeDefeated()
  {
    defeated = true;
  }

  public void becomeSummoned()
  {
    resetCounter();
    super.becomeSummoned();
  }

  public int getCounter()
  {
    return counter;
  }
}