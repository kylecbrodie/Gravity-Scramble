package gravityScramble.touchables;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Touchable
  implements Serializable
{
  protected int width;
  protected int height;
  protected int position;
  protected int altitude;
  protected String whichClass;

  public Touchable(int w, int h, int pos, int alt, String cl)
  {
    position = pos;
    altitude = alt;
    width = w;
    height = h;
    whichClass = cl;
  }

  public abstract void paintComponent(Graphics paramGraphics, int paramInt1, int paramInt2);

  public boolean isTouching(Touchable t)
  {
    return (isPositionSimilar(t)) && (isAltitudeSimilar(t));
  }

  public boolean isPositionSimilar(Touchable t)
  {
    return ((position >= t.position) && (position <= t.position + t.width)) || ((position + width >= t.position) && (position + width <= t.position + t.width)) || ((t.position >= position) && (t.position <= position + width)) || ((t.position + t.width >= position) && (t.position + t.width <= position + width));
  }

  public boolean isAltitudeSimilar(Touchable t)
  {
    return ((altitude >= t.altitude) && (altitude <= t.altitude + t.height)) || ((altitude + height >= t.altitude) && (altitude + height <= t.altitude + t.height)) || ((t.altitude >= altitude) && (t.altitude <= altitude + height)) || ((t.altitude + t.height >= altitude) && (t.altitude + t.height <= altitude + height));
  }

  public int positionCenter()
  {
    return position + width / 2;
  }

  public int altitudeCenter()
  {
    return altitude + height / 2;
  }

  public void setCoordinates(int p, int a)
  {
    position = p;
    altitude = a;
  }

  public int getPosition()
  {
    return position;
  }

  public int getAltitude()
  {
    return altitude;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public int drawingPosition(int x)
  {
    return 610 + (position - x);
  }

  public int drawingAltitude(int y)
  {
    return 640 + y - altitude;
  }

  public int positionDifference(Touchable t)
  {
    return Math.abs(positionCenter() - t.positionCenter());
  }

  public int altitudeDifference(Touchable t)
  {
    return Math.abs(altitudeCenter() - t.altitudeCenter());
  }

  public double distanceTo(Touchable t)
  {
    return Math.sqrt(Math.pow(positionDifference(t), 2.0D) + Math.pow(altitudeDifference(t), 2.0D));
  }

  public boolean toTheLeftOf(Touchable t)
  {
    return positionCenter() < t.positionCenter();
  }

  public boolean toTheRightOf(Touchable t)
  {
    return positionCenter() > t.positionCenter();
  }

  public boolean above(Touchable t)
  {
    return altitudeCenter() > t.altitudeCenter();
  }

  public boolean below(Touchable t)
  {
    return altitudeCenter() < t.altitudeCenter();
  }

  public void setClass(String c)
  {
    whichClass = c;
  }

  public void setDimensions(int x, int y)
  {
    width = x;
    height = y;
  }

  public String whatClass()
  {
    return whichClass;
  }
}