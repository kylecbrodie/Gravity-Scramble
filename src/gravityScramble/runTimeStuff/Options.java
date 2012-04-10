package gravityScramble.runTimeStuff;

import java.io.Serializable;

public class Options
  implements Serializable
{
  private boolean drawOutline;
  private boolean reviveEnemies;
  private boolean sound;
  private int speed;
  private int maxSpeed;
  private final int defaultWidth;
  private final int defaultHeight;
  private int preferredWidth;
  private int preferredHeight;

  public Options()
  {
    drawOutline = false;
    reviveEnemies = false;
    sound = false;
    speed = 0;
    maxSpeed = 1;
    defaultWidth = 1258;
    defaultHeight = 774;
    preferredWidth = defaultWidth;
    preferredHeight = defaultHeight;
  }

  public boolean getDrawOutline()
  {
    return drawOutline;
  }

  public void setDrawOutline(boolean b) {
    drawOutline = b;
  }

  public boolean getReviveEnemies() {
    return reviveEnemies;
  }

  public void setReviveEnemies(boolean b) {
    reviveEnemies = b;
  }

  public boolean getSound() {
    return sound;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int x) {
    speed = x;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(int x) {
    maxSpeed = x;
  }

  public int getDefaultWidth() {
    return defaultWidth;
  }

  public int getDefaultHeight() {
    return defaultHeight;
  }

  public void setPreferredSize(int x, int y) {
    preferredWidth = x;
    preferredHeight = y;
  }

  public int getPreferredWidth() {
    return preferredWidth;
  }

  public int getPreferredHeight() {
    return preferredHeight;
  }
}