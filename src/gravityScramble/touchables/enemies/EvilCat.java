package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class EvilCat extends MovingEnemy
  implements Serializable
{
  public EvilCat(int pos, int alt, Touchable l, Touchable r)
  {
    super(80, 100, pos, alt, false, 255, "none", "right", l, r, "evilCat");
  }

  public EvilCat(int pos, int alt, Touchable l, Touchable r, String power) {
    super(80, 100, pos, alt, false, 255, power, "right", l, r, "evilCat");
    if (power.equals("phantom"))
    {
      super.differentOpacity(210);
    }
  }

  public void drawing(Graphics g, int x, int y)
  {
    defeated = super.getDefeated();
    Color cat;
    Color cat;
    if (powerUp.equals("phantom"))
    {
      cat = new Color(60, 30, 50, getOpacity());
    }
    else
    {
      cat = new Color(225, 130, 60, getOpacity());
    }
    g.setColor(cat);
    g.fillRect(drawingPosition(x), 560 + y - altitude, 80, 60);
    g.fillRect(drawingPosition(x) + 5, 620 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 55, 620 + y - altitude, 20, 20);
    g.fillRect(drawingPosition(x) + 20, 540 + y - altitude, 40, 40);
    int[] xPoints = { drawingPosition(x) + 25, drawingPosition(x) + 30, drawingPosition(x) + 35 };
    int[] yPoints = { 540 + y - altitude, 530 + y - altitude, 540 + y - altitude };
    g.fillPolygon(xPoints, yPoints, 3);
    int[] xPoints2 = { drawingPosition(x) + 45, drawingPosition(x) + 50, drawingPosition(x) + 55 };
    g.fillPolygon(xPoints2, yPoints, 3);
    Color differentBlack = new Color(0, 0, 0, getOpacity());
    if (powerUp == "fire")
    {
      differentBlack = new Color(255, 0, 0, getOpacity());
    }
    g.setColor(differentBlack);
    g.drawRect(drawingPosition(x) + 20, 540 + y - altitude, 40, 40);
    if (powerUp.equals("phantom"))
    {
      g.drawLine(drawingPosition(x) + 30, 570 + y - altitude, drawingPosition(x) + 50, 570 + y - altitude);
      Color differentWhite = new Color(255, 255, 255, getOpacity());
      g.setColor(differentWhite);
    }
    g.fillRect(drawingPosition(x) + 30, 555 + y - altitude, 5, 5);
    g.fillRect(drawingPosition(x) + 45, 555 + y - altitude, 5, 5);
  }

  public void changePosition()
  {
    if (direction == "right")
    {
      position += 1;
      super.setCoordinates(position, altitude);
      if (isPositionSimilar(rightBoundary))
      {
        direction = "left";
        super.setDirection("left");
      }
    }
    else
    {
      position -= 1;
      super.setCoordinates(position, altitude);
      if (isPositionSimilar(leftBoundary))
      {
        direction = "right";
        super.setDirection("right");
      }
    }
  }

  public void changePowerUp(String p)
  {
    powerUp = p;
  }

  public void becomeDefeated()
  {
    defeated = true;
  }
}