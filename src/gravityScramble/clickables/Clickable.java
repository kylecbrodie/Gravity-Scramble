package gravityScramble.clickables;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Clickable
  implements Serializable
{
  protected int position;
  protected int altitude;

  public Clickable(int pos, int alt)
  {
    position = pos;
    altitude = alt;
  }
  public abstract void paintComponent(Graphics paramGraphics, int paramInt1, int paramInt2);

  public abstract boolean isClickedBy(int paramInt1, int paramInt2);

  public int drawingPosition(int x) { return 610 + (position - x);
  }

  public int drawingAltitude(int y)
  {
    return 640 + y - altitude;
  }

  public int getPosition()
  {
    return position;
  }

  public int getAltitude()
  {
    return altitude;
  }
}