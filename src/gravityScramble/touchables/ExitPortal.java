package gravityScramble.touchables;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class ExitPortal extends Touchable
  implements Serializable
{
  private int brightness;
  private boolean brightening;

  public ExitPortal(int pos, int alt)
  {
    super(100, 100, pos, alt, "exitPortal");
    brightness = 20;
    brightening = true;
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    g.setColor(new Color(brightness, 0, brightness));
    g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
    if (brightening)
    {
      brightness += 1;
      if (brightness == 70)
      {
        brightening = false;
      }
    }
    else
    {
      brightness -= 1;
      if (brightness == 20)
      {
        brightening = true;
      }
    }
  }

  public int getBrightness()
  {
    return brightness;
  }

  public void grow()
  {
    position -= 1;
    altitude -= 1;
    width += 2;
    height += 2;
  }
}