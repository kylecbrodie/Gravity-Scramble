package gravityScramble.touchables.obstacles;

import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class FireCube extends Touchable
  implements Serializable
{
  private boolean timed;
  private int offTime;
  private int onTime;
  private int delay;
  private boolean activated;
  private int opacity;
  private int counter;
  private int delayCounter;
  public boolean difficulty;

  public FireCube(int pos, int alt, int off, int on, int del, boolean dif)
  {
    super(150, 150, pos, alt, "fireCube");
    timed = true;
    offTime = off;
    onTime = on;
    delay = del;
    activated = false;
    opacity = 85;
    counter = 0;
    delayCounter = 0;
    difficulty = dif;
  }

  public FireCube(int pos, int alt, boolean act) {
    super(150, 150, pos, alt, "fireCube");
    width = 150;
    height = 150;
    position = pos;
    altitude = alt;
    timed = false;
    offTime = 0;
    onTime = 0;
    delay = 0;
    activated = act;
    if (activated)
    {
      opacity = 255;
    }
    else
    {
      opacity = 85;
    }
    counter = 0;
    delayCounter = 0;
    difficulty = false;
    whichClass = "fireCube";
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    Color differentRed = new Color(250, 0, 0, opacity);
    g.setColor(differentRed);
    g.fillRect(drawingPosition(x), 490 + y - altitude, 150, 150);
  }

  public void changeTime()
  {
    if (timed)
    {
      if (delayCounter < delay)
      {
        delayCounter += 1;
      }
      else
      {
        counter += 1;
        if (counter == offTime * 4 / 5)
        {
          opacity = 170;
        }
        if (counter == offTime)
        {
          opacity = 255;
          activated = true;
        }
        if (counter == offTime + onTime)
        {
          opacity = 85;
          counter = 0;
          activated = false;
        }

      }

    }
    else if (activated)
    {
      opacity = 255;
    }
    else
    {
      opacity = 85;
    }
  }

  public boolean getActivated()
  {
    return activated;
  }

  public void turnOff()
  {
    activated = false;
  }
}